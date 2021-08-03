<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
   <title>Home</title>
   <script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<h1 algin="center">
   Spring에서의 ajax 사용 테스트 페이지
</h1>

   <ol>
      <li>
      서버 쪽 컨트롤러로 값 보내기
      <button id="test1">테스트</button>
         <script>
            //test1버튼을 클릭했을 때 test1.do로 넘어가는 ajax(name: 강건강, age:25) 실행
            $('#test1').on('click', function() {
               $.ajax({
                  url:'test1.do',
                  dataType: 'json',
                  data: {name:'강건강', age:25},
                  success: function(data){
                     console.log(data);
                  }
               });
            });
         </script>
      </li>
      
      <li>
         컨트롤러에서 리턴하는 json 객체 받아서 출력하기 1
         
         <button id="test2">테스트</button>
         <div id="d2"></div>
         <script>
            $('#test2').on('click', function() {
               $.ajax({
                  url: 'test2.do',
                  success: function(data) {
                     console.log(data);
                     $('#d2').html('번호 : ' + data.no + "<br>"
                               + '제목 : ' + data.title + "<br>"      
                               + '작성자 : ' + data.writer + "<br>"      
                                + '내용 : ' + data.content + "<br>");
                  }
               });
            });
         </script>         
      </li>
   
      <li>
         컨트롤러에서 리턴하는 json 객체 받아서 출력하기 2
         <button id="test3">테스트</button>
         <div id="d3"></div>
         <script>
            $('#test3').on('click', function() {
               $.ajax({
                  url: 'test3.do',
//                  dataType: 'json',
                  success: function(data) {
                     console.log(data);
                     $('#d3').html('번호 : ' + data.no + "<br>"
                            + '제목 : ' + data.title + "<br>"      
                            + '작성자 : ' + decodeURIComponent(data.writer) + "<br>"      
                             + '내용 : ' + decodeURIComponent(data.content.replace(/\+/g, ' ')) + "<br>"
                             + '내용 : ' + decodeURIComponent(data.content.replaceAll('+', ' ')) + "<br>");
                  }
               });
            });
         </script>
      </li>
      
      <li>
         컨트롤러에서 리턴하는 json 배열을 받아서 출력하기
         <button id="test4">테스트</button>
         <div id="d4"></div>
         <script>
            $('#test4').on('click', function(){
               $.ajax({
                  url: 'test4.do',
                  dataType: 'json',
                  success: function(data) {
                     console.log(data);
                     
                     var value = $('#d4').html();
                     
                     for(var i in data){
                        value += data[i].userId + ", "
                              + data[i].userPwd + ", "
                              + data[i].userName + ", "
                              + data[i].age + ", "
                              + data[i].email + ", "
                              + data[i].phone + "<br>"
                     }
                     
                     $('#d4').html(value);

                  }
               });
            });
         </script>
      </li>
      
      <li>
         컨트롤러에서 리턴하는 json 배열을 받아서 출력하기2
         <button id="test5">테스트</button>
         <div id="d5"></div>
         <script>
            $('#test5').on('click', function(){
               $.ajax({
                  url: 'test5.do',
                  success: function(data) {
                     console.log(data);
                     
                     var value = $('#d5').html();
                     
                     for(var i in data){
                        value += data[i].userId + ", "
                              + data[i].userPwd + ", "
                              + data[i].userName + ", "
                              + data[i].age + ", "
                              + data[i].email + ", "
                              + data[i].phone + "<br>"
                     }
                     
                     $('#d5').html(value);

                  }
               });
            });
         </script>
      </li>
   </ol>

</body>
</html>





