package bai.kang.yun.zxd.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.jess.arms.utils.UiUtils;

import java.util.List;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.mvp.model.entity.ReturnOrderList;
import bai.kang.yun.zxd.mvp.ui.view.Search_View;

/**
 * Created by Administrator on 2017/5/8 0008.
 */

public class MyOrderListAdapter extends BaseAdapter{
    private List<ReturnOrderList.ItemEntiy> orders;
    private Context context;
    private List<ReturnOrderList.GoodsEntiy> goodses;
    private MyOrderGoodsListAdapter myOrderGoodsListAdapter;
    private LayoutInflater mInflater;


    public MyOrderListAdapter(Context context, List<ReturnOrderList.ItemEntiy> list){
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
            viewHolder.btn_zf= (Button) convertView.findViewById(R.id.btn_zf);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
        }

        ReturnOrderList.ItemEntiy order=orders.get(position);
        goodses=order.getGoodsList();
        viewHolder.name.setText(order.getShop_Name());
        viewHolder.preact.setText(order.getRealAmount()+"");
        myOrderGoodsListAdapter=new MyOrderGoodsListAdapter( UiUtils.getContext(),goodses);
        viewHolder.goodslist.setAdapter(myOrderGoodsListAdapter);
        if(order.getStatus()==1)
            viewHolder.btn_zf.setVisibility(View.VISIBLE);
        viewHolder.btn_zf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtils.makeText("支付"+order.getOrderNo()+"总价为"+order.getRealAmount());
            }
        });
        return convertView;
    }
    private class ViewHolder{
        TextView name;
        TextView preact;
        Search_View goodslist;
        Button btn_zf;
    }
}
