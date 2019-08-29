package com.mahe.hitt.entity.pageInfo;

import lombok.Data;

/**
 * @Author 马鹤
 * @Date 2019/7/12--10:07
 * @Description
 **/
@Data
public class PageInfo {

	/*
	 * 分页大小
	 */
	private int limit;

	/*
	 * 页数
	 */
	private int page;

	/*
	 * 构造函数
	 */
	public PageInfo(int limit, int page) {
		this.page = page;
		this.limit = limit;

	}

	/*
	 * 字符串转化为int类型
	 */
	public PageInfo(String limit, String page) {
		this.page = Integer.parseInt(page);
		this.limit = Integer.parseInt(limit);
	}

	/*
	 * 查询开始的序号
	 */
	public int getStart() {
		int start = (page - 1) * limit;
		return start;
	}

	/*
	 * 查询结束的序号
	 */
	public int getEnd() {
		int end = limit;
		return end;
	}

}
