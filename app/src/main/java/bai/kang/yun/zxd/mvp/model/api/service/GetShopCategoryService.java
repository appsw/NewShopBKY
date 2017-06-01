package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.ReturnShopCategory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/1 0001.
 */

public interface GetShopCategoryService {
    @GET("/goods/get_category_shop/{id}")
    Observable<ReturnShopCategory> getShopCategory (@Path("id") int id);
}
