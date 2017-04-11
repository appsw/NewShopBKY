package bai.kang.yun.zxd.mvp.ui.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.utils.UiUtils;
import com.paginate.Paginate;
import com.tbruyelle.rxpermissions.RxPermissions;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.di.component.DaggerMainComponent;
import bai.kang.yun.zxd.di.module.MainModule;
import bai.kang.yun.zxd.mvp.contract.MainContract;
import bai.kang.yun.zxd.mvp.presenter.MainPresenter;
import butterknife.BindView;
import common.AppComponent;
import common.WEActivity;
import timber.log.Timber;


public class MainActivity extends WEActivity<MainPresenter> implements MainContract.View,
        SwipeRefreshLayout.OnRefreshListener,View.OnClickListener {

    @Nullable
    @BindView(R.id.rl_frist)
    RelativeLayout rl_frist;
    @Nullable
    @BindView(R.id.rl_find)
    RelativeLayout rl_find;
    @Nullable
    @BindView(R.id.rl_car)
    RelativeLayout rl_car;
    @Nullable
    @BindView(R.id.rl_me)
    RelativeLayout rl_me;
    @Nullable
    @BindView(R.id.iv_frist)
    ImageView im_frist;
    @Nullable
    @BindView(R.id.iv_find)
    ImageView im_find;
    @Nullable
    @BindView(R.id.iv_car)
    ImageView im_car;
    @Nullable
    @BindView(R.id.iv_me)
    ImageView im_me;
    @Nullable
    @BindView(R.id.tv_frist)
    ImageView tv_frist;
    @Nullable
    @BindView(R.id.tv_find)
    ImageView tv_find;
    @Nullable
    @BindView(R.id.tv_car)
    ImageView tv_car;
    @Nullable
    @BindView(R.id.tv_me)
    ImageView tv_me;


    // 底部标签切换的Fragment
    private BaseFragment knowFragment, iWantKnowFragment, meFragment,
            currentFragment;
    private Paginate mPaginate;
    private boolean isLoadingMore;
    private RxPermissions mRxPermissions;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        this.mRxPermissions = new RxPermissions(this);
        DaggerMainComponent
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_main, null, false);
    }

    @Override
    protected void initData() {
        mPresenter.requestUsers(true);//打开app时自动加载列表
    }

    @Override
    public void onRefresh() {
        mPresenter.requestUsers(true);
    }




    @Override
    public void showLoading() {
        Timber.tag(TAG).w("showLoading");

    }

    @Override
    public void hideLoading() {
        Timber.tag(TAG).w("hideLoading");

    }

    @Override
    public void showMessage(String message) {
        UiUtils.SnackbarText(message);
    }

    @Override
    public void launchActivity(Intent intent) {
        UiUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void setAdapter(DefaultAdapter adapter) {

    }

    /**
     * 开始加载更多
     */
    @Override
    public void startLoadMore() {
        isLoadingMore = true;
    }

    /**
     * 结束加载更多
     */
    @Override
    public void endLoadMore() {
        isLoadingMore = false;
    }

    @Override
    public RxPermissions getRxPermissions() {
        return mRxPermissions;
    }



    @Override
    protected void onDestroy() {

        super.onDestroy();
        this.mRxPermissions = null;
        this.mPaginate = null;
    }



    @Override
    public void onClick(View v) {

    }
}
