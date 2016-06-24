<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.BoardDto"%>
<%@page import="model.CardDto"%>
<%@page import="model.AddDto"%>
<%@page import="model.ReplyDto"%>


<div id="vertical-wrapper">
	<div class="vertical-narrative read-only" style="">
	
		<c:forEach items="${ReadDto.cardList }" var="card" >
		
		<div id="section${card.card_num}"
			class="narrative-section vertical-narrative-section read has-title"
			data-vertical-index="${card.card_num}" >
			<div class="round-icon active">
				<a href="#section0" class="type_top"><span
					class="glyphicon glyphicon-${card.card_type}"></span></a>
			</div>
			<section>
				<div class="title" dir="auto">${card.card_title}</div>
				<div class="content" dir="auto">
					${card.card_content}<br/>
				</div>
			</section>
			<div class="vertical-reply-row">
				<a class="popup-btn" data-popup-open="popup-section${card.card_num}" href="#"><span
					class="glyphicon glyphicon-comment"></span> ${card.card_reply_cnt} </a>
			</div>
		</div>
		</c:forEach>
		
	</div>
</div>

