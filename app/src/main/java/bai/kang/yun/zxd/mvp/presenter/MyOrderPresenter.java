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

import bai.kang.yun.zxd.mvp.contract.MyOrderContract;
import bai.kang.yun.zxd.mvp.model.entity.ReturnOrderList;
import bai.kang.yun.zxd.mvp.model.entity.ReturnPayUrl;
import bai.kang.yun.zxd.mvp.ui.adapter.MyOrderListAdapter;
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
 * Created by Administrator on 2017/5/2 0002.
 */

@ActivityScope
public class MyOrderPresenter extends BasePresenter<MyOrderContract.Model, MyOrderContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;
    private SharedPreferences config;
    private MyOrderListAdapter myOrderListAdapter;
    private List<ReturnOrderList.ItemEntiy> orders=new ArrayList<>();

    @Inject
    public MyOrderPresenter(MyOrderContract.Model model, MyOrderContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
        config=application.getSharedPreferences("config", Context.MODE_PRIVATE);
        myOrderListAdapter=new MyOrderListAdapter(application,orders);
        mRootView.setAdapter(myOrderListAdapter);
    }
    public void getUrl(String type,int orderId){

        mModel.getPayUrl(config.getInt("id",0),config.getString("salt","0"),type,orderId)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.<ReturnPayUrl>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(new ErrorHandleSubscriber<ReturnPayUrl>(mErrorHandler) {
                    @Override
                    public void onNext(ReturnPayUrl shops) {
                        if(shops.getStatus()==1){
                            if(shops.getSingle().getRetcode().equals("SUCCESS")){
                                if(shops.getSingle().getTrxstatus().equals("0000")){
                                    mRootView.Alipay(shops.getSingle().getPayinfo());
                                }else {
                                    UiUtils.makeText(shops.getSingle().getTrxstatus());
                                }
                            }else {
                                UiUtils.makeText(shops.getSingle().getRetcode());
                            }
                        }else
                            UiUtils.makeText(shops.getMessage());
                    }
                });

    }
    public void getOrderList(int status){
        orders.clear();
        mModel.getOrderList(config.getInt("id",0),config.getString("salt","0"),status,1)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.<ReturnOrderList>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(new ErrorHandleSubscriber<ReturnOrderList>(mErrorHandler) {
                    @Override
                    public void onNext(ReturnOrderList shops) {
                        if(shops.getStatus()==1){
                            orders.addAll(shops.getPage_data().getItems());
                            myOrderListAdapter.notifyDataSetChanged();
                        }else
                            UiUtils.makeText(shops.getMessage());
                    }
                });
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
