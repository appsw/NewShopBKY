package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.ReturnShopDetail;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/5/31 0031.
 */

public interface GetShopDetailService {
    @GET("/goods/get_shop_info/{id}")
    Observable<ReturnShopDetail> getShopDetail (@Path("id") int id);

}
