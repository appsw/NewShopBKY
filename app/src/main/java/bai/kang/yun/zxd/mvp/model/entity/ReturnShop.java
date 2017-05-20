package bai.kang.yun.zxd.mvp.model.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/20 0020.
 */

public class ReturnShop {
    private int status;
    private String Message;
    private String url;
    private List<DataEntity> data;


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


    }
}
