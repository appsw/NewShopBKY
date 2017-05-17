package bai.kang.yun.zxd.mvp.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/16 0016.
 */

public class ReturnCategory {
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
        private String name;                //名字
        private String imageurl;            //图片地址
        private int id;                     //商品id
        private int pid;                    //父类id

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        private List<DataEntity> grid3=new ArrayList<>();

        public List<DataEntity> getGrid3() {
            return grid3;
        }

        public void setGrid3(List<DataEntity> grid3) {
            this.grid3 = grid3;
        }
        @Override
        public String toString() {
            return "DataEntity{" +
                    "name='" + name + '\'' +
                    ", imageurl='" + imageurl + '\'' +
                    ", id=" + id +
                    ", pid=" + pid +
                    '}';
        }
    }

}
