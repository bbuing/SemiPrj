/*
 * By Horyun Song
 * 2016.6.14
 * Elements(css) follow scroll event
 * Last edited 2016.6.16
 */

function setSpy() {
	var lastId; //마지막에 있을 구역
	var	topMenu = $("#theme_order"); //상단에 카드타입들을 모아둔 메뉴
	var	topMenuHeight = 200; //헤더와 메뉴만큼 더해준값을 맨 상단으로 친다
	var menuItems = topMenu.find("a"); // 상단 카드 타입 각 버튼
	var cardItems = $(".narrative-section"); //현재 보여지는 horizontal-narrative-section
	
	
	var scrollItems = menuItems.map(function() { // 각 타입 버튼을 눌렀을 때 갈 곳을 map으로 해서 배열 만들기
		var item = $($(this).attr("href")); //누르면 가는 곳들 -> #id
		if (item.length) {
			return item;
		}
	});

	// 클릭 핸들러를 카드타입 메뉴에 
	menuItems.click(function(e) {
		var href = $(this).attr("href"); 
		var offsetTop = href === "#" ? 0 : $(href).offset().top - topMenuHeight + 100;
		//지정되어 있지 않으면 맨 위로, 아니면 스스로보다 메뉴만큼 올라간
		$('html, body').stop().animate({scrollTop : offsetTop}, 300); //부드럽게 올라가도록 시간차를 준다
		e.preventDefault();
	});
	// 스크롤 하면서 바뀌는 
	$(window).scroll(
			function() {
				var fromTop = $(this).scrollTop() + topMenuHeight; 
				// container의 스크롤 위치를 위에서부터 재고 메뉴만큼 더함
				
				var cur = scrollItems.map(function() { // 현재 스크롤 있는 장소의 id
					if ($(this).offset().top < fromTop) 
						return this; //있는 곳이 자기 자신 
				});
				
				// 현재 스크롤 장소에 해당하는 메뉴 id
				cur = cur[cur.length - 1];
				var id = cur && cur.length ? cur[0].id : "";
				console.log(id);
				if (lastId !== id) {
					lastId = id;
					
					// 클래스 지우기/추가
					
					//상단 타입메뉴의 css
					menuItems.parent().removeClass("active").end().filter("[href='#" + id + "']").parent().addClass("active");
					
					//카드 css
					cardItems.removeClass("read-selected").filter("[id='" + id + "']").addClass("read-selected");
					
					//현재 보여주는(current-row) 가로 부가정보와 그 안의 이미지 등 (in-current-row)
					$(".horizontal-context").removeClass("current-row").filter("[id='hori-" + id + "']").addClass("current-row");
					$(".horizontal-narrative-section").removeClass("in-current-row");
					$(".current-row .horizontal-narrative-section").addClass("in-current-row");
				}
			});
}

function fnFocus(text) {
	var inputOffset = $(text).offset().top - 300; //위의 메뉴/헤더 높이만큼 빼준다
    $('html, body').animate({scrollTop : inputOffset}, 300); //걸리는 시간 300 ms
    var index = $("#theme_list").children().index($(text).parent());
    index = (index-1) / 2;
    $(".hori").css("display","none");
    $("#hori-section"+index).css("display","inline");
}

$(document).ready(function(){
	setSpy();
});
