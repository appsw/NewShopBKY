package bai.kang.yun.zxd.mvp.presenter;

import android.app.Application;

import com.jess.arms.base.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import bai.kang.yun.zxd.mvp.contract.MainContract;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * Created by jess on 9/4/16 10:59
 * Contact with jess.yan.effort@gmail.com
 */
@ActivityScope
public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> {
    private RxErrorHandler mErrorHandler;
        private AppManager mAppManager;
        private Application mApplication;

        private int lastUserId = 1;
        private boolean isFirst = true;
        private int preEndIndex;


    @Inject
    public MainPresenter(MainContract.Model model, MainContract.View rootView, RxErrorHandler handler
                , AppManager appManager, Application application) {
            super(model, rootView);
            this.mApplication = application;
            this.mErrorHandler = handler;
            this.mAppManager = appManager;


        }

    public void SetView() {
        mRootView.initTab();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mApplication = null;
    }
}
