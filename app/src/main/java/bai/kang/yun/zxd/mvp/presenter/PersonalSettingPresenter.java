package bai.kang.yun.zxd.mvp.presenter;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.jess.arms.base.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxUtils;
import com.jess.arms.utils.UiUtils;
import com.jess.arms.widget.imageloader.ImageLoader;

import java.io.File;

import javax.inject.Inject;

import bai.kang.yun.zxd.mvp.contract.PersonalSettingContract;
import bai.kang.yun.zxd.mvp.model.entity.ReturnDeleteAdd;
import bai.kang.yun.zxd.mvp.model.entity.ReturnSetAdd;
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
 * Created by Administrator on 2017/5/27 0027.
 */

@ActivityScope
public class PersonalSettingPresenter extends BasePresenter<PersonalSettingContract.Model, PersonalSettingContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;
    SharedPreferences config;

    @Inject
    public PersonalSettingPresenter(PersonalSettingContract.Model model, PersonalSettingContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
        config=application.getSharedPreferences("config", Context.MODE_PRIVATE);
    }
    public void uploadHeadPic(File file){
        mModel.uploadFile(config.getInt("id",0),config.getString("salt","0"),file)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.<ReturnSetAdd>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(
                        new ErrorHandleSubscriber<ReturnSetAdd>(mErrorHandler) {
                            @Override
                            public void onNext(ReturnSetAdd category) {
                                if(category.getStatus()==1){
                                    UiUtils.makeText(category.getMessage());

                                }else {
                                    UiUtils.makeText(category.getMessage());
                                }
                            }
                        });
    }
    public void ReSetPswd(String pswd,String newpswd){
        mModel.ReSetPswd(config.getInt("id",0),config.getString("salt","0"),newpswd)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.<ReturnDeleteAdd>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(
                        new ErrorHandleSubscriber<ReturnDeleteAdd>(mErrorHandler) {
                            @Override
                            public void onNext(ReturnDeleteAdd category) {
                                if(category.getStatus()==1){
                                    UiUtils.makeText(category.getMessage());
                                    config.edit().putBoolean("isLog",false).commit();
                                    mRootView.killMyself();
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
