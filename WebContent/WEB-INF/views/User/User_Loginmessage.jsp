<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.User_Dto"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% request.setCharacterEncoding("UTF-8"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<title>로그인</title>
</head>
<body>
	<% User_Dto emp = (User_Dto) session.getAttribute("list"); %>
	<% if(emp.getUser_name() == null) {%>
	<script>
		alert("로그인에 실패하셨습니다. 이메일 주소와 비밀번호를 다시 확인해주세요.");
		$(document).ready(function() {
				$("#logform").submit();
			});
	</script>
	<% } else {%>
	<script>
		alert("<%=emp.getUser_name()%> 님 환영합니다.");
		$(document).ready(function() {
			$("#myform").submit();
		});
	</script>
	<%} %>
</body>
<form action="user.do" id="logform" method="post">
<input type="hidden" name="param" value="log" />
</form>
<form action="user.do" id="myform" method="post">
<input type="hidden" name="param" value="main" />
</form>
</html>