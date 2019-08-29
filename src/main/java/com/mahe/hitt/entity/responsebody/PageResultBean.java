package com.mahe.hitt.entity.responsebody;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author 马鹤
 * @Date 2019/7/11--16:19
 * @Description
 **/
@Data
public class PageResultBean<T> extends AbstractResult implements Serializable {

	/*
	 * 返回的数据
	 */
	private T data;

	/*
	 * 总记录数
	 */
	private Integer count;

	/*
	*   构造函数
	* */
	public PageResultBean() {
		super();
	}

	/*
	*   返回数据和总数
	* */
	public PageResultBean(T data, Integer count) {
		super();
		this.data = data;
		this.count = count;
	}

	/*
	*   返回分页系统错误异常
	* */
	public PageResultBean(IErroCode iErroCode) {
		super();
		this.msg = iErroCode.toString();
		this.code = SYSTEM_FAIL;
	}

	/*
	*   返回分页异常信息
	* */
	public PageResultBean(Throwable throwable) {
		super();
		this.msg = throwable.toString();
		this.code = code;
	}

	/*
	*   返回自定义翻页信息
	* */
    public PageResultBean(String msg , String code){
        super();
        this.msg=msg;
        this.code=code;
    }
}
