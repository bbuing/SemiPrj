<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.User_Nicknamechk"%>
<%@page import="dbcp.DBConnectionMgr"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% request.setCharacterEncoding("UTF-8"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>별명 중복 확인 페이지</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
</head>
<%
	String nick = request.getParameter("chk");
User_Nicknamechk manager = User_Nicknamechk.getInstance();
	int check = manager.confirmId(nick); // confirmId 결과인 숫자 1 또는 -1을 받아옴
%>
<body>
	<% if (check == 1) { // 받아온 숫자가 1이면 중복%>
	<form class="form-horizontal" action="JoinNicknamechk.jsp" name="form1" method="post">
		<h4 class="text-center">"<strong><%=nick%></strong>"</h4>
		<h4 class="text-center">중복된 별명입니다.</h4>
		<br/>
		<div class="form-group">
			<div class="col-xs-offset-1 col-xs-7">
				<input type="text" class="form-control" id="chk" name="chk"/>
			</div>
			<div class="col-xs-2">
				<input type="submit" class="btn btn-info center" value="중복 확인"/>
			</div>
		</div>
	</form>
	<% } else { // 1이 아니면 사용 가능%>
	<form class="form-horizontal">
		<h4 class="text-center">"<strong><%=nick%></strong>"</h4>
		<h4 class="text-center">사용 가능한 별명입니다.</h4>
		<br/>
		<div class="col-xs-offset-4 col-xs-2">
		<input type="button" class="btn btn-success" value="확인" onclick="setid2()">
		</div>
	</form>
	<% } %>
	<script type="text/javascript">
		function setid() {
			self.close();
		}
		function setid2() {
			// 오프너는 현재 팝업창의 값을 부모창으로 전달해줌
			// 문자 b는 중복체크 여부를 위해 가져감
			opener.document.nickform.nick.value = "b";
			opener.document.form.Joinnickname.value = "<%=nick%>";
			self.close();
		}
	</script>
</body>
</html>
