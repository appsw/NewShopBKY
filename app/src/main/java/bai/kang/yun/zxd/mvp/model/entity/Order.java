package bai.kang.yun.zxd.mvp.model.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/8 0008.
 */

public class Order {
    String ShopName;
    String preact;
    List<Goods> goodses;

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public String getPreact() {
        return preact;
    }

    public void setPreact(String preact) {
        this.preact = preact;
    }

    public List<Goods> getGoodses() {
        return goodses;
    }

    public void setGoodses(List<Goods> goodses) {
        this.goodses = goodses;
    }
}
