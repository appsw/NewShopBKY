package bai.kang.yun.zxd.mvp.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.base.BaseFragment;

import bai.kang.yun.zxd.R;

/**
 * Created by Administrator on 2017/6/15 0015.
 */

public class ChuFangTextFragment extends BaseFragment {
    public static ChuFangTextFragment newInstance() {
        ChuFangTextFragment fragment = new ChuFangTextFragment();
        return fragment;
    }
    @Override
    protected void ComponentInject() {

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_chufangtext, container, false);
    }

    @Override
    protected void initData() {

    }
}
