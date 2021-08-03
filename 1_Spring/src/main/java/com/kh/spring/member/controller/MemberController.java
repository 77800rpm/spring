package com.kh.spring.member.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.member.model.exception.MemberException;
import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.service.MemberServiceImpl;
import com.kh.spring.member.model.vo.Member;

@SessionAttributes("loginUser")
@Controller // Controller bean 등록
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
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
//	@RequestMapping(value = "/login.me", method = RequestMethod.POST)
//	public String login(Member m, HttpSession session) {
//		System.out.println(m);
//		
//		// 1. 매 요청마다 새로운 service 객체 만들어짐 (주소값이 계속 변함)
//		// 2. 클래스 명을 변경한다고 했을 때 직접적인 영향을 받음
//		// -> 요소마다 결합도가 높다 --> 높은 결합도 낮추기
////		MemberServiceImpl service = new MemberServiceImpl();
////		System.out.println(service);
//		
//		Member loginUser = service.login(m);
//		
////		System.out.println(loginUser);
//		
//		if(loginUser != null) {
//			session.setAttribute("loginUser", loginUser);			
//		}
//		
//		// home.me라서 /WEB-INF/views/member이 기본 경로다
//		// 상위 폴더로 한 번 가야 한다 = ../
////		return "../home";
//		return "redirect:home.do";
//	}
	
	
	/********* 요청 후 데이터 전달하는 방법 **********/
	// 1. Model 객체 : 맵 형식(key, value), request scope
//	@RequestMapping(value = "/login.me", method = RequestMethod.POST)
//	public String login(Member m, HttpSession session, Model model) {
//		System.out.println(m);
//
//		Member loginUser = service.login(m);
//
//		if(loginUser != null) {
//			session.setAttribute("loginUser", loginUser);			
//		} else {
//			model.addAttribute("message", "로그인에 실패하였습니다");
//			return "../common/errorPage";
//		}
//
//		return "redirect:home.do";
//	}	
	
	// 2. ModelAndView 객체 :Model(맵 형식) + View(뷰 페이지)
//	@RequestMapping(value = "/login.me", method = RequestMethod.POST)
//	public ModelAndView login(Member m, HttpSession session, ModelAndView mv) {
//		System.out.println(m);
//		
//		Member loginUser = service.login(m);
//		
//		if(loginUser != null) {
//			session.setAttribute("loginUser", loginUser);	
////			return "redirect:home.do";
//			mv.setViewName("redirect:home.do");
//		} else {
//			// Model
////			model.addAttribute("message", "로그인에 실패하였습니다");
//			mv.addObject("message", "로그인에 실패하였습니다.");
//			//View
//			mv.setViewName("../common/errorPage");
//		}
//		
//		return mv;
//	}	
	
//	@RequestMapping("logout.me")
//	public String logout(HttpSession session) {
//		session.invalidate();
//		
//		return "redirect:home.do";
//	}
	
	// @SessionAttributes 사용 : model에 attribute가 추가될 때 자동으로 키를 찾아 세션에 등록
//	@RequestMapping(value = "/login.me", method = RequestMethod.POST)
//	public String login(Member m, Model model) {
//		System.out.println(m);
//		
//		Member loginUser = service.login(m);
//		
//		if(loginUser != null) {
//			model.addAttribute("loginUser", loginUser);	
//			return "redirect:home.do";
//		} else {
//			model.addAttribute("message", "로그인에 실패하였습니다.");
//			return "../common/errorPage";
//		}
//		
//	}	
	
	@RequestMapping("logout.me")
	public String logout(SessionStatus session) {
		session.setComplete();
		
		return "redirect:home.do";
	}
	
	@RequestMapping("enrollView.me")
	public String enrollView() {
		return "memberJoin";
	}
	

	@RequestMapping("minsert.me")
	public String insertMember(@ModelAttribute Member m, @RequestParam("post") String post,
														 @RequestParam("address1") String address1,
														 @RequestParam("address2") String address2) {
		
		m.setAddress(post + "/" + address1 + "/" + address2);
		
		// bcrypt 암호화 방식 사용 : 1차로 암호화한 결과물을 가지고 salt값(랜덤 값)을 이용해 매번 새로운 암호화 데이터 생성
		String encPwd = bcrypt.encode(m.getPwd());
		m.setPwd(encPwd);
		
		int result = service.insertMember(m);
		
		if(result > 0) {
			return "redirect:home.do";
		} else {
			throw new MemberException("회원가입에 실패하였습니다.");
		}
		
	}	
	
	
	// 암호화 후 로그인(로그인 최종본)
	@RequestMapping(value = "/login.me", method = RequestMethod.POST)
	public String login(Member m, Model model) {
//		m.setPwd(bcrypt.encode(m.getPwd()));					
		Member loginUser = service.login(m);
		
		boolean match = bcrypt.matches(m.getPwd(), loginUser.getPwd());		
		
		
		if(match) {
			model.addAttribute("loginUser", loginUser);				
		} else {
			throw new MemberException("로그인에 실패했습니다.");
		}
		return "redirect:home.do";
	}	
	
	@RequestMapping("myinfo.me")
	public String myInfoView() {
		
		return "mypage";

	}
	
	@RequestMapping("mupdateView.me")
	public String updateForm(@ModelAttribute Member m) {
		return "memberUpdateForm";
	}
	
	@RequestMapping("mupdate.me")
	public String updateInfo(@ModelAttribute Member m, Model model, @RequestParam("post") String post,
																 	@RequestParam("address1") String address1,
																	@RequestParam("address2") String address2) {
		
		m.setAddress(post + "/" + address1 + "/" + address2);
		
//		Member loginUser = service.login(m);	
		
		int result = service.updateMember(m);
		
//		System.out.println("로그인 유저 : " + loginUser);

		
		if(result > 0) {
			Member loginUser = service.login(m);	
//			Member newMember = service.login(m);
//			System.out.println("newMember : " + newMember);
//			loginUser = newMember;
			model.addAttribute("loginUser", loginUser);
//			System.out.println("loginUser : " + loginUser);
			return "redirect:myinfo.me";
		} else {
			throw new MemberException("회원 정보 수정에 실패하였습니다.");
		}
	}
	
	
	@RequestMapping("mpwdUpdateView.me")
	public String updatePwdForm() {
		return "memberPwdUpdateForm";
	}
		
	
	@RequestMapping("mPwdUpdate.me")
	public String updatePwd(@ModelAttribute Member m, Model model, @RequestParam("pwd") String pwd, @RequestParam("newPwd1") String newPwd1, @RequestParam("newPwd2") String newPwd2) {

//		if(newPwd1 != newPwd2) {
//			throw new MemberException("새 비밀번호와 새 비밀번호 확인이 서로 일치하지 않습니다.");
//		}		
		
		Member loginUser = service.login(m);	
		
//		System.out.println(loginUser);

		
		String id = loginUser.getId();
		String pwd1 = loginUser.getPwd();
//		System.out.println(pwd1);
		

		String encPwd = bcrypt.encode(newPwd1);
		
		HashMap<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("newPwd1", encPwd);
		
//		System.out.println(map);
		
		int result = service.updatePwd(map);
		
		System.out.println(result);
		
		if(bcrypt.matches(pwd, pwd1)) {
		
			if(result > 0) {	
				model.addAttribute("loginUser", loginUser);
				System.out.println(model);
				return "redirect:myinfo.me";			
			} else {
				throw new MemberException("비밀번호 수정에 실패하였습니다.");
			}
		
		} else {
			throw new MemberException("현재 비밀번호가 일치하지 않습니다. 다시 확인해 주세요.");
		}
		
	}
	
	
	
	@RequestMapping("mdelete.me")
	public String deleteMember(@RequestParam("id") String id, SessionStatus status) {
			int result = service.deleteMember(id);
		 if(result > 0) {
			status.setComplete();
		} else {
			throw new MemberException("현재 비밀번호가 일치하지 않습니다. 다시 확인해 주세요.");
		}
		 
		 return "redirect:home.do";
	}
	
	@RequestMapping("dupid.me")
	public void duplicateId(@RequestParam("userId") String userId, HttpServletResponse response) {
		int result = service.duplicateId(userId);
		boolean bool = result == 0 ? true : false;
		try {
			response.getWriter().println(bool);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
