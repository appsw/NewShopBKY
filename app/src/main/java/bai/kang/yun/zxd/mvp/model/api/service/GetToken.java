package bai.kang.yun.zxd.mvp.model.api.service;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

public interface GetToken {
    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";

    @Headers({HEADER_API_VERSION})
    @GET("/banners")
    Observable<Map<String,String>> getBannerUrl(@Query("since") int lastIdQueried);
}
