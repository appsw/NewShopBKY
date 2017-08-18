package bai.kang.yun.zxd.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;

import java.util.List;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.mvp.model.entity.ReturnADGrid;
import common.WEApplication;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class FristADGridAdapter extends BaseAdapter {

    List<ReturnADGrid.DataEntity> spCategories;
    private LayoutInflater mInflater;
    Context context;
    private ImageLoader mImageLoader;//用于加载图片的管理类,默认使用glide,使用策略模式,可替换框架
    private final WEApplication mApplication;
    private String HOST="http://www.baikangyun.com";
    public FristADGridAdapter(List<ReturnADGrid.DataEntity> spCategories,Context context){
        this.spCategories=spCategories;
        this.context=context;
        this.mInflater = LayoutInflater.from(context);
        //可以在任何可以拿到Application的地方,拿到AppComponent,从而得到用Dagger管理的单例对象
        mApplication = (WEApplication) context.getApplicationContext();
        mImageLoader = mApplication.getAppComponent().imageLoader();

    }
    @Override
    public int getCount() {
        return spCategories.size();
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
            convertView = mInflater.inflate(R.layout.item_gridview,null);
            viewHolder=new ViewHolder();
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.pt);
            viewHolder.name=(TextView) convertView.findViewById(R.id.name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
        }
        ReturnADGrid.DataEntity spCategory=spCategories.get(position);

        if(spCategory.getImg()!=""){
            mImageLoader.loadImage(mApplication, GlideImageConfig
                    .builder()
                    .url(HOST+spCategory.getImg())
                    .errorPic(R.mipmap.imgerror)
                    .imageView(viewHolder.imageView)
                    .build());
        }else {
            viewHolder.imageView.setImageResource(R.mipmap.yaowan);
        }
        viewHolder.name.setText(spCategory.getTitle());
        return convertView;
    }
    private class ViewHolder{
        ImageView imageView;
        TextView name;
    }
    public List<ReturnADGrid.DataEntity> getList(){
        return spCategories;
    }
}
