package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.ReturnRegion;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/5/25 0025.
 */

public interface GetRegionService {
    @GET("/app/get_ares_list/{id}")
    Observable<ReturnRegion> getRegion (@Path("id") int id);
}
