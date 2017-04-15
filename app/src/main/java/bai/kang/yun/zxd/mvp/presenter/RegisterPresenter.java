package bai.kang.yun.zxd.mvp.presenter;

import android.app.Application;

import com.jess.arms.base.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.UiUtils;
import com.jess.arms.widget.imageloader.ImageLoader;

import javax.inject.Inject;

import bai.kang.yun.zxd.mvp.contract.RegisterContract;
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
public class RegisterPresenter extends BasePresenter<RegisterContract.Model, RegisterContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    private String name;
    private String email;
    private String pswd;
    private String rpswd;
    private String verification;
    @Inject
    public RegisterPresenter(RegisterContract.Model model, RegisterContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }
    public void register(){
        name=mRootView.getName();
        pswd=mRootView.getPsw();
        rpswd=mRootView.getRPsw();
        email=mRootView.getEmail();
        verification=mRootView.getVerification();
        if(name.isEmpty()){
            UiUtils.makeText("请输入用户名");
        }else if(pswd.isEmpty()){
            UiUtils.makeText("请输入密码");
        }else if(rpswd.isEmpty()){
            UiUtils.makeText("请输入确认密码");
        }else if(rpswd.equals(pswd)){
            UiUtils.makeText("两次密码输入不同");
        }else if(email.isEmpty()){
            UiUtils.makeText("请输入邮箱");
        }else if(verification.isEmpty()){
            UiUtils.makeText("请输入验证码");
        }else {
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
