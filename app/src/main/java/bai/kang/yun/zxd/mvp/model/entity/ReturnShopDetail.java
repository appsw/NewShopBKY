package bai.kang.yun.zxd.mvp.model.entity;

/**
 * Created by Administrator on 2017/5/31 0031.
 */

public class ReturnShopDetail {
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

    public static class SingleEntity {

        private float service;   //服务得分
        private float delivery;
        private float wuliu;    //物流得分
        private float baozhuang;//包装得分
        private float zonghe;   //综合得分
        private float back_order;
        private float delivery_time;

        private String name;            //店铺名称
        private String create_date;     //创建时间
        private String reg_address;     //店铺地址
        private String logo;            //店铺logo

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public float getService() {
            return service;
        }

        public void setService(float service) {
            this.service = service;
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

        public float getZonghe() {
            return zonghe;
        }

        public void setZonghe(float zonghe) {
            this.zonghe = zonghe;
        }

        public float getBack_order() {
            return back_order;
        }

        public void setBack_order(float back_order) {
            this.back_order = back_order;
        }

        public float getDelivery_time() {
            return delivery_time;
        }

        public void setDelivery_time(float delivery_time) {
            this.delivery_time = delivery_time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public String getReg_address() {
            return reg_address;
        }

        public void setReg_address(String reg_address) {
            this.reg_address = reg_address;
        }
    }
}
