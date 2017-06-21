package bai.kang.yun.zxd.mvp.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jess.arms.utils.UiUtils;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.util.List;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.mvp.model.entity.ReturnDetail;
import common.WEApplication;

/**
 * Created by Administrator on 2017/4/12 0012.
 */

public class DetailImgAdapter extends StaticPagerAdapter {
    List<ReturnDetail.ImgEntity> list;
    String host="http://www.baikangyun.com";
    private ImageLoader mImageLoader;//用于加载图片的管理类,默认使用glide,使用策略模式,可替换框架
    private final WEApplication mApplication;
    public DetailImgAdapter( List<ReturnDetail.ImgEntity> list){
        this.list=list;
        //可以在任何可以拿到Application的地方,拿到AppComponent,从而得到用Dagger管理的单例对象
        mApplication = (WEApplication) UiUtils.getContext().getApplicationContext();
        mImageLoader = mApplication.getAppComponent().imageLoader();

    }
    @Override
    public View getView(ViewGroup container, int position) {
        final ImageView imageView=new ImageView(container.getContext());
        String imageUrl= host+list.get(position).getImg_url();
        mImageLoader.loadImage(mApplication, GlideImageConfig
                .builder()
                .url(imageUrl)
                .errorPic(R.mipmap.imgerror)
                .imageView(imageView)
                .build());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        return imageView;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
