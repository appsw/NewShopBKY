package bai.kang.yun.zxd.mvp.ui.adapter;

import android.view.View;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.mvp.model.entity.ReturnShopGoods;
import bai.kang.yun.zxd.mvp.ui.holder.GoodsListForShopItemHolder;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class GoodsListForShopAdapter extends DefaultAdapter<ReturnShopGoods.DataEntity> {
    public GoodsListForShopAdapter(List<ReturnShopGoods.DataEntity> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<ReturnShopGoods.DataEntity> getHolder(View v, int viewType) {
        return new GoodsListForShopItemHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_shop_goodslist;
    }
}
