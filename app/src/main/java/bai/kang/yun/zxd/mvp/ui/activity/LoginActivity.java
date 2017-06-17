package bai.kang.yun.zxd.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jess.arms.utils.UiUtils;

import javax.annotation.Nullable;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.di.component.DaggerLoginComponent;
import bai.kang.yun.zxd.di.module.LoginModule;
import bai.kang.yun.zxd.mvp.contract.LoginContract;
import bai.kang.yun.zxd.mvp.presenter.LoginPresenter;
import butterknife.BindView;
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

public class LoginActivity extends WEActivity<LoginPresenter> implements LoginContract.View {

    @Nullable
    @BindView(R.id.login_name)
    EditText name;
    @BindView(R.id.login_password)
    EditText psaaword;
    @BindView(R.id.login_register)
    TextView register;
    @BindView(R.id.btn_login)
    Button login;
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


}
