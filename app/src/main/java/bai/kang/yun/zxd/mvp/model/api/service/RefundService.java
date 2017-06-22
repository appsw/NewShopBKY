package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.ReturnSetAdd;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/22 0022.
 */

public interface RefundService {
    @POST("/user/refund_order/{uid}/{salt}/{order_id}")
    Observable<ReturnSetAdd> Cancel (@Path("uid") int uid,
                                     @Path("salt") String salt,
                                     @Path("order_id") int order_id);
}
