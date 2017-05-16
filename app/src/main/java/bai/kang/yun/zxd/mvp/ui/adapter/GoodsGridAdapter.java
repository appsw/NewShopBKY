package bai.kang.yun.zxd.mvp.ui.adapter;

import android.view.View;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.mvp.model.entity.ReturnGoods;
import bai.kang.yun.zxd.mvp.ui.holder.GoodsGridItemHolder;

/**
 * Created by Administrator on 2017/4/17 0017.
 */

public class GoodsGridAdapter extends DefaultAdapter<ReturnGoods.DataEntity> {
    public GoodsGridAdapter(List<ReturnGoods.DataEntity> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<ReturnGoods.DataEntity> getHolder(View v, int viewType) {
        return new GoodsGridItemHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_goodsgrid;
    }
}
