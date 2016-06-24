<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="model.BoardDto"%>
<%@page import="model.CardDto"%>
<%@page import="model.AddDto"%>
<%@page import="model.ReplyDto"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<style>
.title-box h5, h6{
	margin: 0;
}
.round-like {
	background-color: 	#FF4500;
    -webkit-box-shadow: 1px 1px 5px rgba(76, 76, 76, 0.7);
    -moz-box-shadow: 1px 1px 5px rgba(76, 76, 76, 0.7);
    box-shadow: 1px 1px 5px rgba(76, 76, 76, 0.7);
}
.round-like a {
	color: white;
}
</style>
<div id="title" class="" align="center"
	style="background-image: url(${ReadDto.board_header})">
	<!--  img class="title-background" src="${ReadDto.board_header}" /-->
	<div class="title-box">
		<h2>${ReadDto.board_title}</h2>
		
		<h5>${ReadDto.board_period}</h5>
		<h5>${ReadDto.board_tag}</h5>
		<h6>
			<div class="board-user-profile">
				<img class="img-circle" alt="" width="30" height="30"
					src="${ReadDto.user_profile }" />
			</div>
			${ReadDto.user_nick}님이 ${ReadDto.board_date}에 작성
		</h6>
	</div>
	<div id="top-menu-wrapper">
		<ul id="top-menu">
			<li class="round-icon round-like"><a href="" class="">
					<span class="glyphicon glyphicon-heart">${ReadDto.board_like}</span></a>
				</li>
			<c:forEach items="${ReadDto.cardList }" var="card">
				<li class="round-icon"><a href="#section${card.card_num }"
					class="type_top"><span
						class="glyphicon glyphicon-${card.card_type }"></span></a></li>

			</c:forEach>
			
				
			
		</ul>

	</div>

</div>