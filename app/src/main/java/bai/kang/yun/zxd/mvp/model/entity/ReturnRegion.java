package bai.kang.yun.zxd.mvp.model.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/25 0025.
 */

public class ReturnRegion  {
    private int status;
    private String Message;
    List<DataEntity> data;

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

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity{

        int regionid;           //id
        int regioparentidnid;   //父id
        int displaysequence;
        int depth;              //级数
        String regionname;      //名称
        String path;            //路径

        public int getRegionid() {
            return regionid;
        }

        public void setRegionid(int regionid) {
            this.regionid = regionid;
        }

        public int getRegioparentidnid() {
            return regioparentidnid;
        }

        public void setRegioparentidnid(int regioparentidnid) {
            this.regioparentidnid = regioparentidnid;
        }

        public int getDisplaysequence() {
            return displaysequence;
        }

        public void setDisplaysequence(int displaysequence) {
            this.displaysequence = displaysequence;
        }

        public int getDepth() {
            return depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }

        public String getRegionname() {
            return regionname;
        }

        public void setRegionname(String regionname) {
            this.regionname = regionname;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
