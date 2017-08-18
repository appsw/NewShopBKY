package bai.kang.yun.zxd.mvp.model.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/20 0020.
 */

public class ReturnDetail {
    //哈哈哈哈
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
        private int shop_id;                    //店铺id
        private int salestatus;             //销售状态
        private float saleprice;                //售价
        private int weight;                     //重量
        private int is_chufangi;                //是否处方药
        private float market_price;                //市场价
        private int stock;                          //库存

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public int getIs_chufangi() {
            return is_chufangi;
        }

        public void setIs_chufangi(int is_chufangi) {
            this.is_chufangi = is_chufangi;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public float getMarket_price() {
            return market_price;
        }

        public void setMarket_price(float market_price) {
            this.market_price = market_price;
        }

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

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }
    }
    public static class InfoEntity{

        private int is_chufang;         //是否处方
        private int IsImported;          //是否进口
        private String Pizhunwenhao;   //卫生许可证号
        private String DrugstyName;            //产品名称
        private String Manufacturers;       //生产厂商
        private String DrugsSymptom;        //性状
        private String DrugsStore;           //储存条件
        private String ValidityTime;     //有效期
        private String Zhuyaochengfen;            //主要成分
        private String Jinji;              //禁忌
        private String Xianghuzuoyong;             //相互作用
        private String Yaoliduli;              //药理毒理
        private String Gongnengzhuzhi;       //功能主治
        private String Yongfayongliang;       //用法用量
        private String Buliangfanying;       //不良反应
        private String Zhuyishixiang;       //注意事项

        public int getIs_chufang() {
            return is_chufang;
        }

        public void setIs_chufang(int is_chufang) {
            this.is_chufang = is_chufang;
        }

        public int getIsImported() {
            return IsImported;
        }

        public void setIsImported(int isImported) {
            IsImported = isImported;
        }

        public String getPizhunwenhao() {
            return Pizhunwenhao;
        }

        public void setPizhunwenhao(String pizhunwenhao) {
            Pizhunwenhao = pizhunwenhao;
        }

        public String getDrugstyName() {
            return DrugstyName;
        }

        public void setDrugstyName(String drugstyName) {
            DrugstyName = drugstyName;
        }

        public String getManufacturers() {
            return Manufacturers;
        }

        public void setManufacturers(String manufacturers) {
            Manufacturers = manufacturers;
        }

        public String getDrugsSymptom() {
            return DrugsSymptom;
        }

        public void setDrugsSymptom(String drugsSymptom) {
            DrugsSymptom = drugsSymptom;
        }

        public String getDrugsStore() {
            return DrugsStore;
        }

        public void setDrugsStore(String drugsStore) {
            DrugsStore = drugsStore;
        }

        public String getValidityTime() {
            return ValidityTime;
        }

        public void setValidityTime(String validityTime) {
            ValidityTime = validityTime;
        }

        public String getZhuyaochengfen() {
            return Zhuyaochengfen;
        }

        public void setZhuyaochengfen(String zhuyaochengfen) {
            Zhuyaochengfen = zhuyaochengfen;
        }

        public String getJinji() {
            return Jinji;
        }

        public void setJinji(String jinji) {
            Jinji = jinji;
        }

        public String getXianghuzuoyong() {
            return Xianghuzuoyong;
        }

        public void setXianghuzuoyong(String xianghuzuoyong) {
            Xianghuzuoyong = xianghuzuoyong;
        }

        public String getYaoliduli() {
            return Yaoliduli;
        }

        public void setYaoliduli(String yaoliduli) {
            Yaoliduli = yaoliduli;
        }

        public String getGongnengzhuzhi() {
            return Gongnengzhuzhi;
        }

        public void setGongnengzhuzhi(String gongnengzhuzhi) {
            Gongnengzhuzhi = gongnengzhuzhi;
        }

        public String getYongfayongliang() {
            return Yongfayongliang;
        }

        public void setYongfayongliang(String yongfayongliang) {
            Yongfayongliang = yongfayongliang;
        }

        public String getBuliangfanying() {
            return Buliangfanying;
        }

        public void setBuliangfanying(String buliangfanying) {
            Buliangfanying = buliangfanying;
        }

        public String getZhuyishixiang() {
            return Zhuyishixiang;
        }

        public void setZhuyishixiang(String zhuyishixiang) {
            Zhuyishixiang = zhuyishixiang;
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
