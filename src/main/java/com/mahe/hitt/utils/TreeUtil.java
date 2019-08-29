package com.mahe.hitt.utils;

import com.mahe.hitt.entity.Meun;

import java.util.*;

/**
 * @Author 马鹤
 * @Date 2019/7/12--15:48
 * @Description
 **/

public class TreeUtil {
	/*
	 * public static List<Meun> build(List<Meun> meun, int topId) { List<Meun> meuns
	 * = new ArrayList<Meun>(); for (Meun m : meuns) { if (m.getParentId() == topId)
	 * { meuns.add(m); } for (Meun n : meuns) { if (n.getParentId() ==
	 * m.getMenuId()) { m.getChilden().add(n); } } } return meuns; }
	 */
	/*
	 * 排序根据order
	 */
	public static Comparator<Meun> order() {
		Comparator<Meun> comparator = new Comparator<Meun>() {
			public int compare(Meun o1, Meun o2) {
				if (o1.getOrder() != o2.getOrder()) {
					return o1.getOrder() - o2.getOrder();
				}
				return 0;
			}
		};
		return comparator;
	}
	/*
	 * 查询所有菜单
	 */
	public static Map<String, Object> findTree(List<Meun> allMenu) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			// 根节点
			List<Meun> rootMenu = new ArrayList<Meun>();
			for (Meun nav : allMenu) {
				if (nav.getParentId() == 0) {// 父节点是0的，为根节点。
					rootMenu.add(nav);
				}
			}
			/* 根据Menu类的order排序 */
			Collections.sort(rootMenu, order());
			// 为根菜单设置子菜单，getClild是递归调用的
			for (Meun nav : rootMenu) {
				/* 获取根节点下的所有子节点 使用getChild方法 */
				List<Meun> childList = getChild(nav.getMenuId(), allMenu);
				nav.setChildren(childList);// 给根节点设置子节点
			}
			/**
			 * 输出构建好的菜单数据。
			 */
			data.put("success", "true");
			data.put("list", rootMenu);
			return data;
		} catch (Exception e) {
			data.put("success", "false");
			data.put("list", new ArrayList());
			return data;
		}
	}
	/**
	 * 获取子节点
	 * 
	 * @param meunId
	 *            父节点id
	 * @param allMenu
	 *            所有菜单列表
	 * @return 每个根节点下，所有子菜单列表
	 */
	public static List<Meun> getChild(int meunId, List<Meun> allMenu) {
		// 子菜单
		List<Meun> childList = new ArrayList<Meun>();
		for (Meun nav : allMenu) {
			// 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
			// 相等说明：为该根节点的子节点。
			if (nav.getParentId() == meunId) {
				childList.add(nav);
			}
		}
		// 递归
		for (Meun nav : childList) {
			nav.setChildren(getChild(nav.getMenuId(), allMenu));
		}
		Collections.sort(childList, order());// 排序
		// 如果节点下没有子节点，返回一个空List（递归退出）
		if (childList.size() == 0) {
			return new ArrayList<Meun>();
		}
		return childList;
	}
}
