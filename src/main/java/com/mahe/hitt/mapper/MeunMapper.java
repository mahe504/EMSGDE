package com.mahe.hitt.mapper;

import com.mahe.hitt.entity.Meun;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author 马鹤
 * @Date 2019/7/14--15:49
 * @Description 菜单的持久接口
 **/
public interface MeunMapper {

    /*
    *   根据权限id查找菜单
    * */
    List<Meun> selectMeunBypermissionId(@Param("id") int id);
}
