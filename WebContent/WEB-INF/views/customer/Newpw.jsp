<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.customerDto"%>
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
	margin-top: 80px
}
</style>
<script type="text/javascript">
function checkIt(){ //비밀번호 버튼 클릭시 이벤트
    var userinput=document.form;
    if(!userinput.newpw.value){
        alert("비밀번호를 입력하세요.");
        userinput.newpw.focus();
        return false;
    }
    var upw = document.form.newpw.value;
	if (!/^[a-zA-Z0-9]{8,20}$/.test(upw)) {
		alert("비밀번호는 숫자와 영문자 조합으로 8~20자리를 사용해야 합니다.");
		return false;
	}
	var chk_num = upw.search(/[0-9]/g);
	var chk_eng = upw.search(/[a-z]/ig);
	if (chk_num < 0 || chk_eng < 0)
	{
		alert("비밀번호는 숫자와 영문자를 혼용하여야 합니다.");
		return false;
	}
    if(!userinput.newpw2.value){
        alert("비밀번호 확인을 입력하세요.");
        userinput.newpw2.focus();
        return false;
    }
    var pw1 = document.form.newpw.value;
	var pw2 = document.form.newpw2.value;
	if (pw1 != pw2) {
		alert("동일한 암호를 입력하세요.");
		 return false;
	} 
    return true;
	}
	function checkPwd() {
		var upw = document.form.newpw.value;
		if (!/^[a-zA-Z0-9]{8,20}$/.test(upw)) {
			document.getElementById('checkPwd').style.color = "red";
			document.getElementById('checkPwd').innerHTML = "비밀번호는 숫자와 영문자 조합으로 8~20자리를 사용해야 합니다.";
			return false;
		}
		var chk_num = upw.search(/[0-9]/g);
		var chk_eng = upw.search(/[a-z]/ig);
		if (chk_num < 0 || chk_eng < 0)
		{
			document.getElementById('checkPwd').style.color = "red";
			document.getElementById('checkPwd').innerHTML = "비밀번호는 숫자와 영문자를 혼용하여야 합니다.";
			return false;
		}
		document.getElementById('checkPwd').style.color = "green";
		document.getElementById('checkPwd').innerHTML = "안전한 비밀번호 입니다.";
		return true;
	}
	function checkPwd2() {
		var pw1 = document.form.newpw.value;
		var pw2 = document.form.newpw2.value;
		if (pw1 != pw2) {
			document.getElementById('checkPwd2').style.color = "red";
			document.getElementById('checkPwd2').innerHTML = "동일한 암호를 입력하세요.";
		} else {
			document.getElementById('checkPwd2').style.color = "green";
			document.getElementById('checkPwd2').innerHTML = "암호가 확인 되었습니다.";
		}
	}
</script>
</head>
<body>
	<div>
		<jsp:include page="../../../header.jsp" flush="false" />
	</div>
	<% customerDto emp = (customerDto) session.getAttribute("list"); %>
	<div class="container body">
			<% if(emp.getUser_answer() == "no") { %>
			<form class="form-horizontal" method="post" action="customer.do" name="form2" >
			<input type="hidden" name="param" value="lose"/>
			<h2 class="text-center">질문/답 인증 실패</h2>
			<hr/>
			<h4 class="text-center"> 질문의 답이 틀렸습니다. 다시 입력해주세요.</h4>
			<div class="form-group">
				<div class="col-sm-offset-5 col-sm-2">
					&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit" class="btn btn-danger"
						value="돌아가기" />
				</div>
			</div>
			</form>
			<% } else if(emp.getUser_answer() == "yes") { %>
			<form class="form-horizontal" method="post" action="customer.do" name="form" >
				<input type="hidden" name="param" value="newpw"/>
				<input type="hidden" name="email" value="<%=emp.getUser_email()%>"/>
			<h2 class="text-center">새로운 비밀번호 변경</h2>
			<hr/>
			<div class="form-group">
				<label for="Loseidname" class="col-sm-3 control-label">비밀번호 </label>
				<div class="col-sm-6">
					<input type="password" class="form-control" id="newpw" name="newpw" 
					placeholder="비밀번호" onkeyup="checkPwd()"/>
				</div>
				<div class="col-sm-3" id="checkPwd"></div>
			</div>
			
			<div class="form-group">
				<label for="Loseidnickname" class="col-sm-3 control-label">비밀번호 확인 </label>
				<div class="col-sm-6">
					<input type="password" class="form-control" id="newpw2" name="newpw2" 
					placeholder="비밀번호 확인 입력" onkeyup="checkPwd2()"/>
				</div>
				<div class="col-sm-3" id="checkPwd2"></div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-5 col-sm-2">&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="submit" class="btn btn-info" value="비밀번호 변경" onclick="return checkIt()"/>
				</div>
			</div>
			</form>
			<%} %>
	</div>
</body>
</html>