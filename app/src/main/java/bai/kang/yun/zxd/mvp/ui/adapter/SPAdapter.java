package bai.kang.yun.zxd.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import bai.kang.yun.zxd.R;

/**
 * Created by Administrator on 2017/5/25 0025.
 */

public abstract class SPAdapter<T> extends BaseAdapter {
    List<T> list;
    private LayoutInflater mInflater;
    Context context;
    public SPAdapter( Context context,List<T> list){
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
        if(list.size()!=0){
            SetData(list,position,viewHolder.tv_name);
        }
        return convertView;
    }
    private class ViewHolder{
        TextView tv_name;
    }
    public List<T> getList(){
        return list;
    }

    public abstract void SetData(List<T> list,int position,TextView tv);
}
