package bai.kang.yun.zxd.mvp.model.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/16 0016.
 */

public class ReturnGoods {
    private int status;
    private String Message;
    private String url;
    private List<DataEntity> data;

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

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity{
        private String productname;     //名字
        private String imageurl;          //图片地址
        private String guige;            //规格
        private String productcode;     //准字号
        private String unit;            //单位
        private int shop_category;      //分类id
        private int id;                 //商品id
        private int shop_id;            //店铺id
        private float market_price;     //市场价
        private float saleprice;        //商城价

        public String getProductname() {
            return productname;
        }

        public void setProductname(String productname) {
            this.productname = productname;
        }

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

        public String getGuige() {
            return guige;
        }

        public void setGuige(String guige) {
            this.guige = guige;
        }

        public String getProductcode() {
            return productcode;
        }

        public void setProductcode(String productcode) {
            this.productcode = productcode;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getShop_category() {
            return shop_category;
        }

        public void setShop_category(int shop_category) {
            this.shop_category = shop_category;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public float getMarket_price() {
            return market_price;
        }

        public void setMarket_price(float market_price) {
            this.market_price = market_price;
        }

        public float getSaleprice() {
            return saleprice;
        }

        public void setSaleprice(float saleprice) {
            this.saleprice = saleprice;
        }

        @Override
        public String toString() {
            return "DataEntity{" +
                    "productname='" + productname + '\'' +
                    ", imageurl='" + imageurl + '\'' +
                    ", guige='" + guige + '\'' +
                    ", productcode='" + productcode + '\'' +
                    ", unit='" + unit + '\'' +
                    ", shop_category=" + shop_category +
                    ", id=" + id +
                    ", shop_id=" + shop_id +
                    ", market_price=" + market_price +
                    ", saleprice=" + saleprice +
                    '}';
        }
    }
}
