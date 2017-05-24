package bai.kang.yun.zxd.mvp.model.api.cache;

import java.util.List;
import java.util.concurrent.TimeUnit;

import bai.kang.yun.zxd.mvp.model.entity.Advertisement;
import bai.kang.yun.zxd.mvp.model.entity.Banner;
import bai.kang.yun.zxd.mvp.model.entity.CategoryGoods;
import bai.kang.yun.zxd.mvp.model.entity.Goods;
import bai.kang.yun.zxd.mvp.model.entity.ReturnAddress;
import bai.kang.yun.zxd.mvp.model.entity.ReturnCategory;
import bai.kang.yun.zxd.mvp.model.entity.ReturnDetail;
import bai.kang.yun.zxd.mvp.model.entity.ReturnGoods;
import bai.kang.yun.zxd.mvp.model.entity.ReturnShop;
import bai.kang.yun.zxd.mvp.model.entity.ShoppingCartBean;
import bai.kang.yun.zxd.mvp.model.entity.Token;
import bai.kang.yun.zxd.mvp.model.entity.User;
import io.rx_cache.DynamicKey;
import io.rx_cache.DynamicKeyGroup;
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
    @LifeCache(duration = 2, timeUnit = TimeUnit.HOURS)
    Observable<Reply<Banner>> getBannres(Observable<Banner> oBanners);
    Observable<Reply<List<Goods>>> getGoodsList(Observable<List<Goods>> oBanners, EvictProvider evictProvider);
    Observable<Reply<ReturnGoods>> getGoodsGrid(Observable<ReturnGoods> goods);

    Observable<Reply<List<ShoppingCartBean>>> getCarList(int oCarList);
    @LifeCache(duration = 2, timeUnit = TimeUnit.HOURS)
    Observable<Reply<Token>> getToken(Observable<Token> scope,DynamicKey idLastUserQueried);

    @LifeCache(duration = 7, timeUnit = TimeUnit.DAYS)
    Observable<Reply<ReturnCategory>> getCategory(Observable<ReturnCategory> scope, DynamicKey idLastUserQueried);

    @LifeCache(duration = 2, timeUnit = TimeUnit.HOURS)
    Observable<Reply<CategoryGoods>> getCategoryGoods(Observable<CategoryGoods> scope, DynamicKeyGroup idLastUserQueried, EvictProvider evictProvider);

    @LifeCache(duration = 2, timeUnit = TimeUnit.HOURS)
    Observable<Reply<ReturnShop>> getShopList(Observable<ReturnShop> scope, DynamicKeyGroup idLastUserQueried, EvictProvider evictProvider);

    @LifeCache(duration = 2, timeUnit = TimeUnit.HOURS)
    Observable<Reply<ReturnDetail>> getGoodsDetail(Observable<ReturnDetail> scope, DynamicKey id);
    @LifeCache(duration = 2, timeUnit = TimeUnit.HOURS)
    Observable<Reply<ReturnAddress>> getAddress(Observable<ReturnAddress> scope, DynamicKey id);


    Observable<Reply<Advertisement>> getAD(Observable<Advertisement> scope);
}
