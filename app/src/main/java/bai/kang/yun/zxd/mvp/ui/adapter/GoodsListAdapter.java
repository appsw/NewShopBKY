package bai.kang.yun.zxd.mvp.ui.adapter;

import android.view.View;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.mvp.model.entity.Goods;
import bai.kang.yun.zxd.mvp.ui.holder.GoodsListItemHolder;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class GoodsListAdapter extends DefaultAdapter<Goods> {
    public GoodsListAdapter(List<Goods> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<Goods> getHolder(View v, int viewType) {
        return new GoodsListItemHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_goodslist;
    }
}
