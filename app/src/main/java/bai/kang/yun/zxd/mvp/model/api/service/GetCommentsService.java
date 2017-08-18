package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.ReturnComment;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/8/3 0003.
 */

public interface GetCommentsService {
    @GET("/goods/get_shop_comments/{id}/{page}")
    Observable<ReturnComment> getComment (@Path("id") int id, @Path("page") int page);
}
