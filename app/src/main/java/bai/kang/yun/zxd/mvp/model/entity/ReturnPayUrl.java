package bai.kang.yun.zxd.mvp.model.entity;

/**
 * Created by Administrator on 2017/6/14 0014.
 */

public class ReturnPayUrl {

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

    public ReturnPayUrl.Single getSingle() {
        return Single;
    }

    public void setSingle(ReturnPayUrl.Single single) {
        Single = single;
    }

    public static class Single{
        private String appid;
        private String cusid;
        private String payinfo;
        private String reqsn;
        private String retcode;
        private String key;
        private String trxid;
        private String trxstatus;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getCusid() {
            return cusid;
        }

        public void setCusid(String cusid) {
            this.cusid = cusid;
        }

        public String getPayinfo() {
            return payinfo;
        }

        public void setPayinfo(String payinfo) {
            this.payinfo = payinfo;
        }

        public String getReqsn() {
            return reqsn;
        }

        public void setReqsn(String reqsn) {
            this.reqsn = reqsn;
        }

        public String getRetcode() {
            return retcode;
        }

        public void setRetcode(String retcode) {
            this.retcode = retcode;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getTrxid() {
            return trxid;
        }

        public void setTrxid(String trxid) {
            this.trxid = trxid;
        }

        public String getTrxstatus() {
            return trxstatus;
        }

        public void setTrxstatus(String trxstatus) {
            this.trxstatus = trxstatus;
        }
    }
}
