package bai.kang.yun.zxd.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import bai.kang.yun.zxd.mvp.contract.GoodsListContract;
import bai.kang.yun.zxd.mvp.model.api.cache.CacheManager;
import bai.kang.yun.zxd.mvp.model.api.service.ServiceManager;
import bai.kang.yun.zxd.mvp.model.entity.CategoryGoods;
import bai.kang.yun.zxd.mvp.model.entity.ShopCategoryGoods;
import io.rx_cache.DynamicKeyGroup;
import io.rx_cache.EvictDynamicKey;
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
 * Created by Administrator on 2017/4/13 0013.
 */

@ActivityScope
public class GoodsListModel extends BaseModel<ServiceManager, CacheManager> implements GoodsListContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public GoodsListModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson, Application application) {
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
    public Observable<CategoryGoods> getGoodslist(int id,int page, boolean update) {
        Observable<CategoryGoods> Goods = mServiceManager.
                getCategoryGoodsService().getCategory(id,page);
        //使用rxcache缓存,上拉刷新则不读取缓存,加载更多读取缓存
        return mCacheManager.getCommonCache()
                .getCategoryGoods(Goods
                        ,new DynamicKeyGroup(id,page)
                ,new EvictDynamicKey(update))
                .flatMap(new Func1<Reply<CategoryGoods>, Observable<CategoryGoods>>() {
                    @Override
                    public Observable<CategoryGoods> call(Reply<CategoryGoods> listReply) {
                        return Observable.just(listReply.getData());
                    }
                });
    }

    @Override
    public Observable<ShopCategoryGoods> getShopGoodslist(int id,int kind, int page, boolean updata) {
        Observable<ShopCategoryGoods> Goods = mServiceManager.
                getGetShopCategoryGoodsService().getShopCategoryGoods(id,kind,page);
        //使用rxcache缓存,上拉刷新则不读取缓存,加载更多读取缓存
        return mCacheManager.getCommonCache()
                .getShopCategoryGoods(Goods
                        ,new DynamicKeyGroup(id,page)
                        ,new EvictDynamicKey(updata))
                .flatMap(new Func1<Reply<ShopCategoryGoods>, Observable<ShopCategoryGoods>>() {
                    @Override
                    public Observable<ShopCategoryGoods> call(Reply<ShopCategoryGoods> listReply) {
                        return Observable.just(listReply.getData());
                    }
                });
    }

    @Override
    public Observable<CategoryGoods> getGoods(int kind, String key, int page, boolean updata) {
        Observable<CategoryGoods> Goods = mServiceManager.
                getGetSearchService().getGoods(kind,key,page);

        //使用rxcache缓存,上拉刷新则不读取缓存,加载更多读取缓存
        return mCacheManager.getCommonCache()
                .getSerchGoods(Goods
                        ,new DynamicKeyGroup(key,page)
                        ,new EvictDynamicKey(updata))
                .flatMap(new Func1<Reply<CategoryGoods>, Observable<CategoryGoods>>() {
                    @Override
                    public Observable<CategoryGoods> call(Reply<CategoryGoods> listReply) {
                        return Observable.just(listReply.getData());
                    }
                });
    }
}
