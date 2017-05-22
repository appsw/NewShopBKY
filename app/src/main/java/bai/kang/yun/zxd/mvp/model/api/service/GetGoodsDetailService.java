package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.ReturnDetail;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/5/22 0022.
 */

public interface GetGoodsDetailService {
    @GET("/goods/get_goods_detail/{id}")
    Observable<ReturnDetail> getGoodsDetail (@Path("id") int id);

}
