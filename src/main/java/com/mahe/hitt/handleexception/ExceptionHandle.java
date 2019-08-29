package com.mahe.hitt.handleexception;

import com.mahe.hitt.entity.responsebody.IResult;
import com.mahe.hitt.entity.responsebody.ResultBean;
import com.mahe.hitt.exception.CheckException;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 马鹤
 * @Date 2019/7/16-- 19:11
 * @Description 全局异常处理
 **/
@ControllerAdvice
public class ExceptionHandle {

    /*
    *   只要抛出Check异常则去处理这个异常返回和controller一样的IResult接口的数据类型里面封装了异常的信息统一返回给前台去显示
    * */
    @ExceptionHandler(CheckException.class)
    @ResponseBody
    public IResult handleCheckException(CheckException ce, HttpServletRequest request, HttpServletResponse response){
        ResultBean<?> result = new ResultBean();
        result.setMsg(ce.getMessage());
        result.setCode(ce.getErrorCode());
        return result;
    }

    /*
    *   最后不知道抛出了什么错误就打印全栈错误信息统一返回给前台去显示
    * */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public IResult handleException(Throwable e, HttpServletRequest request,HttpServletResponse response){
        ResultBean<?> result = new ResultBean();
        result.setMsg(e.getMessage());
        result.setCode(ResultBean.SYSTEM_FAIL);
        return result;
    }

    /*
    *   这个是数据库访问异常如果 数据库出现了不能访问的情况走如下的异常处理的handle同一返回给前台去显示
    * */
    @ExceptionHandler(DataAccessException.class)
    @ResponseBody
    public IResult handleException(DataAccessException e, HttpServletRequest request,HttpServletResponse response){
        ResultBean<?> result = new ResultBean();
        result.setMsg("数据库访问异常");
        result.setCode(ResultBean.SYSTEM_FAIL);
        return result;
    }

}
