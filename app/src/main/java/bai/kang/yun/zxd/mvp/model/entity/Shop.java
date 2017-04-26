package bai.kang.yun.zxd.mvp.model.entity;

/**
 * Created by Administrator on 2017/4/26 0026.
 */

public class Shop {
    private String Name;        //店铺名称
    private String Add;         //店铺地址
    private String goods_Price; //商品价格
    private String probability; //好评率
    private String imgUrl;      //图片地址
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAdd() {
        return Add;
    }

    public void setAdd(String add) {
        Add = add;
    }

    public String getGoods_Price() {
        return goods_Price;
    }

    public void setGoods_Price(String goods_Price) {
        this.goods_Price = goods_Price;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
