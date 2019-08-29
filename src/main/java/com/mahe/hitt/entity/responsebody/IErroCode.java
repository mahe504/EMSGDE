package com.mahe.hitt.entity.responsebody;

/**
 * @Author 马鹤
 * @Date 2019/7/11--14:47
 * @Description 错误接口
 **/
public interface IErroCode {
	/*
	 * 错误代码
	 */
	String getCode();
	/*
	 * 错误描述
	 */
	String getDesc();
	/*
	 * 打印信息
	 */
	@Override
	String toString();

}
