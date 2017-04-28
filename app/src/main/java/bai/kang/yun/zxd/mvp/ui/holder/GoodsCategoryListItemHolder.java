package bai.kang.yun.zxd.mvp.ui.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.utils.UiUtils;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.mvp.model.entity.SPCategory;
import butterknife.BindView;
import rx.Observable;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class GoodsCategoryListItemHolder extends BaseHolder<SPCategory> {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.item_ll_text)
    LinearLayout itemView;

    int id;
    static public int curSelectRow = 0;	//当前被选中的行索引
    public GoodsCategoryListItemHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(SPCategory data, int position) {
        /** 选中的行: 背景白色, 否则灰色  */
        if(curSelectRow == position){
            itemView.setBackgroundColor(UiUtils.getContext().getResources().getColor(R.color.slategrey));
        }else{
            itemView.setBackgroundColor(UiUtils.getContext().getResources().getColor(R.color.white));
        }
        Observable.just(data.getName())
                .subscribe(RxTextView.text(name));
        id=data.getParentId();
    }
    static public void setSelectIndex(int selectIndex){
        curSelectRow = selectIndex ;
    }
}
