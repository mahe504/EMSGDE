package com.mahe.hitt.entity.povo;

import com.mahe.hitt.entity.User;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author 马鹤
 * @Date 2019/7/18--15:07
 * @Description
 **/
@Data
public class UserRolePOVO extends User implements Serializable {

    private String rname;
}
