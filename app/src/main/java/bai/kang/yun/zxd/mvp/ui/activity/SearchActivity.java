package bai.kang.yun.zxd.mvp.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jess.arms.utils.UiUtils;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.di.component.DaggerSearchComponent;
import bai.kang.yun.zxd.di.module.SearchModule;
import bai.kang.yun.zxd.mvp.contract.SearchContract;
import bai.kang.yun.zxd.mvp.presenter.SearchPresenter;
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
 * Created by Administrator on 2017/4/24 0024.
 */

public class SearchActivity extends WEActivity<SearchPresenter> implements SearchContract.View {


    @BindView(R.id.search_edtv)
    EditText et_search;
    @BindView(R.id.search_icon)
    ImageView search_icon;
    @BindView(R.id.search_key_listv)
    ListView search_key_listv;
    @BindView(R.id.back_imgv)
    ImageView back_imgv;
    @BindView(R.id.search_delete_btn)
    Button search_delete;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerSearchComponent
                .builder()
                .appComponent(appComponent)
                .searchModule(new SearchModule(this)) //请将SearchModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_search, null, false);
    }

    @Override
    protected void initData() {
        // 第一次进入时查询所有的历史记录
        mPresenter.queryData("");
        //搜索框的文本变化实时监听
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            //输入后调用该方法
            @Override
            public void afterTextChanged(Editable s) {

                //每次输入后都查询数据库并显示
                //根据输入的值去模糊查询数据库中有没有数据
                String tempName = et_search.getText().toString();
                mPresenter.queryData(tempName);

            }
        });
        // 搜索框的键盘搜索键
        // 点击回调
        et_search.setOnKeyListener(new View.OnKeyListener() {// 输入完后按键盘上的搜索键


            // 修改回车键功能
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    // 隐藏键盘，这里getCurrentFocus()需要传入Activity对象，如果实际不需要的话就不用隐藏键盘了，免得传入Activity对象，这里就先不实现了
//                    ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
//                            getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    // 按完搜索键后将当前查询的关键字保存起来,如果该关键字已经存在就不执行保存
                    boolean hasData = mPresenter.hasData(et_search.getText().toString().trim());
                    if (!hasData) {
                        mPresenter.insertData(et_search.getText().toString().trim());

                        mPresenter.queryData("");
                    }
                    //根据输入的内容模糊查询商品，并跳转到另一个界面，这个需要根据需求实现
                    Toast.makeText(mApplication, "点击搜索", Toast.LENGTH_SHORT).show();

                }
                return false;
            }
        });
        //列表监听
        //即当用户点击搜索历史里的字段后,会直接将结果当作搜索字段进行搜索
        search_key_listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //获取到用户点击列表里的文字,并自动填充到搜索框内
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                String name = textView.getText().toString();
                et_search.setText(name);
                Toast.makeText(mApplication, name, Toast.LENGTH_SHORT).show();

            }
        });


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
    public void setAdapter(BaseAdapter adapter) {
        search_key_listv.setAdapter(adapter);
    }
    @OnClick(R.id.search_delete_btn)
    void delete(){
        //清空数据库
        mPresenter.deleteData();
        mPresenter.queryData("");
    }
    @OnClick(R.id.search_icon)
    void search(){
        boolean hasData = mPresenter.hasData(et_search.getText().toString().trim());
        if (!hasData) {
            mPresenter.insertData(et_search.getText().toString().trim());

            //搜索后显示数据库里所有搜索历史是为了测试
            mPresenter.queryData("");
        }
        //根据输入的内容模糊查询商品，并跳转到另一个界面，这个根据需求实现
        Toast.makeText(mApplication, "clicked!", Toast.LENGTH_SHORT).show();
    }
}
