package bai.kang.yun.zxd.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.jess.arms.utils.UiUtils;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.di.component.DaggerAddressListComponent;
import bai.kang.yun.zxd.di.module.AddressListModule;
import bai.kang.yun.zxd.mvp.contract.AddressListContract;
import bai.kang.yun.zxd.mvp.presenter.AddressListPresenter;
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
 * Created by Administrator on 2017/5/19 0019.
 */

public class AddressListActivity extends WEActivity<AddressListPresenter> implements AddressListContract.View {


    @BindView(R.id.register_back)
    ImageView im_back;
    @BindView(R.id.address_listv)
    ListView listView;
    @BindView(R.id.add_address_btn)
    Button btn_add;
    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerAddressListComponent
                .builder()
                .appComponent(appComponent)
                .addressListModule(new AddressListModule(this)) //请将AddressListModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_addresslist, null, false);
    }

    @Override
    protected void initData() {
        mPresenter.initData();
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

//    @OnClick(R.id.add_address_btn)
    @Override
    public void killMyself() {
        finish();
    }

    @OnClick(R.id.add_address_btn)
    void add(){
        mPresenter.add();
        Intent intent=new Intent(this,AddressDetailActivity.class);
        Bundle bundle=new Bundle();
        bundle.putBoolean("add",true);
        intent.putExtra("data",bundle);
        startActivityForResult(intent,3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void setAdapter(BaseAdapter adapter) {
        listView.setAdapter(adapter);
    }
}
