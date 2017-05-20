package bai.kang.yun.zxd.mvp.ui.adapter;

import android.view.View;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.mvp.model.entity.ReturnShop;
import bai.kang.yun.zxd.mvp.ui.holder.ShopListItemHolder;

/**
 * Created by Administrator on 2017/4/26 0026.
 */

public class ShopListAdapter extends DefaultAdapter<ReturnShop.ItemEntity> {
    public ShopListAdapter(List<ReturnShop.ItemEntity> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<ReturnShop.ItemEntity> getHolder(View v, int viewType) {
        return new ShopListItemHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_shoplist;
    }
}
