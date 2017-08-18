package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.ReturnADGrid;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2017/8/10 0010.
 */

public interface GetADGrid {
    @GET("/app/get_ADIndex")
    Observable<ReturnADGrid> getADGrid();
}
