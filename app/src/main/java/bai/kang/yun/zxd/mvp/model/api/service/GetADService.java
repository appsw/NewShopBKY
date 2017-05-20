package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.Advertisement;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2017/5/16 0016.
 */

public interface GetADService {

    @GET("/app/get_adSlot")
    Observable<Advertisement> getAD ();
}
