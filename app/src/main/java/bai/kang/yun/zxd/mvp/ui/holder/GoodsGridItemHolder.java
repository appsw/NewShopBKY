package bai.kang.yun.zxd.mvp.ui.holder;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.utils.UiUtils;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.app.utils.Transfer;
import bai.kang.yun.zxd.mvp.model.entity.ReturnGoods;
import bai.kang.yun.zxd.mvp.ui.activity.DetailActivity;
import butterknife.BindView;
import butterknife.OnClick;
import common.WEApplication;
import rx.Observable;

/**
 * Created by Administrator on 2017/4/17 0017.
 */

public class GoodsGridItemHolder extends BaseHolder<ReturnGoods.DataEntity> {


    @BindView(R.id.iv_goods)
    ImageView im;
    @BindView(R.id.tv_name)
    TextView name;
    @BindView(R.id.tv_p)
    TextView Price;
    private int id;
    static final String HOST="http://www.baikangyun.com";
    private ImageLoader mImageLoader;//用于加载图片的管理类,默认使用glide,使用策略模式,可替换框架
    private final WEApplication mApplication;
    public GoodsGridItemHolder(View itemView) {
        super(itemView);
        //可以在任何可以拿到Application的地方,拿到AppComponent,从而得到用Dagger管理的单例对象
        mApplication = (WEApplication) itemView.getContext().getApplicationContext();
        mImageLoader = mApplication.getAppComponent().imageLoader();
    }
    @Override
    public void setData(ReturnGoods.DataEntity data, int position) {
        id=data.getId();
        Observable.just(data.getProductname())
                .subscribe(RxTextView.text(name));
        Observable.just("￥"+data.getSaleprice())
                .subscribe(RxTextView.text(Price));

        mImageLoader.loadImage(mApplication, GlideImageConfig
                .builder()
                .url(HOST+data.getImageurl())
                .errorPic(R.mipmap.imgerror)
                .imageView(im)
                .build());
    }
    @OnClick(R.id.iv_goods)
    void onclick(){
        Transfer.chosegoods_for_open_goodsdetail_id=id;
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
