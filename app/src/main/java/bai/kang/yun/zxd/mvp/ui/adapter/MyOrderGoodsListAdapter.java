package bai.kang.yun.zxd.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.mvp.model.entity.Goods;

/**
 * Created by Administrator on 2017/5/8 0008.
 */

public class MyOrderGoodsListAdapter extends BaseAdapter{
    Context context;
    List<Goods> goodses;
    private LayoutInflater mInflater;
    public MyOrderGoodsListAdapter(Context context, List<Goods> goodses){
        this.context=context;
        this.goodses=goodses;
        this.mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return goodses.size();
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
            convertView = mInflater.inflate(R.layout.item_myorder_goodslist,null);
            viewHolder=new ViewHolder();
            viewHolder.name=(TextView) convertView.findViewById(R.id.item_name);
            viewHolder.preact=(TextView) convertView.findViewById(R.id.item_jg);
            viewHolder.num= (TextView) convertView.findViewById(R.id.item_num);
            viewHolder.image= (ImageView) convertView.findViewById(R.id.item_im);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
        }
        Goods goods=goodses.get(position);
        viewHolder.name.setText(goods.getName());
        viewHolder.preact.setText(goods.getPrice());
        viewHolder.preact.setText(goods.getNum());
        return convertView;
    }
    private class ViewHolder{
        TextView name;
        TextView preact;
        TextView num;
        ImageView image;
    }
}
