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
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.utils.UiUtils;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;
import com.jude.rollviewpager.RollPagerView;

import java.util.List;

import javax.annotation.Nullable;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.app.utils.SpaceItemDecoration;
import bai.kang.yun.zxd.app.utils.Transfer;
import bai.kang.yun.zxd.di.component.DaggerFristComponent;
import bai.kang.yun.zxd.di.module.FristModule;
import bai.kang.yun.zxd.mvp.contract.FristContract;
import bai.kang.yun.zxd.mvp.model.entity.Advertisement;
import bai.kang.yun.zxd.mvp.model.entity.ReturnADGrid;
import bai.kang.yun.zxd.mvp.presenter.FristPresenter;
import bai.kang.yun.zxd.mvp.ui.activity.DetailActivity;
import bai.kang.yun.zxd.mvp.ui.activity.GoodsListActivity;
import bai.kang.yun.zxd.mvp.ui.activity.MainActivity;
import bai.kang.yun.zxd.mvp.ui.activity.MapActivity;
import bai.kang.yun.zxd.mvp.ui.activity.SearchActivity;
import bai.kang.yun.zxd.mvp.ui.activity.ShopDetailActivity;
import bai.kang.yun.zxd.mvp.ui.adapter.FristADGridAdapter;
import bai.kang.yun.zxd.mvp.ui.adapter.RollViewpagerAdapter;
import butterknife.BindView;
import butterknife.OnClick;
import common.AppComponent;
import common.WEApplication;
import common.WEFragment;

/**
 * Created by Administrator on 2017/4/12 0012.
 */

public class FristFragment extends WEFragment<FristPresenter>implements FristContract.View,
        View.OnClickListener{


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
    private List<Advertisement.DataEntity> urls;

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
        ggw_im1.setOnClickListener(this);
        ggw_im2.setOnClickListener(this);
        ggw_im3.setOnClickListener(this);
        mPresenter.requestUrls();
        mPresenter.getGoodsGrid();
        mPresenter.setGrid();
        mPresenter.getGGwUrl();
        mPresenter.getADGrid();
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
                List<ReturnADGrid.DataEntity> list=((FristADGridAdapter)gridView.getAdapter()).getList();
                ReturnADGrid.DataEntity dataEntity=list.get(position);
                String alt=dataEntity.getAlt();
                Intent intent;
                switch (alt){
                    case "product":
                        Transfer.chosegoods_for_open_goodsdetail_id=Integer.parseInt(list.get(position).getUrl());
                        intent=new Intent(UiUtils.getContext(), DetailActivity.class);
                        UiUtils.startActivity(intent);
                        break;
                    case "shop":
                        Transfer.choseshop_for_open_shopdetail_id=Integer.parseInt(list.get(position).getUrl());
                        intent=new Intent(UiUtils.getContext(), ShopDetailActivity.class);
                        UiUtils.startActivity(intent);
                        break;
                    case "category":
                        Transfer.chosegoods=Integer.parseInt(dataEntity.getUrl());
                        intent=new Intent(getActivity(), GoodsListActivity.class);
                        UiUtils.startActivity(intent);
                        break;
                    case "allCategory":
                        ((MainActivity)getActivity()).clickTab2Layout();
                        break;
                    case "map":
                        intent=new Intent(getActivity(), MapActivity.class);
                        UiUtils.startActivity(intent);
                        break;
                    default:
                        break;
                }

            }
        });
    }


    @Override
    public void setAdapter(RollViewpagerAdapter rolladapter, BaseAdapter simpleAdapter,
                           DefaultAdapter defaultadapter) {
        rollPagerView.setPlayDelay(3000);//*播放间隔
        rollPagerView.setAnimationDurtion(500);//透明度
        rollPagerView.setAdapter(rolladapter);//配置适配器
        gridView.setAdapter(simpleAdapter);
        recyclerView_tj.setAdapter(defaultadapter);
        recyclerView_tj.setNestedScrollingEnabled(false);
        UiUtils.configRecycleView(recyclerView_tj, new GridLayoutManager(getActivity(), 2));
        recyclerView_tj.addItemDecoration(new SpaceItemDecoration(5));
    }

    @Override
    public void setImg(List<Advertisement.DataEntity> urls) {
        this.urls=urls;
        mImageLoader.loadImage(mApplication, GlideImageConfig
                .builder()
                .url("http://www.baikangyun.com"+urls.get(0).getImg())
                .errorPic(R.mipmap.imgerror)
                .imageView(ggw_im1)
                .build());
        mImageLoader.loadImage(mApplication, GlideImageConfig
                .builder()
                .url("http://www.baikangyun.com"+urls.get(1).getImg())
                .errorPic(R.mipmap.imgerror)
                .imageView(ggw_im2)
                .build());
        mImageLoader.loadImage(mApplication, GlideImageConfig
                .builder()
                .url("http://www.baikangyun.com"+urls.get(2).getImg())
                .errorPic(R.mipmap.imgerror)
                .imageView(ggw_im3)
                .build());

    }
    @OnClick(R.id.ss)
    void ss(){
        Intent intent=new Intent(mApplication, SearchActivity.class);
        UiUtils.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.frist_ggw_1:
                String url=urls.get(0).getUrl();
                String alt=urls.get(0).getAlt();
                Intent intent;
                switch (alt){
                    case "product":
                        Transfer.chosegoods_for_open_goodsdetail_id=Integer.parseInt(url);
                        intent=new Intent(UiUtils.getContext(), DetailActivity.class);
                        UiUtils.startActivity(intent);
                        break;
                    case "shop":
                        Transfer.choseshop_for_open_shopdetail_id=Integer.parseInt(url);
                        intent=new Intent(UiUtils.getContext(), ShopDetailActivity.class);
                        UiUtils.startActivity(intent);
                        break;
                    default:
                        break;
                }

                break;
            case R.id.frist_ggw_2:
                String url1=urls.get(1).getUrl();
                String alt1=urls.get(1).getAlt();
                Intent intent1;
                switch (alt1){
                    case "product":
                        Transfer.chosegoods_for_open_goodsdetail_id=Integer.parseInt(url1);
                        intent1=new Intent(UiUtils.getContext(), DetailActivity.class);
                        UiUtils.startActivity(intent1);
                        break;
                    case "shop":
                        Transfer.choseshop_for_open_shopdetail_id=Integer.parseInt(url1);
                        intent1=new Intent(UiUtils.getContext(), ShopDetailActivity.class);
                        UiUtils.startActivity(intent1);
                        break;
                    default:
                        break;
                }
                break;
            case R.id.frist_ggw_3:
                String url2=urls.get(2).getUrl();
                String alt2=urls.get(2).getAlt();
                Intent intent2;
                switch (alt2){
                    case "product":
                        Transfer.chosegoods_for_open_goodsdetail_id=Integer.parseInt(url2);
                        intent2=new Intent(UiUtils.getContext(), DetailActivity.class);
                        UiUtils.startActivity(intent2);
                        break;
                    case "shop":
                        Transfer.choseshop_for_open_shopdetail_id=Integer.parseInt(url2);
                        intent2=new Intent(UiUtils.getContext(), ShopDetailActivity.class);
                        UiUtils.startActivity(intent2);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }
}
