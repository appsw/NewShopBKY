package bai.kang.yun.zxd.mvp.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.mvp.model.entity.CarGoods;
import bai.kang.yun.zxd.mvp.model.entity.CarShop;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/7 0007.
 */

public class MakeOrderListAdapter extends BaseAdapter {
    private Context mContext;
    private List<CarShop> mListShop = new ArrayList();
    private MakeOrderGoodsListAdapter makeOrderGoodsListAdapter;
    public MakeOrderListAdapter(Context mContext,List<CarShop> mListGoods){
        this.mContext=mContext;
        this.mListShop=mListGoods;
    }
    @Override
    public int getCount() {
        return mListShop.size();
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
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_order_shop, parent, false);
            viewHolder=new ViewHolder();
            viewHolder.ShopName= (TextView) convertView.findViewById(R.id.tv_shopname);
            viewHolder.PostType= (TextView) convertView.findViewById(R.id.tv_posttype);
            viewHolder.listView=(ListView) convertView.findViewById(R.id.list_goods);
            viewHolder.sum=(TextView) convertView.findViewById(R.id.tv_sum);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CarShop carShop=mListShop.get(position);
        List<CarGoods> goodses=carShop.getGoods();
        makeOrderGoodsListAdapter=new MakeOrderGoodsListAdapter(mContext,goodses);
        viewHolder.listView.setAdapter(makeOrderGoodsListAdapter);
        float sum=0;
        for(CarGoods carGoods:goodses){
            sum+=Float.parseFloat(carGoods.getPrice());
        }
        Observable.just(carShop.getMerchantName()).subscribe(RxTextView.text(viewHolder.ShopName));
        Observable.just(sum+"").subscribe(RxTextView.text(viewHolder.sum));
        int weight=0;
        for(CarGoods carGoods:goodses){
            weight+=carGoods.getWeight();
        }
        Log.e("weight",""+weight);
        return convertView;
    }
    private class ViewHolder{
        TextView ShopName;
        TextView PostType;
        ListView listView;
        TextView sum;

    }
}
