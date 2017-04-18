package bai.kang.yun.zxd.mvp.model;

import com.jess.arms.mvp.BaseModel;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import bai.kang.yun.zxd.mvp.contract.FristContract;
import bai.kang.yun.zxd.mvp.model.api.cache.CacheManager;
import bai.kang.yun.zxd.mvp.model.api.service.ServiceManager;
import bai.kang.yun.zxd.mvp.model.entity.Goods;
import io.rx_cache.EvictDynamicKey;
import io.rx_cache.Reply;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/4/12 0012.
 */

public class FristModel extends BaseModel<ServiceManager, CacheManager> implements FristContract.Model {
    @Inject
    public FristModel(ServiceManager serviceManager, CacheManager cacheManager) {
        super(serviceManager, cacheManager);
    }

    @Override
    public Observable<Map<String, String>> getBannerUrl() {
        Observable<Map<String,String>> banner = mServiceManager.getBannerService().getBannerUrl(1);
        return mCacheManager.getCommonCache()
                .getBannres(banner)
                .flatMap(new Func1<Reply<Map<String, String>>, Observable<Map<String, String>>>() {
                    @Override
                    public Observable<Map<String, String>> call(Reply<Map<String, String>> mapReply) {
                        return Observable.just(mapReply.getData());
                    }
                });
    }

    @Override
    public Observable<List<Goods>> getGoodsGrid(int id,boolean updata) {
        Observable<List<Goods>> Goods = mServiceManager.getGoodsGridService()
                .getGoodsGrid(id);
        //使用rxcache缓存,上拉刷新则不读取缓存,加载更多读取缓存
        return mCacheManager.getCommonCache()
                .getGoodsList(Goods
                        ,new EvictDynamicKey(updata))
                .flatMap(new Func1<Reply<List<Goods>>, Observable<List<Goods>>>() {
                    @Override
                    public Observable<List<Goods>> call(Reply<List<Goods>> listReply) {
                        return Observable.just(listReply.getData());
                    }
                });
    }
}
