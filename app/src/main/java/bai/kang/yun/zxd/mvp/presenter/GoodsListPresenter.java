package bai.kang.yun.zxd.mvp.presenter;

import android.app.Application;

import com.jess.arms.base.AppManager;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.PermissionUtil;
import com.jess.arms.utils.RxUtils;
import com.jess.arms.utils.UiUtils;
import com.jess.arms.widget.imageloader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import bai.kang.yun.zxd.mvp.contract.GoodsListContract;
import bai.kang.yun.zxd.mvp.model.entity.CategoryGoods;
import bai.kang.yun.zxd.mvp.model.entity.Goods;
import bai.kang.yun.zxd.mvp.ui.adapter.GoodsListAdapter;
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
 * Created by Administrator on 2017/4/13 0013.
 */

@ActivityScope
public class GoodsListPresenter extends BasePresenter<GoodsListContract.Model, GoodsListContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;
    private List<CategoryGoods.ItemEntity> GoodsList = new ArrayList<>();
    private DefaultAdapter mAdapter;
    private boolean isFirst = true;
    private int preEndIndex;
    private int page = 1;

    @Inject
    public GoodsListPresenter(GoodsListContract.Model model, GoodsListContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
        mAdapter = new GoodsListAdapter(GoodsList);
        mRootView.setAdapter(mAdapter);//设置Adapter
    }

    public void requestUsers(final int id,final boolean pullToRefresh) {

        //请求外部存储权限用于适配android6.0的权限管理机制
        PermissionUtil.externalStorage(() -> {
            //request permission success, do something.
        }, mRootView.getRxPermissions(), mRootView, mErrorHandler);

        if (pullToRefresh) page = 1;//上拉刷新默认只请求第一页
        //关于RxCache缓存库的使用请参考 http://www.jianshu.com/p/b58ef6b0624b

        boolean isEvictCache = pullToRefresh;//是否驱逐缓存,为ture即不使用缓存,每次上拉刷新即需要最新数据,则不使用缓存

        if (pullToRefresh && isFirst) {//默认在第一次上拉刷新时使用缓存
            isFirst = false;
            isEvictCache = false;
        }

        mModel.getGoodslist(id,page,isEvictCache)
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
                .compose(RxUtils.<CategoryGoods>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(
                        new ErrorHandleSubscriber<CategoryGoods>(mErrorHandler) {
                    @Override
                    public void onNext(CategoryGoods users) {
                        if(users.getStatus()==1){
                            if (pullToRefresh) GoodsList.clear();//如果是上拉刷新则清空列表
                            preEndIndex = GoodsList.size();//更新之前列表总长度,用于确定加载更多的起始位置
                            GoodsList.addAll(users.getPage_data().getItems());
                            if (pullToRefresh){
                                mAdapter.notifyDataSetChanged();}
                            else
                                mAdapter.notifyItemRangeInserted(preEndIndex, users.getPage_data().getItems().size());
                                page++;
                        }else{
                            UiUtils.makeText(users.getMessage());
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
    public Observable<List<Goods>> getGoodsList(){


        return Observable.create(new Observable.OnSubscribe<List<Goods>>() {
            @Override
            public void call(Subscriber<? super List<Goods>> subscriber) {
                //Emit Data
                List<Goods> goodses=new ArrayList();
                for (int i=0;i<3;i++){
                    Goods goods=new Goods();
                    goods.setName(""+i);
                    goods.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492057604491&di=71f6ebba0c795ae4ce664eeea3021cce&imgtype=0&src=http%3A%2F%2Fpic.baike.soso.com%2Fp%2F20130705%2F20130705113951-882480559.jpg");
                    goodses.add(goods);
                    subscriber.onNext(goodses);
                }
                subscriber.onCompleted();
            }
        });
    }

}
