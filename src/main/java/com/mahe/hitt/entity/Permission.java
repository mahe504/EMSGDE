package com.mahe.hitt.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author 马鹤
 * @Date 2019/7/12--15:50
 * @Description
 **/
@Data
public class Permission implements Serializable {
    private int id;
    private int pid;
    private String type;
    private String percode;
    private String name;
    private String icon;
    private String href;
    private String target;
    private int spread;
    private int ordernum;
    private int available;


}
