package bai.kang.yun.zxd.mvp.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.utils.UiUtils;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.app.utils.MyGridView;
import bai.kang.yun.zxd.app.utils.SPMoreImageView;
import bai.kang.yun.zxd.app.utils.Transfer;
import bai.kang.yun.zxd.di.component.DaggerShopDetailComponent;
import bai.kang.yun.zxd.di.module.ShopDetailModule;
import bai.kang.yun.zxd.mvp.contract.ShopDetailContract;
import bai.kang.yun.zxd.mvp.model.entity.Shop;
import bai.kang.yun.zxd.mvp.presenter.ShopDetailPresenter;
import butterknife.BindView;
import butterknife.OnClick;
import common.AppComponent;
import common.WEActivity;
import rx.Observable;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */

/**
 * Created by Administrator on 2017/4/27 0027.
 */

public class ShopDetailActivity extends WEActivity<ShopDetailPresenter> implements ShopDetailContract.View {

    @BindView(R.id.shop_name) TextView tv_name;
    @BindView(R.id.shop_add) TextView tv_add;
    @BindView(R.id.shop_item_zz) TextView tv_zz;
    @BindView(R.id.shop_item_category) TextView tv_category;
    @BindView(R.id.head_mimgv) SPMoreImageView image;
    @BindView(R.id.shop_goodslist) RecyclerView list;
    @BindView(R.id.shop_goodscategory)
    MyGridView gridView;
    @BindView(R.id.shop_zz) MyGridView ZZgridView;
    private ImageLoader mImageLoader;//用于加载图片的管理类,默认使用glide,使用策略模式,可替换框架

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        mImageLoader = appComponent.imageLoader();
        DaggerShopDetailComponent
                .builder()
                .appComponent(appComponent)
                .shopDetailModule(new ShopDetailModule(this)) //请将ShopDetailModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_shopdetail, null, false);
    }

  @Override
    protected void initData() {
        mPresenter.getShop(Transfer.choseshop_for_open_shopdetail_id);
      mPresenter.getShopGoods(1,Transfer.choseshop_for_open_shopdetail_id);

    }
    @OnClick(R.id.shop_item_rexiao)
    void rexiao(){
        mPresenter.getShopGoods(1,Transfer.choseshop_for_open_shopdetail_id);
    }
    @OnClick(R.id.shop_item_tuijian)
    void tuijian(){
        mPresenter.getShopGoods(2,Transfer.choseshop_for_open_shopdetail_id);
    }
    @OnClick(R.id.shop_item_category)
    void category(){
        mPresenter.getShopCategory(Transfer.choseshop_for_open_shopdetail_id);
    }
    @OnClick(R.id.shop_item_zz)
    void zz(){ mPresenter.getShopZZ(Transfer.choseshop_for_open_shopdetail_id);}
    @Override
    public void showLoading() {
        loadingDialog.show();
    }

    @Override
    public void hideLoading() {
        loadingDialog.close();
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.SnackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        UiUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }
    /**
     * 初始化RecycleView
     */
    private void initRecycleView() {

        UiUtils.configRecycleView(list, new LinearLayoutManager(this));
    }

    @Override
    public void setAdapter(DefaultAdapter adapter, BaseAdapter baseAdapter,BaseAdapter baseAdapter1) {
        list.setAdapter(adapter);
        gridView.setAdapter(baseAdapter);
        ZZgridView.setAdapter(baseAdapter1);
        initRecycleView();
    }



    @Override
    public void setShop(Shop shop) {
        Observable.just(shop.getName())
                .subscribe(RxTextView.text(tv_name));
        Observable.just(shop.getAdd())
                .subscribe(RxTextView.text(tv_add));
        if(shop.getImgUrl()!=null){
            mImageLoader.loadImage(mApplication, GlideImageConfig
                    .builder()
                    .url("http://www.baikangyun.com"+shop.getImgUrl())
                    .errorPic(R.mipmap.imgerror)
                    .imageView(image)
                    .build());
            Log.e("url","http://www.baikangyun.com"+shop.getImgUrl());
        }

    }

    @Override
    public void setListView(boolean is) {
        if(is){
            list.setVisibility(View.VISIBLE);
        }else {
            list.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void setGridView(boolean is) {
        if(is){
            gridView.setVisibility(View.VISIBLE);
        }else {
            gridView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void setZZGridView(boolean is) {
        if(is){
            ZZgridView.setVisibility(View.VISIBLE);
        }else {
            ZZgridView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        DefaultAdapter.releaseAllHolder(list);//super.onDestroy()之后会unbind,所有view被置为null,所以必须在之前调用
        super.onDestroy();
    }
    @OnClick(R.id.register_back)
    public void black(){
        killMyself();
    }
}
