<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.User_Emailchk"%>
<%@page import="dbcp.DBConnectionMgr"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% request.setCharacterEncoding("UTF-8"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>이메일 중복 확인 페이지</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
</head>
<%
User_Emailchk manager = User_Emailchk.getInstance();
	String eamil = request.getParameter("chk");
	int check = manager.confirmId(eamil); // confirmId 결과인 숫자 1 또는 -1을 받아옴
%>
<body>
	<% if (check == 1) { // 받아온 숫자가 1이면 중복%>
	<form class="form-horizontal" action="JoinEmailchk.jsp" name="form1" method="post">
		<h4 class="text-center">"<strong><%=eamil%></strong>"</h4>
		<h4 class="text-center">중복된 이메일 주소입니다.</h4>
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
		<h4 class="text-center">"<strong><%=eamil%></strong>"</h4>
		<h4 class="text-center">사용 가능한 이메일 주소입니다.</h4>
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
			//오프너는 현재 팝업창의 값을 부모창으로 전달해줌
			// 문자 b는 중복체크 여부를 위해 가져감
			opener.document.emailform.emi.value = "b";
			opener.document.form.Joinemail.value = "<%=eamil%>";
			self.close();
		}
	</script>
</body>
</html>
