package bai.kang.yun.zxd.mvp.presenter;

import android.app.Application;
import android.widget.SimpleAdapter;

import com.jess.arms.base.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxUtils;
import com.jess.arms.utils.UiUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.mvp.contract.FristContract;
import bai.kang.yun.zxd.mvp.model.entity.Goods;
import bai.kang.yun.zxd.mvp.ui.adapter.GoodsGridAdapter;
import bai.kang.yun.zxd.mvp.ui.adapter.RollViewpagerAdapter;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/4/12 0012.
 */
@ActivityScope
public class FristPresenter  extends BasePresenter<FristContract.Model, FristContract.View> {
    private RxErrorHandler mErrorHandler;
    private AppManager mAppManager;
    private Application mApplication;
    private List<Map<String,String>> UrlList= new ArrayList();
    private List<Goods> GoodsList= new ArrayList();
    RollViewpagerAdapter rollViewpagerAdapter;
    GoodsGridAdapter goodsGridAdapter;
    SimpleAdapter simpleadapter;
    String [] name={"商品分类","附近药店","健康问答","签到抽奖","男性","女性",
            "老人","儿童"};
    List<Map<String,Object>> list=new ArrayList<>();
    private boolean isFirst = true;
    private int preEndIndex;

    @Inject
    public FristPresenter(FristContract.Model model, FristContract.View rootView, RxErrorHandler handler
            , AppManager appManager, Application application) {
        super(model, rootView);
        this.mApplication = application;
        this.mErrorHandler = handler;
        this.mAppManager = appManager;
        rollViewpagerAdapter = new RollViewpagerAdapter(UrlList);
        goodsGridAdapter=new GoodsGridAdapter(GoodsList);
        simpleadapter=new SimpleAdapter(UiUtils.getContext(),list, R.layout.item_gridview,
                new String[]{"pt","name"},new int[]{R.id.pt,R.id.name});
        mRootView.setAdapter(rollViewpagerAdapter,simpleadapter,goodsGridAdapter);//设置Adapter


    }
    public void getGGwUrl(){
        List<String> urls=new ArrayList<>();
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492057604491&di=71f6ebba0c795ae4ce664eeea3021cce&imgtype=0&src=http%3A%2F%2Fpic.baike.soso.com%2Fp%2F20130705%2F20130705113951-882480559.jpg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492057604491&di=71f6ebba0c795ae4ce664eeea3021cce&imgtype=0&src=http%3A%2F%2Fpic.baike.soso.com%2Fp%2F20130705%2F20130705113951-882480559.jpg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492057604491&di=71f6ebba0c795ae4ce664eeea3021cce&imgtype=0&src=http%3A%2F%2Fpic.baike.soso.com%2Fp%2F20130705%2F20130705113951-882480559.jpg");
        mRootView.setImg(urls);
    }
    public void requestUrls() {
        mModel.getBannerUrl()
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .compose(RxUtils.bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(
                        new ErrorHandleSubscriber<Map<String,String>>(mErrorHandler) {
                    @Override
                    public void onNext(Map<String,String> stringStringMap) {
                            Map<String,String> string=new HashMap();
                            string.put("url","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492057604491&di=71f6ebba0c795ae4ce664eeea3021cce&imgtype=0&src=http%3A%2F%2Fpic.baike.soso.com%2Fp%2F20130705%2F20130705113951-882480559.jpg");
                            UrlList.add(string);

                        rollViewpagerAdapter.notifyDataSetChanged();

                    }
                });
        Map<String,String> string=new HashMap();
        string.put("url","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492057604491&di=71f6ebba0c795ae4ce664eeea3021cce&imgtype=0&src=http%3A%2F%2Fpic.baike.soso.com%2Fp%2F20130705%2F20130705113951-882480559.jpg");
        UrlList.add(string);

        rollViewpagerAdapter.notifyDataSetChanged();
    }
    public void getGoodsGrid(){

//           mModel.getGoodslist(123,isEvictCache)
        getGoodsList()
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.<List<Goods>>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(
                        new ErrorHandleSubscriber<List<Goods>>(mErrorHandler) {
                            @Override
                            public void onNext(List<Goods> goods) {
                                GoodsList.addAll(goods);
                                goodsGridAdapter.notifyDataSetChanged();}
                        });
    }
    public void setGrid(){
        for(int i=0;i<name.length;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("pt",R.mipmap.ic_launcher);
            map.put("name",name[i]);
            list.add(map);
        }
        simpleadapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mApplication = null;
    }
    private Observable<List<Goods>> getGoodsList(){
        return Observable.create(new Observable.OnSubscribe<List<Goods>>() {
            @Override
            public void call(Subscriber<? super List<Goods>> subscriber) {
                //Emit Data
                List<Goods> goodses=new ArrayList();
                for (int i=0;i<15;i++){
                    Goods goods=new Goods();
                    goods.setName(""+i);
                    goods.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492057604491&di=71f6ebba0c795ae4ce664eeea3021cce&imgtype=0&src=http%3A%2F%2Fpic.baike.soso.com%2Fp%2F20130705%2F20130705113951-882480559.jpg");
                    goodses.add(goods);

                }
                subscriber.onNext(goodses);
                subscriber.onCompleted();
            }
        });
    }
}
