package bai.kang.yun.zxd.mvp.ui.holder;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.utils.UiUtils;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.mvp.model.entity.SPCategory;
import bai.kang.yun.zxd.mvp.ui.activity.DetailActivity;
import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class GoodsCategoryListItemHolder extends BaseHolder<SPCategory> {

    @BindView(R.id.find_textview)
    TextView name;
    public GoodsCategoryListItemHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(SPCategory data, int position) {
        Observable.just(data.getName())
                .subscribe(RxTextView.text(name));
    }
    @OnClick(R.id.find_textview)
    void onclick(){
        Intent intent=new Intent(UiUtils.getContext(), DetailActivity.class);
        UiUtils.startActivity(intent);
    }
}
