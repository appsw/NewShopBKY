package bai.kang.yun.zxd.mvp.presenter;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.jess.arms.base.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxUtils;
import com.jess.arms.widget.imageloader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import bai.kang.yun.zxd.mvp.contract.MakeOrderContract;
import bai.kang.yun.zxd.mvp.model.entity.Address;
import bai.kang.yun.zxd.mvp.model.entity.CarShop;
import bai.kang.yun.zxd.mvp.ui.adapter.MakeOrderListAdapter;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import rx.android.schedulers.AndroidSchedulers;


/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */


/**
 * Created by Administrator on 2017/6/7 0007.
 */

@ActivityScope
public class MakeOrderPresenter extends BasePresenter<MakeOrderContract.Model, MakeOrderContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;
    private MakeOrderListAdapter makeOrderListAdapter;
    List<CarShop> mListGoods = new ArrayList<CarShop>();
    private SharedPreferences config;
    @Inject
    public MakeOrderPresenter(MakeOrderContract.Model model, MakeOrderContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
        makeOrderListAdapter=new MakeOrderListAdapter(application,mListGoods);
        mRootView.SetAdapter(makeOrderListAdapter);
        config=application.getSharedPreferences("config", Context.MODE_PRIVATE);
    }
    public void GetAdd(){
        if(config.getBoolean("isAdd",false)){
            Address address=new Address();
            address.setName(config.getString("add_name",""));
            address.setId(config.getInt("add_id",0));
            address.setNumber_phone(config.getString("add_tel",""));
            address.setAdd_deils(config.getString("add_deils",""));
            mRootView.SetAdd(address);
        }

    }

    public void GetGoodsList(){
        mModel.SelectGoodsList()
                .subscribeOn(/*Schedulers.io()*/AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .compose(RxUtils.<List<CarShop>>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(new ErrorHandleSubscriber<List<CarShop>>(mErrorHandler) {
                    @Override
                    public void onNext(List<CarShop> shops) {
                        Log.e("shop.size",shops.size()+"");
                        mListGoods.addAll(shops);
                        makeOrderListAdapter.notifyDataSetChanged();
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

}
