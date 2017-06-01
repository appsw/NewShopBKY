package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.ShopCategoryGoods;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/1 0001.
 */

public interface GetShopCategoryGoodsService {
    @GET("/goods/get_goods_shop_page/{id}/{kind}/{page}")
    Observable<ShopCategoryGoods> getShopCategoryGoods (@Path("id") int id,
                                                   @Path("kind") int kind,
                                                   @Path("page") int page);
}
