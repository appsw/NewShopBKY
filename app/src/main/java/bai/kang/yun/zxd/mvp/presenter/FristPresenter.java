package bai.kang.yun.zxd.mvp.presenter;

import android.app.Application;
import android.util.Log;
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
import bai.kang.yun.zxd.mvp.model.entity.Advertisement;
import bai.kang.yun.zxd.mvp.model.entity.Banner;
import bai.kang.yun.zxd.mvp.model.entity.Goods;
import bai.kang.yun.zxd.mvp.model.entity.ReturnGoods;
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
    private List<Banner.DataEntity> banners=new ArrayList<>();
    private List<ReturnGoods.DataEntity> GoodsList= new ArrayList();
    RollViewpagerAdapter rollViewpagerAdapter;
    GoodsGridAdapter goodsGridAdapter;
    SimpleAdapter simpleadapter;
    String [] name={"男性","女性",
            "老人","儿童","中成药","西成药","附近药店","健康问答"};
    int[] ic={R.mipmap.ic_nan,R.mipmap.ic_nv,
            R.mipmap.ic_lao,R.mipmap.ic_shao,
            R.mipmap.ic_zhong,R.mipmap.ic_xi,R.mipmap.ic_fu,
            R.mipmap.ic_wen};
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
        rollViewpagerAdapter = new RollViewpagerAdapter(banners);
        goodsGridAdapter=new GoodsGridAdapter(GoodsList);
        simpleadapter=new SimpleAdapter(UiUtils.getContext(),list, R.layout.item_gridview,
                new String[]{"pt","name"},new int[]{R.id.pt,R.id.name});
        mRootView.setAdapter(rollViewpagerAdapter,simpleadapter,goodsGridAdapter);//设置Adapter


    }
    public void getGGwUrl(){
        mModel.getAD()
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .compose(RxUtils.bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new ErrorHandleSubscriber<Advertisement>(mErrorHandler) {
                            @Override
                            public void onNext(Advertisement advertisement) {
                                if(advertisement.getStatus()==1){
                                    mRootView.setImg(advertisement.getData());
                                }
                            }
                        });

    }
    public void requestUrls() {
        mModel.getBannerUrl()
                 .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .compose(RxUtils.bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(
                        new ErrorHandleSubscriber<Banner>(mErrorHandler) {
                    @Override
                    public void onNext(Banner banner) {
                        if(banner.getStatus()==1){
                            banners.addAll(banner.getData());
                            rollViewpagerAdapter.notifyDataSetChanged();
                        }

                        Log.e("state",banner.getData().toString()+"");


                    }
                });
    }
    public void getGoodsGrid(){

           mModel.getGoodsGrid()
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.<ReturnGoods>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(
                        new ErrorHandleSubscriber<ReturnGoods>(mErrorHandler) {
                            @Override
                            public void onNext(ReturnGoods goods) {
                                if(goods.getStatus()==1){
                                    GoodsList.addAll(goods.getData());
                                    goodsGridAdapter.notifyDataSetChanged();
                                }
                                }
                        });
    }
    public void setGrid(){
        for(int i=0;i<name.length;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("pt",ic[i]);
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
                for (int i=0;i<14;i++){
                    Goods goods=new Goods();
                    goods.setName("复方氨酚烷胺胶囊");
                    goods.setPrice("￥1"+i);
                    goods.setImageUrl("http://c1.yaofangwang.net/Common/Upload/Medicine/280/280759/dd5ff363-cb6f-4570-be4d-5777cc9c99019495.jpg_300x300.jpg");
                    goodses.add(goods);

                }
                subscriber.onNext(goodses);
                subscriber.onCompleted();
            }
        });
    }
    private Observable<List<Map<String,String>>> getBanner(){
        return Observable.create(new Observable.OnSubscribe<List<Map<String,String>>>() {
            @Override
            public void call(Subscriber<? super List<Map<String,String>>> subscriber) {
                //Emit Data
                List<Map<String,String>> maps=new ArrayList<Map<String, String>>();

                Map<String,String> string=new HashMap();
                string.put("url","https://c1.yaofangwang.net/Common/Upload/Imggg/20170329/8b1024b8-c8df-44d5-8e23-e27a7ed87ad54415.jpg");
                maps.add(string);


                Map<String,String> string1=new HashMap();
                string1.put("url","https://c1.yaofangwang.net/Common/Upload/Imggg/20170417/1324b133-f405-4b52-8a0b-6159a0f5482f5769.jpg");
                maps.add(string1);


                Map<String,String> string2=new HashMap();
                string2.put("url","https://c1.yaofangwang.net/Common/Upload/Imggg/20170426/564a1746-ad8b-4901-b4c8-e8e165855ec26803.jpg");
                maps.add(string2);


                Map<String,String> string3=new HashMap();
                string3.put("url","http://img.800pharm.com/images/20170418/20170418152104_197.jpg");
                maps.add(string3);
                subscriber.onNext(maps);
                subscriber.onCompleted();
            }
        });
    }
}
