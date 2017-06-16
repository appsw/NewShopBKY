package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.ReturnSetAdd;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/16 0016.
 */

public interface SetImgChuFangService {
    @POST("/user/upload_chufang/{uid}/{salt}/{order_id}")
    @Multipart
    Observable<ReturnSetAdd> SetImgChuFang(@Path("uid") int uid,
                                        @Path("salt") String salt,
                                        @Path("order_id") int order_id,
                                        @Part MultipartBody.Part MultipartFile);
}
