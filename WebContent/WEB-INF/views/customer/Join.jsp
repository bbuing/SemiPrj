<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="basic.BasicBean"%>
<%@page import="dbcp.DBConnectionMgr"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% request.setCharacterEncoding("UTF-8"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입 페이지</title>
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
<script type="text/javascript">
	function checkIt() {
		var userinput = document.form;
		if (!userinput.Joinname.value) {
			alert("이름를 입력하세요.");
			userinput.Joinname.focus();
			return false;
		}
		if (!userinput.Joinnickname.value) {
			alert("별명을 입력하세요.");
			userinput.Joinnickname.focus();
			return false;
		}
		if (!userinput.Joinemail.value) {
			alert("이메일 주소를 입력하세요.");
			userinput.Joinemail.focus();
			return false;
		}
		if (!userinput.Joinpw.value) {
			alert("비밀번호를 입력하세요.");
			userinput.Joinpw.focus();
			return false;
		}
		var upw = document.form.Joinpw.value;
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
		if (!userinput.Joinpw2.value) {
			alert("비밀번호 확인을 입력하세요.");
			userinput.Joinpw2.focus();
			return false;
		}
		var pw1 = document.form.Joinpw.value;
		var pw2 = document.form.Joinpw2.value;
		if (pw1 != pw2) {
			alert("동일한 암호를 입력하세요.");
			return false;
		}
		if (!userinput.Joinanswer.value) {
			alert("질문의 답변을 입력하세요.");
			userinput.Joinanswer.focus();
			return false;
		}
		if (!userinput.Joinphone2.value) {
			alert("핸드폰 번호를 입력하세요.");
			userinput.Joinphone2.focus();
			return false;
		}
		if (!userinput.Joinphone3.value) {
			alert("핸드폰 번호를 입력하세요.");
			userinput.Joinphone3.focus();
			return false;
		}
		var ni = document.nickform.nick.value;
		var em = document.emailform.emi.value;
		var b = "b";
		if (ni != b) {
			alert("별명 중복체크를 확인해주세요.");
			return false;
		}
		if (em != b) {
			alert("이메일 중복체크를 확인해주세요.");
			return false;
		}
		return true;
	}

	function checkPwd() {
		var upw = document.form.Joinpw.value;
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
		var pw1 = document.form.Joinpw.value;
		var pw2 = document.form.Joinpw2.value;
		if (pw1 != pw2) {
			document.getElementById('checkPwd2').style.color = "red";
			document.getElementById('checkPwd2').innerHTML = "동일한 암호를 입력하세요.";
		} else {
			document.getElementById('checkPwd2').style.color = "green";
			document.getElementById('checkPwd2').innerHTML = "암호가 확인 되었습니다.";
		}
	}
	function nicknamechk() {
		var chk = document.form.Joinnickname.value;
		window.open("JoinNicknamechk.jsp?chk=" + chk, "",
					"width=350, height=200, toolbar=no, location=no,status=no,menubar=no, scrollbars=no, resizable=no,fullscreen=no");
	}
	function eamilchk() {
		var chk = document.form.Joinemail.value;
		window.open("JoinEmailchk.jsp?chk=" + chk,"",
					"width=350, height=200, toolbar=no, location=no,status=no,menubar=no, scrollbars=no, resizable=no,fullscreen=no");
	}
</script>
</head>
<body>
	<div class="container body">
	<div>
		<jsp:include page="../../../header.jsp" flush="false" />
	</div>
		<form class="form-horizontal" method="post" action="customer.do"
			name="form">
			<input type="hidden" name="param" value="join" />
			<h2 class="text-center">회원가입</h2>
			<hr />
			<div class="form-group">
				<label for="Joinname" class="col-sm-3 control-label">이름 </label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="Joinname"
						id="Joinname" placeholder="이름" />
				</div>
			</div>
			
			<div class="form-group">
				<label for="Joinnickname" class="col-sm-3 control-label">별명
				</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="Joinnickname"
						id="Joinnickname" placeholder="별명" />
				</div>
				<div class="col-sm-1">
					<input type="button" class="btn btn-default" value="중복확인"
						onclick="nicknamechk()" />
				</div>
			</div>

			<div class="form-group">
				<label for="Joinemail" class="col-sm-3 control-label">이메일 주소</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="Joinemail"
						id="Joinemail" placeholder="이메일" />
				</div>
				<div class="col-sm-1">
					<input type="button" class="btn btn-default" value="중복확인"
						onclick="eamilchk()" />
				</div>
			</div>

			<div class="form-group">
				<label for="Joinpw" class="col-sm-3 control-label">비밀번호</label>
				<div class="col-sm-6">
					<input type="password" class="form-control" name="Joinpw"
						id="Joinpw" placeholder="영문과 숫자 조합 8자리 이상 입력하세요."
						onkeyup="checkPwd()" />
				</div>
				<div class="col-sm-3" id="checkPwd"></div>
			</div>

			<div class="form-group">
				<label for="Joinpw2" class="col-sm-3 control-label">비밀번호 확인</label>
				<div class="col-sm-6">
					<input type="password" class="form-control" name="Joinpw2"
						placeholder="비밀번호 확인" onkeyup="checkPwd2()" />
				</div>
				<div class="col-sm-3" id="checkPwd2"></div>
			</div>

			<div class="form-group">
				<label for="Joinquestion" class="col-sm-3 control-label">질문
				</label>
				<div class="col-sm-6">
					<select class="form-control" name="Joinquestion">
						<option value="hint1">기억에 남는 추억의 장소는?</option>
						<option value="hint2">자신의 인생 좌우명은?</option>
						<option value="hint3">자신의 보물 제1호는?</option>
						<option value="hint4">가장 기억에 남는 선생님 성함은?</option>
						<option value="hint5">타인이 모르는 자신만의 신체비밀이 있다면?</option>
						<option value="hint6">추억하고 싶은 날짜가 있다면?</option>
						<option value="hint7">받았던 선물 중 기억에 남는 독특한 선물은?</option>
						<option value="hint8">유년시절 가장 생각나는 친구 이름은?</option>
						<option value="hint9">인상 깊게 읽은 책 이름은?</option>
						<option value="hint10">읽은 책 중에서 좋아하는 구절이 있다면?</option>
						<option value="hint11">자신이 두번째로 존경하는 인물은?</option>
						<option value="hint12">친구들에게 공개하지 않은 어릴 적 별명이 있다면?</option>
						<option value="hint13">초등학교 때 기억에 남는 짝꿍 이름은?</option>
						<option value="hint14">다시 태어나면 되고 싶은 것은?</option>
						<option value="hint15">내가 좋아하는 캐릭터는?</option>
					</select>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-6">
					<input type="text" class="form-control" name="Joinanswer"
						placeholder="질문의 답을 입력하세요." />
				</div>
			</div>

			<div class="form-group">
				<label for="Joinphone" class="col-sm-3 control-label">핸드폰 번호
				</label>
				<div class="col-sm-2">
					<select class="form-control" name="Joinphone1">
						<option>010</option>
						<option>011</option>
						<option>016</option>
						<option>017</option>
						<option>019</option>
					</select>
				</div>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="Joinphone2" />
				</div>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="Joinphone3" />
				</div>

			</div>
			<div class="form-group">
				<label for="Joininterest" class="col-sm-3 control-label">관심
					여행</label> <label class="checkbox-inline">&nbsp;&nbsp;&nbsp; <input
					type="checkbox" name="Checkbox1" id="Checkbox1" value="option1"
					name="chkbox" /> 커플 여행
				</label> <label class="checkbox-inline"> <input type="checkbox"
					name="Checkbox2" id="Checkbox2" value="option2" name="chkbox" />
					가족 여행
				</label> <label class="checkbox-inline"> <input type="checkbox"
					name="Checkbox3" id="Checkbox3" value="option3" name="chkbox" />
					나홀로 여행
				</label> <label class="checkbox-inline"> <input type="checkbox"
					name="Checkbox4" id="Checkbox4" value="option4" name="chkbox" />
					기차 여행
				</label> <label class="checkbox-inline"> <input type="checkbox"
					name="Checkbox5" id="Checkbox5" value="option5" name="chkbox" />
					무전 여행
				</label>
			</div>
			<hr />

			<div class="form-group">
				<div class="col-sm-offset-5 col-sm-2">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
						type="submit" class="btn btn-primary" value="가입하기"
						onclick="return checkIt()" />
				</div>
			</div>
		</form>
		<form name="nickform">
			<input type="hidden" id="nick" />
		</form>
		<form name="emailform">
			<input type="hidden" id="emi" />
		</form>
		
	</div>
</body>
</html>