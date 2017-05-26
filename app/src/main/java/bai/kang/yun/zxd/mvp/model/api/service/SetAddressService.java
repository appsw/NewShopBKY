package bai.kang.yun.zxd.mvp.model.api.service;

import java.util.Map;

import bai.kang.yun.zxd.mvp.model.entity.ReturnSetAdd;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/5/24 0024.
 */

public interface SetAddressService {
    @FormUrlEncoded
    @POST("/user/save_user_deliver/{uid}/{salt}")
    Observable<ReturnSetAdd> setAddress (@Path("uid") int uid,
                                         @Path("salt") String salt,
                                         @FieldMap Map<String,String> map);
}
//【int id】：收货地址编号 【int User_id】 ： 用户ID 【int Province_id】 ： 省份ID
// 【int City_id】 ： 城市ID 【int Area_id】 ： 地区ID 【string Phone】 ： 手机号
// 【string Real_name】 ： 联系人名字 【string Address】 ： 详细地址
// 【string Zipcode】 ： 邮政编码 【string Areaname】 ：地图坐标
// 【int Isdefault】 ： 是否默认（1,0） 【string tel】 ： 座机电话