<%@page import="javax.servlet.http.HttpServlet"%>
<%@page import="java.io.IOException"%>
<%@page import="javax.sql.rowset.serial.SerialException"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="model.ReadDao"%>
<%@page import="model.ReadDto"%>
<%@page import="model.CardDto"%>
<%@page import="model.AddDto"%>
<%@page import="model.ReplyDto"%>
<%@page import="model.User_Dto"%>
<%
	
%>

<script>
	$(document).ready(function() {
		$('#submitBtn').click(function() {
			$.ajax({
				type : "post",
				url : "reply.do", //this is my servlet
				//data: "input=" +$('#ip').val()+"&output="+$('#op').val(),
				success : function() {
					$('#include-reply').load("read/Read_reply.jsp");
				}
			});
		});

	});
	/*
	 $(document).ready(function() {
	 $('#replyForm').submit(function () {
	 location.reload();
	 });
	 });
	 */
	/*
	 $(document).ready(function() {
	 $("#replyForm").submit(function() {
	 var options = {
	

	 success: function(html) {
	 $("#include-reply").replaceWith($('#include-reply', $(html)));
	 },

	 url: "http://localhost:8080/semiProject/read/Read-reply.jsp"
	 }

	 $(this).ajaxSubmit(options);
	 return false;
	 });
	 });
	 */
	/*
	 $(document).ready(function() {
	 $('#submitBtn').click(function(e) {
	 e.preventDefault();
	 var param = $("#param").val();
	 var value ='param='+ajaxdata;
	
	 $.ajax({
	 url: "reply.do",
	 type: "post",
	 data: value,
	 cache: false,
	 success: function(data) {
	 //$("#country").val('');
	 $("#replyForm").html(data).slideDown('slow');
	 }
	 });
	 });
	 });
	 */
</script>



<c:forEach items="${ReadDto.cardList }" var="card">
	<div class="popup" data-popup="popup-section${card.card_num}">
		<div class="popup-inner">


			<a class="popup-close"
				data-popup-close="popup-section${card.card_num}" href="#"><span
				class="glyphicon glyphicon-remove"></span></a>
			<div class="reply-header">
				<div class="reply-title">${card.card_title}</div>
			</div>
			<div class="reply-context">
				<ul>
					<c:if test="${card.card_add_cnt>=0}">
						<c:forEach items="${card.replyList }" var="reply">
							<li>
								<div class="reply-section">
									<div class="reply-profile profile-shadow">
										<img class="img-circle" alt="" width="30" height="30"
											src="${reply.reply_user_profile}" />
									</div>
									<div class="reply-info">
										<div class="reply-user">${reply.reply_user_nick}</div>
										<div class="reply-content">${reply.reply_content}</div>
										<div class="reply-date">${reply.reply_date}</div>
									</div>
								</div>
							</li>
						</c:forEach>
					</c:if>
					<c:if test="${card.card_add_cnt>=0}">
					</c:if>
				</ul>
			</div>
			<div class="reply-form">
				<div class="reply-form-inner">
					<div class="reply-form-profile">
						<c:choose>

							<c:when test="${list.user_id == null}">
								<img class="img-circle" alt="" width="30" height="30"
									src="nomal.jpg" />
							</c:when>

							<c:when test="${list.user_profile == 'null'}">
								<img class="img-circle" alt="" width="30" height="30"
									src="nomal.jpg" />
							</c:when>

							<c:otherwise>
								<img class="img-circle" alt="" width="30" height="30"
									src="/semiProject/upload/${list.user_profile}" />
							</c:otherwise>

						</c:choose>

					</div>
					<form id="replyForm" action="reply.do" method="post">
						<input type="hidden" name="param" id="param" value="reply" /> <input
							type="hidden" name="card_num" value="${card.card_num}" /> <input
							type="hidden" name="card_index" value="${card.card_index}" /> <input
							type="hidden" name="card_reply_cnt"
							value="${card.card_reply_cnt}" /> <input type="hidden"
							name="board_num" value="${ReadDto.board_num}" /> <input
							type="hidden" name="user_id" value="${ReadDto.user_id}" /> <input
							type="hidden" name="reply_user_id" value="${list.user_id }" /> <input
							type="hidden" name="reply_user_nick" value="${ReadDto.user_id}" />
						<input type="hidden" name="reply_user_profile"
							value="${ReadDto.user_profile}" />

						<c:if test="${list.user_id == null}">
							<input type="text" name="reply_content" placeholder="로그인을 해주세요"
								disabled />
						</c:if>
						<c:if test="${list.user_id != null}">
							<input type="text" name="reply_content" placeholder="댓글을 입력해 주세요" />
						</c:if>
						<input id="submitBtn" type="submit" value="등록" />
					</form>
				</div>

			</div>

		</div>
	</div>
</c:forEach>