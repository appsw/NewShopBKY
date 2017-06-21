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
import bai.kang.yun.zxd.mvp.model.entity.Banner;
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
                UiUtils.makeText("您点击了第"+position+"个");
//                Intent intent=new Intent(context, XqForBannerActivity.class);
//                Bundle bundle =new Bundle();
//                bundle.putString("GoodsId",list.get(position).get("id"));
//                Log.e("GoodsId",""+list.get(position).get("id"));
//                intent.putExtras(bundle);
//                startActivityForResult(intent,3);

            }
        });
        return imageView;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
