package common;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.mvp.Presenter;
import com.umeng.analytics.MobclickAgent;

import bai.kang.yun.zxd.app.utils.ActivityManger;
import bai.kang.yun.zxd.mvp.ui.dialog.LoadingDialog;

/**
 * Created by jess on 8/5/16 13:13
 * contact with jess.yan.effort@gmail.com
 */
public abstract class WEActivity<P extends Presenter> extends BaseActivity<P> {
    protected WEApplication mWeApplication;
    public LoadingDialog loadingDialog;

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityManger.addAvtivity(this);
        if(loadingDialog==null)
            loadingDialog=new LoadingDialog(this,"玩命加载中...");

        super.onCreate(savedInstanceState);


    }

    @Override
    protected void ComponentInject() {
        mWeApplication = (WEApplication) getApplication();
        setupActivityComponent(mWeApplication.getAppComponent());
        MobclickAgent.setScenarioType(getBaseContext(), MobclickAgent.EScenarioType.E_UM_NORMAL);
    }

    //提供AppComponent(提供所有的单例对象)给子类，进行Component依赖
    protected abstract void setupActivityComponent(AppComponent appComponent);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManger.removeAvtivity(this);
        this.mWeApplication = null;
        MobclickAgent.onPause(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);

    }
}
