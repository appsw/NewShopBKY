package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.ReturnUser;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

public interface LoginService {
    @FormUrlEncoded
    @POST("/user/user_login")
    Observable<ReturnUser> login (@Field("user_name") String user_name,
                                  @Field("password") String password);

}
