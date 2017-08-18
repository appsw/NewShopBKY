package bai.kang.yun.zxd.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import bai.kang.yun.zxd.mvp.contract.DetailContract;
import bai.kang.yun.zxd.mvp.model.api.cache.CacheManager;
import bai.kang.yun.zxd.mvp.model.api.service.ServiceManager;
import bai.kang.yun.zxd.mvp.model.entity.CarGoods;
import bai.kang.yun.zxd.mvp.model.entity.CarShop;
import bai.kang.yun.zxd.mvp.model.entity.ReturnComment;
import bai.kang.yun.zxd.mvp.model.entity.ReturnDetail;
import io.realm.Realm;
import io.realm.RealmResults;
import io.rx_cache.DynamicKey;
import io.rx_cache.Reply;
import rx.Observable;
import rx.functions.Func1;


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

@ActivityScope
public class DetailModel extends BaseModel<ServiceManager, CacheManager> implements DetailContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public DetailModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson, Application application) {
        super(serviceManager, cacheManager);
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<ReturnDetail> getGoodsDetail(int id) {
        Observable<ReturnDetail> detail = mServiceManager.getGetGoodsDetailService().getGoodsDetail(id);

        return mCacheManager.getCommonCache()
                .getGoodsDetail(detail,new DynamicKey(id))
                .flatMap(new Func1<Reply<ReturnDetail>, Observable<ReturnDetail>>() {
                    @Override
                    public Observable<ReturnDetail> call(Reply<ReturnDetail> mapReply) {
                        return Observable.just(mapReply.getData());
                    }
                });
    }

    @Override
    public Observable addGoods(final CarGoods goods) {
        Realm realm=Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
//                查询店铺是否存在
                RealmResults<CarShop> carShop=realm.where(CarShop.class)
                        .equalTo("merID", goods.getFID()).findAll();
                if(carShop.size()==0){
//                    查到的个数为0店铺不存在，新建店铺插入商品
                    CarShop shop = realm.createObject(CarShop.class);
                    shop.setMerchantName(goods.getFName());
                    shop.setMerID(goods.getFID());
                    shop.getGoods().add(goods);
                }else {
//                    店铺已存在，查询当前商品是否存在
                    RealmResults<CarGoods> carGoods=realm.where(CarGoods.class)
                            .equalTo("goodsID", goods.getGoodsID()).findAll();
                    if(carGoods.size()==0){
//                        商品不存在，直接插入商品
                        CarShop shop=carShop.get(0);
                        shop.getGoods().add(goods);
                    }else {
//                        商品存在添加商品个数
                        CarGoods goods1=carGoods.get(0);
                        goods1.setNumber((Integer.parseInt(goods1.getNumber())+Integer.parseInt(goods.getNumber()))+"");
                    }


                }

            }
        });
        return realm.asObservable();
    }
    @Override
    public Observable<ReturnComment> getShopComment(int id, int page) {
        Observable<ReturnComment> Comment = mServiceManager.
                getGetCommentsService().getComment(id,page);
        return Comment;
    }
}
