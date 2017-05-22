package bai.kang.yun.zxd.mvp.presenter;

import android.app.Application;

import com.jess.arms.base.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxUtils;
import com.jess.arms.utils.UiUtils;
import com.jess.arms.widget.imageloader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import bai.kang.yun.zxd.app.utils.ShoppingCartBiz;
import bai.kang.yun.zxd.mvp.contract.CarContract;
import bai.kang.yun.zxd.mvp.model.entity.ShoppingCartBean;
import bai.kang.yun.zxd.mvp.ui.adapter.MyExpandableListAdapter;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
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
 * Created by Administrator on 2017/4/21 0021.
 */

@ActivityScope
public class CarPresenter extends BasePresenter<CarContract.Model, CarContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;
    MyExpandableListAdapter myExpandableListAdapter=new MyExpandableListAdapter(UiUtils.getContext());
    List<ShoppingCartBean> mListGoods = new ArrayList<ShoppingCartBean>();
    @Inject
    public CarPresenter(CarContract.Model model, CarContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
        myExpandableListAdapter.setList(mListGoods);
        mRootView.setAdapter(myExpandableListAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
    /** 获取购物车列表的数据（数据和网络请求也是非通用部分） */
    public void requestShoppingCartList(int id) {
        ShoppingCartBiz.delAllGoods();
        testAddGood();
//        mModel.ShoppingCartList(id)
        getCartsList()
                .subscribeOn(Schedulers.io())
                .doOnNext(new Action1<List<ShoppingCartBean>>() {
                    @Override
                    public void call(List<ShoppingCartBean> shoppingCartBeen) {
                        ShoppingCartBiz.updateShopList(shoppingCartBeen);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .compose(RxUtils.<List<ShoppingCartBean>>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(new ErrorHandleSubscriber<List<ShoppingCartBean>>(mErrorHandler) {
                            @Override
                            public void onNext(List<ShoppingCartBean> stringStringMap) {
                                updateListView(stringStringMap);
                            }
                        });
    }
    /** 测试添加数据 ，添加的动作是通用的，但数据上只是添加ID而已，数据非通用 */
    private void testAddGood() {
        ShoppingCartBiz.addGoodToCart("279457f3-4692-43bf-9676-fa9ab9155c38", "6");
        ShoppingCartBiz.addGoodToCart("95fbe11d-7303-4b9f-8ca4-537d06ce2f8a", "8");
        ShoppingCartBiz.addGoodToCart("8c6e52fb-d57c-45ee-8f05-50905138801b", "9");
        ShoppingCartBiz.addGoodToCart("7d6e52fb-d57c-45ee-8f05-50905138801d", "3");
        ShoppingCartBiz.addGoodToCart("7d6e52fb-d57c-45ee-8f05-50905138801e", "3");
        ShoppingCartBiz.addGoodToCart("7d6e52fb-d57c-45ee-8f05-50905138801f", "3");
        ShoppingCartBiz.addGoodToCart("7d6e52fb-d57c-45ee-8f05-50905138801g", "3");
        ShoppingCartBiz.addGoodToCart("7d6e52fb-d57c-45ee-8f05-50905138801h", "3");
    }


    private void updateListView(List<ShoppingCartBean> stringStringMap) {
        myExpandableListAdapter.setList(stringStringMap);
        myExpandableListAdapter.notifyDataSetChanged();
        mRootView.expandAllGroup(stringStringMap);
    }





    private Observable<List<ShoppingCartBean>> getCartsList(){
        return Observable.create(new Observable.OnSubscribe<List<ShoppingCartBean>>() {
            @Override
            public void call(Subscriber<? super List<ShoppingCartBean>> subscriber) {
                //Emit Data
                List<ShoppingCartBean> goodses=new ArrayList();
                List<ShoppingCartBean.Goods> goodses1=new ArrayList();
                for (int i=0;i<=10;i++){
                    ShoppingCartBean goods=new ShoppingCartBean();
                    ShoppingCartBean.Goods goods1=new ShoppingCartBean.Goods();
                    goods.setMerchantName("123123"+i);
                    goods1.setGoodsName(""+i);
                    goods1.setPrice("12");
                    goods1.setNumber(""+i);
                    goodses1.add(goods1);
                    goods.setGoods((ArrayList<ShoppingCartBean.Goods>) goodses1);
                    goodses.add(goods);
                }
                subscriber.onNext(goodses);
                subscriber.onCompleted();
            }
        });
    }
}
