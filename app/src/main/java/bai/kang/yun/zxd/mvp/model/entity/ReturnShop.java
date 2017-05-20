package bai.kang.yun.zxd.mvp.model.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/20 0020.
 */

public class ReturnShop {
    private int status;
    private String Message;
    private String url;
    private PageDataEntity page_data;

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

    public PageDataEntity getPage_data() {
        return page_data;
    }

    public void setPage_data(PageDataEntity page_data) {
        this.page_data = page_data;
    }

    public static class PageDataEntity{

        private int CurrentPage;
        private int TotalPages;
        private int TotalItems;
        private int ItemsPerPage;
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

        public int getItemsPerPage() {
            return ItemsPerPage;
        }

        public void setItemsPerPage(int itemsPerPage) {
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

        private String name;     //名字
        private String ImageUrl;          //图片地址
        private String area_name;            //地址
        private String ProductName;     //商品名
        private String Unit;            //单位
        private int peta_rn;      //分类id
        private int shopId;                 //店铺id
        private int SaleStatus;            //销售状态
        private int VistiCounts;      //分类id
        private int Id;                 //商品id
        private int drugs_id;            //
        private int fahuodays;      //发货时间
        private int Stock;                 //
        private float SalePrice;        //价格
        private float score;        //店铺评分

        public float getScore() {
            return score;
        }

        public void setScore(float score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImageUrl() {
            return ImageUrl;
        }

        public void setImageUrl(String imageUrl) {
            ImageUrl = imageUrl;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String productName) {
            ProductName = productName;
        }

        public String getUnit() {
            return Unit;
        }

        public void setUnit(String unit) {
            Unit = unit;
        }

        public int getPeta_rn() {
            return peta_rn;
        }

        public void setPeta_rn(int peta_rn) {
            this.peta_rn = peta_rn;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public int getSaleStatus() {
            return SaleStatus;
        }

        public void setSaleStatus(int saleStatus) {
            SaleStatus = saleStatus;
        }

        public int getVistiCounts() {
            return VistiCounts;
        }

        public void setVistiCounts(int vistiCounts) {
            VistiCounts = vistiCounts;
        }

        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }

        public int getDrugs_id() {
            return drugs_id;
        }

        public void setDrugs_id(int drugs_id) {
            this.drugs_id = drugs_id;
        }

        public int getFahuodays() {
            return fahuodays;
        }

        public void setFahuodays(int fahuodays) {
            this.fahuodays = fahuodays;
        }

        public int getStock() {
            return Stock;
        }

        public void setStock(int stock) {
            Stock = stock;
        }

        public float getSalePrice() {
            return SalePrice;
        }

        public void setSalePrice(float salePrice) {
            SalePrice = salePrice;
        }
    }
}
