package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.ReturnPayUrl;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/14 0014.
 */

public interface GetAlipayUrlService {
    @GET("/sweep/payment/{id}/{salt}/{payid}/{orderid}")
    Observable<ReturnPayUrl> geturl (@Path("id") int id, @Path("salt") String salt, @Path("payid") String payid, @Path("orderid") int orderid);
}
