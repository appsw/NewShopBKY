package bai.kang.yun.zxd.mvp.model.entity;

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
}
