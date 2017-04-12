package bai.kang.yun.zxd.mvp.ui.fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.mvp.presenter.MainPresenter;
import common.AppComponent;
import common.WEFragment;

/**
 * Created by Administrator on 2017/4/12 0012.
 */

public class CarFragment extends WEFragment<MainPresenter> {

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_car, container,
                false);
        return view;
    }

    @Override
    protected void initData() {

    }
    public static WEFragment getInstance(){
        CarFragment instance = new CarFragment();
        Bundle args = new Bundle();
        instance.setArguments(args);
        return instance;
    }

    public CarFragment(){

    }
    public static void OnKeyDown(int keyCode, KeyEvent event) {

    }
}
