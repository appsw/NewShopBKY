package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.ReturnOrderList;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/6 0006.
 */

public interface GetOrderService {
    @GET("/user/get_order_list/{uid}/{salt}/{status}/{page}")
    Observable<ReturnOrderList> getCategory (@Path("uid") int uid,
                                             @Path("salt") String salt,
                                             @Path("status") int status,
                                             @Path("page") int page);
}
