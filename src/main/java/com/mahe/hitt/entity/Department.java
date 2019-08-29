package com.mahe.hitt.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author 马鹤
 * @Date 2019/7/21--15:31
 * @Description
 **/
@Data
public class Department implements Serializable {

    private int id;
    private String dname;
    private String createuser;
    private Date dtabletime;
    private Date updatetime;
    private String updateuser;
    private int usercount;
    private int maxnumber;

}
