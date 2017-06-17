package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.ReturnDefaultAdd;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/17 0017.
 */

public interface GetDefaultAddService {
    @GET("/user/get_deliver_default/{uid}/{salt}")
    Observable<ReturnDefaultAdd> getDefaultAdd (@Path("uid") int uid,
                                                @Path("salt") String salt);
}
