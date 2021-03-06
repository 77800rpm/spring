package com.kh.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.spring.member.model.vo.Member;

public class LoginInterceptor implements HandlerInterceptor {
	
	private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 모든 작업이 완료된 후 수행
		
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			logger.info(loginUser.getId());
		}
	}
}
