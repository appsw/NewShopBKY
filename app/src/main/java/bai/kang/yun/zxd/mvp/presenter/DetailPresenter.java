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

import bai.kang.yun.zxd.mvp.contract.DetailContract;
import bai.kang.yun.zxd.mvp.model.entity.CarGoods;
import bai.kang.yun.zxd.mvp.model.entity.ReturnComment;
import bai.kang.yun.zxd.mvp.model.entity.ReturnDetail;
import bai.kang.yun.zxd.mvp.ui.adapter.CommentsListAdapter;
import bai.kang.yun.zxd.mvp.ui.adapter.DetailImgAdapter;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
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
 * Created by Administrator on 2017/4/15 0015.
 */

@ActivityScope
public class DetailPresenter extends BasePresenter<DetailContract.Model, DetailContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;
    private List<ReturnDetail.ImgEntity> list;
    private List<ReturnComment.ItemsEntity> commentlist;
    private DetailImgAdapter detailImgAdapter;
    private CommentsListAdapter commentsListAdapter;

    @Inject
    public DetailPresenter(DetailContract.Model model, DetailContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
        list=new ArrayList<>();
        commentlist=new ArrayList<>();
        detailImgAdapter=new DetailImgAdapter(list);
        commentsListAdapter=new CommentsListAdapter(commentlist);
        mRootView.setAdapter(detailImgAdapter,commentsListAdapter);
    }
    public void getDetail(int id){
        mRootView.showLoading();
        mModel.getGoodsDetail(id)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.<ReturnDetail>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(
                        new ErrorHandleSubscriber<ReturnDetail>(mErrorHandler) {
                            @Override
                            public void onNext(ReturnDetail category) {
                                mRootView.hideLoading();
                                if(category.getStatus()==1){
                                    mRootView.setDetail(category);
                                    if(category.getSingle().getImgList().size()!=0){
                                        list.addAll(category.getSingle().getImgList());
                                    }else {
                                        ReturnDetail.ImgEntity imgEntity=new ReturnDetail.ImgEntity();
                                        imgEntity.setImg_url(category.getSingle().getGoods().getImageurl());
                                        list.add(imgEntity);
                                    }
                                    detailImgAdapter.notifyDataSetChanged();
                                }else {
                                    UiUtils.makeText(category.getMessage());
                                }
                            }
                        });
    }
    public void addToCar(CarGoods goods){
        mModel.addGoods(goods)
                .subscribeOn(/*Schedulers.io()*/AndroidSchedulers.mainThread())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber(mErrorHandler) {
                    @Override
                    public void onNext(Object o) {
                        mRootView.showMessage("成功加入购物车！");
                    }
                });

    }

    public void getComment(int id){
        mRootView.showLoading();
        mModel.getShopComment(id,1)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.<ReturnComment>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(
                        new ErrorHandleSubscriber<ReturnComment>(mErrorHandler) {
                            @Override
                            public void onNext(ReturnComment category) {
                                mRootView.hideLoading();
                                if(category.getStatus()==1){
                                    commentlist.addAll(category.getPage_data().getItems());
                                    commentsListAdapter.notifyDataSetChanged();
                                }else {
                                    UiUtils.makeText(category.getMessage());
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

}
