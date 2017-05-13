package bai.kang.yun.zxd.mvp.model.api.service;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/4/12 0012.
 */

public interface BannerService {
//    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";
//
//    @Headers({HEADER_API_VERSION})
    @POST("/app/get_AD")
    Observable<Map<String,String>> getBannerUrl(@Query("data") int data);
}
