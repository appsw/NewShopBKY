package bai.kang.yun.zxd.mvp.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.utils.UiUtils;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;
import com.jude.rollviewpager.RollPagerView;

import java.util.List;

import javax.annotation.Nullable;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.app.utils.SpaceItemDecoration;
import bai.kang.yun.zxd.di.component.DaggerFristComponent;
import bai.kang.yun.zxd.di.module.FristModule;
import bai.kang.yun.zxd.mvp.contract.FristContract;
import bai.kang.yun.zxd.mvp.model.entity.Advertisement;
import bai.kang.yun.zxd.mvp.presenter.FristPresenter;
import bai.kang.yun.zxd.mvp.ui.activity.GoodsListActivity;
import bai.kang.yun.zxd.mvp.ui.activity.MapActivity;
import bai.kang.yun.zxd.mvp.ui.activity.SearchActivity;
import bai.kang.yun.zxd.mvp.ui.adapter.RollViewpagerAdapter;
import butterknife.BindView;
import butterknife.OnClick;
import common.AppComponent;
import common.WEApplication;
import common.WEFragment;

/**
 * Created by Administrator on 2017/4/12 0012.
 */

public class FristFragment extends WEFragment<FristPresenter>implements FristContract.View {


    static Context context;
    @Nullable
    @BindView(R.id.rollViewpager)
    RollPagerView rollPagerView;
    @Nullable
    @BindView(R.id.frist_gridview)
    GridView gridView;
    @BindView(R.id.frist_recyclerView)
    RecyclerView recyclerView_tj;
    @BindView(R.id.frist_ggw_1)
    ImageView ggw_im1;
    @BindView(R.id.frist_ggw_2)
    ImageView ggw_im2;
    @BindView(R.id.frist_ggw_3)
    ImageView ggw_im3;
    private ImageLoader mImageLoader;//用于加载图片的管理类,默认使用glide,使用策略模式,可替换框架
    private WEApplication mApplication;

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerFristComponent
                .builder()
                .appComponent(appComponent)
                .fristModule(new FristModule(this))
                .build()
                .inject(this);
        context=getActivity();
//可以在任何可以拿到Application的地方,拿到AppComponent,从而得到用Dagger管理的单例对象
        mApplication = (WEApplication) getActivity().getApplicationContext();
        mImageLoader = mApplication.getAppComponent().imageLoader();
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_frist, container,
                false);
        return view;
    }

    @Override
    protected void initData() {

        mPresenter.requestUrls();
        mPresenter.getGoodsGrid();
        mPresenter.setGrid();
        mPresenter.getGGwUrl();
        setOnclick();
    }
    public static WEFragment getInstance(){
        FristFragment instance = new FristFragment();
        Bundle args = new Bundle();
        instance.setArguments(args);
        return instance;
    }

    public FristFragment(){

    }

    public static boolean OnKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("确认退出吗？");
            builder.setTitle("提示");
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    System.exit(0);//退出程序
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();

        }
        return true;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMyself() {

    }
    public void setOnclick(){
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==6){
                    Intent intent=new Intent(getActivity(), MapActivity.class);
                    UiUtils.startActivity(intent);
                    return;
                }
               Intent intent=new Intent(getActivity(), GoodsListActivity.class);
                UiUtils.startActivity(intent);
                UiUtils.makeText("您点击了第"+position+"个");
//                Intent intent =new Intent(context, CategoryList.class);
//                intent.putExtra("keywords",cat_id[position]+"");
//                startActivityForResult(intent,3);
            }
        });
    }


    @Override
    public void setAdapter(RollViewpagerAdapter rolladapter, SimpleAdapter simpleAdapter,
                           DefaultAdapter defaultadapter) {
        rollPagerView.setPlayDelay(3000);//*播放间隔
        rollPagerView.setAnimationDurtion(500);//透明度
        rollPagerView.setAdapter(rolladapter);//配置适配器
        gridView.setAdapter(simpleAdapter);
        recyclerView_tj.setAdapter(defaultadapter);
        UiUtils.configRecycleView(recyclerView_tj, new GridLayoutManager(getActivity(), 2));
        recyclerView_tj.addItemDecoration(new SpaceItemDecoration(5));
    }

    @Override
    public void setImg(List<Advertisement.DataEntity> urls) {
        mImageLoader.loadImage(mApplication, GlideImageConfig
                .builder()
                .url("http://www.baikangyun.com"+urls.get(0).getImg())
                .imageView(ggw_im1)
                .build());
        mImageLoader.loadImage(mApplication, GlideImageConfig
                .builder()
                .url("http://www.baikangyun.com"+urls.get(1).getImg())
                .imageView(ggw_im2)
                .build());
        mImageLoader.loadImage(mApplication, GlideImageConfig
                .builder()
                .url("http://www.baikangyun.com"+urls.get(2).getImg())
                .imageView(ggw_im3)
                .build());

    }
    @OnClick(R.id.ss)
    void ss(){
        Intent intent=new Intent(mApplication, SearchActivity.class);
        UiUtils.startActivity(intent);
    }
}
