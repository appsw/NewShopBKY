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
import bai.kang.yun.zxd.app.utils.Transfer;
import bai.kang.yun.zxd.mvp.model.entity.CategoryGoods;
import bai.kang.yun.zxd.mvp.ui.activity.DetailActivity;
import bai.kang.yun.zxd.mvp.ui.activity.ShopListActivity;
import butterknife.BindView;
import butterknife.OnClick;
import common.WEApplication;
import rx.Observable;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class GoodsListItemHolder extends BaseHolder<CategoryGoods.ItemEntity> {
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
    @BindView(R.id.item_guige)
    TextView guige;
    @Nullable
    @BindView(R.id.item_seleing)
    TextView seleing;
    @BindView(R.id.item_ll)
    LinearLayout ll;
    private CategoryGoods.ItemEntity itemEntity;
    private ImageLoader mImageLoader;//用于加载图片的管理类,默认使用glide,使用策略模式,可替换框架
    private final WEApplication mApplication;
    final String HOST="http://www.baikangyun.com";
    public GoodsListItemHolder(View itemView) {
        super(itemView);
        //可以在任何可以拿到Application的地方,拿到AppComponent,从而得到用Dagger管理的单例对象
        mApplication = (WEApplication) itemView.getContext().getApplicationContext();
        mImageLoader = mApplication.getAppComponent().imageLoader();
    }

    @Override
    public void setData(CategoryGoods.ItemEntity data, int position) {
        itemEntity=data;
        Observable.just(data.getDrugs_name())
                .subscribe(RxTextView.text(name));
        Observable.just(data.getPizhunwenhao())
                .subscribe(RxTextView.text(abst));
        Observable.just(data.getGuige())
                .subscribe(RxTextView.text(guige));
        Observable.just(data.getMinPrice()+"")
                .subscribe(RxTextView.text(price));
        Observable.just(data.getSpCount()+"")
                .subscribe(RxTextView.text(seleing));

        mImageLoader.loadImage(mApplication, GlideImageConfig
                .builder()
                .url(HOST+data.getImg_title())
                .errorPic(R.mipmap.none)
                .imageView(im)
                .build());
    }
    @OnClick(R.id.item_ll)
    void onclick(){
        Transfer.chosegoods_for_open_shoplist_kind=itemEntity.getKind();
        Transfer.chosegoods_for_open_shoplist_id=itemEntity.getId();
        Transfer.chosegoods_for_open_shoplist_name=itemEntity.getDrugs_name();
        Transfer.chosegoods_for_open_shoplist_zzh=itemEntity.getPizhunwenhao();
        Transfer.chosegoods_for_open_shoplist_guige=itemEntity.getGuige();
        Transfer.chosegoods_for_open_shoplist_Manufacturers=itemEntity.getManufacturers();
        Transfer.chosegoods_for_open_shoplist_imurl=HOST+itemEntity.getImg_title();
        Intent intent = null;
        if(Transfer.chosegoods_for_open_shoplist_type==Transfer.GOODS_CATEGORY)
            intent=new Intent(UiUtils.getContext(), ShopListActivity.class);
        else if(Transfer.chosegoods_for_open_shoplist_type==Transfer.SHOP_CATEGORY){
            Transfer.chosegoods_for_open_goodsdetail_id=itemEntity.getId();
            intent=new Intent(UiUtils.getContext(), DetailActivity.class);
        }
        else if(Transfer.chosegoods_for_open_shoplist_type==Transfer.GOODS_SEARCH)
            intent=new Intent(UiUtils.getContext(), ShopListActivity.class);
        UiUtils.startActivity(intent);
    }

    @Override
    protected void onRelease() {
        mImageLoader.clear(mApplication, GlideImageConfig.builder()
                .imageViews(im)
                .build());
    }
}
