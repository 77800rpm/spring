package com.kh.spring.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jTest {
	
	private Logger logger = LoggerFactory.getLogger(Log4jTest.class); // 다른 곳에 대한 클래스 정보를 넣어도 됨
	
	public static void main(String[] args) {
		new Log4jTest().test();
	}
	
	public void test() {
		logger.trace("trace 로그");
		logger.debug("debug 로그");
		logger.info("info 로그");
		logger.warn("warn 로그");
		logger.error("error 로그");
		
	}
	
/*
 * 												     라인 수
 * 		<실습>	'DEBUG:MemberController.enrollView{300} - '회원등록페이지' 로그가 출력되도록 하기 
 * 		<조건>	새 Appender 추가 및 중복 로깅이 안 되도록 하기
*/	
}
