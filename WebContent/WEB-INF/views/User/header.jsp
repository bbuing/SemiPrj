<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.User_Dto"%>
<%@page import="dbcp.DBConnectionMgr"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% request.setCharacterEncoding("UTF-8"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/card3.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
</head>
<style>
.img { margin-top: 5px;
}
</style>
<script>
	$(document).ready(function() {
		$("#log").click(function() { 
			$("#logform").submit(); 
		});
		$("#gomain").click(function() {
			$("#gomainform").submit(); 
		});
		$("#gomypage").click(function() {
			$("#gomypageform").submit(); 
		});
	});
	function logout_event() {
		if (confirm("정말 로그아웃 하시겠습니까??") == true) { //확인
			$("#logoutform").submit();
		} else { //취소
			return;
		}
	}
</script>
<body>
	<% User_Dto emp = (User_Dto) session.getAttribute("list"); %>
	<% if (emp == null || emp.getUser_name() == null || emp.getUser_profile() == null) { %>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<img class="img" src="upload/semiDummy/3.png" style="height: 40px; width: 40px">
				<a class="navbar-brand" href="#" id="gomain">Criss+Cross</a>
				 
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="#">서비스 소개 </a></li>
					<li><a href="#">인기 글보기</a></li>
					<li><a href="#">글 작성하기</a></li>
				</ul>
				
				<ul class="nav navbar-nav navbar-right">
					<li><input type="button" class="btn btn-primary h_notice"
						value="로그인" id="log" /></li>
					<li><a></a></li>
					<li><a></a></li>
				</ul>
			</div>
		</div>
	</nav>
	<%
		} else {
	%>

	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<img class="img" src="upload/semiDummy/3.png" style="height: 40px; width: 40px">
				<a class="navbar-brand" href="#" id="gomain">Criss+Cross</a>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="#">서비스 소개 </a></li>
					<li><a href="#">인기 글보기</a></li>
					<li><a href="#">글 작성하기</a></li>
				</ul>
				
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><%=emp.getUser_name()%> 님 환영합니다.</a></li>
					<li><a href="#" id="gomypage">마이페이지</a></li>
					<li><a href="#" onclick="logout_event()">로그아웃</a></li>
					<li><a></a></li>
					<li><a></a></li>
				</ul>
			</div>
		</div>
	</navbar>
	<%
		}
	%>
	<form action="user.do" id="logform" method="post">
		<input type="hidden" name="param" value="log" />
	</form>
	<form id="logoutform" method="post" action="user.do">
		<input type="hidden" name="param" value="logout" />
		<input type="hidden" name="out" value="0" />
	</form>
	<form action="user.do" id="gomainform" method="post">
		<input type="hidden" name="param" value="main" />
	</form>
	<form action="user.do" id="gomypageform" method="post">
		<input type="hidden" name="param" value="mypage" />
	</form>
</body>
</html>