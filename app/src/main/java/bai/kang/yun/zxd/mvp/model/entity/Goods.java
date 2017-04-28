package bai.kang.yun.zxd.mvp.model.entity;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class Goods {

    String Price;
    int Seleing;
    String Name;
    String Abstract;
    String ImageUrl;

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public int getSeleing() {
        return Seleing;
    }

    public void setSeleing(int seleing) {
        Seleing = seleing;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAbstract() {
        return Abstract;
    }

    public void setAbstract(String anAbstract) {
        Abstract = anAbstract;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
