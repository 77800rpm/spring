package com.kh.ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.ajax.model.vo.User;

@Controller
public class TestController {
   
   @RequestMapping("test1.do")
   public void test1(@RequestParam("name") String name, @RequestParam("age") int age, HttpServletResponse response) {
      
      try {
         PrintWriter out = response.getWriter();
         
         if(name.equals("강건강") && age == 25) {
            out.println("ok");
         } else {
            out.println("fail");
         }
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      //우리는 ajax를 할 거기 때문에 반환값을 Spring으로 할 필요 없다 = 돌아갈 곳이 다른 곳이 아니라 home.jsp로 갈거니까
   }
   
   @RequestMapping("test2.do")
   public void test2(HttpServletResponse response) {
      response.setContentType("application/json; charset=UTF-8");
      JSONObject job = new JSONObject();
      job.put("no", 123);
      job.put("title", "text json object");
      job.put("writer", "남나눔");
      job.put("content", "JSON 객체 리턴하기");      
      
      try {
         PrintWriter out = response.getWriter();
         out.println(job);
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
   
   @RequestMapping(value="test3.do", produces="application/json; charset=UTF-8")
   @ResponseBody // 내가 보내는 건 뷰의 이름이 아니라 데이터 return 값이야
   public String test3(HttpServletResponse response) {
//      response.setContentType("application/json; charset=UTF-8");
      
      JSONObject job = new JSONObject();
      job.put("no", 456);
      job.put("title", "text json object2");
      job.put("writer", "도대담");
      job.put("content", "JSON 객체 또 리턴하기");
      
      // URLEncoder 방식
      
//      job.put("no", 456);
//      job.put("title", "text json object2");
//      try {
//         job.put("writer", URLEncoder.encode("도대담", "UTF-8"));
//         job.put("content", URLEncoder.encode("JSON 객체 또 리턴하기", "UTF-8"));
//      } catch (UnsupportedEncodingException e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      }
      
      
      return job.toJSONString(); // 데이터로 인지하는 게 아니라 뷰의 이름으로 인지하고 있음
   }
   
   
   @RequestMapping(value="test4.do", produces="application/json; charset=UTF-8")
   public void test4(HttpServletResponse response) {
      ArrayList<User> list = new ArrayList<User>();
      list.add(new User("id111", "pw111", "강건강", 25, "k111@kh.or.kr", "01011112222"));
      list.add(new User("id222", "pw222", "남나눔", 34, "n222@kh.or.kr", "01022223333"));
      list.add(new User("id333", "pw333", "도대담", 17, "d333@kh.or.kr", "01033334444"));
      list.add(new User("id444", "pw444", "류라라", 21, "r444@kh.or.kr", "01044445555"));
      list.add(new User("id555", "pw555", "문미미", 18, "m555@kh.or.kr", "01055556666"));
      
      JSONArray jArr = new JSONArray();
      for(User user : list) {
         JSONObject obj = new JSONObject();
         obj.put("userId", user.getUserId());
         obj.put("userPwd", user.getUserPwd());
         obj.put("userName", user.getUserName());
         obj.put("age", user.getAge());
         obj.put("email", user.getEmail());
         obj.put("phone", user.getPhone());
         
         jArr.add(obj);
      }
      
      response.setContentType("application/json; charset=UTF-8");
      
      try {
         PrintWriter out = response.getWriter();
         out.println(jArr);
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
   @RequestMapping(value="test5.do", produces="application/json; charset=UTF-8")
   @ResponseBody
   public String test5(HttpServletResponse response) {
      ArrayList<User> list = new ArrayList<User>();
      list.add(new User("id111", "pw111", "강건강", 25, "k111@kh.or.kr", "01011112222"));
      list.add(new User("id222", "pw222", "남나눔", 34, "n222@kh.or.kr", "01022223333"));
      list.add(new User("id333", "pw333", "도대담", 17, "d333@kh.or.kr", "01033334444"));
      list.add(new User("id444", "pw444", "류라라", 21, "r444@kh.or.kr", "01044445555"));
      list.add(new User("id555", "pw555", "문미미", 18, "m555@kh.or.kr", "01055556666"));
      
      JSONArray jArr = new JSONArray();
      for(User user : list) {
         JSONObject obj = new JSONObject();
         obj.put("userId", user.getUserId());
         obj.put("userPwd", user.getUserPwd());
         obj.put("userName", user.getUserName());
         obj.put("age", user.getAge());
         obj.put("email", user.getEmail());
         obj.put("phone", user.getPhone());
         
         jArr.add(obj);
      }
      
      return jArr.toJSONString();
   }
   
   
}