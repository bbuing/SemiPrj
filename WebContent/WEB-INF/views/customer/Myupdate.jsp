<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.StringTokenizer"%>
<%@page import="model.customerDto"%>
<%@page import="dbcp.DBConnectionMgr"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% request.setCharacterEncoding("UTF-8"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>개인정보수정 페이지</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="jquery-2.2.4.min.js"></script>
<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
</head>
<body>
	<%
		customerDto emp = (customerDto) session.getAttribute("list");
		StringTokenizer phonetoken = new StringTokenizer(emp.getUser_phone(), "-");
		String phone1 = phonetoken.nextToken();
		String phone2 = phonetoken.nextToken();
		String phone3 = phonetoken.nextToken();
		StringTokenizer interest = new StringTokenizer(emp.getUser_interest(), "-");
		String interest1 = interest.nextToken();
		String interest2 = interest.nextToken();
		String interest3 = interest.nextToken();
		String interest4 = interest.nextToken();
		String interest5 = interest.nextToken();
	%>
	<div>
		<jsp:include page="../../../header.jsp" flush="false" />
	</div>
	<div class="container">
		<form class="form-horizontal" method="post" action="customer.do" name=form enctype="multipart/form-data">
			<input type="hidden" name="param" value="update"/>
			<h2 class="text-center">개인정보수정</h2>
			<hr />

			<div class="form-group">
				<label for="Viewname" class="col-sm-3 control-label">이름 </label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="Viewname"
						value="<%=emp.getUser_name()%>"  readonly="readonly" />
				</div>
			</div>
			<div class="form-group">
					<label for="Viewemail" class="col-sm-3 control-label">이메일
						주소</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="Viewemail"
							value="<%=emp.getUser_email()%>" readonly="readonly" />
					</div>
			</div>
			<div class="form-group">
				<label for="Viewnickname" class="col-sm-3 control-label">별명
				</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="Viewnickname" id="Viewnickname"
						value="<%=emp.getUser_nick()%>" />
				</div>
				<div class="col-sm-1">
					<input type="button" class="btn btn-default" value="중복확인" id="nickchk"
					name="nickchk"  />
				</div>
			</div>

			<div class="form-group">
				<label for="Viewpw" class="col-sm-3 control-label">비밀번호</label>
				<div class="col-sm-6">
					<input type="password" class="form-control" name="Viewpw" id="Viewpw" onkeyup="checkPwd()"
						/>
				</div>
				<div class="col-sm-3" id="checkPwd"></div>
			</div>

			<div class="form-group">
				<label for="Viewpw2" class="col-sm-3 control-label">비밀번호 확인</label>
				<div class="col-sm-6">
					<input type="password" class="form-control" name="Viewpw2" id="Viewpw2" onkeyup="checkPwd2()"
						placeholder="비밀번호 확인" />
				</div>
				<div class="col-sm-3" id="checkPwd2"></div>
			</div>

			<div class="form-group">
				<label for="Viewquestion" class="col-sm-3 control-label">질문
				</label>
				<div class="col-sm-6">
					<select class="form-control" name="Viewquestion">
						<option <%if (emp.getUser_question().equals("hint1")) {%> selected="selected"
							<%}%> value="hint1">기억에 남는 추억의 장소는?</option>
						<option <%if (emp.getUser_question().equals("hint2")) {%> selected="selected"
							<%}%> value="hint2">자신의 인생 좌우명은?</option>
						<option <%if (emp.getUser_question().equals("hint3")) {%> selected="selected"
							<%}%> value="hint3">자신의 보물 제1호는?</option>
						<option <%if (emp.getUser_question().equals("hint4")) {%> selected="selected"
							<%}%> value="hint4">가장 기억에 남는 선생님 성함은?</option>
						<option <%if (emp.getUser_question().equals("hint5")) {%> selected="selected"
							<%}%> value="hint5">타인이 모르는 자신만의 신체비밀이 있다면?</option>
						<option <%if (emp.getUser_question().equals("hint6")) {%> selected="selected"
							<%}%> value="hint6">추억하고 싶은 날짜가 있다면?</option>
						<option <%if (emp.getUser_question().equals("hint7")) {%> selected="selected"
							<%}%> value="hint7">받았던 선물 중 기억에 남는 독특한 선물은?</option>
						<option <%if (emp.getUser_question().equals("hint8")) {%> selected="selected"
							<%}%> value="hint8">유년시절 가장 생각나는 친구 이름은?</option>
						<option <%if (emp.getUser_question().equals("hint9")) {%> selected="selected"
							<%}%> value="hint9">인상 깊게 읽은 책 이름은?</option>
						<option <%if (emp.getUser_question().equals("hint10")) {%> selected="selected"
							<%}%> value="hint10">읽은 책 중에서 좋아하는 구절이 있다면?</option>
						<option <%if (emp.getUser_question().equals("hint11")) {%> selected="selected"
							<%}%> value="hint11">자신이 두번째로 존경하는 인물은?</option>
						<option <%if (emp.getUser_question().equals("hint12")) {%> selected="selected"
							<%}%> value="hint12">친구들에게 공개하지 않은 어릴 적 별명이 있다면?</option>
						<option <%if (emp.getUser_question().equals("hint13")) {%> selected="selected"
							<%}%> value="hint13">초등학교 때 기억에 남는 짝꿍 이름은?</option>
						<option <%if (emp.getUser_question().equals("hint14")) {%> selected="selected"
							<%}%> value="hint14">다시 태어나면 되고 싶은 것은?</option>
						<option <%if (emp.getUser_question().equals("hint15")) {%> selected="selected"
							<%}%> value="hint15">내가 좋아하는 캐릭터는?</option>
					</select>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-6">
					<input type="text" class="form-control" name="Viewanswer"
						value=<%=emp.getUser_answer()%> />
				</div>
			</div>

			<div class="form-group">
				<label for="Viewphone" class="col-sm-3 control-label">핸드폰 번호
				</label>
				<div class="col-sm-2">
					<select class="form-control" name="Viewphone1">
						<option <%if (phone1.equals("010")) {%> selected="selected" <%}%>>010</option>
						<option <%if (phone1.equals("011")) {%> selected="selected" <%}%>>011</option>
						<option <%if (phone1.equals("016")) {%> selected="selected" <%}%>>016</option>
						<option <%if (phone1.equals("017")) {%> selected="selected" <%}%>>017</option>
						<option <%if (phone1.equals("019")) {%> selected="selected" <%}%>>019</option>
					</select>
				</div>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="Viewphone2"
						value=<%=phone2%> />
				</div>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="Viewphone3"
						value=<%=phone3%> />
				</div>

			</div>
			<div class="form-group">
				<label for="Joininterest" class="col-sm-3 control-label">관심
					여행</label> <label class="checkbox-inline">&nbsp;&nbsp;&nbsp; <input
					type="checkbox" name="Checkbox1" id="Checkbox1" value="option1" <%if (interest1.equals("option1")) {%> checked="checked"
							<%}%>/> 커플 여행
				</label> <label class="checkbox-inline"> <input type="checkbox"
					name="Checkbox2" id="Checkbox2" value="option2" <%if (interest2.equals("option2")) {%> checked="checked"
							<%}%>/> 가족 여행
				</label> <label class="checkbox-inline"> <input type="checkbox"
					name="Checkbox3" id="Checkbox3" value="option3" <%if (interest3.equals("option3")) {%> checked="checked"
							<%}%>/> 나홀로 여행
				</label> <label class="checkbox-inline"> <input type="checkbox"
					name="Checkbox4" id="Checkbox4" value="option4" <%if (interest4.equals("option4")) {%> checked="checked"
							<%}%>/> 기차 여행
				</label> <label class="checkbox-inline"> <input type="checkbox"
					name="Checkbox5" id="Checkbox5" value="option5" <%if (interest5.equals("option5")) {%> checked="checked"
							<%}%>/> 무전 여행
				</label>
			</div>

			<hr />
			<div class="form-group">
				<label for="Viewname" class="col-sm-3 control-label">프로필 사진 불러오기
				</label>
				<div class="col-sm-7">
					<img <%if (emp.getUser_profile().equals("null")) {%> src="nomal.jpg" <%} else {%> src="/semiProject/upload/<%=emp.getUser_profile()%>" <%}%>
					 class="img-thumbnail" id="UploadedProfile" style="height: 100px; width: 100px"
					/>
					<input type="hidden" id = "profile" name="profile" value="<%=emp.getUser_profile()%>"/>
				</div>
				<div class="col-sm-2">
					<input type="file" onchange="readURL(this);" id="InputFile" name="InputFile" />
					<br/>
					<label>프로필 사진의 사이즈는 <strong>100px * 100px</strong> 고정입니다. </label>
				</div>
				
			</div>
			<div class="form-group">
			<label for="Viewback" class="col-sm-3 control-label">배경 사진 불러오기</label>
			<div class="col-sm-7">
					<img <%if (emp.getUser_hearder().equals("null")) {%> src="back.jpg" <%} else {%> src="/semiProject/upload/<%=emp.getUser_hearder()%>" <%}%> 
					class="img-thumbnail" id="UploadedBack" style="height: 200px; width: 500px" />
					<input type="hidden" id = "background" name="background" value="<%=emp.getUser_hearder()%>"/>
				</div>
				<div class="col-sm-2">
					<input type="file" onchange="readURL2(this);" id="InputBackground" name="InputBackground" />
					<br/>
					<label>배경 사진의 사이즈는 <strong>800px * 400px</strong> 고정입니다. </label>
				</div>
			</div>
			<hr />
			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-2">
					<input type="submit" class="btn btn-info" value="수정하기"  onclick="return checkIt()"/>
				</div>
				<div class="col-sm-2">
				 <input type="button" class="btn btn-danger" value="회원탈퇴" id="delbtn" onclick="return checkdel()"/>
				</div>
			</div>
		</form>
		<form action="customer.do" name="delform" method="post">
			<input type="hidden" name="param" value="remove"/>
			<input type="hidden" name="del" value="<%=emp.getUser_email()%>"/>
		</form>
		<form name="nickform">
			<input type="hidden" id="nick"/>
		</form> 
		<script>
		function readURL(input) {
		    if (input.files && input.files[0]) {
		        var reader = new FileReader();
		        reader.onload = function (e) {
		            $('#UploadedProfile').attr('src', e.target.result);
		        }
		        reader.readAsDataURL(input.files[0]);
		    }
		}
		function readURL2(input) {
		    if (input.files && input.files[0]) {
		        var reader = new FileReader();
		        reader.onload = function (e) {
		            $('#UploadedBack').attr('src', e.target.result);
		        }
		        reader.readAsDataURL(input.files[0]);
		    }
		}
		function checkIt()
		{    
		    var userinput=document.form;
		    if(!userinput.Viewnickname.value){
		        alert("별명을 입력하세요.");
		        userinput.Viewnickname.focus();
		        return false;
		    }
		    if(!userinput.Viewpw.value){
		        alert("비밀번호를 입력하세요.");
		        userinput.Viewpw.focus();
		        return false;
		    }
		    var upw = document.form.Viewpw.value;

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
		    if(!userinput.Viewpw2.value){
		        alert("비밀번호 확인을 입력하세요.");
		        userinput.Viewpw2.focus();
		        return false;
		    }
		    var pw1 = document.form.Viewpw.value;
			var pw2 = document.form.Viewpw2.value;
		    
			if (pw1 != pw2) {
				alert("동일한 비밀번호를 입력하세요.");
				 return false;
			}
			
		    if(!userinput.Viewanswer.value){
		        alert("질문의 답변을 입력하세요.");
		        userinput.Viewanswer.focus();
		        return false;
		    }
		    if(!userinput.Viewphone2.value){
		        alert("핸드폰 번호를 입력하세요.");
		        userinput.Viewphone2.focus();
		        return false;
		    }
		    if(!userinput.Viewphone3.value){
		    	alert("핸드폰 번호를 입력하세요.");
		        userinput.Viewphone3.focus();
		        return false;
		    }
		    var ni = document.nickform.nick.value;
		    var realni = document.form.Viewnickname.value
		    var realni2 = "<%=emp.getUser_nick()%>";
		    var b = "b";
		    if(realni != realni2)
		    	{
		   		if(ni != b){
		    		alert("별명 중복체크를 확인해주세요.");
		    	return false;
		    	}
		    }
		    return true;
		}
		
		function checkPwd() {
			var upw = document.form.Viewpw.value;

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
			var pw1 = document.form.Viewpw.value;
			var pw2 = document.form.Viewpw2.value;
			if (pw1 != pw2) {
				document.getElementById('checkPwd2').style.color = "red";
				document.getElementById('checkPwd2').innerHTML = "동일한 비밀번호를 입력하세요.";
			} else {
				document.getElementById('checkPwd2').style.color = "green";
				document.getElementById('checkPwd2').innerHTML = "비밀번호가 확인 되었습니다.";

			}
		}
		function checkdel() {
			var pw3 = document.form.Viewpw.value;
			var pw4 = document.form.Viewpw2.value;
			var realpw = "<%=emp.getUser_pw()%>";
			if (pw3 != realpw) {
				alert("비밀번호가 틀렸습니다.");
				 return false;
			} else if (pw3 != pw4) {
				 alert("동일한 비밀번호를 입력하세요.");
				 return false;
			} else if (confirm("정말 회원탈퇴를 하시겠습니까?") == true){
				delform.submit();
			}else{
				    return false;
				}
			return
			}
			
		window.onload = function()
		{
			
			$("#nickchk").click(function(){
				var chk = document.form.Viewnickname.value;
				window.open("UpdateNicknamechk.jsp?chk="+chk,"","width=400, height=200, toolbar=no, location=no,status=no,menubar=no, scrollbars=no, resizable=no,fullscreen=no");
				
			});
			
			$("#Viewnickname").change(function(){
				$("#Viewnickname").val($("#Viewnickname").val());
			});
			
		}
		</script>
	</div>
</body>
</html>