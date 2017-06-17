package bai.kang.yun.zxd.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.jess.arms.utils.UiUtils;

import java.util.List;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.di.component.DaggerAddressListComponent;
import bai.kang.yun.zxd.di.module.AddressListModule;
import bai.kang.yun.zxd.mvp.contract.AddressListContract;
import bai.kang.yun.zxd.mvp.model.entity.ReturnAddress;
import bai.kang.yun.zxd.mvp.presenter.AddressListPresenter;
import bai.kang.yun.zxd.mvp.ui.Listener.AddEditListener;
import bai.kang.yun.zxd.mvp.ui.adapter.AddressListAdapter;
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
 * Created by Administrator on 2017/5/19 0019.
 */

public class AddressListActivity extends WEActivity<AddressListPresenter> implements AddressListContract.View,
        OnClickListener {


    @BindView(R.id.register_back)
    ImageView im_back;
    @BindView(R.id.address_listv)
    ListView listView;
    @BindView(R.id.add_address_btn)
    Button btn_add;
    private int type;
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
        type=getIntent().getIntExtra("type",0);
        return LayoutInflater.from(this).inflate(R.layout.activity_addresslist, null, false);
    }

    @Override
    protected void initData() {
        mPresenter.initData();
        mPresenter.Request(false);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(type==1){
                    AddressListAdapter addressListAdapter= (AddressListAdapter) listView.getAdapter();
                    List<ReturnAddress.ItemsEntity> addresses=addressListAdapter.getAddresses();
                    ReturnAddress.ItemsEntity itemsEntity=addresses.get(position);
                    Intent intent=new Intent();
                    intent.putExtra("name",itemsEntity.getReal_name());
                    intent.putExtra("id",itemsEntity.getId());
                    intent.putExtra("tel",itemsEntity.getPhone());
                    intent.putExtra("add",itemsEntity.getAddress());
                    setResult(1,intent);
                    killMyself();
                }else
                    return ;
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
        mPresenter.Request(true);
    }

    @Override
    public void setAdapter(BaseAdapter adapter) {
        listView.setAdapter(adapter);
        ((AddressListAdapter)adapter).setAddEditListener(new AddEditListener() {
            @Override
            public void edit(int position) {
                ReturnAddress.ItemsEntity address=((AddressListAdapter)adapter).getAddresses().get(position);
                Intent intent=new Intent(AddressListActivity.this,AddressDetailActivity.class);
                Bundle bundle=new Bundle();
                bundle.putBoolean("add",false);
                bundle.putInt("id",address.getId());
                bundle.putString("name",address.getReal_name());
                bundle.putString("phone",address.getPhone());
                bundle.putString("address",address.getAddress());
                intent.putExtra("data",bundle);
                startActivityForResult(intent,3);
//                UiUtils.makeText("编辑第"+position+"个");
            }

            @Override
            public void delect(int position) {
                ReturnAddress.ItemsEntity address=((AddressListAdapter)adapter).getAddresses().get(position);
                int id=address.getId();
                final UIAlertView delDialog = new UIAlertView(AddressListActivity.this, "温馨提示", "确认删除该地址吗?",
                        "取消", "确定");
                delDialog.show();

                delDialog.setClicklistener(new UIAlertView.ClickListenerInterface() {

                    public void doLeft() {
                                                   delDialog.dismiss();
                                               }

                    public void doRight() {
                        mPresenter.delete(id);
                        delDialog.dismiss();
                    }}
                );
//                UiUtils.makeText("删除第"+position+"个");
            }

            @Override
            public void chose(int position) {
                ReturnAddress.ItemsEntity address=((AddressListAdapter)adapter).getAddresses().get(position);

                mPresenter.SetDefault(address);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.address_delete_btn:
                UiUtils.makeText("address_delete_btn");
                break;
            default:
                UiUtils.makeText("其他地方");
                break;
        }
    }
}
