package bai.kang.yun.zxd.mvp.model.entity;

/**
 * Created by Administrator on 2017/6/23 0023.
 */

public class ReturnApp {
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

    public ReturnApp.Single getSingle() {
        return Single;
    }

    public void setSingle(ReturnApp.Single single) {
        Single = single;
    }

    public static class Single{

        private int version;
        private String file;

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }
    }
}
