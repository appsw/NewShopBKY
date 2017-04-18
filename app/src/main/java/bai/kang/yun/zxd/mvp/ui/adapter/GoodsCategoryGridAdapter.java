package bai.kang.yun.zxd.mvp.ui.adapter;

import android.view.View;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.mvp.model.entity.SPCategory;
import bai.kang.yun.zxd.mvp.ui.holder.GoodsCategoryGridItemHolder;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class GoodsCategoryGridAdapter extends DefaultAdapter<SPCategory>  {
    public GoodsCategoryGridAdapter(List<SPCategory> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<SPCategory> getHolder(View v, int viewType) {
        return new GoodsCategoryGridItemHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_goodsgrid;
    }

}
