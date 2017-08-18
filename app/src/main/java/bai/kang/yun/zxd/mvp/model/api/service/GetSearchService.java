package bai.kang.yun.zxd.mvp.model.api.service;

import bai.kang.yun.zxd.mvp.model.entity.CategoryGoods;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/20 0020.
 */

public interface GetSearchService {
    @GET("/goods/search_goods/{kind}/{key}/{page}/{price}/{spcount}")
    Observable<CategoryGoods> getGoods (@Path("kind") int kind,
                                         @Path("key") String key,
                                         @Path("page") int page,
                                         @Path("price") String price,
                                         @Path("spcount") String spcount);
}
