package com.mahe.hitt.work.service;

import net.sf.json.JSONArray;

/**
 * @Author 马鹤
 * @Date 2019/7/25--9:42
 * @Description 出勤率的业务接口
 **/
public interface WorkService {

    /*
    *   获取出勤的json数据
    * */
    JSONArray getWorkData();
}
