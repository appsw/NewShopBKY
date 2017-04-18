package bai.kang.yun.zxd.mvp.ui.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.utils.UiUtils;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.di.component.DaggerFindComponent;
import bai.kang.yun.zxd.di.module.FindModule;
import bai.kang.yun.zxd.mvp.contract.FindContract;
import bai.kang.yun.zxd.mvp.presenter.FindPresenter;
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
 * Created by Administrator on 2017/4/18 0018.
 */

public class FindFragment extends WEFragment<FindPresenter> implements FindContract.View {


    @BindView(R.id.find_list)
    RecyclerView list;
    @BindView(R.id.find_grid)
    RecyclerView grid;

    public static FindFragment newInstance() {
        FindFragment fragment = new FindFragment();
        return fragment;
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerFindComponent
                .builder()
                .appComponent(appComponent)
                .findModule(new FindModule(this))//请将FindModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_find, container, false);
    }

    @Override
    protected void initData() {
        mPresenter.getCategorylist();
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
    public void setAdapter(DefaultAdapter listadapter,DefaultAdapter gridadapter) {
        list.setAdapter(listadapter);
        grid.setAdapter(gridadapter);
        initRecycleView();
    }
    /**
     * 初始化RecycleView
     */
    private void initRecycleView() {
        UiUtils.configRecycleView(list, new LinearLayoutManager(getActivity()));
        UiUtils.configRecycleView(grid, new GridLayoutManager(getActivity(), 3));
    }

}