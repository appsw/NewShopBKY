package bai.kang.yun.zxd.mvp.model.entity;

/**
 * Created by Administrator on 2017/6/17 0017.
 */

public class ReturnDefaultAdd {
    private int status;
    private String Message;
    private Single Single;

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

    public ReturnDefaultAdd.Single getSingle() {
        return Single;
    }

    public void setSingle(ReturnDefaultAdd.Single single) {
        Single = single;
    }

    public static class Single{

        private int id;
        private String address;
        private String real_name;
        private String phone;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
