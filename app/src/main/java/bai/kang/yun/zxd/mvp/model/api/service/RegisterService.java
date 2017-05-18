package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.PhoneYzm;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/5/17 0017.
 */

public interface RegisterService {
    @FormUrlEncoded
    @POST("/user/user_regist")
    Observable<PhoneYzm> getCategory (@Field("password") String password,
                                      @Field("user_name") String user_name,
                                      @Field("mobile") String mobile);
}
