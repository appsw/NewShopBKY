package bai.kang.yun.zxd.mvp.model.entity;

/**
 * Created by Administrator on 2017/6/1 0001.
 */

public class ReturnShopCategory {
    private int status;
    private String Message;
    private String url;
    private ReturnAddress.DataEntity data;

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

    public ReturnAddress.DataEntity getData() {
        return data;
    }

    public void setData(ReturnAddress.DataEntity data) {
        this.data = data;
    }

    public static class DataEntity {
        private int Id;
        private String Name;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
}
