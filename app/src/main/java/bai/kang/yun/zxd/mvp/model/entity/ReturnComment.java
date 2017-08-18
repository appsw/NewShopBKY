package bai.kang.yun.zxd.mvp.model.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3 0003.
 */

public class ReturnComment {
    private int status;
    private String Message;
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

    public DataEntity getPage_data() {
        return page_data;
    }

    public void setPage_data(DataEntity page_data) {
        this.page_data = page_data;
    }

    public static class DataEntity{

        private int CurrentPage;
        private int TotalPages;
        private int TotalItems;
        private int ItemsPerPage;
        private List<ItemsEntity> Items;

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

        private int peta_rn;
        private int id;
        private int is_reply;
        private float servcice;
        private float delivery;
        private float wuliu;
        private float baozhuang;
        private String comment_body;
        private String avatar;
        private String nick_name;
        private String create_time;
        private String reply_body;
        private String reply_time;

        public int getPeta_rn() {
            return peta_rn;
        }

        public void setPeta_rn(int peta_rn) {
            this.peta_rn = peta_rn;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIs_reply() {
            return is_reply;
        }

        public void setIs_reply(int is_reply) {
            this.is_reply = is_reply;
        }

        public float getServcice() {
            return servcice;
        }

        public void setServcice(float servcice) {
            this.servcice = servcice;
        }

        public float getDelivery() {
            return delivery;
        }

        public void setDelivery(float delivery) {
            this.delivery = delivery;
        }

        public float getWuliu() {
            return wuliu;
        }

        public void setWuliu(float wuliu) {
            this.wuliu = wuliu;
        }

        public float getBaozhuang() {
            return baozhuang;
        }

        public void setBaozhuang(float baozhuang) {
            this.baozhuang = baozhuang;
        }

        public String getComment_body() {
            return comment_body;
        }

        public void setComment_body(String comment_body) {
            this.comment_body = comment_body;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getReply_body() {
            return reply_body;
        }

        public void setReply_body(String reply_body) {
            this.reply_body = reply_body;
        }

        public String getReply_time() {
            return reply_time;
        }

        public void setReply_time(String reply_time) {
            this.reply_time = reply_time;
        }
    }
}
