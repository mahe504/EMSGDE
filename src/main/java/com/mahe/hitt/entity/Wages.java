package com.mahe.hitt.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author 马鹤
 * @Date 2019/7/23--19:36
 * @Description
 **/
@Data
public class Wages implements Serializable {

    private int id;
    private String lv;//级别
    private double wnumber;//工资

}
