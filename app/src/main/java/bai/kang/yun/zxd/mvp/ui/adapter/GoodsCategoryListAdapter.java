package bai.kang.yun.zxd.mvp.ui.adapter;

import android.view.View;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.mvp.model.entity.ReturnCategory;
import bai.kang.yun.zxd.mvp.ui.holder.GoodsCategoryListItemHolder;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class GoodsCategoryListAdapter extends DefaultAdapter<ReturnCategory.DataEntity> {
    public GoodsCategoryListAdapter(List<ReturnCategory.DataEntity> infos) {
        super(infos);
    }
    GoodsCategoryListItemHolder goodsCategoryListItemHolder;
    @Override
    public BaseHolder<ReturnCategory.DataEntity> getHolder(View v, int viewType) {
        goodsCategoryListItemHolder=new GoodsCategoryListItemHolder(v);
        return goodsCategoryListItemHolder;
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_find_left;
    }

}
