package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.ReturenExpress;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/10 0010.
 */

public interface GetExpressListService {
    @GET("/order/get_wuliu_list/{uid}/{salt}/{storeId}/{weight}/{deliver_id}")
    Observable<ReturenExpress> getExpress (@Path("uid") int uid,
                                            @Path("salt") String salt,
                                            @Path("storeId") int storeId,
                                            @Path("weight") int weight,
                                            @Path("deliver_id") int deliver_id);
}
