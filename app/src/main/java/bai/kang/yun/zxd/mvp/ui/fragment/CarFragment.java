package bai.kang.yun.zxd.mvp.ui.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.utils.UiUtils;

import java.util.List;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.app.utils.OnShoppingCartChangeListener;
import bai.kang.yun.zxd.app.utils.ShoppingCartBiz;
import bai.kang.yun.zxd.di.component.DaggerCarComponent;
import bai.kang.yun.zxd.di.module.CarModule;
import bai.kang.yun.zxd.mvp.contract.CarContract;
import bai.kang.yun.zxd.mvp.model.entity.CarShop;
import bai.kang.yun.zxd.mvp.presenter.CarPresenter;
import bai.kang.yun.zxd.mvp.ui.adapter.MyExpandableListAdapter;
import butterknife.BindView;
import common.AppComponent;
import common.WEFragment;

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

public class CarFragment extends WEFragment<CarPresenter> implements CarContract.View {

    @BindView(R.id.expandableListView)
    ExpandableListView expandableListView;
    @BindView(R.id.ivSelectAll)
    ImageView ivSelectAll;
    @BindView(R.id.btnSettle)
    TextView btnSettle;
    @BindView(R.id.tvCountMoney)
    TextView tvCountMoney;
    @BindView(R.id.rlShoppingCartEmpty)
    RelativeLayout rlShoppingCartEmpty;
    @BindView(R.id.rlBottomBar)
    RelativeLayout rlBottomBar;
//    @BindView(R.id.tvTitle)
//    TextView tvTitle;


    public static CarFragment newInstance() {
        CarFragment fragment = new CarFragment();
        return fragment;
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerCarComponent
                .builder()
                .appComponent(appComponent)
                .carModule(new CarModule(this))//请将CarModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_car, container, false);
    }

    @Override
    protected void initData() {

//        mPresenter.requestShoppingCartList();
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

    @Override
    public void setAdapter(BaseExpandableListAdapter adapter1) {
        MyExpandableListAdapter adapter= (MyExpandableListAdapter) adapter1;
        expandableListView.setAdapter(adapter);
        adapter.setOnShoppingCartChangeListener(new OnShoppingCartChangeListener() {

            public void onDataChange(String selectCount, String selectMoney) {
                int goodsCount = ShoppingCartBiz.getGoodsCount();
//                if (!isNetworkOk) {//网络状态判断暂时不显示
//                }
                if (goodsCount == 0) {
                    showEmpty(true);
                } else {
                    showEmpty(false);//其实不需要做这个判断，因为没有商品的时候，必须退出去添加商品；
                }
                String countMoney = String.format(getResources().getString(R.string.count_money), selectMoney);
                String countGoods = String.format(getResources().getString(R.string.count_goods), selectCount);
//                String title = String.format(getResources().getString(R.string.shop_title), goodsCount + "");
                tvCountMoney.setText(countMoney);
                btnSettle.setText(countGoods);
//                tvTitle.setText(title);
            }


            public void onSelectItem(boolean isSelectedAll) {
                ShoppingCartBiz.checkItem(isSelectedAll, ivSelectAll);
            }
        });
        //通过监听器关联Activity和Adapter的关系，解耦；
        View.OnClickListener listener = adapter.getAdapterListener();
        if (listener != null) {
            //即使换了一个新的Adapter，也要将“全选事件”传递给adapter处理；
            ivSelectAll.setOnClickListener(adapter.getAdapterListener());
            //结算时，一般是需要将数据传给订单界面的
            btnSettle.setOnClickListener(adapter.getAdapterListener());
        }
        expandableListView.setGroupIndicator(null);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return true;
            }
        });
    }
    /**
     * 展开所有组
     */
    @Override
    public void expandAllGroup(List<CarShop> mListGoods) {
        Log.e("mListGoods.size",":"+mListGoods.size());
        for (int i = 0; i < mListGoods.size(); i++) {
            expandableListView.expandGroup(i);
        }
    }

    public void showEmpty(boolean isEmpty) {
        if (isEmpty) {
            expandableListView.setVisibility(View.GONE);
            rlShoppingCartEmpty.setVisibility(View.VISIBLE);
            rlBottomBar.setVisibility(View.GONE);
        } else {
            expandableListView.setVisibility(View.VISIBLE);
            rlShoppingCartEmpty.setVisibility(View.GONE);
            rlBottomBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.requestShoppingCartList();
    }
}