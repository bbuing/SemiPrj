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
.title-box h5, h6 {
	margin: 0;
}

.round-like.active {
	background-color: #FF4500;
	-webkit-box-shadow: 1px 1px 5px rgba(76, 76, 76, 0.7);
	-moz-box-shadow: 1px 1px 5px rgba(76, 76, 76, 0.7);
	box-shadow: 1px 1px 5px rgba(76, 76, 76, 0.7);
}

.round-like.active a {
	color: white;
}
</style>
<!-- 댓글 -->
<script>
var flag = 0;
$(window).load (
	function() {
		//getLike();	
	}
);
   function getLike(board_num){ 
	  console.log("function getLike");
      $.ajax({
         type : "POST",         
         url : "getLike.do?board_num=" + board_num,
         success : function(data){         
            var jsonData = jQuery.parseJSON(data);            
            
            $("#likeDiv").empty();                     
            if(jsonData){        
               $("#likeDiv").append(jsonData);
            }
         }
      });
      
   };

   function writeComment(user_id, board_num){
      $.ajax({
         type : "POST",         
         url : "like.do?user_id=" + user_id + "&board_num=" + board_num,
         data : {"board_like" : $("#likeDiv").text()},
         success : function(){      
            getLike(board_num);         
            //$("#writeCommentContent" + apprNumber).val("");
         }         
      });
      
   };


</script>
<div id="title" class="" align="center"
	style="background-image: url(upload/${ReadDto.board_header})">
	<div class="title-box">
		<h2>${ReadDto.board_title}</h2>

		<h5>${ReadDto.board_period}</h5>
		<h5>${ReadDto.board_tag}</h5>
		<h5>
			<b>지역: </b>${ReadDto.board_region} <b>교통: </b>${ReadDto.board_transport}
			<b>숙소: </b>${ReadDto.board_stay} <b>테마: </b>${ReadDto.board_theme}</h5>
		<h6>
			<div class="board-user-profile">
				<img class="img-circle" alt="" width="30" height="30"
					src="upload/${ReadDto.user_profile }" />
			</div>
			${ReadDto.user_nick}님이 ${ReadDto.board_date}에 작성
		</h6>
	</div>
	<div id="top-menu-wrapper">
		<ul id="top-menu">
			<li class="round-icon round-like">
				<a href="#" onclick="writeComment(1, ${ReadDto.board_num});" class=""> 
					<span class="glyphicon glyphicon-heart">
						<div id="likeDiv" class="display: inline">${ReadDto.board_like}</div>
					</span>
				</a>
			</li>
			<c:forEach items="${ReadDto.cardList }" var="card">
				<li class="round-icon"><a href="#section${card.card_num }"
					class="type_top"><span
						class="glyphicon glyphicon-${card.card_type }"></span></a></li>

			</c:forEach>



		</ul>

	</div>

</div>