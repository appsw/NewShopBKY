package bai.kang.yun.zxd.mvp.model.api.service;

import java.util.List;

import bai.kang.yun.zxd.mvp.model.entity.Goods;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/4/17 0017.
 */

public interface GoodsGridService {
    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";

    @Headers({HEADER_API_VERSION})
    @GET("/goodsgrid")
    Observable<List<Goods>> getGoodsGrid(@Query("id") int id);
}
