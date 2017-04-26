package bai.kang.yun.zxd.mvp.ui.adapter;

import android.view.View;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.mvp.model.entity.Shop;
import bai.kang.yun.zxd.mvp.ui.holder.ShopListItemHolder;

/**
 * Created by Administrator on 2017/4/26 0026.
 */

public class ShopListAdapter extends DefaultAdapter<Shop> {
    public ShopListAdapter(List<Shop> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<Shop> getHolder(View v, int viewType) {
        return new ShopListItemHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_shoplist;
    }
}
