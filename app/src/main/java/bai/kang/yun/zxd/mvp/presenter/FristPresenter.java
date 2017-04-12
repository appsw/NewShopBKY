package bai.kang.yun.zxd.mvp.presenter;

import android.app.Application;

import com.jess.arms.base.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxUtils;

import javax.inject.Inject;

import bai.kang.yun.zxd.mvp.contract.FristContract;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/4/12 0012.
 */
@ActivityScope
public class FristPresenter  extends BasePresenter<FristContract.Model, FristContract.View> {
    private RxErrorHandler mErrorHandler;
    private AppManager mAppManager;
    private Application mApplication;

    private int lastUserId = 1;
    private boolean isFirst = true;
    private int preEndIndex;


    @Inject
    public FristPresenter(FristContract.Model model, FristContract.View rootView, RxErrorHandler handler
            , AppManager appManager, Application application) {
        super(model, rootView);
        this.mApplication = application;
        this.mErrorHandler = handler;
        this.mAppManager = appManager;


    }
    public void requestUsers() {


        mModel.getBannerUrl()
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .compose(RxUtils.bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mApplication = null;
    }
}
