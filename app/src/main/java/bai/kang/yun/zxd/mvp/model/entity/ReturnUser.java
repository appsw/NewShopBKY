package bai.kang.yun.zxd.mvp.model.entity;

/**
 * Created by Administrator on 2017/5/18 0018.
 */

public class ReturnUser {
    private int status;
    private String Message;
    private String url;
    private SingleEntity Single;

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

    public SingleEntity getSingle() {
        return Single;
    }

    public void setSingle(SingleEntity single) {
        Single = single;
    }

    public static class SingleEntity{
        private String user_name;     //用户名
        private String salt;          //用户token
        private String nick_name;          //用户昵称
        private int id;                 //用户id

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
