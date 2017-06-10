package bai.kang.yun.zxd.mvp.ui.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import bai.kang.yun.zxd.mvp.model.entity.ReturnRegion;

/**
 * Created by Administrator on 2017/6/10 0010.
 */

public class AddSpAdapter extends SPAdapter<ReturnRegion.DataEntity> {
    public AddSpAdapter(Context context, List<ReturnRegion.DataEntity> list) {
        super(context, list);
    }

    @Override
    public void SetData(List<ReturnRegion.DataEntity> list, int position, TextView tv) {
        ReturnRegion.DataEntity dataEntity=  list.get(position);
        tv.setText(dataEntity.getRegionname());
    }
}
