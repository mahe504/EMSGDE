package com.mahe.hitt.utils;

import java.util.UUID;

/**
 * @Author 马鹤
 * @Date 2019/7/7--20:40
 * @Description 此方法是产生UUID的工具类
 **/
public class UUIDUtil {

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
