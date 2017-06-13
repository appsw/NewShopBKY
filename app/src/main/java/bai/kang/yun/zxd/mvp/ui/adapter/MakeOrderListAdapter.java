package bai.kang.yun.zxd.mvp.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.mvp.model.entity.CarGoods;
import bai.kang.yun.zxd.mvp.model.entity.CarShop;
import bai.kang.yun.zxd.mvp.model.entity.ReturenExpress;
import bai.kang.yun.zxd.mvp.presenter.MakeOrderPresenter;
import bai.kang.yun.zxd.mvp.ui.Listener.ExperssChengeListener;
import io.realm.Realm;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/7 0007.
 */

public class MakeOrderListAdapter extends BaseAdapter {
    private Context mContext;
    private List<CarShop> mListShop = new ArrayList();
    private Float[] sums;
    private int mPosition;
    private MakeOrderGoodsListAdapter makeOrderGoodsListAdapter;
    private MakeOrderPresenter mPresenter;
    private String ExpressId="0";
    private float ExpressPre=0;
    private ExperssChengeListener experssChengeListener;
    private Realm mRealm = Realm.getDefaultInstance();
    private float shopsum;
    public MakeOrderListAdapter(Context mContext,List<CarShop> listGoods,MakeOrderPresenter mPresenter){
        this.mContext=mContext;
        this.mListShop=listGoods;
        this.mPresenter=mPresenter;


    }
    @Override
    public int getCount() {
        int num=mListShop.size();
        return num;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(sums==null&&mListShop.size()!=0)
            sums=new Float[mListShop.size()];
        mPosition=position;
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_order_shop, parent, false);
            viewHolder=new ViewHolder();
            viewHolder.ShopName= (TextView) convertView.findViewById(R.id.tv_shopname);
            viewHolder.PostType= (Spinner) convertView.findViewById(R.id.tv_posttype);
            viewHolder.listView=(ListView) convertView.findViewById(R.id.list_goods);
            viewHolder.sum=(TextView) convertView.findViewById(R.id.tv_sum);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CarShop carShop=mListShop.get(position);
        List<CarGoods> goodses=carShop.getGoods();
        makeOrderGoodsListAdapter=new MakeOrderGoodsListAdapter(mContext,goodses);
        viewHolder.listView.setAdapter(makeOrderGoodsListAdapter);
        Observable.just(carShop.getMerchantName()).subscribe(RxTextView.text(viewHolder.ShopName));
        shopsum=getSum(goodses,carShop);
        Observable.just(shopsum+"").subscribe(RxTextView.text(viewHolder.sum));
        int weight=0;
        for(CarGoods carGoods:goodses){
            weight+=carGoods.getWeight();
        }
        int finalWeight = weight;
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //先查找后得到User对象
                carShop.setWeight(finalWeight);
            }
        });
        mPresenter.GetShopExperss(Integer.parseInt(carShop.getMerID()),weight,viewHolder.PostType);
        viewHolder.PostType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position1, long id) {
                ShopExperssAdapter shopExperssAdapter= (ShopExperssAdapter) viewHolder.PostType.getAdapter();
                List<ReturenExpress.DataEntity> dataEntities=shopExperssAdapter.getList();
                ReturenExpress.DataEntity dataEntity=dataEntities.get(position1);
                ExpressId=dataEntity.getStoreLogisticsId();
                ExpressPre=dataEntity.getTrafficPrice();
                float sum=getSum(goodses,carShop);
                Observable.just(sum+"").subscribe(RxTextView.text(viewHolder.sum));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return convertView;
    }
    private float getSum(List<CarGoods> goodses,CarShop carShop){
        if(goodses.size()==0)
            return 0;
        float sum=0;
        for(CarGoods carGoods:goodses){
            sum+=Float.parseFloat(carGoods.getPrice());
        }
        sum+=ExpressPre;
        float finalSum = sum;
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //先查找后得到User对象
                carShop.setShopSum(finalSum);
                carShop.setTrafficId(Integer.parseInt(ExpressId));
            }
        });
        getOrderSum();
        return sum;
    }
    public void getOrderSum(){
        float sum=0;
        for(CarShop carGoods:mListShop){
            sum+=carGoods.getShopSum();
            Log.e("sum",""+carGoods.getShopSum());
        }
        experssChengeListener.Chenge(sum);
    }
    public void setOnChengeListener(ExperssChengeListener onChengeListener){
        this.experssChengeListener=onChengeListener;
    }
    public List<CarShop> getList(){
        return mListShop;
    }
    private class ViewHolder{
        TextView ShopName;
        Spinner PostType;
        ListView listView;
        TextView sum;

    }
}
