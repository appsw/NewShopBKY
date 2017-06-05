package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.ReturnSetAdd;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/5 0005.
 */

public interface SetUserHeadPicService {
    @POST("/user/set_head_img/{uid}/{salt}")
    @Multipart
    Observable<ReturnSetAdd> uploadFile(@Path("uid") int uid,
                                        @Path("salt") String salt,
                                        @Part MultipartBody.Part MultipartFile);
}
