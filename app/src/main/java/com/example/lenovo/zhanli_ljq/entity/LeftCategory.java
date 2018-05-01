package com.example.lenovo.zhanli_ljq.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eric on 2018/4/18.
 */

public class LeftCategory {
    private Long cid;
    @SerializedName("createtime")
    private String createTime;
    private String icon;
    @SerializedName("ishome")
    private int isHome;
    private String name;

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getIsHome() {
        return isHome;
    }

    public void setIsHome(int isHome) {
        this.isHome = isHome;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
