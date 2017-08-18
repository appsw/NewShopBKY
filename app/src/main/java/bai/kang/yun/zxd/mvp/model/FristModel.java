package bai.kang.yun.zxd.mvp.model;

import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import bai.kang.yun.zxd.mvp.contract.FristContract;
import bai.kang.yun.zxd.mvp.model.api.cache.CacheManager;
import bai.kang.yun.zxd.mvp.model.api.service.ServiceManager;
import bai.kang.yun.zxd.mvp.model.entity.Advertisement;
import bai.kang.yun.zxd.mvp.model.entity.Banner;
import bai.kang.yun.zxd.mvp.model.entity.ReturnADGrid;
import bai.kang.yun.zxd.mvp.model.entity.ReturnGoods;
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
    public Observable<Banner> getBannerUrl() {
        Observable<Banner> banner = mServiceManager.getBannerService().getBannerUrl();
        return mCacheManager.getCommonCache()
                .getBannres(banner)
                .flatMap(new Func1<Reply<Banner>, Observable<Banner>>() {
                    @Override
                    public Observable<Banner> call(Reply<Banner> mapReply) {
                        return Observable.just(mapReply.getData());
                    }
                });
    }

    @Override
    public Observable<Advertisement> getAD() {
        Observable<Advertisement> advertisement = mServiceManager.getGetADService().getAD();
        return mCacheManager.getCommonCache()
                .getAD(advertisement)
                .flatMap(new Func1<Reply<Advertisement>, Observable<Advertisement>>() {
                    @Override
                    public Observable<Advertisement> call(Reply<Advertisement> mapReply) {
                        return Observable.just(mapReply.getData());
                    }
                });
    }

    @Override
    public Observable<ReturnGoods> getGoodsGrid() {
        Observable<ReturnGoods> goods = mServiceManager.getGoodsGridService()
                .getGoodsGrid();
        //使用rxcache缓存,上拉刷新则不读取缓存,加载更多读取缓存
        return mCacheManager.getCommonCache()
                .getGoodsGrid(goods)
                .flatMap(new Func1<Reply<ReturnGoods>, Observable<ReturnGoods>>() {
                    @Override
                    public Observable<ReturnGoods> call(Reply<ReturnGoods> listReply) {
                        return Observable.just(listReply.getData());
                    }
                });
    }

    @Override
    public Observable<ReturnADGrid> getADGrid() {
        Observable<ReturnADGrid> goods = mServiceManager.getGetADGrid().getADGrid();
        //使用rxcache缓存,上拉刷新则不读取缓存,加载更多读取缓存
        return mCacheManager.getCommonCache()
                .getADGrid(goods)
                .flatMap(new Func1<Reply<ReturnADGrid>, Observable<ReturnADGrid>>() {
                    @Override
                    public Observable<ReturnADGrid> call(Reply<ReturnADGrid> listReply) {
                        return Observable.just(listReply.getData());
                    }
                });
    }
}
