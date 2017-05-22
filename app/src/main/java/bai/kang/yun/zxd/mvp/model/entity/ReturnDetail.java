package bai.kang.yun.zxd.mvp.model.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/20 0020.
 */

public class ReturnDetail {
    //123123
    private int status;
    private String Message;
    private String url;
    private SingleEntity Single;

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

    public SingleEntity getSingle() {
        return Single;
    }

    public void setSingle(SingleEntity single) {
        Single = single;
    }

    public static class SingleEntity{
        private GoodsEntity Goods;
        private InfoEntity Info;
        private List<ImgEntity> ImgList;

        public GoodsEntity getGoods() {
            return Goods;
        }

        public void setGoods(GoodsEntity goods) {
            Goods = goods;
        }

        public InfoEntity getInfo() {
            return Info;
        }

        public void setInfo(InfoEntity info) {
            Info = info;
        }

        public List<ImgEntity> getImgList() {
            return ImgList;
        }

        public void setImgList(List<ImgEntity> imgList) {
            ImgList = imgList;
        }
    }
    public static class GoodsEntity{

        private String productname;                //名字
        private String unit;                //单位
        private String guige;                //规格
        private String imageurl;            //图片地址
        private int id;                     //商品id
        private int user_id;                    //商户id
        private int salestatus;             //销售状态
        private float saleprice;                //售价

        public String getProductname() {
            return productname;
        }

        public void setProductname(String productname) {
            this.productname = productname;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getGuige() {
            return guige;
        }

        public void setGuige(String guige) {
            this.guige = guige;
        }

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

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

        public int getSalestatus() {
            return salestatus;
        }

        public void setSalestatus(int salestatus) {
            this.salestatus = salestatus;
        }

        public float getSaleprice() {
            return saleprice;
        }

        public void setSaleprice(float saleprice) {
            this.saleprice = saleprice;
        }
    }
    public static class InfoEntity{
        private String Description;                //详细

        public String getDescription() {
            return Description;
        }

        public void setDescription(String description) {
            Description = description;
        }
    }
    public static class ImgEntity{
        private String img_url;            //图片地址
        private int id;                     //商品id
        private int productid;                    //

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getProductid() {
            return productid;
        }

        public void setProductid(int productid) {
            this.productid = productid;
        }
    }

}
