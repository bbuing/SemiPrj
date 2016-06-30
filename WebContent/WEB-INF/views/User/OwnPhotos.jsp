<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
<script src="js/holder.js"></script>
<script>
// 추가한 자신의 사진과 설명을 최종 전송을 위한 form으로 옴겨주는 함수
function setPhoto() {
	var cardIdx = $("#card_idx").val();
	var form = $("#upload");
	var photo = $("#photo");
	var index = $("#upload .photo").index(photo); // 해당 사진이 몇 번째로 추가한 사진인지 확인 할 수 있는 인덱스

	// 해당 사진뒤에 파일명과 설명을 추가
	var val = $(photo).val().split("fakepath\\");	// 경로는 잘라내고 파일명만 저장
	photo.append($("<input>",{	// DB에 저장할때 구분할 타입
		type: "hidden",
		name: "card",
		addClass: cardIdx,
		value: "photo"
	}));
	photo.append($("<input>",{	// 해당 자료가 속한 본문의 위치
		type: "hidden",
		name: "card",
		value: cardIdx
	}));
	photo.append($("<input>",{	// 파일명
		type: "text",
		addClass: "photo_name" + index,
		name: "card",
		value: val[1],
		css: {"display":"none"}
	}));
	photo.append($("<input>",{	// 내용 설명
		type : "text",
		addClass : "photo_desc" + index,
		name : "card",
		value : $("#photo_desc").val(),
		css : {"display":"none"}
	}));
	// 캐러셀에 출력할 용도로 저장하는 div와 이미지
	var addIdx = $("#hori-section" + cardIdx).children().size() * 1;
	var left =  1065 + (addIdx * 320) + 530;
	var add_content = $("#card_img_preview").attr("src");
	var add_title = $("#photo_desc").val();
	$("#hori-section" + cardIdx).append(
			'<div class="narrative-section horizontal-narrative-section selected " data-index="' + addIdx + '" style="width: 310px; left: ' + left + 'px;">'
			+'<section>'
			+	'<div class="display-context-section image-section">'
			+		'<img src="'+ add_content + '" />'
			+		'<div class="text-content add-img" dir="auto">'+ add_title +'</div>'
			+	'</div>'
			+'</section>'
			+'</div>'		
	);
	if(addIdx > 1) {
		$("#hori-section" + cardIdx).append(
			'<div class="left story-browser" index="' + addIdx + '" style="display:none;">'
			+	'<svg version="1.1" class="browse-arrow-left browse-arrow icon" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"'
			+	'width="8px" height="15px" viewBox="0 0 8 15" enable-background="new 0 0 8 15" xml:space="preserve">'
			+		'<polygon class="fg" points="7.516,14.532 0.484,7.5 7.516,0.468 "></polygon>'
			+	'</svg>'
			+'</div>'
			+'<div class="right story-browser" index="' + addIdx + '"style="display:none;">'
			+	'<svg version="1.1" class="browse-arrow-right browse-arrow icon" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"'
			+		'width="8px" height="15px" viewBox="0 0 8 15" enable-background="new 0 0 8 15" xml:space="preserve">'
			+		'<polygon class="fg" points="0.484,0.468 7.516,7.5 0.484,14.532 "></polygon>'
			+	'</svg>'
			+'</div>'
		);
	}
	// 사진이 저장된 파일은 id와 name을 기존값에 index를 추가하여 변경
	$(photo).attr("id","photo"+index);
	alert("사진이 저장되었습니다.");
	$("#card_img_preview").attr("src","data:image/svg+xml;charset=UTF-8,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%22310%22%20height%3D%22260%22%20viewBox%3D%220%200%20310%20260%22%20preserveAspectRatio%3D%22none%22%3E%3C!--%0ASource%20URL%3A%20holder.js%2F310x260%0ACreated%20with%20Holder.js%202.8.1.%0ALearn%20more%20at%20http%3A%2F%2Fholderjs.com%0A(c)%202012-2015%20Ivan%20Malopinsky%20-%20http%3A%2F%2Fimsky.co%0A--%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%3C!%5BCDATA%5B%23holder_1555ccbd30f%20text%20%7B%20fill%3A%23AAAAAA%3Bfont-weight%3Abold%3Bfont-family%3AArial%2C%20Helvetica%2C%20Open%20Sans%2C%20sans-serif%2C%20monospace%3Bfont-size%3A16pt%20%7D%20%5D%5D%3E%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_1555ccbd30f%22%3E%3Crect%20width%3D%22310%22%20height%3D%22260%22%20fill%3D%22%23EEEEEE%22%2F%3E%3Cg%3E%3Ctext%20x%3D%22113.484375%22%20y%3D%22137.2%22%3E310x260%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E");
	$("#photo_desc").val("");
	cardTab($("#photo_tab"));
}
</script>
</head>
<body>
	<div class="thumbnail">
		<img id="card_img_preview" src="holder.js/310x260"/>
		<div class="caption">
			<center><p><input type="text" id="photo_desc" placeholder="설명"/></p></center>
			<center><p><a class="btn btn-primary" id="photo_add_btn" role="button" onClick="setPhoto()">추가</a></p></center>
		</div>
	</div>
</body>
</html>