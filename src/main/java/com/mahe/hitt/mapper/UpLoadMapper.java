package com.mahe.hitt.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @Author 马鹤
 * @Date 2019/7/18--
 * @Description
 **/
public interface UpLoadMapper {

    Boolean insertUserPic(@Param("pic") String picFilePath , @Param("uid") String uid);
}
