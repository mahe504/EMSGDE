package com.mahe.hitt.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author 马鹤
 * @Date 2019/7/9--16:47
 * @Description
 **/
@Data
public class Loginlog implements Serializable {

	private String id;
	private String logusername;
	private Date lastlogtime;
	private String logip;

	public Loginlog(String id , String logusername , Date lastlogtime , String logip){
        this.id = id ;
        this.logip = logip;
        this.lastlogtime = lastlogtime;
        this.logusername = logusername;
    }
    public Loginlog(){

    }
}
