package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.ReturnUser;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/21 0021.
 */

public interface OauthLogService {
    @FormUrlEncoded
    @POST("/user/oauth_login")
    Observable<ReturnUser> login (@Field("oauth_type") String oauth_type,
                                  @Field("openID") String openID,
                                  @Field("nickName") String nickName,
                                  @Field("headImg") String headImg);
}
