package bai.kang.yun.zxd.mvp.model.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/6/10 0010.
 */

public class ReturenExpress {
    private int status;
    private String Message;
    private String url;
    private List<DataEntity> data;

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

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity{
        private String StoreId;                     //店铺Id
        private String StoreLogisticsId;            //店铺物流Id
        private String TrafficNameAndPrice;         //店铺物流名称
        private float TrafficPrice;                 //店铺物流价格

        public String getStoreId() {
            return StoreId;
        }

        public void setStoreId(String storeId) {
            StoreId = storeId;
        }

        public String getStoreLogisticsId() {
            return StoreLogisticsId;
        }

        public void setStoreLogisticsId(String storeLogisticsId) {
            StoreLogisticsId = storeLogisticsId;
        }

        public String getTrafficNameAndPrice() {
            return TrafficNameAndPrice;
        }

        public void setTrafficNameAndPrice(String trafficNameAndPrice) {
            TrafficNameAndPrice = trafficNameAndPrice;
        }

        public float getTrafficPrice() {
            return TrafficPrice;
        }

        public void setTrafficPrice(float trafficPrice) {
            TrafficPrice = trafficPrice;
        }
    }
}
