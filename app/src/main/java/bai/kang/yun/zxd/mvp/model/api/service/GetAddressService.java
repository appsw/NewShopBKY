package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.ReturnAddress;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/5/24 0024.
 */

public interface GetAddressService {
    @GET("/user/get_user_deliver_list/{uid}/{salt}/{page}")
    Observable<ReturnAddress> getAddress (@Path("uid") int id,
                                          @Path("salt") String salt,
                                          @Path("page") int page);

}
