package bai.kang.yun.zxd.mvp.model.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24 0024.
 */

public class ReturnAddress {
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

        int CurrentPage;
        int TotalPages;
        int TotalItems;
        int ItemsPerPage;
        List<ItemsEntity> Items;

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

        public List<ItemsEntity> getItems() {
            return Items;
        }

        public void setItems(List<ItemsEntity> items) {
            Items = items;
        }
    }
    public static class ItemsEntity{

    }

}
