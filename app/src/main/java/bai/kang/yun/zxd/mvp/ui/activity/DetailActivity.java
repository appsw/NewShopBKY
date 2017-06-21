package bai.kang.yun.zxd.mvp.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jess.arms.utils.UiUtils;
import com.jude.rollviewpager.RollPagerView;
import com.tencent.connect.auth.QQAuth;
import com.tencent.open.wpa.WPA;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.app.utils.Transfer;
import bai.kang.yun.zxd.di.component.DaggerDetailComponent;
import bai.kang.yun.zxd.di.module.DetailModule;
import bai.kang.yun.zxd.mvp.contract.DetailContract;
import bai.kang.yun.zxd.mvp.model.entity.CarGoods;
import bai.kang.yun.zxd.mvp.model.entity.ReturnDetail;
import bai.kang.yun.zxd.mvp.presenter.DetailPresenter;
import bai.kang.yun.zxd.mvp.ui.adapter.DetailImgAdapter;
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
 * Created by Administrator on 2017/4/15 0015.
 */

public class DetailActivity extends WEActivity<DetailPresenter> implements DetailContract.View,
        View.OnClickListener{

    private View inflate;
    private TextView qq;
    private TextView call;
    private Dialog dialog;
    private float price;
    private int number=1;
    private ReturnDetail.GoodsEntity goodsEntity;
    private String HOST="http://www.baikangyun.com";

    @BindView(R.id.textView)
    TextView tv_name;

    @BindView(R.id.textView2)
    TextView tv_price;

    @BindView(R.id.tv_sum)
    TextView tv_sum;

    @BindView(R.id.et_num)
    EditText et_num;

    @BindView(R.id.rollViewpager)
    RollPagerView rollPagerView;

    @BindView(R.id.xq_webview)
    WebView wv_detail;


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerDetailComponent
                .builder()
                .appComponent(appComponent)
                .detailModule(new DetailModule(this)) //请将DetailModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_detailed, null, false);
    }

    @Override
    protected void initData() {
        mPresenter.getDetail(Transfer.chosegoods_for_open_goodsdetail_id);
    }
    @OnClick(R.id.btn_reduce)
    void reduce(){
        // 减少
        if(number<=1){
            UiUtils.makeText("不能再少啦！");
        }else {
            number--;
            setNum(number);
            setSum();
        }
    }
    @OnClick(R.id.btn_add)
    void add(){
        //        增加
        if(number>=999){
            UiUtils.makeText("不能在多啦！");
        }else {
            number++;
            setNum(number);
            setSum();
        }

    }
    @OnClick(R.id.im_inshop)
    void startshop(){
        Intent intent=new Intent(DetailActivity.this,ShopDetailActivity.class);
        UiUtils.startActivity(intent);
    }
    @OnClick(R.id.addtocar)
    void addtocar(){
        CarGoods goods=new CarGoods();
        goods.setGoodsID(goodsEntity.getId()+"");
        goods.setNumber(number+"");
        goods.setGoodsName(goodsEntity.getProductname());
        goods.setPrice(goodsEntity.getSaleprice()+"");
        goods.setMkPrice(goodsEntity.getMarket_price()+"");
        goods.setWeight(goodsEntity.getWeight());
        goods.setFID(goodsEntity.getShop_id()+"");
        goods.setFName(Transfer.chosegoods_for_open_goodsdetail_shop_name);
        goods.setGoodsLogo(HOST+goodsEntity.getImageurl());
        goods.setIs_chufangi(goodsEntity.getIs_chufangi());
        mPresenter.addToCar(goods);
    }
    @OnClick(R.id.im_link)
    void startlink(){
        dialog = new Dialog(this,R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        inflate = LayoutInflater.from(this).inflate(R.layout.dialog_choselink, null);
        //初始化控件
        qq = (TextView) inflate.findViewById(R.id.qq);
        call = (TextView) inflate.findViewById(R.id.call);
        qq.setOnClickListener(this);
        call.setOnClickListener(this);
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity( Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;//设置Dialog距离底部的距离
//       将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }
    public void qq(){
        QQAuth mqqAuth = QQAuth.createInstance("1106092569", mApplication); // 10000000为你申请的APP_ID,mContext是上下文
        WPA mWPA = new WPA(mApplication, mqqAuth.getQQToken());
        String ESQ = "943009802";  //512821255为客服QQ号
        int ret = mWPA.startWPAConversation(this,ESQ, "你好，我正在看这个商品~"+ESQ); //客服QQ
        if (ret != 0) { //如果ret不为0，就说明调用SDK出现了错误
            Toast.makeText(mApplication,
                    "抱歉，联系客服出现了错误~. error:" + ret,
                    Toast.LENGTH_LONG).show();
        }
    }
    public void call(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        //url:统一资源定位符
        //uri:统一资源标示符（更广）
        intent.setData(Uri.parse("tel:" + "18764018870"));
        //开启系统拨号器
        startActivity(intent);
    }


    @Override
    public void showLoading() {
        loadingDialog.show();
    }

    @Override
    public void hideLoading() {
        loadingDialog.close();
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
        switch (v.getId()){
            case R.id.qq:
               qq();
                break;
            case R.id.call:
               call();
                break;
        }
        dialog.dismiss();

    }

    @Override
    public void setDetail(ReturnDetail detail) {
         goodsEntity=detail.getSingle().getGoods();
        tv_name.setText(goodsEntity.getProductname());
        tv_price.setText(goodsEntity.getSaleprice()+"");
        price=goodsEntity.getSaleprice();
        setSum();
        setWeb(detail.getSingle().getInfo().getDescription());
    }

    @Override
    public int getnum() {
        return Integer.parseInt(et_num.getText().toString());
    }

    @Override
    public void setSum() {
        tv_sum.setText(getnum()*price+"");
    }
    public void setNum(int i){
        et_num.setText(i+"");
    }

    @Override
    public void setAdapter(DetailImgAdapter rolladapter) {
        rollPagerView.setPlayDelay(3000);//*播放间隔
        rollPagerView.setAnimationDurtion(500);//透明度
        rollPagerView.setAdapter(rolladapter);//配置适配器
    }
    public void setWeb(String s){
        Log.e("web",""+s);
        wv_detail.loadDataWithBaseURL(null,s, "text/html", "utf-8", null);
    }

    @Override
    protected void onDestroy() {
        rollPagerView.removeAllViews();
        super.onDestroy();
    }
    @OnClick(R.id.register_back)
    public void black(){
        killMyself();
    }
}
