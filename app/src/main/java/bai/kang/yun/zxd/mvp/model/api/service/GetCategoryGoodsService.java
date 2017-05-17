package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.CategoryGoods;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/5/17 0017.
 */

public interface GetCategoryGoodsService {
    @GET("/goods/get_goods_by_category/{id}/{page}")
    Observable<CategoryGoods> getCategory (@Query("id") int id, @Query("page") int page);
}
