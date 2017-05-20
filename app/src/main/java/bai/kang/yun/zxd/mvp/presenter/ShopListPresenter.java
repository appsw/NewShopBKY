package bai.kang.yun.zxd.mvp.presenter;

import android.app.Application;

import com.jess.arms.base.AppManager;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxUtils;
import com.jess.arms.widget.imageloader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import bai.kang.yun.zxd.mvp.contract.ShopListContract;
import bai.kang.yun.zxd.mvp.model.entity.ReturnShop;
import bai.kang.yun.zxd.mvp.model.entity.Shop;
import bai.kang.yun.zxd.mvp.ui.adapter.ShopListAdapter;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */


/**
 * Created by Administrator on 2017/4/26 0026.
 */

@ActivityScope
public class ShopListPresenter extends BasePresenter<ShopListContract.Model, ShopListContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;
    private List<ReturnShop.ItemEntity> ShopList = new ArrayList<>();
    private DefaultAdapter mAdapter;
    private boolean isFirst = true;
    private int preEndIndex;
    private int page = 1;

    @Inject
    public ShopListPresenter(ShopListContract.Model model, ShopListContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
        mAdapter = new ShopListAdapter(ShopList);
        mRootView.setAdapter(mAdapter);//设置Adapter
    }

    public void requestUsers(final int kind,final int id,final boolean pullToRefresh) {


        //关于RxCache缓存库的使用请参考 http://www.jianshu.com/p/b58ef6b0624b
        if (pullToRefresh) page = 1;//上拉刷新默认只请求第一页

        boolean isEvictCache = pullToRefresh;//是否驱逐缓存,为ture即不使用缓存,每次上拉刷新即需要最新数据,则不使用缓存

        if (pullToRefresh && isFirst) {//默认在第一次上拉刷新时使用缓存
            isFirst = false;
            isEvictCache = false;
        }

         mModel.getShoplist(kind,id,page,isEvictCache)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .doOnSubscribe(() -> {
                    if (pullToRefresh)
                        mRootView.showLoading();//显mRootView.showLoadi示上拉刷新的进度条
                    else
                        mRootView.startLoadMore();//显示下拉加载更多的进度条
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> {
                    if (pullToRefresh)
                        mRootView.hideLoading();//隐藏上拉刷新的进度条
                    else
                        mRootView.endLoadMore();//隐藏下拉加载更多的进度条
                })
                .compose(RxUtils.<ReturnShop>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(
                        new ErrorHandleSubscriber<ReturnShop>(mErrorHandler) {
                            @Override
                            public void onNext(ReturnShop shops) {
                                if(shops.getStatus()==1){
                                    if (pullToRefresh) ShopList.clear();//如果是上拉刷新则清空列表
                                    preEndIndex = ShopList.size();//更新之前列表总长度,用于确定加载更多的起始位置
                                    ShopList.addAll(shops.getPage_data().getItems());
                                    if (pullToRefresh){
                                        mAdapter.notifyDataSetChanged();}
                                    else
                                        mAdapter.notifyItemRangeInserted(preEndIndex,shops.getPage_data().getItems().size());
                                    page++;
                                }

                            }
                        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
    public Observable<List<Shop>> getGoodsList(){


        return Observable.create(new Observable.OnSubscribe<List<Shop>>() {
            @Override
            public void call(Subscriber<? super List<Shop>> subscriber) {
                //Emit Data
                List<Shop> goodses=new ArrayList();
                for (int i=0;i<3;i++){
                    Shop goods=new Shop();
                    goods.setName(""+i);
                    goods.setGoods_Price("￥123");
                    goods.setImgUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492057604491&di=71f6ebba0c795ae4ce664eeea3021cce&imgtype=0&src=http%3A%2F%2Fpic.baike.soso.com%2Fp%2F20130705%2F20130705113951-882480559.jpg");
                    goodses.add(goods);
                    subscriber.onNext(goodses);
                }
                subscriber.onCompleted();
            }
        });
    }

}
