package bai.kang.yun.zxd.mvp.presenter;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;

import com.jess.arms.base.AppManager;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxUtils;
import com.jess.arms.widget.imageloader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import bai.kang.yun.zxd.mvp.contract.ShopDetailContract;
import bai.kang.yun.zxd.mvp.model.entity.Goods;
import bai.kang.yun.zxd.mvp.model.entity.Shop;
import bai.kang.yun.zxd.mvp.ui.adapter.GoodsListForShopAdapter;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import rx.Observable;
import rx.Subscriber;
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
    private List<Goods> GoodsList = new ArrayList<>();
    private DefaultAdapter mAdapter;

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
        mRootView.setAdapter(mAdapter);
    }
    public void getShop(){
        Shop shop=new Shop();
        shop.setName("1111");
        shop.setAdd("济南济南");
        shop.setImgUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492057604491&di=71f6ebba0c795ae4ce664eeea3021cce&imgtype=0&src=http%3A%2F%2Fpic.baike.soso.com%2Fp%2F20130705%2F20130705113951-882480559.jpg");
        mRootView.setShop(shop);
    }
    public void getShopGoods(){
        getGoodsList()
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔

                .observeOn(AndroidSchedulers.mainThread())

                .compose(RxUtils.<List<Goods>>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(
                        new ErrorHandleSubscriber<List<Goods>>(mErrorHandler) {
                            @Override
                            public void onNext(List<Goods> users) {
                                GoodsList.addAll(users);
                                mAdapter.notifyDataSetChanged();}

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
