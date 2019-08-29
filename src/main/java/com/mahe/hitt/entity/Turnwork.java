package com.mahe.hitt.entity;

import java.io.Serializable;

/**
 * @Author 马鹤
 * @Date 2019/7/30--9:30
 * @Description
 **/
public class Turnwork implements Serializable {

    private int id ;
    private String turnwork ;
    private int wid;
    private int did;

    public Turnwork(int id, String turnwork, int wid, int did) {
        this.id = id;
        this.turnwork = turnwork;
        this.wid = wid;
        this.did = did;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTurnwork() {
        return turnwork;
    }

    public void setTurnwork(String turnwork) {
        this.turnwork = turnwork;
    }

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    @Override
    public String toString() {
        return "Turnwork{" +
                "id=" + id +
                ", turnwork='" + turnwork + '\'' +
                ", wid=" + wid +
                ", did=" + did +
                '}';
    }

    public Turnwork() {
    }
}
