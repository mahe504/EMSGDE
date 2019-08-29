package com.mahe.hitt.aop;

import com.mahe.hitt.entity.responsebody.IResult;
import com.mahe.hitt.utils.CheckUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author 马鹤
 * @Date 2019/7/16--18:48
 * @Description
 **/
@Aspect
@Component
public class ControllerAOP {

	/*
	 * 创建切点 只接受返回值类型是IResult的控制器
	 */
	@Pointcut("execution (public com.mahe.hitt.entity.responsebody.IResult *(..))")
	public void rResultPtCut() {
	}

	/*
	*   获取到返回IResult类型的controller接受检查错误即返回错误信息
	* */
	@Around("rResultPtCut()")
	public Object handlerControllerMethods(ProceedingJoinPoint pjp) throws Throwable {
		IResult iResult;
		CheckUtil.checkModel(pjp);
		iResult = (IResult) pjp.proceed();
		return iResult;
	}

}
