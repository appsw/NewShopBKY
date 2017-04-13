package bai.kang.yun.zxd.mvp.model.api.service;

import java.util.List;

import bai.kang.yun.zxd.mvp.model.entity.Goods;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public interface GoodsListService {
    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";

    @Headers({HEADER_API_VERSION})
    @GET("/goodslist")
    Observable<List<Goods>> getGoodslist(@Query("id") int id);
}
