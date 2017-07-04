package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.ReturnApp;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/23 0023.
 */

public interface GetAppVersionService {
    @GET("/app/get_version")
    Observable<ReturnApp> get();
}
