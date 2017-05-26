package bai.kang.yun.zxd.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.mvp.model.entity.ReturnRegion;

/**
 * Created by Administrator on 2017/5/25 0025.
 */

public class SPAdapter extends BaseAdapter {
    List<ReturnRegion.DataEntity> list;
    private LayoutInflater mInflater;
    Context context;
    public SPAdapter( Context context,List<ReturnRegion.DataEntity> list){
        this.context=context;
        this.list=list;
        this.mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if(convertView==null){
            convertView = mInflater.inflate(R.layout.item_sp_tv,null);
            viewHolder=new ViewHolder();
            viewHolder.tv_name=(TextView) convertView.findViewById(R.id.sp_tv);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
        }
        ReturnRegion.DataEntity dataEntity=list.get(position);
        viewHolder.tv_name.setText(dataEntity.getRegionname());
        return convertView;
    }
    private class ViewHolder{
        TextView tv_name;
    }
    public List<ReturnRegion.DataEntity> getList(){
        return list;
    }
}
