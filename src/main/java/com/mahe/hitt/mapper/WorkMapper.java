package com.mahe.hitt.mapper;

import com.mahe.hitt.entity.Department;
import com.mahe.hitt.entity.Turnwork;
import com.mahe.hitt.entity.Work;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author 马鹤
 * @Date 2019/7/25--
 * @Description 出勤记录的持久层接口
 **/
public interface WorkMapper {


    int selectDepartmentListCount() ;


    List<Work> selectWorkData();


    List<Department> selectDepartmentList();


    List<Turnwork> selectWorkDataNumber(@Param("dname") String dname);

}
