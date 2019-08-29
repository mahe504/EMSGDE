package com.mahe.hitt.entity.responsebody;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author 马鹤
 * @Date 2019/7/11--15:34
 * @Description
 **/
@Data
public class ResultBean<T> extends AbstractResult implements Serializable {

	private T data;

	public ResultBean() {
		super();
	}
	/*
	*   返回数据
	* */
    public ResultBean(T data) {
        super();
        this.data = data;
    }
	/*
	 * 发生错误调用这个构造方法
	 */
	public ResultBean(IErroCode iErroCode) {
		super();
		this.code = iErroCode.getCode();
		this.msg = iErroCode.getDesc();
	}
	/*
	 * 发生了异常调用这个构造方法
	 */
	public ResultBean(Throwable throwable) {
		super();
		this.msg = throwable.toString();
		this.code = SYSTEM_FAIL;
	}
	/*
	 * 自定义返回值的状态
	 */
	public ResultBean(String msg, String code) {
		super();
		this.msg = msg;
		this.code = code;
	}
	/*
	*   重写toString方法
	* */
	@Override
	public String toString() {
		return "[code=" + code + ",data=" + data + "]";
	}
}
