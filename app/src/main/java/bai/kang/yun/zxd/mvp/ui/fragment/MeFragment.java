package bai.kang.yun.zxd.mvp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jess.arms.utils.UiUtils;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.app.utils.SPMoreImageView;
import bai.kang.yun.zxd.di.component.DaggerMeComponent;
import bai.kang.yun.zxd.di.module.MeModule;
import bai.kang.yun.zxd.mvp.contract.MeContract;
import bai.kang.yun.zxd.mvp.presenter.MePresenter;
import bai.kang.yun.zxd.mvp.ui.activity.AddressListActivity;
import bai.kang.yun.zxd.mvp.ui.activity.LoginActivity;
import bai.kang.yun.zxd.mvp.ui.activity.PersonalSettingActivity;
import butterknife.BindView;
import butterknife.OnClick;
import common.AppComponent;
import common.WEFragment;
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
 * Created by Administrator on 2017/4/21 0021.
 */

public class MeFragment extends WEFragment<MePresenter> implements MeContract.View {

    @BindView(R.id.nickname_txtv)
    TextView tv_name;
    @BindView(R.id.head_mimgv)
    SPMoreImageView im_head;
    private ImageLoader mImageLoader;//用于加载图片的管理类,默认使用glide,使用策略模式,可替换框架

    SharedPreferences config;
    public static MeFragment newInstance() {
        MeFragment fragment = new MeFragment();
        return fragment;
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerMeComponent
                .builder()
                .appComponent(appComponent)
                .meModule(new MeModule(this))//请将MeModule()第一个首字母改为小写
                .build()
                .inject(this);
        mImageLoader=appComponent.imageLoader();
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {

        return inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        config=getActivity().getApplication().getSharedPreferences("config", Context.MODE_PRIVATE);
        if(config!=null){
            String name;
            String im_url;
            if(config.getBoolean("isLog",false)){
                name=config.getString("name","点击此处登录");
                if(config.getBoolean("isHead",false)){
                    im_url=config.getString("headUrl",null);
                    mImageLoader.loadImage(getActivity().getApplication(), GlideImageConfig
                            .builder()
                            .url(im_url)
                            .imageView(im_head)
                            .build());
                }
            }else {
                name="点击此处登录";
            }
            Observable.just(name)
                    .subscribe(RxTextView.text(tv_name));

        }

    }

    @Override
    protected void initData() {

    }

    /**
     * 此方法是让外部调用使fragment做一些操作的,比如说外部的activity想让fragment对象执行一些方法,
     * 建议在有多个需要让外界调用的方法时,统一传bundle,里面存一个what字段,来区分不同的方法,在setData
     * 方法中就可以switch做不同的操作,这样就可以用统一的入口方法做不同的事,和message同理
     *
     * 使用此方法时请注意调用时fragment的生命周期,如果调用此setData方法时onActivityCreated
     * 还没执行,setData里调用presenter的方法时,是会报空的,因为dagger注入是在onActivityCreated
     * 方法中执行的,如果要做一些初始化操作,可以不必让外部调setData,在内部onActivityCreated中
     * 初始化就可以了
     *
     * @param data
     */

    @Override
    public void setData(Object data) {

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

    }
    public static void OnKeyDown(int keyCode, KeyEvent event) {

    }
    @OnClick(R.id.nickname_txtv)
    void longin(){
        Intent intent=new Intent(getActivity(), LoginActivity.class);
        UiUtils.startActivity(intent);
    }
    @OnClick(R.id.person_receive_address_aview)
    void newAdd(){
        Intent intent=new Intent(getActivity(), AddressListActivity.class);
        UiUtils.startActivity(intent);
    }
    @OnClick(R.id.account_rlayout)
    void personal(){
        Intent intent=new Intent(getActivity(), PersonalSettingActivity.class);
        UiUtils.startActivity(intent);
    }


}