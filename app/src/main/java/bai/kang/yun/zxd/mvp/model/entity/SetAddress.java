package bai.kang.yun.zxd.mvp.model.entity;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/24 0024.
 */

public class SetAddress {

    private int id;  //收货地址编号
    private int User_id;  //用户ID
    private int Province_id;// 省份ID
    private int City_id;  // 城市ID
    private int Area_id;  //地区ID
    private int Isdefault;  // 是否默认（1,0）
    private String Phone;  // 手机号
    private String Real_name;  // 联系人名字
    private String Address;     // 详细地址
    private String Zipcode;     // 邮政编码
    private String Areaname;    //地图坐标
    private String tel;         // 座机电话

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public int getProvince_id() {
        return Province_id;
    }

    public void setProvince_id(int province_id) {
        Province_id = province_id;
    }

    public int getCity_id() {
        return City_id;
    }

    public void setCity_id(int city_id) {
        City_id = city_id;
    }

    public int getArea_id() {
        return Area_id;
    }

    public void setArea_id(int area_id) {
        Area_id = area_id;
    }

    public int getIsdefault() {
        return Isdefault;
    }

    public void setIsdefault(int isdefault) {
        Isdefault = isdefault;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getReal_name() {
        return Real_name;
    }

    public void setReal_name(String real_name) {
        Real_name = real_name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getZipcode() {
        return Zipcode;
    }

    public void setZipcode(String zipcode) {
        Zipcode = zipcode;
    }

    public String getAreaname() {
        return Areaname;
    }

    public void setAreaname(String areaname) {
        Areaname = areaname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    /**
     * 将实体类转换成请求参数,以map<k,v>形式返回
     *
     * @return
     */
    public Map<String, String> getMapParams() {
        Class<? extends SetAddress> clazz = this.getClass();
        Class<? extends Object> superclass = clazz.getSuperclass();

        Field[] fields = clazz.getDeclaredFields();
        Field[] superFields = superclass.getDeclaredFields();

        if (fields == null || fields.length == 0) {
            return Collections.emptyMap();
        }

        Map<String, String> params = new HashMap<String, String>();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                params.put(field.getName(), String.valueOf(field.get(this)));
            }

            for (Field superField : superFields) {
                superField.setAccessible(true);
                params.put(superField.getName(), String.valueOf(superField.get(this)));
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return params;
    }
}
