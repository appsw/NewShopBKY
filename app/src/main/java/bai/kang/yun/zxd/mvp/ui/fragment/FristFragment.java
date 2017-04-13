package bai.kang.yun.zxd.mvp.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.jess.arms.utils.UiUtils;
import com.jude.rollviewpager.RollPagerView;

import javax.annotation.Nullable;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.di.component.DaggerFristComponent;
import bai.kang.yun.zxd.di.module.FristModule;
import bai.kang.yun.zxd.mvp.contract.FristContract;
import bai.kang.yun.zxd.mvp.presenter.FristPresenter;
import bai.kang.yun.zxd.mvp.ui.activity.GoodsListActivity;
import bai.kang.yun.zxd.mvp.ui.adapter.RollViewpagerAdapter;
import butterknife.BindView;
import common.AppComponent;
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
    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerFristComponent
                .builder()
                .appComponent(appComponent)
                .fristModule(new FristModule(this))
                .build()
                .inject(this);
        context=getActivity();

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
        mPresenter.setGrid();
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
    public void setAdapter(RollViewpagerAdapter adapter, SimpleAdapter simpleAdapter) {
        rollPagerView.setPlayDelay(3000);//*播放间隔
        rollPagerView.setAnimationDurtion(500);//透明度
        rollPagerView.setAdapter(adapter);//配置适配器
        gridView.setAdapter(simpleAdapter);
    }
}
