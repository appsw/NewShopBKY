package bai.kang.yun.zxd.mvp.model.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/6/6 0006.
 */

public class ReturnOrderList {

    private int status;
    private String Message;
    private PageEntiy page_data;

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

    public PageEntiy getPage_data() {
        return page_data;
    }

    public void setPage_data(PageEntiy page_data) {
        this.page_data = page_data;
    }

    public static class PageEntiy{
        private int CurrentPage;
        private int TotalPages;
        private int TotalItems;
        private int ItemsPerPage;
        private List<ItemEntiy> Items;

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

        public List<ItemEntiy> getItems() {
            return Items;
        }

        public void setItems(List<ItemEntiy> items) {
            Items = items;
        }
    }
    public static class ItemEntiy{

        private int OrderId;
        private int Status;
        private int QueryStatus;
        private int ExpressStatus;//0未发货 2已发货 3已签收
        private int PayStatus;      //0支付 1已支付
        private float RealAmount;
        private String OrderNo;
        private String Shop_Name;
        private boolean  HasChufang;
        private boolean  IsChuFang;
        private List<GoodsEntiy> GoodsList;

        public int getPayStatus() {
            return PayStatus;
        }

        public void setPayStatus(int payStatus) {
            PayStatus = payStatus;
        }

        public int getQueryStatus() {
            return QueryStatus;
        }

        public void setQueryStatus(int queryStatus) {
            QueryStatus = queryStatus;
        }

        public float getRealAmount() {
            return RealAmount;
        }

        public void setRealAmount(float realAmount) {
            RealAmount = realAmount;
        }

        public boolean isHasChufang() {
            return HasChufang;
        }

        public void setHasChufang(boolean hasChufang) {
            HasChufang = hasChufang;
        }

        public boolean isChuFang() {
            return IsChuFang;
        }

        public void setChuFang(boolean chuFang) {
            IsChuFang = chuFang;
        }

        public int getOrderId() {
            return OrderId;
        }

        public void setOrderId(int orderId) {
            OrderId = orderId;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int status) {
            Status = status;
        }

        public int getExpressStatus() {
            return ExpressStatus;
        }

        public void setExpressStatus(int expressStatus) {
            ExpressStatus = expressStatus;
        }


        public String getOrderNo() {
            return OrderNo;
        }

        public void setOrderNo(String orderNo) {
            OrderNo = orderNo;
        }

        public String getShop_Name() {
            return Shop_Name;
        }

        public void setShop_Name(String shop_Name) {
            Shop_Name = shop_Name;
        }

        public List<GoodsEntiy> getGoodsList() {
            return GoodsList;
        }

        public void setGoodsList(List<GoodsEntiy> goodsList) {
            GoodsList = goodsList;
        }
    }
    public static class GoodsEntiy{

        private int Order_Id;
        private int Buy_Count;
        private int Is_ChuFang;
        private float SellPrice;
        private String ProductName;
        private String ImageUrl;
        private String Guige;
        private String PiZhunwenhao;

        public int getOrder_Id() {
            return Order_Id;
        }

        public void setOrder_Id(int order_Id) {
            Order_Id = order_Id;
        }

        public int getBuy_Count() {
            return Buy_Count;
        }

        public void setBuy_Count(int buy_Count) {
            Buy_Count = buy_Count;
        }

        public int getIs_ChuFang() {
            return Is_ChuFang;
        }

        public void setIs_ChuFang(int is_ChuFang) {
            Is_ChuFang = is_ChuFang;
        }

        public float getSellPrice() {
            return SellPrice;
        }

        public void setSellPrice(float sellPrice) {
            SellPrice = sellPrice;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String productName) {
            ProductName = productName;
        }

        public String getImageUrl() {
            return ImageUrl;
        }

        public void setImageUrl(String imageUrl) {
            ImageUrl = imageUrl;
        }

        public String getGuige() {
            return Guige;
        }

        public void setGuige(String guige) {
            Guige = guige;
        }

        public String getPiZhunwenhao() {
            return PiZhunwenhao;
        }

        public void setPiZhunwenhao(String piZhunwenhao) {
            PiZhunwenhao = piZhunwenhao;
        }
    }
}
