package bai.kang.yun.zxd.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

import com.jess.arms.utils.UiUtils;

import java.util.List;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.di.component.DaggerAddressDetailComponent;
import bai.kang.yun.zxd.di.module.AddressDetailModule;
import bai.kang.yun.zxd.mvp.contract.AddressDetailContract;
import bai.kang.yun.zxd.mvp.model.entity.ReturnRegion;
import bai.kang.yun.zxd.mvp.model.entity.SetAddress;
import bai.kang.yun.zxd.mvp.presenter.AddressDetailPresenter;
import bai.kang.yun.zxd.mvp.ui.adapter.SPAdapter;
import butterknife.BindView;
import butterknife.OnClick;
import common.AppComponent;
import common.WEActivity;

import static bai.kang.yun.zxd.R.id.checkBox2;
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
 * Created by Administrator on 2017/5/25 0025.
 */

public class AddressDetailActivity extends WEActivity<AddressDetailPresenter> implements AddressDetailContract.View {


    private boolean isNew=true;
    private boolean istype=true;
    SharedPreferences config;
    private Bundle bundle;
    private String sheng_name;
    private int sheng_id;
    private String shi_name;
    private int shi_id;
    private String qu_name;
    private int qu_id;
    @BindView(R.id.consignee_name_edtv)
    EditText et_name;
    @BindView(R.id.consignee_mobile_edtv)
    EditText et_mobil;
    @BindView(R.id.consignee_address_edtv)
    EditText et_address;

    @BindView(checkBox2)
    CheckBox cb_type;
    @BindView(R.id.sp_1)
    Spinner sp_1;
    @BindView(R.id.sp_2)
    Spinner sp_2;
    @BindView(R.id.sp_3)
    Spinner sp_3;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerAddressDetailComponent
                .builder()
                .appComponent(appComponent)
                .addressDetailModule(new AddressDetailModule(this)) //请将AddressDetailModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected View initView() {
        config=this.getApplication().getSharedPreferences("config", Context.MODE_PRIVATE);
        bundle=getIntent().getBundleExtra("data");
        isNew=bundle.getBoolean("add");
        return LayoutInflater.from(this).inflate(R.layout.activity_adds, null, false);
    }

    @Override
    protected void initData() {
        cb_type.setChecked(istype);
        cb_type.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                istype=isChecked;
            }
        });
        if(!isNew){
            setData();
        }
        mPresenter.initSP(0,1);
    }
    public void setData(){
        String name=bundle.getString("");
        String mobile=bundle.getString("");
        String address=bundle.getString("");
        int Province_id=bundle.getInt("");
        int City_id=bundle.getInt("");
        int Area_id=bundle.getInt("");
        et_name.setText(name);
        et_mobil.setText(mobile);
        et_address.setText(address);
    }

    @OnClick(R.id.submit_btn)
    void save(){
        String name=et_name.getText().toString().trim();
        String mobile=et_mobil.getText().toString().trim();
        String address=et_address.getText().toString().trim();
        if(name.isEmpty()){
            UiUtils.makeText("请输入收货人");
        }else if(mobile.isEmpty()){
            UiUtils.makeText("请输入手机号");
        }else if(address.isEmpty()){
            UiUtils.makeText("请输入详细地址");
        }else if(sheng_name.isEmpty()){
            UiUtils.makeText("请选择省份");
        }else if(shi_name.isEmpty()){
            UiUtils.makeText("请输入城市");
        }else if(qu_name.isEmpty()){
            UiUtils.makeText("请输入区/县");
        } else {
            SetAddress setAddress=new SetAddress();
//            setAddress.setId(0);
            setAddress.setReal_name(name);
            setAddress.setPhone(mobile);
            setAddress.setUser_id(config.getInt("id",0));
            setAddress.setProvince_id(sheng_id);
            setAddress.setCity_id(shi_id);
            setAddress.setArea_id(qu_id);
            if(istype)
                setAddress.setIsdefault(1);
            else
                setAddress.setIsdefault(0);
            setAddress.setAddress(address);
            mPresenter.save(setAddress.getMapParams());
        }
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
    public void setAdapter(BaseAdapter adapter1, BaseAdapter adapter2, BaseAdapter adapter3) {
        sp_1.setAdapter(adapter1);
        sp_2.setAdapter(adapter2);
        sp_3.setAdapter(adapter3);
        sp_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<ReturnRegion.DataEntity> list=((SPAdapter)adapter1).getList();
                ReturnRegion.DataEntity entity=list.get(position);
                mPresenter.initSP(entity.getRegionid(),2);
                sheng_name=entity.getRegionname();
                sheng_id=entity.getRegionid();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<ReturnRegion.DataEntity> list=((SPAdapter)adapter2).getList();
                ReturnRegion.DataEntity entity=list.get(position);
                mPresenter.initSP(entity.getRegionid(),3);
                shi_name=entity.getRegionname();
                shi_id=entity.getRegionid();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<ReturnRegion.DataEntity> list=((SPAdapter)adapter1).getList();
                ReturnRegion.DataEntity entity=list.get(position);

                qu_name=entity.getRegionname();
                qu_id=entity.getRegionid();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
