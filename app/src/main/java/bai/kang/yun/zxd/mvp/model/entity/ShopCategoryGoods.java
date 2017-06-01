package bai.kang.yun.zxd.mvp.model.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */

public class ShopCategoryGoods {
//    public static int status;
//    public static String Message;
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

        private int CurrentPage;      //第几页
        private int TotalPages;                 //总页数
        private int TotalItems;            //商品总数
        private float ItemsPerPage;     //一页商品数量
        private List<ItemEntity> Items;

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

        public float getItemsPerPage() {
            return ItemsPerPage;
        }

        public void setItemsPerPage(float itemsPerPage) {
            ItemsPerPage = itemsPerPage;
        }

        public List<ItemEntity> getItems() {
            return Items;
        }

        public void setItems(List<ItemEntity> items) {
            Items = items;
        }
    }
    public static class ItemEntity{
        private String ProductName;     //名字
        private String ImageUrl;          //图片地址
        private String Unit;            //规格

        private int Id;                 //商品ID
        private int peta_rn;                 //
        private int sort_num;            //库存

        private float SalePrice;     //价格

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

        public String getUnit() {
            return Unit;
        }

        public void setUnit(String unit) {
            Unit = unit;
        }

        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }

        public int getPeta_rn() {
            return peta_rn;
        }

        public void setPeta_rn(int peta_rn) {
            this.peta_rn = peta_rn;
        }

        public int getSort_num() {
            return sort_num;
        }

        public void setSort_num(int sort_num) {
            this.sort_num = sort_num;
        }

        public float getSalePrice() {
            return SalePrice;
        }

        public void setSalePrice(float salePrice) {
            SalePrice = salePrice;
        }
    }

}
