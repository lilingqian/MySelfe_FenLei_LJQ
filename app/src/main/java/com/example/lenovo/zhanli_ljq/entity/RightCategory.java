package com.example.lenovo.zhanli_ljq.entity;

import java.util.List;

/**
 * Created by eric on 2018/4/18.
 */

public class RightCategory {
    private String cid;
    private String name;
    private List<SecondProduct> list;
    private String pcid;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SecondProduct> getList() {
        return list;
    }

    public void setList(List<SecondProduct> list) {
        this.list = list;
    }

    public String getPcid() {
        return pcid;
    }

    public void setPcid(String pcid) {
        this.pcid = pcid;
    }
}
