package bai.kang.yun.zxd.mvp.model.entity;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class SPCategory {
    private int id ;				//分类ID
    private String name ;			//分类名称
    private int parentId ;			//父菜单ID
    private String parentIdPath ;	//一级分类ID到当前ID的路径(保留字段)
    private int level;				//菜单级别
    private String filterAttr;		//筛选属性(保留字段)
    private String image;			//图片URL

    private List<SPCategory> subCategory;	//子分类
    transient JSONArray subCategoryArray;
    SPCategory parentCategory;		//父级分类

    //是否是空白
    private boolean isBlank;			//是否空白

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getParentIdPath() {
        return parentIdPath;
    }

    public void setParentIdPath(String parentIdPath) {
        this.parentIdPath = parentIdPath;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getFilterAttr() {
        return filterAttr;
    }

    public void setFilterAttr(String filterAttr) {
        this.filterAttr = filterAttr;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<SPCategory> getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(List<SPCategory> subCategory) {
        this.subCategory = subCategory;
    }

    public JSONArray getSubCategoryArray() {
        return subCategoryArray;
    }

    public void setSubCategoryArray(JSONArray subCategoryArray) {
        this.subCategoryArray = subCategoryArray;
    }

    public SPCategory getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(SPCategory parentCategory) {
        this.parentCategory = parentCategory;
    }

    public boolean isBlank() {
        return isBlank;
    }

    public void setBlank(boolean blank) {
        isBlank = blank;
    }
}
