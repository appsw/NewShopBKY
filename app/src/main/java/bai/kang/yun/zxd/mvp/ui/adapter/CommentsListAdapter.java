package bai.kang.yun.zxd.mvp.ui.adapter;

import android.view.View;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.mvp.model.entity.ReturnComment;
import bai.kang.yun.zxd.mvp.ui.holder.CommentsListItemHolder;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class CommentsListAdapter extends DefaultAdapter<ReturnComment.ItemsEntity> {
    public CommentsListAdapter(List<ReturnComment.ItemsEntity> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<ReturnComment.ItemsEntity> getHolder(View v, int viewType) {
        return new CommentsListItemHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_comment;
    }
}
