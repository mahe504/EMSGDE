package com.mahe.hitt.entity.povo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author 马鹤
 * @Date 2019/7/28--9:48
 * @Description
 **/
@Data
public class UserGroupDepartmentPovo implements Serializable {

    private String uid;
    private String dname;
    private int maxnumber;
    private String gname;
    private String realname;
    private int did;
    private String username;
}
