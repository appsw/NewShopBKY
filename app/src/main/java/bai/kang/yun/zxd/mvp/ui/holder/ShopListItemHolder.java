package bai.kang.yun.zxd.mvp.ui.holder;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.utils.UiUtils;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;

import javax.annotation.Nullable;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.mvp.model.entity.ReturnShop;
import bai.kang.yun.zxd.mvp.ui.activity.DetailActivity;
import butterknife.BindView;
import butterknife.OnClick;
import common.WEApplication;
import rx.Observable;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class ShopListItemHolder extends BaseHolder<ReturnShop.ItemEntity> {
    @Nullable
    @BindView(R.id.item_im)
    ImageView im;
    @Nullable
    @BindView(R.id.item_name)
    TextView name;
    @Nullable
    @BindView(R.id.item_add)
    TextView add;
    @Nullable
    @BindView(R.id.item_jg)
    TextView price;
    @Nullable
    @BindView(R.id.item_probability)
    TextView probability;
    @BindView(R.id.item_ll)
    LinearLayout ll;
    private ImageLoader mImageLoader;//用于加载图片的管理类,默认使用glide,使用策略模式,可替换框架
    private final WEApplication mApplication;
    final String HOST="http://www.baikangyun.com";

    public ShopListItemHolder(View itemView) {
        super(itemView);
        //可以在任何可以拿到Application的地方,拿到AppComponent,从而得到用Dagger管理的单例对象
        mApplication = (WEApplication) itemView.getContext().getApplicationContext();
        mImageLoader = mApplication.getAppComponent().imageLoader();
    }

    @Override
    public void setData(ReturnShop.ItemEntity data, int position) {
        Observable.just(data.getName())
                .subscribe(RxTextView.text(name));
        Observable.just(data.getArea_name())
                .subscribe(RxTextView.text(add));
        Observable.just("￥"+data.getSalePrice())
                .subscribe(RxTextView.text(price));
        Observable.just(data.getScore()+"")
                .subscribe(RxTextView.text(probability));

        mImageLoader.loadImage(mApplication, GlideImageConfig
                .builder()
                .url(HOST+data.getImageUrl())
                .imageView(im)
                .build());
    }
    @OnClick(R.id.item_ll)
    void onclick(){
        Intent intent=new Intent(UiUtils.getContext(), DetailActivity.class);
        UiUtils.startActivity(intent);
    }

    @Override
    protected void onRelease() {
        mImageLoader.clear(mApplication, GlideImageConfig.builder()
                .imageViews(im)
                .build());
    }
}
