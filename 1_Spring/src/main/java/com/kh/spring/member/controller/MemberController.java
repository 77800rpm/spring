package com.kh.spring.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.service.MemberServiceImpl;
import com.kh.spring.member.model.vo.Member;

@Controller // Controller bean 등록
public class MemberController {
	
	@Autowired
	private MemberService service;
	
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
//	@RequestMapping(value = "/login.me", method = RequestMethod.POST)
//	public void login(@RequestParam("id") String id, @RequestParam("pwd") String pwd) {
//	public void login(@RequestParam(value="id", defaultValue="hello", required=false) String id,
//					  @RequestParam(value="pwd", defaultValue="world", required=false) String pwd,
//					  @RequestParam(value="test", defaultValue="test", required=false) String test) {
//		System.out.println("id2 : " + id);
//		System.out.println("pwd2 : " + pwd);
//		System.out.println("test : " + test);
//	}
	
	
	// 3. @RequestParam 어노테이션 생략
	// 매개변수 명과 파라미터 명을 동일하게 해야 자동 매핑이 되어 생략 가능
//	@RequestMapping(value = "/login.me", method = RequestMethod.POST)
//	public void login(String id, String pwd) {
//		System.out.println("id3 : " + id);
//		System.out.println("pwd3 : " + pwd);
//	}
	
	// 4. @ModelAttribute 어노테이션 방식 : 요청 파라미터가 많을 경우 객체 타입으로 넘겨 받기
	// 기본 생성자와 setter를 이용한 주입 방식이기 때문에 둘 중 하나라도 없으면 에러
//	@RequestMapping(value = "/login.me", method = RequestMethod.POST)
//	public void login(@ModelAttribute Member m) {
//		System.out.println(m);
//	}
	
	
	// 5. @ModelAttribute 어노테이션 생략
	@RequestMapping(value = "/login.me", method = RequestMethod.POST)
	public void login(Member m) {
		System.out.println(m);
		
		// 1. 매 요청마다 새로운 service 객체 만들어짐 (주소값이 계속 변함)
		// 2. 클래스 명을 변경한다고 했을 때 직접적인 영향을 받음
		// -> 요소마다 결합도가 높다 --> 높은 결합도 낮추기
//		MemberServiceImpl service = new MemberServiceImpl();
//		System.out.println(service);
		
		Member loginUser = service.login(m);
		
		System.out.println(loginUser);
	}
	
	
}
