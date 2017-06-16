package bai.kang.yun.zxd.mvp.ui.activity;

import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.utils.UiUtils;

import org.json.JSONObject;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.di.component.DaggerUpChuFangComponent;
import bai.kang.yun.zxd.di.module.UpChuFangModule;
import bai.kang.yun.zxd.mvp.contract.UpChuFangContract;
import bai.kang.yun.zxd.mvp.presenter.UpChuFangPresenter;
import bai.kang.yun.zxd.mvp.ui.fragment.ChuFangImageFragment;
import bai.kang.yun.zxd.mvp.ui.fragment.ChuFangTextFragment;
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
 * Created by Administrator on 2017/6/15 0015.
 */

public class UpChuFangActivity extends WEActivity<UpChuFangPresenter> implements UpChuFangContract.View,
        OnClickListener{


    @BindView(R.id.tv_updata)
    TextView tv_updata;
    @BindView(R.id.tv_shopname)
    TextView tv_shopname;
    @BindView(R.id.tv_order)
    TextView tv_order;
    @BindView(R.id.tv_image)
    TextView tv_image;
    @BindView(R.id.tv_text)
    TextView tv_text;
    private boolean type;//ture 图片
    private int id;
    private String name;
    // 底部标签切换的Fragment
    private BaseFragment textFragment,currentFragment,imageFragment;
    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerUpChuFangComponent
                .builder()
                .appComponent(appComponent)
                .upChuFangModule(new UpChuFangModule(this)) //请将UpChuFangModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_upchufang, null, false);
    }

    @Override
    protected void initData() {
        id=getIntent().getIntExtra("id",0);
        name=getIntent().getStringExtra("name");
        tv_image.setOnClickListener(this);
        tv_text.setOnClickListener(this);
        initTab();
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_image: // 首页
                clickTab1Layout();

                break;
            case R.id.tv_text: // 找药

                clickTab2Layout();

                break;
            default:
                break;
        }
    }
    /**
     * 初始化底部标签
     */
    public void initTab() {
        type=true;
        if (imageFragment == null) {
            imageFragment= ChuFangImageFragment.newInstance();
        }

        if (!imageFragment.isAdded()) {
            // 提交事务
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_layout, imageFragment).commit();

            // 记录当前Fragment
            currentFragment = imageFragment;
            //设置图片文本的变化
            tv_image.setTextColor(getResources()
                    .getColor(R.color.green));
            tv_text.setTextColor(getResources()
                    .getColor(R.color.black));
        }

    }
    /**
     * 点击第一个tab
     */
    private void clickTab1Layout() {
        type=true;
        if (imageFragment == null) {
            imageFragment =  ChuFangImageFragment.newInstance();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), imageFragment);

        //设置图片文本的变化
        tv_image.setTextColor(getResources()
                .getColor(R.color.green));
        tv_text.setTextColor(getResources()
                .getColor(R.color.black));

    }

    /**
     * 点击第二个tab
     */
    private void clickTab2Layout() {
        type=false;
        if (textFragment == null) {
            textFragment = ChuFangTextFragment.newInstance();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), textFragment);

        //设置图片文本的变化
        tv_image.setTextColor(getResources()
                .getColor(R.color.black));
        tv_text.setTextColor(getResources()
                .getColor(R.color.green));

    }

    /**
     * 添加或者显示碎片
     *
     * @param transaction
     * @param fragment
     */
    private void addOrShowFragment(FragmentTransaction transaction,
                                   BaseFragment fragment) {
        if (currentFragment == fragment)
            return;

        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.hide(currentFragment)
                    .add(R.id.content_layout, fragment).commit();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }

        currentFragment = fragment;
    }
    @OnClick(R.id.tv_updata)
    public void upDate(){
        if(type){
            SetImg();
        }else {
            SetText();
        }
    }
    private void SetImg(){
        ChuFangImageFragment chuFangImageFragment=(ChuFangImageFragment)imageFragment;
        if(chuFangImageFragment.getIs()){
            mPresenter.SetImg(id,chuFangImageFragment.getImgFile());
        }
    }
    private void SetText(){
        ChuFangTextFragment chuFangTextFragment= (ChuFangTextFragment) textFragment;
        JSONObject jsonObject=chuFangTextFragment.getData();
        if(jsonObject!=null){
            mPresenter.SetText(id,jsonObject.toString());
        }
    }
}
