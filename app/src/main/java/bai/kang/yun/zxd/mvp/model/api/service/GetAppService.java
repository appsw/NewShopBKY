package bai.kang.yun.zxd.mvp.model.api.service;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/24 0024.
 */

public interface GetAppService {
    @GET("/upload/app/baikangyun.apk")
    Observable<ResponseBody> get();
}
