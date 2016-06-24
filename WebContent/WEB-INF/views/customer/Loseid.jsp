<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.customerDto"%>
<%@page import="dbcp.DBConnectionMgr"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% request.setCharacterEncoding("UTF-8"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>이메일 찾기 페이지</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<style>
.body {
	margin-top: 50px
}
</style>
</head>
<body>
	<% customerDto emp = (customerDto) session.getAttribute("list"); %>
	<div class="container body">
	<div>
		<jsp:include page="../../../header.jsp" flush="false" />
	</div>
		<form class="form-horizontal" method="post" action="customer.do" name="form">
			<input type="hidden" name="param" value="lose" />
			<h2 class="text-center">이메일 찾기</h2>
			<hr />
			<% if(emp.getUser_email() != null) { %>
			<h4 class="text-center">찾으신 이메일 주소는 <strong><%=emp.getUser_email()%></strong> 입니다.</h4>
			<br>
			<div class="form-group">
				<div class="col-sm-offset-5 col-sm-2">
					&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit" class="btn btn-info"
						value="비밀번호 찾기" />
				</div>
			</div>
			<% } else if(emp.getUser_email() == null) { %>
			<h4 class="text-center"> 찾으시는 이메일 주소는 없습니다. 다시 확인해주세요.</h4>
			<div class="form-group">
				<div class="col-sm-offset-5 col-sm-2">
					&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit" class="btn btn-danger"
						value="돌아가기" />
				</div>
			</div>
			<% } %>
		</form>
	</div>
</body>
</html>