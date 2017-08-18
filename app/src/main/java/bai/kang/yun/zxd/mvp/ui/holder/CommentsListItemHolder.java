package bai.kang.yun.zxd.mvp.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jess.arms.base.BaseHolder;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.mvp.model.entity.ReturnComment;
import butterknife.BindView;
import rx.Observable;

/**
 * Created by Administrator on 2017/8/4 0004.
 */

public class CommentsListItemHolder extends BaseHolder<ReturnComment.ItemsEntity> {
    @BindView(R.id.tv_pf)
    TextView tv_pf;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_pj)
    TextView tv_pj;
    @BindView(R.id.tv_time)
    TextView tv_time;
    public CommentsListItemHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(ReturnComment.ItemsEntity data, int position) {
        Observable.just(data.getServcice()+"分")
                .subscribe(RxTextView.text(tv_pf));
        Observable.just(data.getNick_name())
                .subscribe(RxTextView.text(tv_name));
        Observable.just(data.getComment_body())
                .subscribe(RxTextView.text(tv_pj));
        Observable.just(data.getCreate_time())
                .subscribe(RxTextView.text(tv_time));
    }
}
