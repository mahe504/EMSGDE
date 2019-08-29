package com.mahe.hitt.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 马鹤
 * @Date 2019/7/12--15:08
 * @Description
 **/
public class Meun implements Serializable {

    private Integer menuid;
    private String title;
    private String icon;
    private String href;
    private String perms;
    private int parentId;
    private int sorting;
    private String dataname;
    private String classs;
    private String param;
    private String createid;
    private String targrt;
    private Boolean spread;
    private int order;

    public Meun(Integer menuId, String title, String icon, String href, String perms, int parentId, int sorting, String dataname, String classs, String param, String createid, String targrt, Boolean spread, int order, List<Meun> children) {
        this.menuid = menuId;
        this.title = title;
        this.icon = icon;
        this.href = href;
        this.perms = perms;
        this.parentId = parentId;
        this.sorting = sorting;
        this.dataname = dataname;
        this.classs = classs;
        this.param = param;
        this.createid = createid;
        this.targrt = targrt;
        this.spread = spread;
        this.order = order;
        this.children = children;
    }

    private List<Meun> children = new ArrayList<Meun>();

    public int getMenuId() {
        return menuid;
    }

    public void setMenuId(int menuId) {
        this.menuid = menuId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getSorting() {
        return sorting;
    }

    public void setSorting(int sorting) {
        this.sorting = sorting;
    }

    public String getDataname() {
        return dataname;
    }

    public void setDataname(String dataname) {
        this.dataname = dataname;
    }

    public String getClasss() {
        return classs;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getCreateid() {
        return createid;
    }

    public void setCreateid(String createid) {
        this.createid = createid;
    }

    public String getTargrt() {
        return targrt;
    }

    public void setTargrt(String targrt) {
        this.targrt = targrt;
    }

    public Boolean getSpread() {
        return spread;
    }

    public void setSpread(Boolean spread) {
        this.spread = spread;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public List<Meun> getChildren() {
        return children;
    }

    public void setChildren(List<Meun> children) {
        this.children = children;
    }

    public Meun() {
    }

    @Override
    public String toString() {
        return "Meun{" +
                "menuid=" + menuid +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", href='" + href + '\'' +
                ", perms='" + perms + '\'' +
                ", parentId=" + parentId +
                ", sorting=" + sorting +
                ", dataname='" + dataname + '\'' +
                ", classs='" + classs + '\'' +
                ", param='" + param + '\'' +
                ", createid='" + createid + '\'' +
                ", targrt='" + targrt + '\'' +
                ", spread=" + spread +
                ", order=" + order +
                ", children=" + children +
                '}';
    }
}
