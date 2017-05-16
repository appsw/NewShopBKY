package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.Token;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

public interface GetTokenService {


//    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";
//
//    @Headers({HEADER_API_VERSION})

    @FormUrlEncoded
    @POST("/OAuth/get_token")
    Observable<Token> getToken (@Field("client_id") String client_id,
                                @Field("redirect_uri") String redirect_uri,
                                @Field("scope") String scope,
                                @Field("response_type") String response_type);


}
