package bai.kang.yun.zxd.mvp.presenter;

import android.app.Application;
import android.util.Log;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.jess.arms.base.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.UiUtils;
import com.jess.arms.widget.imageloader.ImageLoader;

import javax.inject.Inject;

import bai.kang.yun.zxd.mvp.contract.LoginContract;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;


/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */


/**
 * Created by Administrator on 2017/4/14 0014.
 */

@ActivityScope
public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;
    String name,psw;

    @Inject
    public LoginPresenter(LoginContract.Model model, LoginContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }
    public void Login(){
        name=mRootView.getName();
        psw=mRootView.getPsw();
        if(name.isEmpty()){
            UiUtils.makeText("请输入用户名");
        }else if(psw.isEmpty()){
            UiUtils.makeText("请输入密码");
        }else {
            EMClient.getInstance().login(name, psw, new EMCallBack() {
                @Override
                public void onSuccess() {
                    Log.e("ok","ok");
                   mRootView.showLoading();
                }

                @Override
                public void onError(int i, String s) {
                    EMClient.getInstance().logout(true);
                    Log.e("err",""+s);
                }

                @Override
                public void onProgress(int i, String s) {

                }
            });

        }
    }

    public void re(){
        name=mRootView.getName();
        psw=mRootView.getPsw();
        if(name.isEmpty()){
            UiUtils.makeText("请输入用户名");
        }else if(psw.isEmpty()){
            UiUtils.makeText("请输入密码");
        }else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        EMClient.getInstance().createAccount(name,psw);
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                        Log.e("sing_up","注册失败");
                    }
                }
            }).start();

            UiUtils.makeText("ok");
        }
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
