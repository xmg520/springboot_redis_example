package com.mzx.redisexample.entity;

import java.io.Serializable;

// 包装类 实现Serializable序列化,使数据可以跨平台存储
public class User implements Serializable {

    private Integer id;
    private String sid;
    private String sname;
    private String sclass;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", sid=" + sid +
                ", sname='" + sname + '\'' +
                ", sclass='" + sclass + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }
}
