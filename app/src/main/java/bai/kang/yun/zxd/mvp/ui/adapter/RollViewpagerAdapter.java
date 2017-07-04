package bai.kang.yun.zxd.mvp.ui.adapter;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jess.arms.utils.UiUtils;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.util.List;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.app.utils.Transfer;
import bai.kang.yun.zxd.mvp.model.entity.Banner;
import bai.kang.yun.zxd.mvp.ui.activity.DetailActivity;
import common.WEApplication;

/**
 * Created by Administrator on 2017/4/12 0012.
 */

public class RollViewpagerAdapter extends StaticPagerAdapter {
    List<Banner.DataEntity> list;
    String host="http://www.baikangyun.com";
    private ImageLoader mImageLoader;//用于加载图片的管理类,默认使用glide,使用策略模式,可替换框架
    private final WEApplication mApplication;
    public RollViewpagerAdapter( List<Banner.DataEntity> list){
        this.list=list;
        //可以在任何可以拿到Application的地方,拿到AppComponent,从而得到用Dagger管理的单例对象
        mApplication = (WEApplication) UiUtils.getContext().getApplicationContext();
        mImageLoader = mApplication.getAppComponent().imageLoader();

    }
    @Override
    public View getView(ViewGroup container, int position) {
        final ImageView imageView=new ImageView(container.getContext());
        String imageUrl= host+list.get(position).getImg();
        mImageLoader.loadImage(mApplication, GlideImageConfig
                .builder()
                .url(imageUrl)
                .errorPic(R.mipmap.imgerror)
                .imageView(imageView)
                .build());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transfer.chosegoods_for_open_goodsdetail_id=list.get(position).getId();
                Intent intent=new Intent(UiUtils.getContext(), DetailActivity.class);
                UiUtils.startActivity(intent);
            }
        });
        return imageView;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
