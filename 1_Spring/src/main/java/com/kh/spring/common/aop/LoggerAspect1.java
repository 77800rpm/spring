package com.kh.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerAspect1 {
	private Logger logger = LoggerFactory.getLogger(LoggerAspect1.class);
	
//	public void loggerAdvice(JoinPoint joinPoint) {
	public Object loggerAdvice(ProceedingJoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature(); // 현재 AOP가 적용된 메소드의 정보 가져옴
		logger.debug("signature : " + signature);

		String type = signature.getDeclaringTypeName(); // 메소드가 있는 클래스 풀네임
		String methodName = signature.getName(); // 타겟 객체에 대한 메소드
		logger.debug("type : " + type);
		logger.debug("methodName : " + methodName);
		
		String componentName = null;
		if(type.indexOf("Controller") > -1) {
			componentName = "Controller \t : ";			
		} else if(type.indexOf("Service") > -1) {
			componentName = "ServiceImpl	\t : ";
		} else if(type.indexOf("DAO") > -1) {
			componentName = "DAO	\t\t : ";
		}
		
		logger.debug("[Before] " + componentName + type + "." + methodName + "()");
		
		Object obj = null;
		
		try {
			obj = joinPoint.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.debug("[After] " + componentName + type + "." + methodName + "()" );
		return obj;
	}
}
