package bai.kang.yun.zxd.mvp.ui.adapter;

import android.content.Context;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.List;

import bai.kang.yun.zxd.mvp.model.entity.ReturenExpress;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/10 0010.
 */

public class ShopExperssAdapter extends SPAdapter<ReturenExpress.DataEntity> {
    public ShopExperssAdapter(Context context, List<ReturenExpress.DataEntity> list) {
        super(context, list);
    }

    @Override
    public void SetData(List<ReturenExpress.DataEntity> list, int position, TextView tv) {
        Observable.just(list.get(position).getTrafficNameAndPrice()).subscribe(RxTextView.text(tv));
    }
}
