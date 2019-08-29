package com.mahe.hitt.exception;

import com.mahe.hitt.entity.responsebody.IErroCode;
import lombok.Data;

/**
 * @Author 马鹤
 * @Date 2019/7/11--17:04
 * @Description 自定义检查类型的类，用来检查异常继承异常类，通过调用父类的构造方法来实现
 **/
@Data
public class CheckException extends RuntimeException {

	/*
	 * 错误状态码
	 */
	private String errorCode;

	/*
	 * 构造方法自定义errorCode和错误信息
	 */
	public CheckException(String errorCode, String msg) {
		super(msg);
		this.errorCode = errorCode;
	}

	/*
	 * 如果只传回来IErrCode则使用父类的错误信息
	 */
	public CheckException(IErroCode iErroCode) {
		super(iErroCode.getDesc());
		this.errorCode = iErroCode.getCode();
	}
	/*
	 * 无参构造方法
	 */
	public CheckException() {

	}

	/*
	 * 如果只传回来错误的信息则使用父类的构造方法输出，不输出状态吗
	 */
	public CheckException(String msg) {
		super(msg);
	}

	/*
	 * 如果传回来的是异常，则调用父类的构造方法
	 */

	public CheckException(Throwable throwable) {
		super(throwable);
	}

	/*
	 * 如果传回来异常还有错误的信息则调用父类的构造方法
	 */
	public CheckException(String msg, Throwable throwable) {
		super(msg, throwable);
	}
}
