package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.PhoneYzm;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/5/17 0017.
 */

public interface GetPhoneYzm {
//    @FormUrlEncoded
    @GET("/app/send_sms")
    Observable<PhoneYzm> getCategory (@Query("mobile") String mobile);
}
