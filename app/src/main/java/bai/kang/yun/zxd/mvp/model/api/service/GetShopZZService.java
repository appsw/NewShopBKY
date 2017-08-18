package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.ReturnShopZZ;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/31 0031.
 */

public interface GetShopZZService {
    @GET("/goods/get_shop_zizhi/{id}/1")
    Observable<ReturnShopZZ> getShopZZ (@Path("id") int id);
}
