package bai.kang.yun.zxd.mvp.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.jess.arms.utils.UiUtils;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.app.utils.Code;
import bai.kang.yun.zxd.di.component.DaggerRegisterComponent;
import bai.kang.yun.zxd.di.module.RegisterModule;
import bai.kang.yun.zxd.mvp.contract.RegisterContract;
import bai.kang.yun.zxd.mvp.presenter.RegisterPresenter;
import butterknife.BindView;
import butterknife.OnClick;
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

public class RegisterActivity extends WEActivity<RegisterPresenter> implements RegisterContract.View {


    @BindView(R.id.register_name)
    EditText name;
    @BindView(R.id.register_email)
    EditText email;
    @BindView(R.id.register_password1)
    EditText pswd;
    @BindView(R.id.register_password2)
    EditText rpswd;
    @BindView(R.id.register_phone_yzm)
    EditText phone_yzm;
    @BindView(R.id.et_yzm)
    EditText verification;
    @BindView(R.id.im_yzm)
    ImageView in_yzm;

    @BindView(R.id.btn_send)
    Button btn_send;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerRegisterComponent
                .builder()
                .appComponent(appComponent)
                .registerModule(new RegisterModule(this)) //请将RegisterModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_register, null, false);
    }

    @Override
    protected void initData() {
        mPresenter.setyzm();
    }
    @OnClick(R.id.btn_send)
    void send(){
        mPresenter.send();
        Log.e("code",""+Code.getInstance().getCode());
    }
    @OnClick(R.id.im_yzm)
    void setyzm(){
        mPresenter.setyzm();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

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
    public String getName() {
        return name.getText().toString();
    }

    @Override
    public String getPsw() {
        return pswd.getText().toString();
    }

    @Override
    public String getRPsw() {
        return rpswd.getText().toString();
    }

    @Override
    public String getEmail() {
        return email.getText().toString();
    }

    @Override
    public String getVerification() {
        return verification.getText().toString();
    }

    @Override
    public String getPhoneYzm() {
        return phone_yzm.getText().toString();
    }

    @Override
    public void setYZM(Drawable drawable) {
        in_yzm.setBackground(drawable);
    }

    @Override
    public void setSendBtngray() {
        btn_send.setBackgroundResource(R.color.darkgray);
        btn_send.setEnabled(false);
    }

    @Override
    public void setSendBtngreen() {
        btn_send.setBackgroundResource(R.color.green);
        btn_send.setEnabled(true);
    }

    @OnClick(R.id.register_register)
    void register(){
        mPresenter.register();
    }
}
