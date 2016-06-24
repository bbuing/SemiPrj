<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="dbcp.DBConnectionMgr"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% request.setCharacterEncoding("UTF-8"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>이메일/비밀번호 찾기 페이지</title>
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
	<div class="container body">
	<div>
		<jsp:include page="../../../header.jsp" flush="false" />
	</div>
		<form class="form-horizontal" method="post" action="customer.do" name="form" >
			<input type="hidden" name="param" value="loseid"/>
			<h2 class="text-center">이메일 찾기</h2>
			<hr/>
			<div class="form-group">
				<label for="Loseidname" class="col-sm-3 control-label">이름 </label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="Loseidname" name="Loseidname" placeholder="이름"/>
				</div>
			</div>
			
			<div class="form-group">
				<label for="Loseidnickname" class="col-sm-3 control-label">별명 </label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="Loseidnickname" name="Loseidnickname" placeholder="별명" />
				</div>
			</div>
			
			<div class="form-group">
				<label for="Loseidphone" class="col-sm-3 control-label">핸드폰 번호 </label>
					<div class="col-sm-2">
					<select class="form-control" name="Loseidphone">
						<option>010</option>
						<option>011</option>
						<option>016</option>
						<option>017</option>
						<option>019</option>
					</select>
				</div>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="Loseidphone2" name="Loseidphone2" />
				</div>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="Loseidphone3" name="Loseidphone3" />
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-5 col-sm-2">&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="submit" class="btn btn-info" value="아이디 찾기"/>
				</div>
			</div>
			</form>
			<br/>
			<h2 class="text-center">비밀번호 찾기</h2>
			<hr/>
			<form class="form-horizontal" method="post" action="customer.do" name="form" >
			<input type="hidden" name="param" value="losepw"/>
			<div class="form-group">
				<label for="Losepwname" class="col-sm-3 control-label">이름 </label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="Losepwname" name="Losepwname" placeholder="이름"/>
				</div>
			</div>
			
			<div class="form-group">
				<label for="Losepwnickname" class="col-sm-3 control-label">별명 </label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="Losepwnickname" name="Losepwnickname" placeholder="별명" />
				</div>
			</div>
			<div class="form-group">
				<label for="Losepwemail" class="col-sm-3 control-label">이메일 주소</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="Losepwemail" name="Losepwemail" placeholder="이메일 주소" />
				</div>
				
			</div>
			
			<div class="form-group">
				<label for="Losepwphone" class="col-sm-3 control-label">핸드폰 번호 </label>
					<div class="col-sm-2">
					<select class="form-control" name="Losepwphone">
						<option>010</option>
						<option>011</option>
						<option>016</option>
						<option>017</option>
						<option>019</option>
					</select>
				</div>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="Losepwphone2" name="Losepwphone2"/>
				</div>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="Losepwphone3" name="Losepwphone3"/>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-5 col-sm-2">&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="submit" class="btn btn-info" value="비밀번호 찾기"/>
				</div>
			</div>
		</form>
	</div>
</body>
</html>