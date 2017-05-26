package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.ReturnDeleteAdd;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/5/26 0026.
 */

public interface AddressDeleteService {
    @POST("/user/delete_user_deliver/{uid}/{salt}/{id}")
    Observable<ReturnDeleteAdd> delete (@Path("uid") int uid,
                                        @Path("salt") String salt,
                                        @Path("id") int id);
}
