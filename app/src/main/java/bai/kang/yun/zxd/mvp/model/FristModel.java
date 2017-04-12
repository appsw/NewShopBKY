package bai.kang.yun.zxd.mvp.model;

import com.jess.arms.mvp.BaseModel;

import java.util.Map;

import javax.inject.Inject;

import bai.kang.yun.zxd.mvp.contract.FristContract;
import bai.kang.yun.zxd.mvp.model.api.cache.CacheManager;
import bai.kang.yun.zxd.mvp.model.api.service.ServiceManager;
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
    public Observable<Object> getBannerUrl() {
        Observable<Map<String,String>> banner = mServiceManager.getBannerService().getBannerUrl();
        return mCacheManager.getCommonCache()
                .getBannres(banner
                        ,null
                        , null)
                .flatMap(new Func1<Reply<Map<String, String>>, Observable<?>>() {
                    @Override
                    public Observable<?> call(Reply<Map<String, String>> mapReply) {
                        return Observable.just(mapReply.getData());
                    }
                });
    }
}
