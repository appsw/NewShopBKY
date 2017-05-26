package bai.kang.yun.zxd.mvp.presenter;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.jess.arms.base.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxUtils;
import com.jess.arms.utils.UiUtils;
import com.jess.arms.widget.imageloader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import bai.kang.yun.zxd.mvp.contract.AddressListContract;
import bai.kang.yun.zxd.mvp.model.entity.ReturnAddress;
import bai.kang.yun.zxd.mvp.model.entity.ReturnDeleteAdd;
import bai.kang.yun.zxd.mvp.ui.activity.LoginActivity;
import bai.kang.yun.zxd.mvp.ui.adapter.AddressListAdapter;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */


/**
 * Created by Administrator on 2017/5/19 0019.
 */

@ActivityScope
public class AddressListPresenter extends BasePresenter<AddressListContract.Model, AddressListContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;
    private AddressListAdapter addressListAdapter;
    private List<ReturnAddress.ItemsEntity> addresses;
    SharedPreferences config;

    @Inject
    public AddressListPresenter(AddressListContract.Model model, AddressListContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
        config=application.getSharedPreferences("config", Context.MODE_PRIVATE);

    }
    public void initData(){
        addresses=new ArrayList();
        addressListAdapter=new AddressListAdapter(mApplication,addresses);
        mRootView.setAdapter(addressListAdapter);
    }
    public void Request(boolean Clear){
        if(!config.getBoolean("isLog",false)){
            UiUtils.makeText("请先登录");
            Intent intent=new Intent(mApplication, LoginActivity.class);
            mRootView.launchActivity(intent);
            return;
        }
        addresses.clear();
        mModel.getAddress(config.getInt("id",0),config.getString("salt","0"),1,Clear)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.<ReturnAddress>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(
                        new ErrorHandleSubscriber<ReturnAddress>(mErrorHandler) {
                            @Override
                            public void onNext(ReturnAddress category) {
                                if(category.getStatus()==1){
                                    addresses.addAll(category.getPage_data().getItems());
                                    addressListAdapter.notifyDataSetChanged();
                                }else {
                                    UiUtils.makeText(category.getMessage());
                                }
                            }
                        });
    }
    public void delete(int id){
        mModel.DeleteAdd(config.getInt("id",0),config.getString("salt","0"),id)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.<ReturnDeleteAdd>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(
                        new ErrorHandleSubscriber<ReturnDeleteAdd>(mErrorHandler) {
                            @Override
                            public void onNext(ReturnDeleteAdd category) {
                                if(category.getStatus()==1){
                                    UiUtils.makeText(category.getMessage());
                                    Request(true);
                                }else {
                                    UiUtils.makeText(category.getMessage());
                                }
                            }
                        });
    }
    public void SetDefault(int id){
        mModel.SetDefault(config.getInt("id",0),config.getString("salt","0"),id)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.<ReturnDeleteAdd>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(
                        new ErrorHandleSubscriber<ReturnDeleteAdd>(mErrorHandler) {
                            @Override
                            public void onNext(ReturnDeleteAdd category) {
                                if(category.getStatus()==1){
                                    UiUtils.makeText(category.getMessage());
                                    Request(true);
                                }else {
                                    UiUtils.makeText(category.getMessage());
                                }
                            }
                        });
    }
    public void add(){

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

}
