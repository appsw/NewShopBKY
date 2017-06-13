package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.ReturnMakeOrder;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/13 0013.
 */

public interface MakeOrderService {
    @FormUrlEncoded
    @POST("/order/create_orders/{uid}/{salt}")
    Observable<ReturnMakeOrder> makeorder (@Path("uid") int uid,
                                           @Path("salt") String salt,
                                           @Field("orderInfo") String orderInfo);
}
