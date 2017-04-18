package bai.kang.yun.zxd.mvp.ui.adapter;

import android.view.View;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.mvp.model.entity.Goods;
import bai.kang.yun.zxd.mvp.ui.holder.GoodsGridItemHolder;

/**
 * Created by Administrator on 2017/4/17 0017.
 */

public class GoodsGridAdapter extends DefaultAdapter<Goods> {
    public GoodsGridAdapter(List<Goods> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<Goods> getHolder(View v, int viewType) {
        return new GoodsGridItemHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_goodsgrid;
    }
}
