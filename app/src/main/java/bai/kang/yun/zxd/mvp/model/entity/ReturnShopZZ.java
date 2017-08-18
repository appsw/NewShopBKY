package bai.kang.yun.zxd.mvp.model.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/1 0001.
 */

public class ReturnShopZZ {
    private int status;
    private String Message;
    private String url;
    private  Page_data page_data;

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

    public Page_data getPage_data() {
        return page_data;
    }

    public void setPage_data(Page_data page_data) {
        this.page_data = page_data;
    }

    public static class Page_data{

        private int CurrentPage;
        private int TotalPages;
        private int TotalItems;
        private int ItemsPerPage;
        private List<Items> Items;

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

        public List<ReturnShopZZ.Items> getItems() {
            return Items;
        }

        public void setItems(List<ReturnShopZZ.Items> items) {
            Items = items;
        }
    }
    public static class Items{
        private int peta_rn;
        private String title;
        private String img;

        public int getPeta_rn() {
            return peta_rn;
        }

        public void setPeta_rn(int peta_rn) {
            this.peta_rn = peta_rn;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
