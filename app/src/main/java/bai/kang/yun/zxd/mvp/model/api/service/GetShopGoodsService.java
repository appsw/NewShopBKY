package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.ReturnShopGoods;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/5/31 0031.
 */

public interface GetShopGoodsService {
    @GET("/goods/get_goods_shop_recommend/{kind}/{id}")
    Observable<ReturnShopGoods> getShopgoods (@Path("kind") int kind, @Path("id") int id);
}
