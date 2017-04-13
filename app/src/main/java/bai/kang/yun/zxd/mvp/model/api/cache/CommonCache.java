package bai.kang.yun.zxd.mvp.model.api.cache;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import bai.kang.yun.zxd.mvp.model.entity.Goods;
import bai.kang.yun.zxd.mvp.model.entity.User;
import io.rx_cache.DynamicKey;
import io.rx_cache.EvictProvider;
import io.rx_cache.LifeCache;
import io.rx_cache.Reply;
import rx.Observable;

/**
 * Created by jess on 8/30/16 13:53
 * Contact with jess.yan.effort@gmail.com
 */
public interface CommonCache {



    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<List<User>>> getUsers(Observable<List<User>> oUsers, DynamicKey idLastUserQueried, EvictProvider evictProvider);
    Observable<Reply<Map<String,String>>> getBannres(Observable<Map<String,String>> oBanners);
    Observable<Reply<List<Goods>>> getGoodsList(Observable<List<Goods>> oBanners, EvictProvider evictProvider);
}
