package bai.kang.yun.zxd.mvp.model.entity;

/**
 * Created by Administrator on 2017/6/13 0013.
 */

public class ReturnMakeOrder {
    private int status;
    private String Message;

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
}
