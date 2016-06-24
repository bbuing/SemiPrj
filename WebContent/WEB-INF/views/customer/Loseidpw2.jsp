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
			<% if(emp.getUser_question() == null) { %>
			<form class="form-horizontal" method="post" action="customer.do" name="form" >
			<input type="hidden" name="param" value="lose"/>
			<h2 class="text-center">비밀번호 찾기 실패</h2>
			<hr/>
			<h4 class="text-center"> 찾으시는 회원의 정보가 없습니다. 다시 확인해주세요.</h4>
			<div class="form-group">
				<div class="col-sm-offset-5 col-sm-2">
					&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit" class="btn btn-danger"
						value="돌아가기" />
				</div>
			</div>
			</form>
			<% } else if(emp.getUser_question() != null) { %>
			<form class="form-horizontal" method="post" action="customer.do" name="form" >
				<input type="hidden" name="param" value="losepw2"/>
				<input type="hidden" name="email" value="<%=emp.getUser_email()%>"/>
				<h2 class="text-center">비밀번호 2차 인증</h2>
				<hr/>
			<div class="form-group">
				<label for="Losepwquestion" class="col-sm-3 control-label">질문 </label>
				<div class="col-sm-6">
					<select class="form-control">
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
					<input type="text" class="form-control" id="Losepwanswer" name="Losepwanswer" placeholder="질문의 답을 입력하세요." />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-5 col-sm-2">&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="submit" class="btn btn-info" value = "비밀번호 찾기"/>
				</div>
			</div>
			<% } %>
		</form>
	</div>
	</body>
</html>