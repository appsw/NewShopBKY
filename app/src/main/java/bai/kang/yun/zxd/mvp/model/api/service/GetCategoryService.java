package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.ReturnCategory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/5/16 0016.
 */

public interface GetCategoryService {

    @GET("/goods/get_category_list/{id}")
    Observable<ReturnCategory> getCategory (@Path("id") int id);
}
