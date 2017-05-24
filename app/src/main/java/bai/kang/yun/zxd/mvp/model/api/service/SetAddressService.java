package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.SetAddress;
import bai.kang.yun.zxd.mvp.model.entity.Token;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/5/24 0024.
 */

public interface SetAddressService {
    @POST("/user/save_user_deliver/{uid}/{salt}")
    Observable<Token> getToken (@Path("uid") int uid,
                                @Path("salt") String salt,
                                @Body SetAddress add);
}
