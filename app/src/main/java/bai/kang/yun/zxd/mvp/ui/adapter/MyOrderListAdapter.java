package bai.kang.yun.zxd.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jess.arms.utils.UiUtils;

import java.util.List;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.mvp.model.entity.Goods;
import bai.kang.yun.zxd.mvp.model.entity.Order;
import bai.kang.yun.zxd.mvp.ui.view.Search_View;

/**
 * Created by Administrator on 2017/5/8 0008.
 */

public class MyOrderListAdapter extends BaseAdapter{
    private List<Order> orders;
    private Context context;
    private List<Goods> goodses;
    private MyOrderGoodsListAdapter myOrderGoodsListAdapter;
    private LayoutInflater mInflater;
    public MyOrderListAdapter(Context context, List<Order> list){
        this.context=context;
        this.orders=list;
        this.mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return orders.size();
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
            convertView = mInflater.inflate(R.layout.item_myorder_goods,null);
            viewHolder=new ViewHolder();
            viewHolder.name=(TextView) convertView.findViewById(R.id.order_shopname);
            viewHolder.preact=(TextView) convertView.findViewById(R.id.allpreact);
            viewHolder.goodslist= (Search_View) convertView.findViewById(R.id.goods_list);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
        }
        Order order=orders.get(position);
        goodses=order.getGoodses();
        viewHolder.name.setText(order.getShopName());
        viewHolder.preact.setText(order.getPreact());
        myOrderGoodsListAdapter=new MyOrderGoodsListAdapter( UiUtils.getContext(),goodses);
        viewHolder.goodslist.setAdapter(myOrderGoodsListAdapter);
        return convertView;
    }
    private class ViewHolder{
        TextView name;
        TextView preact;
        Search_View goodslist;
    }
}
