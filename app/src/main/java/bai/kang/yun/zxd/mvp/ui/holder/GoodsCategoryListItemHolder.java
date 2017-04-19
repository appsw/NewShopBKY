package bai.kang.yun.zxd.mvp.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jess.arms.base.BaseHolder;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.mvp.model.entity.SPCategory;
import butterknife.BindView;
import rx.Observable;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class GoodsCategoryListItemHolder extends BaseHolder<SPCategory> {

    @BindView(R.id.find_textview)
    TextView name;
    int id;
    public GoodsCategoryListItemHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(SPCategory data, int position) {
        Observable.just(data.getName())
                .subscribe(RxTextView.text(name));
        id=data.getParentId();
    }

}
