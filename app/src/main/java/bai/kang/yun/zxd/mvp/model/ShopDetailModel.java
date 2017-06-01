package bai.kang.yun.zxd.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import bai.kang.yun.zxd.mvp.contract.ShopDetailContract;
import bai.kang.yun.zxd.mvp.model.api.cache.CacheManager;
import bai.kang.yun.zxd.mvp.model.api.service.ServiceManager;
import bai.kang.yun.zxd.mvp.model.entity.ReturnShopCategory;
import bai.kang.yun.zxd.mvp.model.entity.ReturnShopDetail;
import bai.kang.yun.zxd.mvp.model.entity.ReturnShopGoods;
import io.rx_cache.DynamicKey;
import io.rx_cache.DynamicKeyGroup;
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
 * Created by Administrator on 2017/4/27 0027.
 */

@ActivityScope
public class ShopDetailModel extends BaseModel<ServiceManager, CacheManager> implements ShopDetailContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public ShopDetailModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson, Application application) {
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
    public Observable<ReturnShopDetail> getShopDetail(int id) {
        Observable<ReturnShopDetail> shops = mServiceManager.
                getGetShopDetailService().getShopDetail(id);
        //使用rxcache缓存,上拉刷新则不读取缓存,加载更多读取缓存
        return mCacheManager.getCommonCache()
                .getShopDetail(shops
                        ,new DynamicKey(id)
                        )
                .flatMap(new Func1<Reply<ReturnShopDetail>, Observable<ReturnShopDetail>>() {
                    @Override
                    public Observable<ReturnShopDetail> call(Reply<ReturnShopDetail> listReply) {
                        return Observable.just(listReply.getData());
                    }
                });
    }

    @Override
    public Observable<ReturnShopGoods> getShopGoods(int kind, int id) {
        Observable<ReturnShopGoods> shops = mServiceManager.
                getGetShopGoodsService().getShopgoods(kind,id);
        //使用rxcache缓存,上拉刷新则不读取缓存,加载更多读取缓存
        return mCacheManager.getCommonCache()
                .getShopGoods(shops
                        ,new DynamicKeyGroup(kind,id)
                )
                .flatMap(new Func1<Reply<ReturnShopGoods>, Observable<ReturnShopGoods>>() {
                    @Override
                    public Observable<ReturnShopGoods> call(Reply<ReturnShopGoods> listReply) {
                        return Observable.just(listReply.getData());
                    }
                });
    }

    @Override
    public Observable<ReturnShopCategory> getShopCategory(int id) {
        Observable<ReturnShopCategory> shops = mServiceManager.
                getGetShopCategoryService().getShopCategory(id);
        //使用rxcache缓存,上拉刷新则不读取缓存,加载更多读取缓存
        return mCacheManager.getCommonCache()
                .getShopCategory(shops
                        ,new DynamicKey(id)
                )
                .flatMap(new Func1<Reply<ReturnShopCategory>, Observable<ReturnShopCategory>>() {
                    @Override
                    public Observable<ReturnShopCategory> call(Reply<ReturnShopCategory> listReply) {
                        return Observable.just(listReply.getData());
                    }
                });
    }
}
