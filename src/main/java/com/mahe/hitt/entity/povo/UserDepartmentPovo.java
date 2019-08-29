package com.mahe.hitt.entity.povo;

import com.mahe.hitt.entity.User;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author 马鹤
 * @Date 2019/7/24--
 * @Description
 **/
@Data
public class UserDepartmentPovo extends User implements Serializable {

    private String dname;

}
