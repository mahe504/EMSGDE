package com.mahe.hitt.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author 马鹤
 * @Date 2019/7/14--14:59
 * @Description
 **/
@Data
public class Role implements Serializable {

    private int id;
    private String rname;
    private String rcode;
    private int pid;
    private Date gentime;
    private int available;

}
