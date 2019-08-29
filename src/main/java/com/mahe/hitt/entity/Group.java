package com.mahe.hitt.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author 马鹤
 * @Date 2019/7/27--16:17
 * @Description
 **/
@Data
public class Group implements Serializable {

    private int id;
    private String gname;
    private String gcode;
    private String ptime;

    private int gcount;
}
