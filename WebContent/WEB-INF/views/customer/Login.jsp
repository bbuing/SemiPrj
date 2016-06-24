<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="dbcp.DBConnectionMgr"%>
<%	request.setCharacterEncoding("UTF-8"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 페이지</title>
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
<script>
		function checkIt(){    
		    var userinput=document.form;
		    if(!userinput.Loginemail.value){
		        alert("이메일 주소를 입력하세요.");
		        userinput.Loginemail.focus();
		        return false;
		    }
		    if(!userinput.Loginpw.value){
		        alert("비밀번호를 입력하세요.");
		        userinput.Loginpw.focus();
		        return false;
		    }
		    return true;
		}
		$(document).ready(function(){
			$("#termsbtn").click(function(){
				$("#terms").submit();
			});
			$("#losebtn").click(function(){
				$("#lose").submit();
			});
		});
</script>
<body>
	<div class="container body">
	<div>
		<jsp:include page="../../../header.jsp" flush="false" />
	</div>
		<form class="form-horizontal" method="post" action="customer.do" name="form" >
			<input type="hidden" name="param" value="login"/>
			<h2 class="text-center">로그인</h2>
			<hr/>
			
			<div class="form-group">
				<label class="col-md-3 control-label" for="Loginemail">이메일 주소</label>
				<div class="col-md-6">
					<input type="text" class="form-control" id="Loginemail" name="Loginemail" placeholder="이메일 주소를 입력하세요." />
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-3 control-label" for="Loginpw">비밀번호</label>
				<div class="col-sm-6">
					<input type="password" class="form-control" id="Loginpw" name="Loginpw" placeholder="비밀번호를 입력하세요." />
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-3">&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="submit" class="btn btn-primary" value="로그인" onclick="return checkIt()"/>
					<input type="button" class="btn btn-default col-sm-offset-3" id="termsbtn" value="회원가입"/>
					<input type="button" class="btn btn-default" id="losebtn" value="이메일/비밀번호 찾기"/>
				
				</div>
			</div>	
		</form>
		
		<form action="customer.do" id="terms" method="post">
			<input type="hidden" name="param" value="terms"/>
		</form>
		<form action="customer.do" id="lose" method="post">
			<input type="hidden" name="param" value="lose"/>
		</form>
		</div>
</body>
</html>