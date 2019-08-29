package com.mahe.hitt.entity;

import java.io.Serializable;

/**
 * @Author 马鹤
 * @Date 2019/7/25-- 9:44
 * @Description
 **/
public class Work implements Serializable {

    private int id;
    private String year;

    private int did ;
    private int wid;
    private String turnwork;

    public Work(int id, String year, int did, int wid, String turnwork) {
        this.id = id;
        this.year = year;
        this.did = did;
        this.wid = wid;
        this.turnwork = turnwork;
    }

    public Work(int id, int did, int wid, String turnwork) {
        this.id = id;
        this.did = did;
        this.wid = wid;
        this.turnwork = turnwork;
    }

    public Work(int id, String year) {
        this.id = id;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getYear() {
        return year;
    }

    public int getDid() {
        return did;
    }

    public int getWid() {
        return wid;
    }

    public String getTurnwork() {
        return turnwork;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public void setTurnwork(String turnwork) {
        this.turnwork = turnwork;
    }

    @Override
    public String toString() {
        return "Work{" +
                "id=" + id +
                ", year='" + year + '\'' +
                ", did=" + did +
                ", wid=" + wid +
                ", turnwork='" + turnwork + '\'' +
                '}';
    }

    public Work() {
    }
    public Work(String turnwork){
        this.turnwork = turnwork;
    }
}
