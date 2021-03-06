package bai.kang.yun.zxd.mvp.presenter;

import android.app.Application;

import com.jess.arms.base.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxUtils;
import com.jess.arms.widget.imageloader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import bai.kang.yun.zxd.app.utils.ActivityManger;
import bai.kang.yun.zxd.mvp.contract.CarContract;
import bai.kang.yun.zxd.mvp.model.entity.CarShop;
import bai.kang.yun.zxd.mvp.ui.adapter.MyExpandableListAdapter;
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
 * Created by Administrator on 2017/4/21 0021.
 */

@ActivityScope
public class CarPresenter extends BasePresenter<CarContract.Model, CarContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;
    MyExpandableListAdapter myExpandableListAdapter;
    List<CarShop> mListGoods = new ArrayList<CarShop>();
    @Inject
    public CarPresenter(CarContract.Model model, CarContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;

        myExpandableListAdapter=new MyExpandableListAdapter(ActivityManger.getAvtivity());
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
    public void requestShoppingCartList() {

        mListGoods.clear();

        mModel.ShoppingCartList()
                .subscribeOn(/*Schedulers.io()*/AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .compose(RxUtils.<List<CarShop>>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(new ErrorHandleSubscriber<List<CarShop>>(mErrorHandler) {
                            @Override
                            public void onNext(List<CarShop> shops) {
                                mListGoods.addAll(shops);
                                updateListView(mListGoods);
                            }
                        });
    }



    private void updateListView(List<CarShop> stringStringMap) {
        myExpandableListAdapter.setList(stringStringMap);
        myExpandableListAdapter.notifyDataSetChanged();
        mRootView.expandAllGroup(stringStringMap);
    }






}
