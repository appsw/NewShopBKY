package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.ReturnGoods;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2017/4/17 0017.
 */

public interface GoodsGridService {
//    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";
//
//    @Headers({HEADER_API_VERSION})
    @GET("/goods/get_recommend")
    Observable<ReturnGoods> getGoodsGrid ();

}
