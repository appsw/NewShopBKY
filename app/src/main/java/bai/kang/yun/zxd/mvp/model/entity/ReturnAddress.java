package bai.kang.yun.zxd.mvp.model.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24 0024.
 */

public class ReturnAddress {
    private int status;
    private String Message;
    private String url;
    private DataEntity page_data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DataEntity getPage_data() {
        return page_data;
    }

    public void setPage_data(DataEntity page_data) {
        this.page_data = page_data;
    }

    public static class DataEntity{

        int CurrentPage;
        int TotalPages;
        int TotalItems;
        int ItemsPerPage;
        List<ItemsEntity> Items;

        public int getCurrentPage() {
            return CurrentPage;
        }

        public void setCurrentPage(int currentPage) {
            CurrentPage = currentPage;
        }

        public int getTotalPages() {
            return TotalPages;
        }

        public void setTotalPages(int totalPages) {
            TotalPages = totalPages;
        }

        public int getTotalItems() {
            return TotalItems;
        }

        public void setTotalItems(int totalItems) {
            TotalItems = totalItems;
        }

        public int getItemsPerPage() {
            return ItemsPerPage;
        }

        public void setItemsPerPage(int itemsPerPage) {
            ItemsPerPage = itemsPerPage;
        }

        public List<ItemsEntity> getItems() {
            return Items;
        }

        public void setItems(List<ItemsEntity> items) {
            Items = items;
        }
    }
    public static class ItemsEntity{
//        {"id":108,"areaname":"null","address":"daxuec","zipcode":"null",
// "real_name":"1","phone":"18764018870","tel":"null","isdefault":true,
// "user_id":226,"province_id":1152,"city_id":1153,"area_id":100,
// "area_path":"1152,1153,100","ModifiedColumns":{}
        int id;                 //收货地址id
        int user_id;            //yonghuid
        int province_id;        //省份id
        int city_id;            //城市id
        int area_id;            //区/县id
        String areaname;        //
        String address;        //详细收货地址
        String real_name;        //收货人姓名
        String phone;           //收货手机号
        String tel;             //收货电话
        String area_path;        //地址路径
        boolean isdefault;        //是否默认

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getProvince_id() {
            return province_id;
        }

        public void setProvince_id(int province_id) {
            this.province_id = province_id;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public int getArea_id() {
            return area_id;
        }

        public void setArea_id(int area_id) {
            this.area_id = area_id;
        }

        public String getAreaname() {
            return areaname;
        }

        public void setAreaname(String areaname) {
            this.areaname = areaname;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getArea_path() {
            return area_path;
        }

        public void setArea_path(String area_path) {
            this.area_path = area_path;
        }

        public boolean isdefault() {
            return isdefault;
        }

        public void setIsdefault(boolean isdefault) {
            this.isdefault = isdefault;
        }
    }

}
