package com.kh.spring.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // Controller bean 등록
public class MemberController {
	
//	@RequestMapping(value = "/login.me", method = RequestMethod.POST)
//	public void login() {
//		System.out.println("로그인 처리합니다.");
//	}
	
	/******** 파라미터 전송받는 방법 ********/
	// 1. HttpServletRequest를 통해 전송받기 = JSP/Servlet
	
//	@RequestMapping(value = "/login.me", method = RequestMethod.POST)
//	public void login(HttpServletRequest request) {
//		String id = request.getParameter("id");
//		String pwd = request.getParameter("pwd");
//		
//		System.out.println("id1 : " + id);
//		System.out.println("pwd1 : " + pwd);
//	}
	
	// 2. @RequestParam 어노테이션 방식 : 조금 더 간단하게 파라미터를 가지고 올 수 있는 방식 제공
	//	   value	view에서 받아오는 파라미터 명, @RequestParam 안에 들어가는 속성 값이 하나뿐이라면 value 생략 가능		
	//	   defaultValue
	@RequestMapping(value = "/login.me", method = RequestMethod.POST)
//	public void login(@RequestParam("id") String id, @RequestParam("pwd") String pwd) {
	public void login(@RequestParam(value="id", defaultValue="hello", required=false) String id,
					  @RequestParam(value="pwd", defaultValue="world", required=false) String pwd,
					  @RequestParam(value="test", defaultValue="test", required=false) String test) {
		System.out.println("id2 : " + id);
		System.out.println("pwd2 : " + pwd);
	}
	
	
	
}
