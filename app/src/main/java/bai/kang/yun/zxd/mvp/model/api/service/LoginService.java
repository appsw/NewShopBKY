package bai.kang.yun.zxd.mvp.model.api.service;

import java.util.List;

import bai.kang.yun.zxd.mvp.model.entity.User;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

public interface LoginService {
    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";

    @Headers({HEADER_API_VERSION})
    @GET("/users")
    Observable<List<User>> Login(@Query("name") String name, @Query("psw") String psw);

}
