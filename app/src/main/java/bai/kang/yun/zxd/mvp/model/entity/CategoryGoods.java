package bai.kang.yun.zxd.mvp.model.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */

public class CategoryGoods {
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
        private String drugs_name;     //名字
        private String img_title;          //图片地址
        private String guige;            //规格
        private String Pizhunwenhao;     //准字号
        private String Manufacturers;            //生产公司
        private int Id;                 //商品ID
        private int spCount;                 //
        private int peta_rn;            //
        private int kind;            //种类
        private float minPrice;     //最低价格

        public int getKind() {
            return kind;
        }

        public void setKind(int kind) {
            this.kind = kind;
        }

        public String getDrugs_name() {
            return drugs_name;
        }

        public void setDrugs_name(String drugs_name) {
            this.drugs_name = drugs_name;
        }

        public String getImg_title() {
            return img_title;
        }

        public void setImg_title(String img_title) {
            this.img_title = img_title;
        }

        public String getGuige() {
            return guige;
        }

        public void setGuige(String guige) {
            this.guige = guige;
        }

        public String getPizhunwenhao() {
            return Pizhunwenhao;
        }

        public void setPizhunwenhao(String pizhunwenhao) {
            Pizhunwenhao = pizhunwenhao;
        }

        public String getManufacturers() {
            return Manufacturers;
        }

        public void setManufacturers(String manufacturers) {
            Manufacturers = manufacturers;
        }

        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }

        public int getSpCount() {
            return spCount;
        }

        public void setSpCount(int spCount) {
            this.spCount = spCount;
        }

        public int getPeta_rn() {
            return peta_rn;
        }

        public void setPeta_rn(int peta_rn) {
            this.peta_rn = peta_rn;
        }

        public float getMinPrice() {
            return minPrice;
        }

        public void setMinPrice(float minPrice) {
            this.minPrice = minPrice;
        }
    }

}
