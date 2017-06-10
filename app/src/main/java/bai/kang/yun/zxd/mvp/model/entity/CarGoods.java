package bai.kang.yun.zxd.mvp.model.entity;

import io.realm.RealmObject;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class CarGoods extends RealmObject {
    /** 数量 */
    private String number = "1";
    /** 商品ID */
    private String goodsID;
    /** 商品名称 */
    private String goodsName;
    /** 商品宣传图片 */
    private String goodsLogo;
    /** 商品规格 */
    private String pdtDesc;
    /** 市场价，原价 */
    private String mkPrice;
    /** 价格，当前价格 */
    private String price;
    /** 是否失效,0删除(失效),1正常 */
    private String status;
    /** 是否是编辑状态 */
    private boolean isEditing;
    /** 是否被选中 */
    private boolean isChildSelected;
    /** 规格ID */
    private String productID;
    /** 商品重量 */
    private int weight;
    /** 店铺ID */
    private String FID;
    /** 店铺名 */
    private String FName;
    /***/
    private String sellPloyID;

    /** 是否是失效列表的子项 */
    private boolean isInvalidItem;

    /** 是否属于 */
    private boolean isBelongInvalidList;

    /** 临时解决方案，为了避免尾部重绘两次，增加一个虚ITEM，不显示，但是没有子项的组项，会有一条黑线，所以需要做特殊处理 */
    private boolean isLastTempItem;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getFID() {
        return FID;
    }

    public void setFID(String FID) {
        this.FID = FID;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsLogo() {
        return goodsLogo;
    }

    public void setGoodsLogo(String goodsLogo) {
        this.goodsLogo = goodsLogo;
    }

    public String getPdtDesc() {
        return pdtDesc;
    }

    public void setPdtDesc(String pdtDesc) {
        this.pdtDesc = pdtDesc;
    }

    public String getMkPrice() {
        return mkPrice;
    }

    public void setMkPrice(String mkPrice) {
        this.mkPrice = mkPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isEditing() {
        return isEditing;
    }

    public void setEditing(boolean editing) {
        isEditing = editing;
    }

    public boolean isChildSelected() {
        return isChildSelected;
    }

    public void setChildSelected(boolean childSelected) {
        isChildSelected = childSelected;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getSellPloyID() {
        return sellPloyID;
    }

    public void setSellPloyID(String sellPloyID) {
        this.sellPloyID = sellPloyID;
    }

    public boolean isInvalidItem() {
        return isInvalidItem;
    }

    public void setInvalidItem(boolean invalidItem) {
        isInvalidItem = invalidItem;
    }

    public boolean isBelongInvalidList() {
        return isBelongInvalidList;
    }

    public void setBelongInvalidList(boolean belongInvalidList) {
        isBelongInvalidList = belongInvalidList;
    }

    public boolean isLastTempItem() {
        return isLastTempItem;
    }

    public void setLastTempItem(boolean lastTempItem) {
        isLastTempItem = lastTempItem;
    }
}
