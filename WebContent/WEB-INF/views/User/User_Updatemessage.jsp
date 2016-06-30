<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="dbcp.DBConnectionMgr"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% request.setCharacterEncoding("UTF-8"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<title>개인정보 수정 완료</title>
</head>
<body>
	<script>
		alert("개인정보가 수정되었습니다.");
		$(document).ready(function() {
			$("#mypageform").submit();	
		});
	</script>
<form action="user.do" id="mypageform" method="post">
<input type="hidden" name="param" value="mypage" />
</form>
</body>
</html>