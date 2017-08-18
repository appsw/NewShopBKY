package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.ReturnShop;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public interface GetShopListService {
    @GET("/goods/get_shop_list/{kind}/{id}/{page}/{price}/{stock}")
    Observable<ReturnShop> getShopList (@Path("kind") int kind, @Path("id") int id, @Path("page") int page
            , @Path("price") String price, @Path("stock") String stock);
}
