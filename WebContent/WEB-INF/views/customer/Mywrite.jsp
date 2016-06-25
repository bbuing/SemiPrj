<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<%@page import="model.BoardDto"%>
<%@page import="dbcp.DBConnectionMgr"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>내가 작성한 글</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/card3.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="jquery-2.2.4.min.js"></script>
<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<style>
.body {
	margin-top: 80px
}
.icon_like{
   width:15px;
   height:15px;
}
.icon_tag{
   margin-right:30px;
   width:25px;
   height:25px;
}
</style>
</head>
<body>
	<% Vector<BoardDto>  list = (Vector<BoardDto>)session.getAttribute("list1"); %>
	
	<div>
		<jsp:include page="../../../header.jsp" flush="false" />
	</div>
	<div class="container">
		
		<div class="demo-card">
			<div class="card-deck-wrapper">
				<div class="ui link cards centered">
				
<form method="post" action="read.do">
		<input type="hidden" name="param" value="read" />
		<div class="demo-card">
			<div class="card-deck-wrapper">
				<div class="ui link cards centered">
					<%
						if (list == null) {
								out.println("작성한 글이 없습니다.");
							} else {
								for (int i = 0; i < list.size(); i++) {
									BoardDto dto2 = (BoardDto) list.get(i);
					%>

					<%
					
						out.println("<div class='ui card'>");
									out.println("<div class='image'>");
									out.print("<img src='/semiProject/upload/");%><%=dto2.getBoard_num() %><% out.print("/");%><%=dto2.getSearch_header()%><% out.println("'>");
									out.println("</div>");
									out.println("<div class='content'>");
									out.println("<div id = 'title' class='header' style='font-family :Nanum;'>");
					%>
					<a href="read.do?param=read&board_num=<%=dto2.getBoard_num() %> " style="color:000000">
					</a>
					<%
						out.println("</div>");
									out.println(
											"<div id='content' style ='height : 200px;text-overflow:ellipsis; overflow:hidden; font-family :Nanum;' class='description'>");
					%>

					<%=dto2.getSearch_card()%>

					<%
						out.println("</div>");
									out.println("</div>");

									out.println("<div class='extra content'>");
									String[] array = dto2.getSearch_tag().split(",");

									for (int j = 0; j < array.length; j++) {
					%>
					<a href="#" style="color: blue;" id="<%=array[j]%>" class="tags">
						</a>

					<%
						}
									out.println("<hr>");
									out.println("<div style='margin-left: 20px; text-align:center' >");
									%>
									<%
									out.print("<a href='read.do?param=read&board_num=");
									%>
									<%=dto2.getBoard_num()%><%out.print("#section1'><img src='images/transport.png' class='icon_tag'></a>");%>
									<%
									out.print("<a href='read.do?param=read&board_num=");
									%>
									<%=dto2.getBoard_num()%><%out.print("#section2'><img src='images/home.png' class='icon_tag'></a>");%>
									<%
									out.print("<a href='read.do?param=read&board_num=");
									%>
									<%=dto2.getBoard_num()%><%out.print("#section3'><img src='images/pen.png' class='icon_tag'></a>");%>
									<%
									out.print("<a href='read.do?param=read&board_num=");
									%>
									<%=dto2.getBoard_num()%><%out.print("#section4'><img src='images/food.png' class='icon_tag'></a>");%><%
									out.println("</div>");
									out.println("<hr/>");
									out.println("<div>");
									out.println("<img src='img/like.png' class='icon_like'>");
					%>
					좋아요 <%=dto2.getBoard_like()%>개

					<%
						out.println("<div style='text-align : right'>");
					%>
					작성자 :
					<%=dto2.getUser_nick()%>

					<%
						out.println("</div>");
									out.println("</div>");
									out.println("</div>");
									out.println("</div>");

								}							}
						
					%>

				</div>
			</div>



		</div>
</form>

	</div>


</body>


</html>