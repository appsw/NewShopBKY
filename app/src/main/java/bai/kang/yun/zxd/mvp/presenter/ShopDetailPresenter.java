package bai.kang.yun.zxd.mvp.presenter;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;

import com.jess.arms.base.AppManager;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxUtils;
import com.jess.arms.utils.UiUtils;
import com.jess.arms.widget.imageloader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import bai.kang.yun.zxd.mvp.contract.ShopDetailContract;
import bai.kang.yun.zxd.mvp.model.entity.ReturnShopCategory;
import bai.kang.yun.zxd.mvp.model.entity.ReturnShopDetail;
import bai.kang.yun.zxd.mvp.model.entity.ReturnShopGoods;
import bai.kang.yun.zxd.mvp.model.entity.ReturnShopZZ;
import bai.kang.yun.zxd.mvp.model.entity.Shop;
import bai.kang.yun.zxd.mvp.ui.adapter.GoodsListForShopAdapter;
import bai.kang.yun.zxd.mvp.ui.adapter.ShopCategoryAdapter;
import bai.kang.yun.zxd.mvp.ui.adapter.ShopZZImageAdapter;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.jess.arms.utils.UiUtils.startActivity;


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

@ActivityScope
public class ShopDetailPresenter extends BasePresenter<ShopDetailContract.Model, ShopDetailContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;
    private List<ReturnShopGoods.DataEntity> GoodsList = new ArrayList();
    private List<ReturnShopCategory.DataEntity> CategoryList = new ArrayList();
    private List<ReturnShopZZ.Items> ZZList = new ArrayList();
    private DefaultAdapter mAdapter;
    private ShopCategoryAdapter shopCategoryAdapter;
    private ShopZZImageAdapter shopZZImageAdapter;

    @Inject
    public ShopDetailPresenter(ShopDetailContract.Model model, ShopDetailContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
        mAdapter=new GoodsListForShopAdapter(GoodsList);
        shopCategoryAdapter=new ShopCategoryAdapter(application,CategoryList);
        shopZZImageAdapter=new ShopZZImageAdapter(application,ZZList);
        mRootView.setAdapter(mAdapter,shopCategoryAdapter,shopZZImageAdapter);
    }
    public void getShop(int id){
        mRootView.showLoading();
        mModel.getShopDetail(id)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.<ReturnShopDetail>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(
                        new ErrorHandleSubscriber<ReturnShopDetail>(mErrorHandler) {
                            @Override
                            public void onNext(ReturnShopDetail users) {
                                mRootView.hideLoading();
                                ReturnShopDetail.SingleEntity singleEntity=users.getSingle();
                                Shop shop=new Shop();
                                shop.setName(singleEntity.getName());
                                shop.setAdd(singleEntity.getReg_address());
                                shop.setImgUrl(singleEntity.getLogo());
                                mRootView.setShop(shop);
                            }

                        });

    }
    public void getShopGoods(int kind,int id){
        mRootView.showLoading();
        GoodsList.clear();
        mRootView.setListView(true);
        mRootView.setGridView(false);
        mRootView.setZZGridView(false);
        mModel.getShopGoods(kind,id)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔

                .observeOn(AndroidSchedulers.mainThread())

                .compose(RxUtils.<ReturnShopGoods>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(
                        new ErrorHandleSubscriber<ReturnShopGoods>(mErrorHandler) {
                            @Override
                            public void onNext(ReturnShopGoods goods) {
                                mRootView.hideLoading();
                                if(goods.getStatus()==1){
                                GoodsList.addAll(goods.getData());
                                mAdapter.notifyDataSetChanged();
                                }else{
                                    UiUtils.makeText(goods.getMessage());
                                }

                            }

                        });
    }
    public void getShopCategory(int id){
        mRootView.showLoading();
        mRootView.setListView(false);
        mRootView.setGridView(true);
        mRootView.setZZGridView(false);
        mModel.getShopCategory(id)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔

                .observeOn(AndroidSchedulers.mainThread())

                .compose(RxUtils.<ReturnShopCategory>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(
                        new ErrorHandleSubscriber<ReturnShopCategory>(mErrorHandler) {
                            @Override
                            public void onNext(ReturnShopCategory goods) {
                                mRootView.hideLoading();
                                if(goods.getStatus()==1){
                                    CategoryList.clear();
                                    CategoryList.addAll(goods.getData());
                                    shopCategoryAdapter.notifyDataSetChanged();
                                }else{
                                    UiUtils.makeText(goods.getMessage());
                                }

                            }

                        });
    }
    public void getShopZZ(int id){
        mRootView.showLoading();
        mRootView.setListView(false);
        mRootView.setGridView(false);
        mRootView.setZZGridView(true);
        mModel.getShopZZ(id)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.<ReturnShopZZ>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(
                        new ErrorHandleSubscriber<ReturnShopZZ>(mErrorHandler) {
                            @Override
                            public void onNext(ReturnShopZZ goods) {
                                mRootView.hideLoading();
                                if(goods.getStatus()==1){
                                    ZZList.clear();
                                    ZZList.addAll(goods.getPage_data().getItems());
                                    shopZZImageAdapter.notifyDataSetChanged();
                                }else{
                                    UiUtils.makeText(goods.getMessage());
                                }

                            }

                        });
    }
    public void call(String s){
        //意图：想干什么事
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        //url:统一资源定位符
        //uri:统一资源标示符（更广）
        intent.setData(Uri.parse("tel:" + s));
        //开启系统拨号器
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }



}
