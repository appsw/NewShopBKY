package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.Banner;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2017/4/12 0012.
 */

public interface BannerService {
//    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";
//
//    @Headers({HEADER_API_VERSION})
    @GET("/app/get_banner")
    Observable<Banner> getBannerUrl();
}
