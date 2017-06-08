package bai.kang.yun.zxd.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;

import java.util.ArrayList;
import java.util.List;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.mvp.model.entity.CarGoods;
import common.WEApplication;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/8 0008.
 */

public class MakeOrderGoodsListAdapter extends BaseAdapter {
    private ImageLoader imageLoader;
    List<CarGoods> carGoodses=new ArrayList<>();
    Context context;
    public MakeOrderGoodsListAdapter(Context context,List<CarGoods> carGoodses){
        this.context=context;
        this.carGoodses=carGoodses;
        imageLoader=((WEApplication)context.getApplicationContext()).getAppComponent().imageLoader();
    }
    @Override
    public int getCount() {
        return carGoodses.size();
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
        ViewHoder viewHoder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_myorder_goodslist,null);
            viewHoder=new ViewHoder();
            viewHoder.name= (TextView) convertView.findViewById(R.id.item_name);
            viewHoder.price= (TextView) convertView.findViewById(R.id.item_jg);
            viewHoder.num= (TextView) convertView.findViewById(R.id.item_num);
            viewHoder.im= (ImageView) convertView.findViewById(R.id.item_im);
            convertView.setTag(viewHoder);
        }else {
            viewHoder= (ViewHoder) convertView.getTag();
        }
        CarGoods carGood=carGoodses.get(position);
        Observable.just(carGood.getGoodsName()).subscribe(RxTextView.text(viewHoder.name));
        Observable.just(carGood.getPrice()).subscribe(RxTextView.text(viewHoder.price));
        Observable.just(carGood.getNumber()).subscribe(RxTextView.text(viewHoder.num));
        imageLoader.loadImage(context, GlideImageConfig
                .builder()
                .url(carGood.getGoodsLogo())
                .imageView(viewHoder.im)
                .build());
        return convertView;
    }
    private class ViewHoder{
        TextView name;
        TextView price;
        TextView num;
        ImageView im;
    }
}
