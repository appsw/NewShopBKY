package bai.kang.yun.zxd.mvp.presenter;

import android.app.Application;
import android.content.Context;
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

import bai.kang.yun.zxd.mvp.contract.AddressDetailContract;
import bai.kang.yun.zxd.mvp.model.entity.ReturnRegion;
import bai.kang.yun.zxd.mvp.model.entity.ReturnSetAdd;
import bai.kang.yun.zxd.mvp.model.entity.SetAddress;
import bai.kang.yun.zxd.mvp.ui.adapter.SPAdapter;
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
 * Created by Administrator on 2017/5/25 0025.
 */

@ActivityScope
public class AddressDetailPresenter extends BasePresenter<AddressDetailContract.Model, AddressDetailContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;
    SharedPreferences config;
    List<ReturnRegion.DataEntity> list1=new ArrayList<>();
    List<ReturnRegion.DataEntity> list2=new ArrayList<>();
    List<ReturnRegion.DataEntity> list3=new ArrayList<>();
    SPAdapter spAdapter1;
    SPAdapter spAdapter2;
    SPAdapter spAdapter3;

    @Inject
    public AddressDetailPresenter(AddressDetailContract.Model model, AddressDetailContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
        config=application.getSharedPreferences("config", Context.MODE_PRIVATE);
        spAdapter1=new SPAdapter(application,list1);
        spAdapter2=new SPAdapter(application,list2);
        spAdapter3=new SPAdapter(application,list3);
        mRootView.setAdapter(spAdapter1,spAdapter2,spAdapter3);
    }
    public void initSP(int id,int ji){
        mModel.getRegion(id)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.<ReturnRegion>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(
                        new ErrorHandleSubscriber<ReturnRegion>(mErrorHandler) {
                            @Override
                            public void onNext(ReturnRegion category) {
                                if(category.getStatus()==1){
                                    switch (ji){
                                        case 1:
                                            list1.clear();
                                            list1.addAll(category.getData());
                                            spAdapter1.notifyDataSetChanged();
                                            break;
                                        case 2:
                                            list2.clear();
                                            list2.addAll(category.getData());
                                            spAdapter2.notifyDataSetChanged();
                                            break;
                                        case 3:
                                            list3.clear();
                                            list3.addAll(category.getData());
                                            spAdapter3.notifyDataSetChanged();
                                            break;
                                        default:
                                            break;
                                    }
                                }else {
                                    UiUtils.makeText(category.getMessage());
                                }
                            }
                        });
    }

    public void save(SetAddress setAddress){

        mModel.setAdd(config.getInt("id",0),config.getString("salt","0"),setAddress.getMapParams())
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.<ReturnSetAdd>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(
                        new ErrorHandleSubscriber<ReturnSetAdd>(mErrorHandler) {
                            @Override
                            public void onNext(ReturnSetAdd category) {
                                if(category.getStatus()==1){
                                    UiUtils.makeText("保存成功");
                                    mRootView.killMyself();
                                }else {
                                    UiUtils.makeText(category.getMessage());
                                }
                            }
                        });
        if(setAddress.getIsdefault()==1){
            config.edit().putBoolean("isAdd",true).putString("add_name",setAddress.getReal_name())
                    .putString("add_tel",setAddress.getPhone()).putString("add_deils",setAddress.getAddress())
                    .putInt("add_id",setAddress.getId()).commit();
        }
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
