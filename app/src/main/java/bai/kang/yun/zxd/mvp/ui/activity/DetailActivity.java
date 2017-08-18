package bai.kang.yun.zxd.mvp.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.jess.arms.base.DefaultAdapter;
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
    @BindView(R.id.tv_kc)
    TextView tv_kc;

    @BindView(R.id.et_num)
    EditText et_num;

    @BindView(R.id.rollViewpager)
    RollPagerView rollPagerView;

    @BindView(R.id.tv_dpzwh)
    TextView tv_dpzwh;
    @BindView(R.id.tv_dname)
    TextView tv_dname;
    @BindView(R.id.tv_dsccs)
    TextView tv_dsccs;
    @BindView(R.id.tv_dsfjk)
    TextView tv_dsfjk;
    @BindView(R.id.tv_dxz)
    TextView tv_dxz;
    @BindView(R.id.tv_dyxcf)
    TextView tv_dyxcf;
    @BindView(R.id.tv_dgnzz)
    TextView tv_dgnzz;
    @BindView(R.id.tv_dyfyl)
    TextView tv_dyfyl;
    @BindView(R.id.tv_dzysx)
    TextView tv_dzysx;
    @BindView(R.id.tv_dsfcf)
    TextView tv_dsfcf;
    @BindView(R.id.tv_dblfy)
    TextView tv_dblfy;
    @BindView(R.id.tv_dyldl)
    TextView tv_dyldl;
    @BindView(R.id.tv_dxhzy)
    TextView tv_dxhzy;
    @BindView(R.id.tv_djjz)
    TextView tv_djjz;
    @BindView(R.id.tv_dcctj)
    TextView tv_dcctj;
    @BindView(R.id.tv_dyxq)
    TextView tv_dyxq;

    @BindView(R.id.TRname)
    TableRow TRname;
    @BindView(R.id.TRpzwh)
    TableRow TRpzwh;
    @BindView(R.id.TRsccs)
    TableRow TRsccs;
    @BindView(R.id.TRsfjk)
    TableRow TRsfjk;
    @BindView(R.id.TRxz)
    TableRow TRxz;
    @BindView(R.id.TRyxcf)
    TableRow TRyxcf;
    @BindView(R.id.TRgnzz)
    TableRow TRgnzz;
    @BindView(R.id.TRyfyl)
    TableRow TRyfyl;
    @BindView(R.id.TRzysx)
    TableRow TRzysx;
    @BindView(R.id.TRsfcf)
    TableRow TRsfcf;
    @BindView(R.id.TRblfy)
    TableRow TRblfy;
    @BindView(R.id.TRyldl)
    TableRow TRyldl;
    @BindView(R.id.TRxhzy)
    TableRow TRxhzy;
    @BindView(R.id.TRjjz)
    TableRow TRjjz;
    @BindView(R.id.TRcctj)
    TableRow TRcctj;
    @BindView(R.id.TRyxq)
    TableRow TRyxq;
    @BindView(R.id.comment)
    RecyclerView mRecyclerView;
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
    /**
     * 初始化RecycleView
     */
    private void initRecycleView() {
        UiUtils.configRecycleView(mRecyclerView, new LinearLayoutManager(this));
    }
    @OnClick(R.id.btn_reduce)
    void reduce(){
        // 减少
        number=getnum();
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
        number=getnum();
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
        goods.setNumber(getnum()+"");
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
        String ESQ = "2853119501";  //2853119501为客服QQ号
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
        intent.setData(Uri.parse("tel:" + "13153181461"));
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
        tv_kc.setText(goodsEntity.getStock()+"");
        price=goodsEntity.getSaleprice();
        Transfer.choseshop_for_open_shopdetail_id=goodsEntity.getShop_id();
        setSum();
        setInfo(detail.getSingle().getInfo());
        mPresenter.getComment(goodsEntity.getShop_id());
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
    public void setAdapter(DetailImgAdapter rolladapter,DefaultAdapter adapter) {
        rollPagerView.setPlayDelay(3000);//*播放间隔
        rollPagerView.setAnimationDurtion(500);//透明度
        rollPagerView.setAdapter(rolladapter);//配置适配器
        mRecyclerView.setAdapter(adapter);
//        mRecyclerView.setNestedScrollingEnabled(false);
        initRecycleView();
    }
//    public void setWeb(String s){
//        Log.e("web",""+s);
//        wv_detail.loadDataWithBaseURL(null,s, "text/html", "utf-8", null);
//    }
    public void setInfo(ReturnDetail.InfoEntity info){
        String name=info.getDrugstyName();
        if(name!="")
            tv_dname.setText(name);
        else
            TRname.setVisibility(View.GONE);
        String pzwh=info.getPizhunwenhao();
        if(pzwh!="")
            tv_dpzwh.setText(pzwh);
        else
            TRpzwh.setVisibility(View.GONE);
        String sccs=info.getManufacturers();
        if(sccs!="")
            tv_dsccs.setText(sccs);
        else
            TRsccs.setVisibility(View.GONE);
        String xz=info.getDrugsSymptom();
        if(xz!="")
            tv_dxz.setText(xz);
        else
            TRxz.setVisibility(View.GONE);
        String cctj=info.getDrugsStore();
        if(cctj!="")
            tv_dcctj.setText(cctj);
        else
            TRcctj.setVisibility(View.GONE);
        String yxq=info.getValidityTime();
        if(yxq!="")
            tv_dyxq.setText(yxq);
        else
            TRyxq.setVisibility(View.GONE);
        String yxcf=info.getZhuyaochengfen();
        if(yxcf!="")
            tv_dyxcf.setText(yxcf);
        else
            TRyxcf.setVisibility(View.GONE);
        String jjz=info.getJinji();
        if(jjz!="")
            tv_djjz.setText(jjz);
        else
            TRjjz.setVisibility(View.GONE);
        String xhzy=info.getXianghuzuoyong();
        if(xhzy!="")
            tv_dxhzy.setText(xhzy);
        else
            TRxhzy.setVisibility(View.GONE);
        String yldl=info.getYaoliduli();
        if(yldl!="")
            tv_dyldl.setText(yldl);
        else
            TRyldl.setVisibility(View.GONE);
        String gnzz=info.getGongnengzhuzhi();
        if(gnzz!="")
            tv_dgnzz.setText(gnzz);
        else
            TRgnzz.setVisibility(View.GONE);
        String yfyl=info.getYongfayongliang();
        if(yfyl!="")
            tv_dyfyl.setText(yfyl);
        else
            TRyfyl.setVisibility(View.GONE);
        String blfy=info.getBuliangfanying();
        if(blfy!="")
            tv_dblfy.setText(blfy);
        else
            TRblfy.setVisibility(View.GONE);
        String zysx=info.getZhuyishixiang();
        if(zysx!="")
            tv_dzysx.setText(zysx);
        else
            TRzysx.setVisibility(View.GONE);
        int sfjk=info.getIsImported();
        switch (sfjk)
                {
                    case -1:
                        TRsfjk.setVisibility(View.GONE);
                        break;
                    case 0:
                        tv_dsfjk.setText("国产");
                        break;
                    case 1:
                        tv_dsfjk.setText("进口");
                        break;
                    default:
                        break;
                }

        int sfcf=info.getIs_chufang();
        switch (sfcf)
        {
            case -1:
                TRsfcf.setVisibility(View.GONE);
                break;
            case 0:
                tv_dsfcf.setText("否");
                break;
            case 1:
                tv_dsfcf.setText("是");
                break;
            default:
                break;
        }

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
    @OnClick(R.id.more_comment)
    public void more_comment(){
        launchActivity(new Intent(DetailActivity.this,CommentActivity.class));
    }

}
