package bai.kang.yun.zxd.mvp.model.api.service;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import rx.Observable;

/**
 * Created by Administrator on 2017/4/12 0012.
 */

public interface BannerService {
    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";

    @Headers({HEADER_API_VERSION})
    @GET("/users")
    Observable<Map<String,String>> getBannerUrl();
}
