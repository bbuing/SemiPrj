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
</style>
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
					<form id="upload" action="customer.do" method="POST" enctype="multipart/form-data">
						<input type="hidden" name="param" value="save" />
						<input type="file" id="title_img" name="title_img" style="display: none"/>
						<input type="hidden" name="user_id" value='<%= request.getParameter("user_id")%>' />
						<input type="hidden" id="title_img_name" name="title_img_name" />
						<input class="form-control input-lg" type="text" size="60" name="title" placeholder="타이틀을 넣어주세요" /><br/><br/>
						<input class="form-control input-lg" type="text" size="60" name="date" placeholder="여행 기간 입력" /><br/><br/>
						지역&nbsp;&nbsp;
						<select name="select">
							<option value="null"></option>
							<option value="서울">서울</option>
							<option value="인천">인천</option>
							<option value="경기도">경기도</option>
							<option value="충청북도">충청북도</option>
							<option value="충청남도">충청남도</option>
							<option value="경상북도">경상북도</option>
							<option value="경상남도">경상남도</option>
							<option value="전라북도">전라북도</option>
							<option value="전라남도">전라남도</option>
						</select>
						&nbsp;&nbsp;테마&nbsp;&nbsp;
						<select name="select">
							<option value="null"></option>
							<option value="커플">커플</option>
							<option value="가족">가족</option>
							<option value="단체">단체</option>
							<option value="나홀로">나홀로</option>
							<option value="힐링">힐링</option>
							<option value="저가여행">저가여행</option>
							<option value="바다">바다</option>
							<option value="계곡">계곡</option>
							<option value="등산">등산</option>
							<option value="반려동물">반려동물</option>
						</select>
						&nbsp;&nbsp;교통수단&nbsp;&nbsp;
						<select name="select">
							<option value="null"></option>
							<option value="자동차">자동차</option>
							<option value="비행기">비행기</option>
							<option value="배">배</option>
							<option value="기차">기차</option>
							<option value="지하철">지하철</option>
							<option value="버스">버스</option>
							<option value="도보">도보</option>
						</select>
						&nbsp;&nbsp;숙박&nbsp;&nbsp;
						<select name="select">
							<option value="null"></option>
							<option value="펜션">펜션</option>
							<option value="호텔">호텔</option>
							<option value="모텔">모텔</option>
							<option value="게스트하우스">게스트하우스</option>
						</select>
						<h3><span class="form-control label label-default">작성 순서</span></h3>
						<div id="theme_order">
							<span class="round-icon"><a href="#section0" class="type_top"><span class="glyphicon glyphicon-send"></span></a></span>							
						</div>
					</form>
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
					<button type="button" class="btn btn-info" style="float:right">저장</button>
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