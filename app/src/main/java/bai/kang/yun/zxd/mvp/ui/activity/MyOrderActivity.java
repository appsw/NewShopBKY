package bai.kang.yun.zxd.mvp.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.jess.arms.utils.UiUtils;
import com.paginate.Paginate;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.di.component.DaggerMyOrderComponent;
import bai.kang.yun.zxd.di.module.MyOrderModule;
import bai.kang.yun.zxd.mvp.contract.MyOrderContract;
import bai.kang.yun.zxd.mvp.presenter.MyOrderPresenter;
import butterknife.BindView;
import common.AppComponent;
import common.WEActivity;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

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
 * Created by Administrator on 2017/5/2 0002.
 */

public class MyOrderActivity extends WEActivity<MyOrderPresenter> implements MyOrderContract.View,
        SwipeRefreshLayout.OnRefreshListener{


    @BindView(R.id.id_swipe_ly)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.myorder_goods)
    ListView listview;
    private boolean isLoadingMore;
    private Paginate mPaginate;
    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMyOrderComponent
                .builder()
                .appComponent(appComponent)
                .myOrderModule(new MyOrderModule(this)) //请将MyOrderModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_myorder, null, false);
    }

    @Override
    protected void initData() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mPresenter.getOrderList(1);
    }


    @Override
    public void showLoading() {
        Observable.just(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> mSwipeRefreshLayout.setRefreshing(true));
    }

    @Override
    public void hideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
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
    public void onRefresh() {
        mPresenter.getOrderList(0);
    }
    /**
     * 初始化Paginate,用于加载更多
     */
    private void initPaginate() {
        if (mPaginate == null) {
            Paginate.Callbacks callbacks = new Paginate.Callbacks() {
                @Override
                public void onLoadMore() {
                    mPresenter.getOrderList(0);
                }

                @Override
                public boolean isLoading() {
                    return isLoadingMore;
                }

                @Override
                public boolean hasLoadedAllItems() {
                    return !isLoadingMore;
                }
            };

            mPaginate = Paginate.with(listview, callbacks)
                    .setLoadingTriggerThreshold(0)
                    .build();
            mPaginate.setHasMoreDataToLoad(false);
        }
    }

    @Override
    public void setAdapter(BaseAdapter adapter) {
        listview.setAdapter(adapter);
        initPaginate();
    }

    @Override
    public void startLoadMore() {
        isLoadingMore = true;
    }

    @Override
    public void endLoadMore() {
        isLoadingMore = false;
    }
}
