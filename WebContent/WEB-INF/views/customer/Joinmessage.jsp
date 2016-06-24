<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% request.setCharacterEncoding("UTF-8"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입 완료</title>
<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
</head>
<body>
<script>
		//회원가입  후 메세지와 메인 페이지 이동
		alert("회원가입이 완료되었습니다.");
		$(document).ready(function() {
			$("#myform").submit();		
		});
</script>
<form action="customer.do" id="myform" method="post">
<input type="hidden" name="param" value="main" />
</form>
</body>
</html>