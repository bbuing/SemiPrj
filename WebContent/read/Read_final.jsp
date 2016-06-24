<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="model.BoardDto"%>
<%@page import="model.CardDto"%>
<%@page import="model.AddDto"%>
<%@page import="model.ReplyDto"%>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/carousel.css" />
<link rel="stylesheet" href="css/read.css" />
<link rel="stylesheet" href="css/popup.css" />
<link rel="stylesheet" href="css/reply.css" />

<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script src="js/carousel.js"></script>
<script src="js/spyscroll.js"></script>
<script src="js/popup.js"></script>
<script src="js/ajax.js"></script>

</head>
<body>
<div>
	<jsp:include page="../header.jsp" flush ="false"/>
</div>

	<!-- 제목  시작 -->
	<jsp:include page="Read_title.jsp" />
	<!-- 제목  끝 -->
	<div class="container">

		<div class="content vertical-context">

			<!-- 세로축 시작 -->
			<jsp:include page="Read_vertical.jsp" />
			<!-- 세로축 끝 -->

			<!-- 가로축 시작 -->
			<jsp:include page="Read_horizontal.jsp" />
			
			<!-- 가로축 끝 -->

		</div>
		
		<!-- 댓글 popup -->
		<div id="include-reply">
			<jsp:include page="Read_reply.jsp" />
		</div>

	</div>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<!-- div class="row" align="center">
		<hr />
		<h1>푸터 부분 입니다.</h1>
	</div-->
</body>
</html>