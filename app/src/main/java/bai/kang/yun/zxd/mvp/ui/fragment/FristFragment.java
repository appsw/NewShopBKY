package bai.kang.yun.zxd.mvp.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.utils.UiUtils;
import com.jude.rollviewpager.RollPagerView;

import java.util.ArrayList;

import javax.annotation.Nullable;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.di.component.DaggerFristComponent;
import bai.kang.yun.zxd.di.module.FristModule;
import bai.kang.yun.zxd.mvp.contract.FristContract;
import bai.kang.yun.zxd.mvp.presenter.MainPresenter;
import bai.kang.yun.zxd.mvp.ui.adapter.RollViewpagerAdapter;
import butterknife.BindView;
import common.AppComponent;
import common.WEFragment;

/**
 * Created by Administrator on 2017/4/12 0012.
 */

public class FristFragment extends WEFragment<MainPresenter>implements FristContract.View {



    @Nullable
    @BindView(R.id.rollViewpager)
    RollPagerView rollPagerView;
    RollViewpagerAdapter rollViewpagerAdapter;
    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerFristComponent
                .builder()
                .appComponent(appComponent)
                .fristModule(new FristModule((FristContract.View) UiUtils.getContext()))
                .build()
                .inject((FristContract.View) UiUtils.getContext());
        rollViewpagerAdapter=new RollViewpagerAdapter(new ArrayList<>());
        rollPagerView.setPlayDelay(3000);//*播放间隔
        rollPagerView.setAnimationDurtion(500);//透明度
        rollPagerView.setAdapter(rollViewpagerAdapter);//配置适配器
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_frist, container,
                false);
        return view;
    }

    @Override
    protected void initData() {


    }
    public static WEFragment getInstance(){
        FristFragment instance = new FristFragment();
        Bundle args = new Bundle();
        instance.setArguments(args);
        return instance;
    }

    public FristFragment(){

    }

    public static boolean OnKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(UiUtils.getContext());
            builder.setMessage("确认退出吗？");
            builder.setTitle("提示");
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    System.exit(0);//退出程序
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();

        }
        return true;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMyself() {

    }
}
