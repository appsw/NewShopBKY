package bai.kang.yun.zxd.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jess.arms.utils.UiUtils;

import java.util.HashMap;

import javax.annotation.Nullable;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.di.component.DaggerLoginComponent;
import bai.kang.yun.zxd.di.module.LoginModule;
import bai.kang.yun.zxd.mvp.contract.LoginContract;
import bai.kang.yun.zxd.mvp.presenter.LoginPresenter;
import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;
import common.AppComponent;
import common.WEActivity;

import static com.jess.arms.utils.Preconditions.checkNotNull;

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

public class LoginActivity extends WEActivity<LoginPresenter> implements LoginContract.View,
        PlatformActionListener {

    @Nullable
    @BindView(R.id.login_name)
    EditText name;
    @BindView(R.id.login_password)
    EditText psaaword;
    @BindView(R.id.login_register)
    Button register;
    @BindView(R.id.btn_login)
    Button login;
    private static final int MSG_SMSSDK_CALLBACK = 1;
    private static final int MSG_AUTH_CANCEL = 2;
    private static final int MSG_AUTH_ERROR= 3;
    private static final int MSG_AUTH_COMPLETE = 4;
    private String smssdkAppkey;
    private String smssdkAppSecret;
    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent
                .builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this)) //请将LoginModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_login, null, false);
    }

    @Override
    protected void initData() {
        if(getIntent().getBundleExtra("data")!=null){
            Bundle bundle=getIntent().getBundleExtra("data");
            name.setText(bundle.getString("name"));
            psaaword.setText(bundle.getString("pswd"));
        }


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UiUtils.getContext(),RegisterActivity.class);
                UiUtils.startActivity(intent);
                killMyself();
//                mPresenter.re();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.Login();
            }
        });
    }
    @OnClick(R.id.im_wechat)
    public void wechatLogin(){
        UiUtils.makeText("暂未实现");
//        Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
//        authorize(wechat);
    }
    @OnClick(R.id.im_qq)
    public void qqLogin(){
        Platform qzone = ShareSDK.getPlatform(QQ.NAME);
        authorize(qzone);
    }
    private void authorize(Platform plat) {
        if (plat == null) {
            return;
        }
        plat.setPlatformActionListener(this);
        // true不使用SSO授权，false使用SSO授权
        plat.SSOSetting(false);
        //获取用户资料
        plat.showUser(null);
    }
    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }

    @Override
    public void showLoading() {
        loadingDialog.show();
    }

    @Override
    public void hideLoading() {
        loadingDialog.close();
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.SnackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        UiUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }


    @Override
    public String getPsw() {
        return psaaword.getText().toString();
    }

    @Override
    public String getName() {
        return name.getText().toString();
    }


    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        if (i == Platform.ACTION_USER_INFOR) {
            //判断指定平台是否已经完成授权
            if(platform.isAuthValid()) {
                String userId = platform.getDb().getUserId();
                Message msg = new Message();
                msg.what = MSG_AUTH_COMPLETE;
                msg.obj = new Object[] {platform.getName(), hashMap,userId};
                handler.sendMessage(msg);
            }

        }
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        if (i == Platform.ACTION_USER_INFOR) {
            handler.sendEmptyMessage(MSG_AUTH_ERROR);
        }
    }

    @Override
    public void onCancel(Platform platform, int i) {
        if (i == Platform.ACTION_USER_INFOR) {
            handler.sendEmptyMessage(MSG_AUTH_CANCEL);
        }
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what) {
                case MSG_AUTH_CANCEL: {
                    //取消授权
                    UiUtils.makeText("取消授权");
                } break;
                case MSG_AUTH_ERROR: {
                    //授权失败
                    UiUtils.makeText("授权失败");
                } break;
                case MSG_AUTH_COMPLETE: {
                    //授权成功
                    Object[] objs = (Object[]) msg.obj;
                    String platform = (String) objs[0];
                    HashMap<String, Object> res = (HashMap<String, Object>) objs[1];
                    String userId=(String) objs[2];
                    mPresenter.OauthLogin(platform.toLowerCase(),userId,res.get("nickname").toString(),res.get("figureurl_qq_1").toString());
                } break;

            }
        }
    };
    @OnClick(R.id.register_back)
    public void black(){
        killMyself();
    }

}
