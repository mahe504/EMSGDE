package com.mahe.hitt.utils;

import com.mahe.hitt.entity.responsebody.ResultBean;
import com.mahe.hitt.exception.CheckException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author 马鹤
 * @Date 2019/7/11--16:47
 * @Description
 **/
@Slf4j
public class CheckUtil {

	/**
	 * @Param condition==false 表示有异常 ，
	 * @Param errorMsg表示异常信息 ，
	 * @Param arges为参数列表 Object... arges 表示不知道有多少个参数 用foreach方法遍历它
	 */
	public static void check(boolean condition, String errorMsg, Object... arges) {
		if (!condition) {
			fail(errorMsg, arges);
		}
	}
	/**
	 * 封装私有的方法 ， 如果失败了则抛出异常
	 **/
	private static void fail(String errorMsg, Object... arags) {
		StringBuilder sb = new StringBuilder();
		if (errorMsg.length() <= 0 && errorMsg == "") {
			// 如果没有异常则直接抛出错误信息
			throw new CheckException(ResultBean.Check_FAIL, errorMsg);
		}
		sb.append(errorMsg + ",");
		for (Object obj : arags) {
            sb.append(obj.toString()+"");
		}
		throw new CheckException(ResultBean.Check_FAIL , sb.toString());
	}

    /**
     * 不能为empty
     *
     * @param str    待检查字符串
     * @param errMsg 错误信息
     * @param args   参数列表
     */
    public static void notEmpty(String str, String errMsg, Object... args) {
        if (str == null || str.isEmpty()) {
            fail(errMsg, args);
        }
    }

    /**
     * 不能为blank
     *
     * @param str    待检查字符串
     * @param errMsg 错误信息
     * @param args   参数列表
     */
    public static void notBlank(String str, String errMsg, Object... args) {
        if (StringUtils.isBlank(str)) {
            fail(errMsg, args);
        }
    }

    /**
     * 不能为Null
     *
     * @param obj    待检查对象
     * @param errMsg 错误信息
     * @param args   参数列表
     */
    public static void notNull(Object obj, String errMsg, Object... args) {
        if (obj == null) {
            fail(errMsg, args);
        }
    }

    /*
    *   检查controller方法
    * */
    public static void checkModel(ProceedingJoinPoint pjp) {
        StringBuilder sb = new StringBuilder();
        try {
            //找到BindingResult参数
            List<BindingResult> results = getBindingResult(pjp);
            if (results != null && !results.isEmpty()) {
                for (BindingResult result : results) {
                    if (null != result && result.hasErrors()) {
                        //拼接错误信息
                        if (null != result.getFieldErrors()) {
                            for (FieldError fe : result.getFieldErrors()) {
                                sb.append(fe.getField() + "-" + fe.getDefaultMessage()).append(" ");
                            }
                        }
                    }
                }

                if (StringUtils.isNotBlank(sb.toString())) {
                    fail(sb.toString());//抛出检查异常
                }
            }
        } catch (Exception e) {
            fail(e.getMessage());//抛出检查异常
        }
    }
    /**
     * 拿到BindResult
     *
     * @param pjp 连接点
     */
    private static List<BindingResult> getBindingResult(ProceedingJoinPoint pjp) throws Exception {
        List<BindingResult> results = new LinkedList<BindingResult>();

        //拿到controller class，method
        Class<?> clazz = pjp.getTarget().getClass();
        String clazzName = clazz.getName();//类完整限定名
        String methodName = pjp.getSignature().getName();//方法名

        //找到该方法对象
        Method targetMethod = null;
        //获取该类的所有方法他和getMethods不通他所有的都可以获得
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                targetMethod = method;
            }
        }
        if (targetMethod == null) {
            return null;
        }

        //找到BindResult类型
        List<Integer> indexs = new LinkedList<Integer>();//不可重复的链表
        Class<?> clazzs[] = targetMethod.getParameterTypes();//返回参数的类型如果没有则返回0
        for (int i = 0; i < clazzs.length; i++) {
            if (clazzs[i].getName().equals("org.springframework.validation.BindingResult")) {
                indexs.add(i);
            }
        }

        //返回BindingResult
        if (indexs != null && !indexs.isEmpty()) {
            for (Integer i : indexs) {
                results.add((BindingResult) pjp.getArgs()[i]);
            }
            return results;
        }

        return null;
    }
}
