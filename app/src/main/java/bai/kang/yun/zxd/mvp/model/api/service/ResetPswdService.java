package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.ReturnDeleteAdd;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/5/27 0027.
 */

public interface ResetPswdService {
    @FormUrlEncoded
    @POST("user/set_password/{uid}/{salt}")
    Observable<ReturnDeleteAdd> set (@Path("uid") int uid,
                                     @Path("salt") String salt,
                                     @Field("password") String pswd);
}
