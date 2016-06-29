<!--
	작성자 : 최락휘
	작성일 : 16.06.16
	유튜브 동영상 검색 리스트 출력 
 -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> youtube api test </title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
<style>
	#get_view {
		width:480px;
		height:700px;
	}
	.media-object {
		width: 105px;
		height: 85px;
	}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	function fnAdd(btn) {
		var index = $(btn).attr("id");
		var video_id = $("#video_id"+index).val();
		var cardIdx = $("#card_idx").val();
		$("#upload").append($("<input>",{
			type: "hidden",
			name: "card",
			value: "youtube"
		}));
		$("#upload").append($("<input>",{
			type: "hidden",
			name: "card",
			value: cardIdx
		}));
		$("#upload").append($("<input>",{
			type: "hidden",
			name: "card",
			value: video_id
		}));
	}
	// 검색창에 엔터 입력시 검색
	$(document).ready(function(){
		$("#search").keypress(function(e){
			if(e.which == 13)
				fnGetList();
		});		
	});
	// 유튜브 동영상 리스트를 받아오는 함수
	function fnGetList(sGetToken){
		var $getval = $("#search").val();	// 검색어
		if($getval==""){
			alert("검색어를 입력하세요.");
			$("#search").focus();
			return;
		}
		// #get_view : 영상을 뿌려주는 div , #nav_view : 페이지 이동을 담당하는 div
		$("#get_view").empty();
		$("#nav_view").empty()


		var sTargetUrl = "https://www.googleapis.com/youtube/v3/search?part=snippet&order=relevance"
							+ "&q="+ $getval +"&key=AIzaSyAl20jhQisZ3ZGIIWwVNylnFfX9mWotKyU";
		// 기본 페이지가 아니면 해당 페이지로 url을 설정
		if(sGetToken){
			sTargetUrl += "&pageToken="+sGetToken;
		}
		$.ajax({
			type: "POST",
			url: sTargetUrl,
			dataType: "jsonp",
			success: function(jdata) {
				console.log(jdata);
				// url로 부터 데이터를 받아와 #get_view에 부트스트랩 미디어객체 스타일을 이용해 넣어준다.
				$(jdata.items).each(function(idx){
					//console.log(this.snippet.channelId);
					$("#get_view").append(
							"<div class='media'>"
							+ "<div class='media-left media-middle'>"
							+ 	"<a>"
							+		"<img class='media-object' src='" + this.snippet.thumbnails.default.url + "'>"
							+ 	"</a>"
							+ "</div>"
							+ 	"<div class='media-body'>"
						    + 		"<h6 class='media-heading'>"
							+		"<strong>" + this.snippet.title + "</strong><br/><br/>" + this.snippet.description
							+ 		"</h6>"
							+		"<button class='btn btn-info btn-sm' id='" +idx+ "'style='float:right;' onClick='fnAdd(this)'>추가</button>"
							+		"<input type='hidden' id='video_id" +idx+ "' value='" + this.id.videoId + "'>"
						  	+ 	"</div>"
							+ "</div>"
							+ "<hr/>");
					// 영상을 뿌려주는 일이 끝나면 #nav_view에 페이지 이동 태그를 달아준다.
				}).promise().done(function(){
					if(jdata.prevPageToken){
						$("#nav_view").append("<a href='javascript:fnGetList(\""+jdata.prevPageToken+"\");'><이전페이지></a>");
					}
					if(jdata.nextPageToken){
						$("#nav_view").append("<a href='javascript:fnGetList(\""+jdata.nextPageToken+"\");'><다음페이지></a>");
					}
				});
			},
			error:function(xhr, textStatus) {
				console.log(xhr.responseText);
				alert("지금은 시스템 사정으로 인하여 요청하신 작업이 이루어지지 않았습니다.\n잠시후 다시 이용하세요.[2]");
				return;
			}
		});
	}
</script>
</head>
<body>
	<div class="input-group">
		<input type="search" id="search" class="form-control">
		<span class="input-group-btn"><button class="btn btn-default" onClick="fnGetList();">가져오기</button></span>
	</div>
	<div id="get_view"></div>
	<div id="nav_view"></div>
</body>
</html>