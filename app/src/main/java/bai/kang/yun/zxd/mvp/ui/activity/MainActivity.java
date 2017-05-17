package bai.kang.yun.zxd.mvp.ui.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.utils.UiUtils;
import com.paginate.Paginate;
import com.tbruyelle.rxpermissions.RxPermissions;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.di.component.DaggerMainComponent;
import bai.kang.yun.zxd.di.module.MainModule;
import bai.kang.yun.zxd.mvp.contract.MainContract;
import bai.kang.yun.zxd.mvp.presenter.MainPresenter;
import bai.kang.yun.zxd.mvp.ui.fragment.CarFragment;
import bai.kang.yun.zxd.mvp.ui.fragment.FindFragment;
import bai.kang.yun.zxd.mvp.ui.fragment.FristFragment;
import bai.kang.yun.zxd.mvp.ui.fragment.MeFragment;
import butterknife.BindView;
import common.AppComponent;
import common.WEActivity;
import common.WEFragment;
import timber.log.Timber;

import static bai.kang.yun.zxd.R.id.iv_frist;


public class MainActivity extends WEActivity<MainPresenter> implements MainContract.View,
        OnClickListener {
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
    @BindView(iv_frist)
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
    TextView tv_frist;
    @Nullable
    @BindView(R.id.tv_find)
    TextView tv_find;
    @Nullable
    @BindView(R.id.tv_car)
    TextView tv_car;
    @Nullable
    @BindView(R.id.tv_me)
    TextView tv_me;


    // 底部标签切换的Fragment
    private WEFragment fristFragment, findFragment,carFragment, meFragment,
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
        rl_frist.setOnClickListener(this);
        rl_find.setOnClickListener(this);
        rl_car.setOnClickListener(this);
        rl_me.setOnClickListener(this);
        mPresenter.SetView();
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
    protected void onDestroy() {

        super.onDestroy();
        this.mRxPermissions = null;
        this.mPaginate = null;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_frist: // 首页
                clickTab1Layout();

                break;
            case R.id.rl_find: // 找药

                clickTab2Layout();

                break;
            case R.id.rl_car: // 购物车

                clickTab3Layout();

                break;
            case R.id.rl_me: // 我的

                clickTab4Layout();

                break;
            default:
                break;
        }
    }
    /**
     * 初始化底部标签
     */
    public void initTab() {
        if (fristFragment == null) {
            fristFragment=FristFragment.getInstance();
        }

        if (!fristFragment.isAdded()) {
            // 提交事务
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_layout, fristFragment).commit();

            // 记录当前Fragment
            currentFragment = fristFragment;
             //设置图片文本的变化
            im_frist.setImageResource(R.mipmap.frists);
            tv_frist.setTextColor(getResources()
                    .getColor(R.color.btn_after));
            im_find.setImageResource(R.mipmap.find);
            tv_find.setTextColor(getResources().getColor(
                    R.color.btn_befor));
            im_car.setImageResource(R.mipmap.car);
            tv_car.setTextColor(getResources().getColor(R.color.btn_befor));
            im_me.setImageResource(R.mipmap.me);
            tv_me.setTextColor(getResources().getColor(R.color.btn_befor));
        }

    }
    /**
     * 点击第一个tab
     */
    private void clickTab1Layout() {
        if (fristFragment == null) {
            fristFragment =  FristFragment.getInstance();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), fristFragment);

        //设置图片文本的变化
        im_frist.setImageResource(R.mipmap.frists);
        tv_frist.setTextColor(getResources()
                .getColor(R.color.btn_after));
        im_find.setImageResource(R.mipmap.find);
        tv_find.setTextColor(getResources().getColor(
                R.color.btn_befor));
        im_car.setImageResource(R.mipmap.car);
        tv_car.setTextColor(getResources().getColor(R.color.btn_befor));
        im_me.setImageResource(R.mipmap.me);
        tv_me.setTextColor(getResources().getColor(R.color.btn_befor));
    }

    /**
     * 点击第二个tab
     */
    private void clickTab2Layout() {
        if (findFragment == null) {
            findFragment = FindFragment.newInstance();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), findFragment);

        //设置图片文本的变化
        im_frist.setImageResource(R.mipmap.frist);
        tv_frist.setTextColor(getResources()
                .getColor(R.color.btn_befor));
        im_find.setImageResource(R.mipmap.finds);
        tv_find.setTextColor(getResources().getColor(
                R.color.btn_after));
        im_car.setImageResource(R.mipmap.car);
        tv_car.setTextColor(getResources().getColor(R.color.btn_befor));
        im_me.setImageResource(R.mipmap.me);
        tv_me.setTextColor(getResources().getColor(R.color.btn_befor));

    }

    /**
     * 点击第三个tab
     */
    private void clickTab3Layout() {
        if (carFragment == null) {
            carFragment = CarFragment.newInstance();
        }

        addOrShowFragment(getSupportFragmentManager().beginTransaction(), carFragment);
        //设置图片文本的变化
        im_frist.setImageResource(R.mipmap.frist);
        tv_frist.setTextColor(getResources()
                .getColor(R.color.btn_befor));
        im_find.setImageResource(R.mipmap.find);
        tv_find.setTextColor(getResources().getColor(
                R.color.btn_befor));
        im_car.setImageResource(R.mipmap.cars);
        tv_car.setTextColor(getResources().getColor(R.color.btn_after));
        im_me.setImageResource(R.mipmap.me);
        tv_me.setTextColor(getResources().getColor(R.color.btn_befor));

    }
    /**
     * 点击第四个tab
     */
    private void clickTab4Layout() {
        if (meFragment == null) {
            meFragment = MeFragment.newInstance();
        }

        addOrShowFragment(getSupportFragmentManager().beginTransaction(), meFragment);
        //设置图片文本的变化
        im_frist.setImageResource(R.mipmap.frist);
        tv_frist.setTextColor(getResources()
                .getColor(R.color.btn_befor));
        im_find.setImageResource(R.mipmap.find);
        tv_find.setTextColor(getResources().getColor(
                R.color.btn_befor));
        im_car.setImageResource(R.mipmap.car);
        tv_car.setTextColor(getResources().getColor(R.color.btn_befor));
        im_me.setImageResource(R.mipmap.mes);
        tv_me.setTextColor(getResources().getColor(R.color.btn_after));

    }

    /**
     * 添加或者显示碎片
     *
     * @param transaction
     * @param fragment
     */
    private void addOrShowFragment(FragmentTransaction transaction,
                                   WEFragment fragment) {
        if (currentFragment == fragment)
            return;

        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.hide(currentFragment)
                    .add(R.id.content_layout, fragment).commit();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }

        currentFragment = fragment;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(currentFragment instanceof FristFragment){
            FristFragment.OnKeyDown(keyCode, event);
        }else if(currentFragment instanceof FindFragment){
            FindFragment.OnKeyDown(keyCode, event);
        }else if(currentFragment instanceof CarFragment){
            CarFragment.OnKeyDown(keyCode, event);
        }else{
            MeFragment.OnKeyDown(keyCode, event);
        }

        //    Toast.makeText(MainActivity.this,"不能返回",Toast.LENGTH_SHORT).show();
        return false;
    }
    public  void startactivity(Intent intent){
        startActivityForResult(intent,3);
    }
}
