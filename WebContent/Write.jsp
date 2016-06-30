<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/wr_carousel.css" />
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script src="js/holder.js"></script>
<script src="js/write.js"></script>
<script src="js/wr_carousel.js"></script>
<script src="js/wr_spyscroll.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<style>
#instaPics{
	width:400px;
	height:250px;
}
#insta{
	width:150px;
	height:150px;
	margin:2px;
}
#instaDesc {
	width:490px;
	height:400px;
	text-align: center;
}
#header {
    position: fixed;
    top: 0px;
    left:30px;
    width:100%;
    z-index: 1;
}
#title {
	margin-top:110px;
}
#card_content {
	border: 1px silver solid;
	padding:5px; 
	width:490px;
	height:385px; 
	display:none;
}
#theme_order {
	width : 500px;
	height : 60px;
	overflow: auto;
}
#board_rtts_title span{
	width:110px;
}
#board_rtts {
	height: 150px;
	display:none;
	z-index: 3;
}
#board_rtts_contents div {
	width:110px;
	height:100px;
	overflow:auto;
}
</style>
<script>

</script>
</head>
<body>
	<input type="hidden" id="token" name="token" />
	<div class="container">
		<div id="header" class="row">
			<jsp:include page="header.jsp"/>
		</div>
		<!-- 제목,여행기간을 작성할 상단 부분 -->
		<div id="title" class="row" class="form-inline col-md-12">
			<div class="form-inline" align="center">
				<div class="form-group thumbnail" style="float:left">
					<img id="title_img_preview" src="holder.js/310x260"/>
					<div class="caption">
						<button type="button" class="btn btn-info" id="title_img_btn">
							<span class="glyphicon glyphicon-picture"></span>
						</button>
					</div>
				</div>
				<div class="form-group">
					<!-- 각각의 타입들을 내부의 class의 숫자는 addIndex -->
					<form id="upload" action="write.do" method="POST" enctype="multipart/form-data">
						<input type="hidden" name="param" value="save" />
						<input type="file" id="title_img" name="title_img" style="display: none"/>
						<input type="hidden" name="user_id" value='<%= request.getAttribute("user_id")%>' />
						<input type="hidden" id="title_img_name" name="title_img_name" />
						<input class="form-control input-lg" type="text" size="60" name="title" placeholder="타이틀을 넣어주세요" /><br/><br/>
						<input class="form-control input-lg" type="text" size="60" name="date" placeholder="여행 기간 입력" /><br/><br/>
					</form>
					<h3><span class="form-control label label-default">작성 순서</span></h3>
					<div id="theme_order">
						<span class="round-icon"><a href="#section0" class="type_top"><span class="glyphicon glyphicon-send"></span></a></span>							
					</div>
					<button class="btn btn-info btn-xs" onClick="show_rtts(this)">게시물 설정</button>
				</div>
				<div id="board_rtts" class="form-group">
					<div id="board_rtts_title" class="form-inline">
						<span class="form-group"><h4>지역</h4></span>
						<span class="form-group"><h4>테마</h4></span>
						<span class="form-group"><h4>교통수단</h4></span>
						<span class="form-group"><h4>숙박</h4></span>
					</div>
					<div id="board_rtts_contents" class="form-inline">
						<div id="board_region" class="form-group">
							<center><input type="checkbox" class="region" value="서울"/> 서울&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
							<center><input type="checkbox" class="region" value="서울"/> 인천&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
							<center><input type="checkbox" class="region" value="서울"/> 경기도&nbsp;&nbsp;&nbsp;</center>
							<center><input type="checkbox" class="region" value="서울"/> 충청북도</center>
							<center><input type="checkbox" class="region" value="서울"/> 충청남도</center>
							<center><input type="checkbox" class="region" value="서울"/> 경상북도</center>
							<center><input type="checkbox" class="region" value="서울"/> 경상남도</center>
							<center><input type="checkbox" class="region" value="서울"/> 전라북도</center>
							<center><input type="checkbox" class="region" value="서울"/> 전라남도</center>
							<center><input type="checkbox" class="region" value="서울"/> 제주도&nbsp;&nbsp;&nbsp;</center>
						</div>
						<div id="board_theme" class="form-group">
							<center><input type="checkbox" class="theme" value="서울"/> 커플&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
							<center><input type="checkbox" class="theme" value="서울"/> 가족&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
							<center><input type="checkbox" class="theme" value="서울"/> 단체&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
							<center><input type="checkbox" class="theme" value="서울"/> 나홀로&nbsp;&nbsp;&nbsp;</center>
							<center><input type="checkbox" class="theme" value="서울"/> 힐링&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
							<center><input type="checkbox" class="theme" value="서울"/> 저가여행</center>
							<center><input type="checkbox" class="theme" value="서울"/> 바다&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
							<center><input type="checkbox" class="theme" value="서울"/> 계곡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
							<center><input type="checkbox" class="theme" value="서울"/> 등산&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
							<center><input type="checkbox" class="theme" value="서울"/> 반려동물</center>
						</div>
						<div id="board_transport" class="form-group">
							<center><input type="checkbox" class="transport" value="서울"/> 자동차</center>
							<center><input type="checkbox" class="transport" value="서울"/> 비행기</center>
							<center><input type="checkbox" class="transport" value="서울"/> 배&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</center>
							<center><input type="checkbox" class="transport" value="서울"/> 기차&nbsp;&nbsp;&nbsp;</center>
							<center><input type="checkbox" class="transport" value="서울"/> 지하철</center>
							<center><input type="checkbox" class="transport" value="서울"/> 버스&nbsp;&nbsp;&nbsp;</center>
							<center><input type="checkbox" class="transport" value="서울"/> 도보&nbsp;&nbsp;&nbsp;</center>
						</div>
						<div id="board_stay" class="form-group">
							<center><input type="checkbox" class="stay" value="서울"/> 펜션</center>
							<center><input type="checkbox" class="stay" value="서울"/> 호텔</center>
							<center><input type="checkbox" class="stay" value="서울"/> 모텔</center>
							<center><input type="checkbox" class="stay" value="서울"/> 게스트하우스</center>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
		<hr />
		<!-- 하단 작성할 내용 부분 -->
		<div class="vertical-narrative read-only">
			<div id="theme_list" class="narrative-section vertical-narrative-section" style="z-index:2;">
				<div class="btn-group">
					<button type="button" class="btn btn-default" onClick="setTheme(this)"><span class="glyphicon glyphicon-plane"></span></button>
					<button type="button" class="btn btn-default" onClick="setTheme(this)"><span class="glyphicon glyphicon-heart"></span></button>
					<button type="button" class="btn btn-default" onClick="setTheme(this)"><span class="glyphicon glyphicon-home"></span></button>
					<button type="button" class="btn btn-default active" onClick="setTheme(this)"><span class="glyphicon glyphicon-send"></span></button>
				</div>
				<div id="section0" class=".narrative-section" style="margin-bottom: 10px;">
					<textarea class="narrative-section vertical-narrative-section read has-title" data-vertical-index="0" rows="16" id="theme" name="theme" placeholder="내용을 작성해 주세요." style="border:1px solid silver" onFocus="fnFocus(this)"></textarea><br/>
					<button type="button" class="btn btn-default btn-block" onClick="addTheme(this)">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
					</button>
				</div>
			</div>
			<center><button id="submit" class="btn btn-info">저장하기</button></center>
		</div>
		<div id="card_btn" style="position:absolute; left: 950px; top: 564px; width:60px; z-index: 2;">
				<!-- 부가내용 추가 버튼(오른쪽) -->
			<div class="btn_group">
				<button class="show_card_btn btn btn-default" onClick="showCard(this)" style="height: 335px; margin-left: 10px;"><span class="glyphicon glyphicon-plus"></span></button>
				<button class="del_card_btn btn btn-default" onClick="delCard(this)" style="height: 335px; margin-left: 10px; display: none;"><span class="glyphicon glyphicon-remove"></span></button>
			</div>
		</div>
			<div id="card_content" style="position:absolute; top:750px; left:1050px; width:600px; height: 600px;">
				<input type="hidden" id="card_idx" name="card_idx"/>
				<ul class="nav nav-tabs nav-justified">
					<li><input type="file" id="card_img" style="display: none" /><a class="btn btn-default" id="photo_tab" onclick="cardTab(this)"><span class="glyphicon glyphicon-picture"></span></a></li>
					<li><a id="instaBtn" class="btn btn-default" onclick="cardTab(this)"><span class="glyphicon glyphicon-camera"></span></a></li>
					<li><a class="btn btn-default"onclick="cardTab(this)"><span class="glyphicon glyphicon-thumbs-up"></span></a></li>
					<li><a class="btn btn-default"onclick="cardTab(this)"><span class="glyphicon glyphicon-map-marker"></span></a></li>
					<li><a class="btn btn-default"onclick="cardTab(this)"><span class="glyphicon glyphicon-option-horizontal"></span></a></li>
				</ul>
				<div id="card" style="width:600px;">
					<div style="display: none;"><jsp:include page = "OwnPhotos.jsp"/></div>
					<div class="continaer" id="insta_div" style="display: none;">
						<div id="instaPics" class="row" style="overflow: auto;"></div>
						<button type="button" id="instaAdd" class="btn btn-info">추가</button>
						<div id="instaDesc" style="display:none;"></div>
					</div>
					<div style="display: none;"><jsp:include page = "Youtube.jsp" /></div>
					<div id="google" style="display: none;">
						<div class="input-group" style="width:360px;">
							<input type="search" id="map_search" class="form-control" placeholder="지역을 입력하세요"/> 
							<span class="input-group-btn">
								<button class="btn btn-default" id="map_search_btn">검색</button>
							</span>
						</div>
					<iframe id="map" width="478" height="270" frameborder="0" style="border:0"
  						src="https://www.google.com/maps/embed/v1/place?key=AIzaSyAmsGI_8BQcslfyf8AnFmSvi0zESELZWOQ
    					&q=%EC%84%9C%EC%9A%B8%20%EA%B4%91%ED%99%94%EB%AC%B8"  allowfullscreen>
					</iframe>
					<button class="btn btn-info" style="float:right">저장</button>
					</div>
				</div>
			</div>
	<!-- scroll이 생기게 하기 위한 -->
	<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
	<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
	<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
	<!-- 보여줄 각각의 카드부분 -->
		<div id="hori-section0" class="hori horizontal-context horizontal-read "></div>
</body>
</html>