package bai.kang.yun.zxd.mvp.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;

import java.util.List;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.mvp.model.entity.ReturnShopZZ;
import common.WEApplication;

/**
 * Created by Administrator on 2017/6/2 0002.
 */

public class ShopZZImageAdapter extends BaseAdapter {
    private List<ReturnShopZZ.Items> CategoryList;
    Context context;
    private LayoutInflater mInflater;
    private ImageLoader mImageLoader;//用于加载图片的管理类,默认使用glide,使用策略模式,可替换框架
    private final WEApplication mApplication;
    private String HOST="http://www.baikangyun.com";
    public ShopZZImageAdapter(Context context,List<ReturnShopZZ.Items> CategoryList){
        this.context=context;
        this.CategoryList=CategoryList;
        this.mInflater = LayoutInflater.from(context);
        //可以在任何可以拿到Application的地方,拿到AppComponent,从而得到用Dagger管理的单例对象
        mApplication = (WEApplication) context.getApplicationContext();
        mImageLoader = mApplication.getAppComponent().imageLoader();
    }
    @Override
    public int getCount() {
        return CategoryList.size();
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
            convertView = mInflater.inflate(R.layout.item_shopzz,null);
            viewHolder=new ViewHolder();
            viewHolder.img=(ImageView) convertView.findViewById(R.id.img_zz);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
        }
        Log.e("url",""+HOST+CategoryList.get(position).getImg());
        mImageLoader.loadImage(mApplication, GlideImageConfig
                .builder()
                .url(HOST+CategoryList.get(position).getImg())
                .imageView(viewHolder.img)
                .errorPic(R.mipmap.imgerror)
                .build());
        return convertView;
    }
    private class ViewHolder{
        ImageView img;

    }
}
