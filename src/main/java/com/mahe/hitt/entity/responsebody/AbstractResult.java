package com.mahe.hitt.entity.responsebody;

import lombok.Data;

/**
 * @Author 马鹤
 * @Date 2019/7/11--11:19
 * @Description 抽象统一数据类型
 **/
@Data
public abstract class AbstractResult implements IResult {
	// 成功的Code码
	public static final String SUCCESS = "0000";
	// 系统错误的Code码
    public static final String SYSTEM_FAIL = "9999";
	// 检查错误的Code码
    public static final String Check_FAIL = "8888";
	// 业务错误额Code码
    public static final String SERVICE_FAIL = "7777";

	// 错误代码
	protected String code = SUCCESS;
	// 错误信息
	protected String msg = "success";

}
