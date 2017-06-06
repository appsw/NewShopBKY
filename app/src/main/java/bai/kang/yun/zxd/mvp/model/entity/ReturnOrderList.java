package bai.kang.yun.zxd.mvp.model.entity;

/**
 * Created by Administrator on 2017/6/6 0006.
 */

public class ReturnOrderList {
//    {"status":1,"Message":null,"url":null,"Single":null,"data":null,
// "page_data":{"CurrentPage":1,"TotalPages":1,"TotalItems":1,"ItemsPerPage":10,
// "Items":[{"peta_rn":1,"id":70,"order_no":"BKYS_201706060920314139",
// "trade_no":"","user_id":226}],"Context":null}}
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

    }
    public static class ItemEntiy{
        private int peta_rn;
        private int id;
        private int user_id;
        private int trade_no;

    }
}
