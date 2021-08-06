<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<style>#tb{margin: auto; width: 700px;}</style>
</head>
<body>
	<c:import url="common/menubar.jsp"/>
	
	<script>
		$(function(){
			var msg ='${ msg }';
			if(msg != ''){
				alert(msg);
			}
		});
	</script>
	
	<h1 align="center">게시글 TOP 5 목록</h1>
	<table id="tb" border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>날짜</th>
				<th>조회수</th>
				<th>첨부파일</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	
	<script>
		function topList() {
			$.ajax({
				url: 'topList.bo',
				success: function(data){
					console.log(data);
					$tableBody = $('#tb tbody');
					$tableBody.html('');
					
					for(var i in data){
						var $tr = $('<tr>');
						var $id = $('<td>').text(data[i].boardId);
						var $title = $("<td>").text(data[i].boardTitle);
						var $writer = $("<td>").text(data[i].boardWriter);
						var $createDate = $("<td>").text(data[i].boardModifyDate);
						var $count = $("<td>").text(data[i].boardCount);
						var $file = $('<td>').text('');
						if(data[i].originalFileName != null) {
							$file = $('<td>').text('O');
						}
						
						$tr.append($id);
						$tr.append($title);
						$tr.append($writer);
						$tr.append($createDate);
						$tr.append($count);
						$tr.append($file);
						$tableBody.append($tr);
					}
				},
				error: function(data){
					console.log(data);
				}
			});
		}
		
		$(function() {
			topList();
						
			setInterval(function() {
				topList();
			}, 3000);
		});
		

	</script>
</body>
</html>
