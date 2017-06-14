package bai.kang.yun.zxd.mvp.model.entity;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class CarShop extends RealmObject {
    /** 是否处于编辑状态 */
    private boolean isEditing;
    /** 组是否被选中 */
    private boolean isGroupSelected;
    /** 店铺名称 */
    private String merchantName;
    /** 店铺订单总价格 */
    private float shopSum;
    /** 店铺订单配送ID */
    private int TrafficId ;
    /** 店铺订单总重量*/
    private int Weight;
    /** 店铺订单是否含有处方药*/
    private int is_chufangi;
    /** 店铺ID */
    private String merID;
    /** 是否失效列表 */
    private boolean isInvalidList;

    private boolean isAllGoodsInvalid;

    public RealmList<CarGoods> goods;

    public int getIs_chufangi() {
        return is_chufangi;
    }

    public void setIs_chufangi(int is_chufangi) {
        this.is_chufangi = is_chufangi;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }

    public int getTrafficId() {
        return TrafficId;
    }

    public void setTrafficId(int trafficId) {
        TrafficId = trafficId;
    }

    public float getShopSum() {
        return shopSum;
    }

    public void setShopSum(float shopSum) {
        this.shopSum = shopSum;
    }

    public boolean isEditing() {
        return isEditing;
    }

    public void setEditing(boolean editing) {
        isEditing = editing;
    }

    public boolean isGroupSelected() {
        return isGroupSelected;
    }

    public void setGroupSelected(boolean groupSelected) {
        isGroupSelected = groupSelected;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerID() {
        return merID;
    }

    public void setMerID(String merID) {
        this.merID = merID;
    }

    public boolean isInvalidList() {
        return isInvalidList;
    }

    public void setInvalidList(boolean invalidList) {
        isInvalidList = invalidList;
    }

    public boolean isAllGoodsInvalid() {
        return isAllGoodsInvalid;
    }

    public void setAllGoodsInvalid(boolean allGoodsInvalid) {
        isAllGoodsInvalid = allGoodsInvalid;
    }

    public RealmList<CarGoods> getGoods() {
        return goods;
    }

    public void setGoods(RealmList<CarGoods> goods) {
        this.goods = goods;
    }
}
