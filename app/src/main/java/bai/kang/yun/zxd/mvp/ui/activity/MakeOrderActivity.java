package bai.kang.yun.zxd.mvp.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jess.arms.utils.UiUtils;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.di.component.DaggerMakeOrderComponent;
import bai.kang.yun.zxd.di.module.MakeOrderModule;
import bai.kang.yun.zxd.mvp.contract.MakeOrderContract;
import bai.kang.yun.zxd.mvp.model.entity.Address;
import bai.kang.yun.zxd.mvp.presenter.MakeOrderPresenter;
import butterknife.BindView;
import butterknife.OnClick;
import common.AppComponent;
import common.WEActivity;
import rx.Observable;

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
 * Created by Administrator on 2017/6/7 0007.
 */

public class MakeOrderActivity extends WEActivity<MakeOrderPresenter> implements MakeOrderContract.View {


    @BindView(R.id.list_orderlist)
    ListView listView;
    @BindView(R.id.im_choseadd)
    ImageView choseadd;
    @BindView(R.id.textView7)
    TextView tv_name;
    @BindView(R.id.tv_tel)
    TextView tv_tel;
    @BindView(R.id.tv_add)
    TextView tv_add;
    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMakeOrderComponent
                .builder()
                .appComponent(appComponent)
                .makeOrderModule(new MakeOrderModule(this)) //请将MakeOrderModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.GetAdd();
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_makeorder, null, false);
    }

    @Override
    protected void initData() {
        mPresenter.GetGoodsList();

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
    @OnClick(R.id.im_choseadd)
    void chose(){
        Intent intent=new Intent(this,AddressListActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:

                break;
            default:
                break;
        }
    }


    @Override
    public void SetAdapter(BaseAdapter baseAdapter) {
        listView.setAdapter(baseAdapter);
    }

    @Override
    public void SetAdd(Address address) {
        Observable.just(address.getName()).subscribe(RxTextView.text(tv_name));
        Observable.just(address.getNumber_phone()).subscribe(RxTextView.text(tv_tel));
        Observable.just(address.getAdd_deils()).subscribe(RxTextView.text(tv_add));
    }
}
