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

import bai.kang.yun.zxd.mvp.contract.FindContract;
import bai.kang.yun.zxd.mvp.model.entity.ReturnCategory;
import bai.kang.yun.zxd.mvp.model.entity.SPCategory;
import bai.kang.yun.zxd.mvp.ui.adapter.FristListAdapter;
import bai.kang.yun.zxd.mvp.ui.adapter.GoodsCategoryListAdapter;
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
 * Created by Administrator on 2017/4/18 0018.
 */

@ActivityScope
public class FindPresenter extends BasePresenter<FindContract.Model, FindContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;
    private List<ReturnCategory.DataEntity> list1= new ArrayList();
    private List<ReturnCategory.DataEntity> list2= new ArrayList();
    private List<ReturnCategory.DataEntity> grid3= new ArrayList();
    FristListAdapter goodsCategoryGridAdapter;
    GoodsCategoryListAdapter goodsCategoryListAdapter;
    private String[] names={"中西药品","养生保健","医疗器械","计生用品","中药饮片","美容护肤"};
    @Inject
    public FindPresenter(FindContract.Model model, FindContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;

        goodsCategoryGridAdapter=new FristListAdapter(mApplication,list2);
        goodsCategoryListAdapter=new GoodsCategoryListAdapter(list1);
        mRootView.setAdapter(goodsCategoryListAdapter,goodsCategoryGridAdapter);
    }
    public void getCategorylist(){
        mModel.getCategory(0)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.<ReturnCategory>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(
                        new ErrorHandleSubscriber<ReturnCategory>(mErrorHandler) {
                            @Override
                            public void onNext(ReturnCategory category) {
                                if(category.getStatus()==1){
                                    list1.addAll(category.getData());
                                    goodsCategoryListAdapter.notifyDataSetChanged();
                                }else {
                                    UiUtils.makeText("网络错误");
                                }
                                }
                        });
    }
    public void setRight2(int id){
        int ID;
        if(id==999){
             ID=118;
        }else {
            ID=list1.get(id).getId();
        }

        list2.clear();
        grid3.clear();
        mModel.getCategory(ID)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.<ReturnCategory>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(
                        new ErrorHandleSubscriber<ReturnCategory>(mErrorHandler) {
                            @Override
                            public void onNext(ReturnCategory goods) {
                                if(goods.getStatus()==1){
                                    list2.addAll(goods.getData());
                                    for(int i=0;i<list2.size();i++){
                                        ReturnCategory.DataEntity dataEntity=list2.get(i);
                                        setRight3(dataEntity.getId(),i);
                                    }
//                                    goodsCategoryGridAdapter.notifyDataSetChanged();
                                }

                            }
                        });

    }

    public void setRight3(int id,int pos){
        mModel.getCategory(id)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.<ReturnCategory>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(
                        new ErrorHandleSubscriber<ReturnCategory>(mErrorHandler) {
                            @Override
                            public void onNext(ReturnCategory goods) {
                                if(pos<list2.size()){
                                    if(goods.getStatus()==1){
                                        list2.get(pos).getGrid3().addAll(goods.getData());
                                        goodsCategoryGridAdapter.notifyDataSetChanged();
                                    }
                                }else {
                                    if(goods.getStatus()==1){
                                        list2.get(pos).getGrid3().addAll(goods.getData());
                                        goodsCategoryGridAdapter.notifyDataSetChanged();
//                                        goodsCategoryGridAdapter=new FristListAdapter(mApplication,list2);
//                                        mRootView.setAdapter(goodsCategoryListAdapter,goodsCategoryGridAdapter);
                                    }
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
    private Observable<List<SPCategory>> getGoodsList(){
        return Observable.create(new Observable.OnSubscribe<List<SPCategory>>() {
            @Override
            public void call(Subscriber<? super List<SPCategory>> subscriber) {
                //Emit Data
                List<SPCategory> goodses=new ArrayList();
                for (int i=0;i<=10;i++){
                    SPCategory goods=new SPCategory();
                    goods.setName(""+i);
                    goods.setImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492057604491&di=71f6ebba0c795ae4ce664eeea3021cce&imgtype=0&src=http%3A%2F%2Fpic.baike.soso.com%2Fp%2F20130705%2F20130705113951-882480559.jpg");
                    goods.setSubCategory(goodses);
                    goodses.add(goods);
                }
                subscriber.onNext(goodses);
                subscriber.onCompleted();
            }
        });
    }
    private Observable<List<SPCategory>> getLeft(){
        return Observable.create(new Observable.OnSubscribe<List<SPCategory>>() {
            @Override
            public void call(Subscriber<? super List<SPCategory>> subscriber) {
                //Emit Data
                List<SPCategory> Categorys=new ArrayList();
                for (int i=0;i<names.length;i++){
                    SPCategory Category=new SPCategory();
                    Category.setName(names[i]);
                    Category.setId(i);
                    Categorys.add(Category);
                }
                subscriber.onNext(Categorys);
                subscriber.onCompleted();
            }
        });
    }

}
