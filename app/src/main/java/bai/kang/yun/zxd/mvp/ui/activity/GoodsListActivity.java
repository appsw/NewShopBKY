package bai.kang.yun.zxd.mvp.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.utils.UiUtils;
import com.paginate.Paginate;
import com.tbruyelle.rxpermissions.RxPermissions;

import javax.annotation.Nullable;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.app.utils.Transfer;
import bai.kang.yun.zxd.di.component.DaggerGoodsListComponent;
import bai.kang.yun.zxd.di.module.GoodsListModule;
import bai.kang.yun.zxd.mvp.contract.GoodsListContract;
import bai.kang.yun.zxd.mvp.presenter.GoodsListPresenter;
import butterknife.BindView;
import butterknife.OnClick;
import common.AppComponent;
import common.WEActivity;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

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
 * Created by Administrator on 2017/4/13 0013.
 */

public class GoodsListActivity extends WEActivity<GoodsListPresenter>
        implements GoodsListContract.View,SwipeRefreshLayout.OnRefreshListener {
    @Nullable
    @BindView(R.id.goodslist_recyclerView)
    RecyclerView mRecyclerView;
    @Nullable
    @BindView(R.id.goodslist_swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private Paginate mPaginate;
    private boolean isLoadingMore;
    private boolean isHave=true;
    private RxPermissions mRxPermissions;
    private int  kind;
    private String key;
    private String price="none";
    private String spcount="none";
    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        this.mRxPermissions = new RxPermissions(this);
        DaggerGoodsListComponent
                .builder()
                .appComponent(appComponent)
                .goodsListModule(new GoodsListModule(this)) //请将GoodsListModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_goodslist, null, false);
    }

    @Override
    protected void initData() {
        Log.e("id","id:"+ Transfer.chosegoods);
        Intent intent=getIntent();
        if(Transfer.chosegoods_for_open_shoplist_type==Transfer.GOODS_CATEGORY){
            mPresenter.requestUsers(Transfer.chosegoods,true,price,spcount);
        }else if(Transfer.chosegoods_for_open_shoplist_type==Transfer.SHOP_CATEGORY){
            mPresenter.ShopGoods(Transfer.choseshop_for_open_shopdetail_id,Transfer.choseshopcategory_open_goods_id,true);
        }else if(Transfer.chosegoods_for_open_shoplist_type==Transfer.GOODS_SEARCH){
            kind=intent.getIntExtra("kind",0);
            key=intent.getStringExtra("key");
            mPresenter.getSerchGoods(kind,key,true,price,spcount);
        }

    }


    @Override
    public void showLoading() {
        Timber.tag(TAG).w("showLoading");
        Observable.just(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> mSwipeRefreshLayout.setRefreshing(true));
    }

    @Override
    public void hideLoading() {
        Timber.tag(TAG).w("hideLoading");
        mSwipeRefreshLayout.setRefreshing(false);
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


    @Override
    public void onRefresh() {
        if(Transfer.chosegoods_for_open_shoplist_type==Transfer.GOODS_CATEGORY){
            mPresenter.requestUsers(Transfer.chosegoods,true,price,spcount);
        }else if(Transfer.chosegoods_for_open_shoplist_type==Transfer.SHOP_CATEGORY){
            mPresenter.ShopGoods(Transfer.choseshop_for_open_shopdetail_id,Transfer.choseshopcategory_open_goods_id,true);
        }else if(Transfer.chosegoods_for_open_shoplist_type==Transfer.GOODS_SEARCH){
            mPresenter.getSerchGoods(kind,key,true,price,spcount);
        }
    }

    /**
     * 初始化RecycleView
     */
    private void initRecycleView() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        UiUtils.configRecycleView(mRecyclerView, new LinearLayoutManager(this));
    }

    @Override
    public void setAdapter(DefaultAdapter adapter) {
        mRecyclerView.setAdapter(adapter);
        initRecycleView();
        initPaginate();
    }

    @Override
    public void startLoadMore() {
        isLoadingMore = true;
    }

    @Override
    public void endLoadMore() {
        isLoadingMore = false;
    }

    @Override
    public void noMore() {
        isHave=false;
    }

    @Override
    public RxPermissions getRxPermissions() {
        return mRxPermissions;
    }
    /**
     * 初始化Paginate,用于加载更多
     */
    private void initPaginate() {
        if(isHave){
        if (mPaginate == null) {
            Paginate.Callbacks callbacks = new Paginate.Callbacks() {
                @Override
                public void onLoadMore() {

                        if(Transfer.chosegoods_for_open_shoplist_type==Transfer.GOODS_CATEGORY){
                            mPresenter.requestUsers(Transfer.chosegoods,false,price,spcount);
                        }else if(Transfer.chosegoods_for_open_shoplist_type==Transfer.SHOP_CATEGORY){
                            mPresenter.ShopGoods(Transfer.choseshop_for_open_shopdetail_id,Transfer.choseshopcategory_open_goods_id,false);
                        }else if(Transfer.chosegoods_for_open_shoplist_type==Transfer.GOODS_SEARCH){
                            mPresenter.getSerchGoods(kind,key,false,price,spcount);
                        }


                }

                @Override
                public boolean isLoading() {
                    return isLoadingMore;
                }

                @Override
                public boolean hasLoadedAllItems() {
                    return false;
                }
            };

            mPaginate = Paginate.with(mRecyclerView, callbacks)
                    .setLoadingTriggerThreshold(0)
                    .build();
            mPaginate.setHasMoreDataToLoad(false);
        }
        }
    }
    @Override
    protected void onDestroy() {
        DefaultAdapter.releaseAllHolder(mRecyclerView);//super.onDestroy()之后会unbind,所有view被置为null,所以必须在之前调用
        super.onDestroy();
        this.mRxPermissions = null;
        this.mPaginate = null;
    }
    @OnClick(R.id.register_back)
    public void black(){
        killMyself();
    }
    @OnClick(R.id.tv_zh)
    public void tv_zh(){
        price="none";
        spcount="none";
        if(Transfer.chosegoods_for_open_shoplist_type==Transfer.GOODS_CATEGORY){
            mPresenter.requestUsers(Transfer.chosegoods,true,price,spcount);
        }else if(Transfer.chosegoods_for_open_shoplist_type==Transfer.SHOP_CATEGORY){
            mPresenter.ShopGoods(Transfer.choseshop_for_open_shopdetail_id,Transfer.choseshopcategory_open_goods_id,true);
        }else if(Transfer.chosegoods_for_open_shoplist_type==Transfer.GOODS_SEARCH){
            mPresenter.getSerchGoods(kind,key,true,price,spcount);
        }
    }
    @OnClick(R.id.tv_jg)
    public void tv_jg(){
        if(price.equals("none")||price.equals("asc")){
            price="desc";
        }else if(price.equals("none")||price.equals("desc")){
            price="asc";
        }else {
            price="none";
        }

        spcount="none";

        if(Transfer.chosegoods_for_open_shoplist_type==Transfer.GOODS_CATEGORY){
            mPresenter.requestUsers(Transfer.chosegoods,true,price,spcount);
        }else if(Transfer.chosegoods_for_open_shoplist_type==Transfer.SHOP_CATEGORY){
            mPresenter.ShopGoods(Transfer.choseshop_for_open_shopdetail_id,Transfer.choseshopcategory_open_goods_id,true);
        }else if(Transfer.chosegoods_for_open_shoplist_type==Transfer.GOODS_SEARCH){
            mPresenter.getSerchGoods(kind,key,true,price,spcount);
        }
    }
    @OnClick(R.id.tv_xl)
    public void tv_xl(){

        price="none";

        if(spcount.equals("none")||spcount.equals("asc")){
            spcount="desc";
        }else if(spcount.equals("none")||spcount.equals("desc")){
            spcount="asc";
        }else {
            spcount="none";
        }
        if(Transfer.chosegoods_for_open_shoplist_type==Transfer.GOODS_CATEGORY){
            mPresenter.requestUsers(Transfer.chosegoods,true,price,spcount);
        }else if(Transfer.chosegoods_for_open_shoplist_type==Transfer.SHOP_CATEGORY){
            mPresenter.ShopGoods(Transfer.choseshop_for_open_shopdetail_id,Transfer.choseshopcategory_open_goods_id,true);
        }else if(Transfer.chosegoods_for_open_shoplist_type==Transfer.GOODS_SEARCH){
            mPresenter.getSerchGoods(kind,key,true,price,spcount);
        }
    }
}
