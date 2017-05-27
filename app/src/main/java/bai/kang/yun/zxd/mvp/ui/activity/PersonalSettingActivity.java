package bai.kang.yun.zxd.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jess.arms.utils.UiUtils;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.di.component.DaggerPersonalSettingComponent;
import bai.kang.yun.zxd.di.module.PersonalSettingModule;
import bai.kang.yun.zxd.mvp.contract.PersonalSettingContract;
import bai.kang.yun.zxd.mvp.presenter.PersonalSettingPresenter;
import bai.kang.yun.zxd.mvp.ui.dialog.ResetPSWD;
import bai.kang.yun.zxd.mvp.ui.view.UIAlertView;
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
 * Created by Administrator on 2017/5/27 0027.
 */

public class PersonalSettingActivity extends WEActivity<PersonalSettingPresenter> implements PersonalSettingContract.View {
    SharedPreferences config;
    @BindView(R.id.phone_num_txt)
    TextView tv_phone;
    @BindView(R.id.nickname_txtv)
    TextView tv_nickname;
    @BindView(R.id.sex_txtv)
    TextView tv_sex;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerPersonalSettingComponent
                .builder()
                .appComponent(appComponent)
                .personalSettingModule(new PersonalSettingModule(this)) //请将PersonalSettingModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected View initView() {
        config=this.getApplication().getSharedPreferences("config", Context.MODE_PRIVATE);

        return LayoutInflater.from(this).inflate(R.layout.activity_personal, null, false);
    }

    @Override
    protected void initData() {
        tv_nickname.setText(config.getString("nick_name",null));
        tv_phone.setText(config.getString("mobile",null));
        tv_sex.setText(config.getString("sex","男"));
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


    @OnClick(R.id.age_txtv)
    void ReSetpswd(){
        ResetPSWD dialog = new ResetPSWD(PersonalSettingActivity.this);
        dialog.setClicklistener(new ResetPSWD.ClickListenerInterface() {
            @Override
            public void doLeft() {
                dialog.dismiss();
            }

            @Override
            public void doRight(String ed1, String ed2, String ed3) {
                if(ed1.isEmpty()){
                    UiUtils.makeText("请输入密码");
                }else if(ed2.isEmpty()){
                    UiUtils.makeText("请输入新密码");
                }else if(ed3.isEmpty()){
                    UiUtils.makeText("请再次输入密码");
                }else if(!ed2.equals(ed3)){
                    UiUtils.makeText("两次输入密码不相同");
                }else {
                    mPresenter.ReSetPswd(ed1,ed2);
                    dialog.dismiss();
                }


            }
        });
        dialog.show();
    }
    @OnClick(R.id.btn_logout)
    void logout(){
        final UIAlertView delDialog = new UIAlertView(this, "温馨提示", "确认退出登录吗?",
                "取消", "确定");
        delDialog.show();

        delDialog.setClicklistener(new UIAlertView.ClickListenerInterface() {

            public void doLeft() {
                delDialog.dismiss();
            }

            public void doRight() {
                config.edit().putBoolean("isLog",false).commit();

                delDialog.dismiss();
                killMyself();
            }}
        );

    }
}
