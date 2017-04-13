package bai.kang.yun.zxd.mvp.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;

import javax.annotation.Nullable;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.mvp.model.entity.Goods;
import butterknife.BindView;
import common.WEApplication;
import rx.Observable;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class GoodsListItemHolder extends BaseHolder<Goods> {
    @Nullable
    @BindView(R.id.item_im)
    ImageView im;
    @Nullable
    @BindView(R.id.item_name)
    TextView name;
    @Nullable
    @BindView(R.id.item_abstract)
    TextView abst;
    @Nullable
    @BindView(R.id.item_jg)
    TextView price;
    @Nullable
    @BindView(R.id.item_seleing)
    TextView seleing;
    private ImageLoader mImageLoader;//用于加载图片的管理类,默认使用glide,使用策略模式,可替换框架
    private final WEApplication mApplication;
    public GoodsListItemHolder(View itemView) {
        super(itemView);
        //可以在任何可以拿到Application的地方,拿到AppComponent,从而得到用Dagger管理的单例对象
        mApplication = (WEApplication) itemView.getContext().getApplicationContext();
        mImageLoader = mApplication.getAppComponent().imageLoader();
    }

    @Override
    public void setData(Goods data, int position) {
        Observable.just(data.getName())
                .subscribe(RxTextView.text(name));
        Observable.just(data.getAbstract())
                .subscribe(RxTextView.text(abst));
        Observable.just(data.getPrice()+"")
                .subscribe(RxTextView.text(price));
        Observable.just(data.getSeleing()+"")
                .subscribe(RxTextView.text(seleing));

        mImageLoader.loadImage(mApplication, GlideImageConfig
                .builder()
                .url(data.getImageUrl())
                .imageView(im)
                .build());
    }

    @Override
    protected void onRelease() {
        mImageLoader.clear(mApplication, GlideImageConfig.builder()
                .imageViews(im)
                .build());
    }
}
