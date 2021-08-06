package com.kh.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//public class TestInterceptor extends HandlerInterceptorAdapter { // Spring 5.3 버전부터  Deprecated 됨
public class TestInterceptor implements HandlerInterceptor {
	
	private Logger logger = LoggerFactory.getLogger(TestInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// Controller로 요청이 들어가기 전에 수행
		// handler는 preHandle()를 수행하고, 수행될 컨트롤러 메소드에 대한 정보를 담고 있음
		
		if(logger.isDebugEnabled()) {
			logger.debug("=================== START ====================");
			logger.debug(request.getRequestURI());
		}
		
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	// controller의 handler가 끝나면 처리됨
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// Controller에서 DispatcherServlet으로 리턴되는 순간 수행
		// modelAndView를 통해 작업 결과 참조 가능
		
		if(logger.isDebugEnabled()) {
			logger.debug("--------------- view ---------------");
		}
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	// view까지 처리가 끝난 후에 처리됨
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 모든 작업이 완료된 후 수행
		
		if(logger.isDebugEnabled()) {
			logger.debug("============ END ===============\n");
		}
		
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
