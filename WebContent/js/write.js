/**
 * 	write.js
 */
// 타이틀 이미지를 미리보기 하는 함수
	function setPreview(img,file) {
		var data = {
			inputFile : file,
			img : img,
			w : 310,
			h : 260
		}
		return data;
	}
    
    function previewImage(input,file) {
    	var data = setPreview(input,file);
    	if(!data.inputFile || !data.img) {
    		return;
    	}
    	var inputFile = data.inputFile.get(0);
    	var img = data.img.get(0);
    	
    	if (window.FileReader) {
            // image 파일만
            if (!inputFile.files[0].type.match(/image\//)) return;
 
            // preview
            try {
                var reader = new FileReader();
                reader.onload = function(e){
                    img.src = e.target.result;
                    img.style.width  = data.w+'px';
                    img.style.height = data.h+'px';
                    img.style.display = '';
                }
                reader.readAsDataURL(inputFile.files[0]);
            } catch (e){}
        // img.filters (MSIE)
        } else if (img.filters) {
            inputFile.select();
            inputFile.blur();
            var imgSrc = document.selection.createRange().text;
 
            img.style.width  = data.w+'px';
            img.style.height = data.h+'px';
            img.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enable='true',sizingMethod='scale',src=\""+imgSrc+"\")";            
            img.style.display = '';
        // no support
        } else { alert("미리보기 기능은 IE, Chrome만 제공됩니다.");}
    }
    // 게시물 설정을 보였다 숨겼다 하는 함수
	function show_rtts(btn) {
		var status = $("#board_rtts").css("display");
		if(status == "none") {
			$("#board_rtts").css("display","inline");
			$(btn).text("숨기기");
		}
		else {
			$("#board_rtts").css("display","none");
			$(btn).text("게시물 설정");
		}
	}
	// 테마(본문)을 추가하는 함수
	function addTheme(theme_list) {
		var divTheme = $(theme_list).parent();
		var sectionIdx = $(theme_list).parent().index();
		sectionIdx = sectionIdx /2 + 0.5;
		var btn = $("<div>", {
			addClass : "btn-group",
		}).append(
			'<button type="button" class="btn btn-default" onClick="setTheme(this)"><span class="glyphicon glyphicon-plane"></span></button>'
			+	'<button type="button" class="btn btn-default" onClick="setTheme(this)"><span class="glyphicon glyphicon-heart"></span></button>'
			+	'<button type="button" class="btn btn-default" onClick="setTheme(this)"><span class="glyphicon glyphicon-home"></span></button>'
			+	'<button type="button" class="btn btn-default active" onClick="setTheme(this)"><span class="glyphicon glyphicon-send"></span></button>'
			+	'<button type="button" class="btn btn-default" onClick="removeTheme(this)"><span class="glyphicon glyphicon-trash"></span></button>'
		);
		var text = $("<div>", {
			id: "section" + sectionIdx,
			addClass : "btn_group",
		}).append(
			'<textarea class="form-control narrative-section vertical-narrative-section read has-title" data-vertical-index="'+ sectionIdx + '" rows="16" name="theme" placeholder="내용을 작성해 주세요." onFocus="fnFocus(this)"></textarea><br/>'
			+	'<button class="form-control form-group-lg" type="button" class="btn btn-default btn-lg btn-block" style="margin-bottom:10px;" onClick="addTheme(this)">'
			+	'<span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>'	
		);
		$(btn).insertAfter(divTheme);
		$(text).insertAfter(btn);
		
		$("#theme_order").append(
			'<span class="round-icon"><a href="#section' + sectionIdx + '" id="a' + sectionIdx + ' class="type_top"><span class="glyphicon glyphicon-send"></span></a></span>'	
		);
		$("#card_btn").append(
			'<div class="form-group" style="margin-top:27px;">'
			+	'<button class="show_card_btn btn btn-default" onClick="showCard(this)" style="margin-top:86px; margin-left:10px; height: 335px;"><span class="glyphicon glyphicon-plus"></span></button>'
			+	'<button class="del_card_btn btn btn-default" onClick="delCard(this)" style="margin-top:86px; margin-left:10px; height: 335px; display: none;"><span class="glyphicon glyphicon-remove"></span></button>'
			+'</div>'	
		);
		$("body").append($("<div>",{
			id: "hori-section" + sectionIdx,
			addClass: "hori horizontal-context horizontal-read "
		}))
		
		var idx = 0;
		$(theme_list).parent().nextAll().filter(".form-group").each(function(i){
			if(i == 0) {
				var section = $(this).attr("id").split("section");
				idx = section[1]; // 현재 사이에 추가된 인덱스
			}
			else {
				++idx;
				$(this).attr("id","section"+ idx);
			}
		})
		$(".hori").each(function(idx){
			$(this).attr("id","hori-section"+idx)
		});
		$(".type_top").each(function(idx){
			$(this).attr({"href":"#section"+idx,"id":"a"+idx})
		}).promise().done(function(){
			setSpy();
		});
	}
	// 테마(본문)을 지우는 함수, 이때 작성 순서에 아이콘도 지워준다
	function removeTheme(theme) {
		var cardIdx = $("#theme_list").children().index($(theme).parent())/2;
		$(theme).parent().next().remove();
		$(theme).parent().remove();
		$("#card_btn").children().eq(cardIdx).remove();
		$("#theme_order").children().eq(cardIdx).remove();
	}
	
	/*function addCard() {
		var content = document.getElementById("content");
		var div = document.createElement("div");
		div.innerHTML = '<div id="card_content" class="col-md-4" style="border: 1px black solid; margin-top: 35px; padding:5px; width:390px; height:345px;">'
			+ '<ul class="nav nav-tabs nav-justified">' 
			+ '<li><input type="file" id="card_img" style="display: none" /><a class="btn btn-default" onclick="cardTab(this)"><span class="glyphicon glyphicon-picture"></span></a></li>' 
			+ '<li><a id="instaBtn" class="btn btn-default" onclick="cardTab(this)"><span class="glyphicon glyphicon-camera"></span></a></li>'
			+ '<li><a class="btn btn-default"onclick="cardTab(this)"><span class="glyphicon glyphicon-thumbs-up"></span></a></li>'
			+ '<li><a class="btn btn-default"onclick="cardTab(this)"><span class="glyphicon glyphicon-map-marker"></span></a></li>'
			+ '<li><a class="btn btn-default"onclick="cardTab(this)"><span class="glyphicon glyphicon-option-horizontal"></span></a></li>'
			+ '</ul>'
			+ '<div id="card">'
			+ '<div style="display: none;"><center><jsp:include page = "OwnPhotos.jsp"/></center></div>'
			+ '<div class="continaer" style="display: none;">'
			+ '<div id="instaPics" class="row"></div>'
			+ '</div>'
			+ '<div style="display: none;"></div>'
			+ '<div style="display: none;"><center><jsp:include page = "GoogleMap.jsp"/></center></div>'
			+ '</div>';
		content.appendChild(div);
	}*/

	function showCard(btn) {
		var index = $("#card_btn").children().index($(btn).parent());
		var top = index * 435 + 570;
		$("#card_content").css("top",top);
		$("#card_content").css("display", "inline");
		$(".show_card_btn").css("display","inline");
		$(".del_card_btn").css("display","none");
		$("#card_btn").children().eq(index).children().eq(0).css("display","none");
		$("#card_btn").children().eq(index).children().eq(1).css("display","inline");
		
		var cardIdx = index;
		$("#card_idx").val(cardIdx);
		$("#hori-section" + cardIdx).children().filter($(".narrative-section")).each(function(){
			var left = $(this).css("left").split("px");
			var intLeft = left[0] *1 + 530;
			$(this).css("left",intLeft + "px");
		});
	}
	
	function delCard(btn) {
		var index = $("#card_btn").children().index($(btn).parent());
		$("#card_content").css("display", "none");
		$("#card_btn").children().eq(index).children().eq(0).css("display","inline");
		$("#card_btn").children().eq(index).children().eq(1).css("display","none");
		
		var cardIdx = index;
		$("#hori-section" + cardIdx).children().filter($(".narrative-section")).each(function(){
			var left = $(this).css("left").split("px");
			$(this).css("left",left[0] - 530 + "px");
		})
	}
	// card의 탭 전환 함수
	function cardTab(tab) {
		var tab_type = $(tab).children().attr("class");	// span의 class, icon 이미지
		var tabs = $(tab).parent().parent().children(); // navbar의 탭들 <li>
		$(tabs).each(function(idx){
			if($(tabs).eq(idx).children().children().attr("class") == tab_type) {
				$(tabs).eq(idx).addClass("active");
				switch(idx) {
					case 0:
						$("#card").children().eq(idx).css("display", "inline");
						$("#card_content").css("height","400px");
						$("#card_img_preview").off();
						if($("#photo").length <= 0) {
							$("#upload").append($("<input>",{
								type: "file",
								id: "photo",
								addClass: "photo",
								name: "card",
								css: {"display":"none"}
							})).promise().done(function(){
								$("#card_img_preview").click(function(){
									$("#photo").click();
								});
								$("#photo").change(function(){
									previewImage($("#card_img_preview"),$("#photo"));
								});
							});
						}
						break;
					case 1:
						$("#insta_div").css("display", "inline");
						$("#card_content").css("height","400px");
						break;
					case 2:
						$("#card").children().eq(idx).css("display", "inline");
						$("#card_content").css("height","820px");
						break;					
					case 3:
						$("#card").children().eq(idx).css("display", "inline");
						$("#card_content").css("height","400px");
						break;
					case 4:
						$("#card").children().eq(idx).css("display", "inline");
						$("#card_content").css("height","385px");
						break;
				}
			}
			else {
				$(tabs).eq(idx).removeClass("active");
				$("#card").children().eq(idx).css("display", "none");
			}
		})
	}
	// 인스타그램 인증후 유저의 이미지를 뿌려주는 callback 함수
	function callback() {
		var token = $("#token").val(); /* Access Tocken 입력 */  
	    var count = "16";
	    $.ajax({  
	        type: "GET",  
	        dataType: "jsonp",  
	        cache: false,  
	        url: "https://api.instagram.com/v1/users/self/media/recent/?access_token=" + token + "&count=" + count,  
	        success: function(response) {  
	         if ( response.data.length > 0 ) {
					var cnt=0;0.
					var insta = "";
					for(var i = 0; i < response.data.length; i++) {
						if(cnt % 4 == 0) {
							if(insta != "") {
								insta += "</div>";	
							}
							insta += '<div class="col-md-10">';
						}
	           	   		insta += '<img src="' + response.data[i].images.thumbnail.url + '" onClick="selectImg(this)" style="border:2px solid white; width:70px; height=70px;"/>';
	           	   		insta += '<input type="hidden" name="link" value=' + response.data[i].images.thumbnail.url + ' />'; 
	           	   		cnt++;
	                   //insta += "<a target='_blank' href='" + response.data[i].link + "'>";  
	                   //insta += "<div class='image-layer'>";  
	                   //insta += "<img src='" + response.data[i].images.thumbnail.url + "'>";  
	                   //console.log(response.data[i].caption.text);  
	                   //if ( response.data[i].caption !== null ) {  
	                     //   insta += "<div class='caption-layer'>";  
	                     //   if ( response.data[i].caption.text.length > 0 ) {  
	                       //      insta += "<p class='insta-caption'>" + response.data[i].caption.text + "</p>"  
	                       // }  
	                       // insta += "<span class='insta-likes'>" + response.data[i].likes.count + " Likes</span>";  
	                        //insta += "</div>";  
	                   //}  
	                   //insta += "</a>";  
	                   //insta += "</div>";    
	              } 
					insta += "</div>";
					$("#instaPics").append(insta);
	         }  
	        }  
	       });  
	}

	// 인스타그램에서 가져온 사진을 선택, 비선택 표시하는 함수
	function selectImg(i) {
		var check = $(i).is(".selected");
		if(check == true) {
			$(i).css("border", "2px solid white").removeClass("selected");
		}
		else {
			$(i).css("border","2px solid red").addClass("selected");
		}
	}
	// 테마의 주제 변경시 테마의 주제 아이콘과 작성순서의 아이콘을 변경하는 함수
	function setTheme(btn) { // btn : theme를 나타내는 버튼
		var btn_group = $(btn).parent();
		var index = $("#theme_list").find(btn_group).index()/2;
		var icon_btn = $(btn).parent().children();		// 한단계 상위로 올라갔다 내려오면서  btn그룹을 받아온다
		var icon_type = $(btn).children().attr("class");	// 현재 누른 버튼의 아이콘 모양
		var title_icon = $("#theme_order");
		
		$(icon_btn).each(function(btn_idx){	// 각각의 버튼을 돌면서 누른 버튼과 일치하면 active클래스 추가 아닐시 삭제
			if($(icon_btn).eq(btn_idx).children().attr("class") == icon_type) {
				$(icon_btn).eq(btn_idx).addClass("active");
				$(title_icon).children().eq(index).children().children().attr("class", icon_type);
			}
			else {
				$(icon_btn).eq(btn_idx).removeClass("active");
			}
		})
	}
	// 검색창의 텍스트를 가져와 띄어쓰기로 파싱 후 맵에 해당 쿼리의 결과를 출력한다.
	function fnMapSearch() {
		var $keyword = $("#map_search").val().split(" ");
		var query = "";
		$($keyword).each(function(){
			query += encodeURI(this); 
		})
		$("#map").attr("src","https://www.google.com/maps/embed/v1/place?key=AIzaSyAmsGI_8BQcslfyf8AnFmSvi0zESELZWOQ&q=" + query);
	}
	// 현재 표시된 지도를 전송하기위해 form에 저장
	function fnSetMap() {
		
	}
	// 연동된 인스타 아이디에서 사진을 가져와 출력해주는 함수
	function fnCallInsta() {
		var token = $("#token").val();
		if(!token == "") {
			$("#instaPics").empty();
			$("#instaPics").css("display","inline");
			$("#instaAdd").css("display","inline");
			$("#instaDesc").css("display","none");
			callback();
		}
		else {
			var win = window.open("https://api.instagram.com/oauth/authorize/?client_id=05496e57bdfa4b7494198b60c3a806d0&redirect_uri=http://192.168.10.213/semiProject/Instagram.jsp&response_type=token&scope=likes+comments+relationships+basic","instaLogin", "left=400, top=300, width=600, height=350");
			var interval = window.setInterval(function(){
				try {
					if(win == null || win.closed) {
						window.clearInterval(interval);
						callback();
					}
				} catch(e){ alert("error");}
			}, 1000);	
		}
	
	}
	// 인스타에서 가져온 사진과 설명을 전송하기 위한 form에 저장하는 함수
	function fnSetInstaPics(){
		var insta = $("#instaDesc").find(".insta");
		var cardIdx = $("#card_idx").val();
		$(insta).each(function(){
			var pic = $(this).find("img").attr("src");
			var desc = $(this).find("input").val();
			
			$("#upload").append($("<input>",{
				type: "hidden",
				name: "card",
				value: "insta"
			}));
			$("#upload").append($("<input>",{
				type: "hidden",
				name: "card",
				value: cardIdx
			}));
			$("#upload").append($("<input>",{
				type : "hidden",
				name : "card",
				value : pic
			}));
			$("#upload").append($("<input>",{
				type : "hidden",
				name : "card",
				value : desc
			}));
		});
		fnCallInsta();
	}
	// 게시글을 전송하기 위해 본문들을 form에 담아주는 함수
	$().append($("<input>",{
		
	}))
	function fnSetTheme() {
		var theme = $("textarea");
		$(theme).each(function(){
			var val = $(this).val();
			$("#upload").append($("<input>",{
				type: "text",
				name: "theme",
				value: val
			}));
		});
	} 
	// 현재 본문의 아이콘의 순서를 form에 담아주는 함수
	function fnSetOrder() {
		var order_list = $("#theme_order").children();
		$(order_list).each(function(){
			var type = $(this).children().children().attr("class");
			$("#upload").append($("<input>",{
				type: "hidden",
				name: "order",
				value: type
			}));
		});
	}
	// 현재 게시글의 지역,교통수단 등의 기본정보를 form에 담아주는 함수
	function fnSetinfo() {
		var region = $(".region")
	}
	jQuery(document).ready(function($){
		$("#title_img_btn").click(function(){
			$("#title_img").click();
		});
		$("#title_img").change(function(){
			previewImage($('#title_img_preview'),$("#title_img"));
			var img_name = $("#title_img").val().split("fakepath\\");
			$("#title_img_name").val(img_name[1]);
		});
		$("#photo").change(function(){
			previewImage($("#card_img_preview"),$("#photo"));
		});
		// card탭에서 인스타그램 탭을 선택했을시 인증 진행 (MVC로 바꿀때 token을 session에 하도록 하는게 좋을듯)
		var fnSetInsta = $("#instaBtn").click(function(event){
			event.preventDefault();
			fnCallInsta();
		});
		// insta에서 가져온 각각의 사진들의 썸네일을 생성, 그 후 설명을 달 수 있게 한다.
		$("#instaAdd").click(function(){
			var image = $("img[class=selected]");
			var size = image.size();
			for(var i=0; i<size; i++) {
				// 썸네일의 크기를 지정할 바깥 div
				var div = $("<div>",{
					addClass : "insta",
					css : {"width":"490px", "height":"400px", "text-align":"center"}
				});
				// 각각의 사진을 담을 썸네일 생성
				var thumbnail = $("<div>",{
					addClass : "thumbnail"
				});
				// 썸네일에 넣을 이미지
				thumbnail.append($("<img>",{
					src : image.get(i).src,
					css : {"width":"300px", "height":"300px"}
				}));
				// 각각의 썸네일에 caption으로 설명text와 button을 달아준다.
				var caption = $("<div>",{
					addClass : "caption"
				});
				// 두 번째 썸네일 부터는 이전 버튼을 달아준다.
				if(i > 0) {
					caption.append($("<button>",{
						type : "button",
						addClass : "btn btn-default",
						css : {"float":"left"},
						click : function() {
							$(this).parent().parent().parent().prev().css("display","inline");
							$(this).parent().parent().parent().css("display","none");
						}
					}).append($("<span>",{
						addClass : "glyphicon glyphicon-chevron-left"
					})));				
				}
				// 다음 버튼
				caption.append($("<input>",{
						type : "text",
						size : 12,
						placeholder : "설명"
					})).append($("<button>",{
						type : "button",
						addClass : "btn btn-default",
						css : {"float":"right"},
						click : function(){
							$(this).parent().parent().parent().css("display","none");
							if($(this).parent().parent().parent().next().size() == 0) {
								fnSetInstaPics();
							}
							$(this).parent().parent().parent().next().css("display","inline");
						}
					}).append($("<span>",{
						addClass : "glyphicon glyphicon-chevron-right"
					})));
				// 두 번째 이후 썸네일은 보이지 않게 한다.
				if(i > 0) {
					$(div).css("display","none");
				}
				else {
					$(div).css("display","inline");
				}
				$(thumbnail).append(caption);
				$(div).append(thumbnail);
				$("#instaDesc").append(div);
			}
			$("#instaPics").css("display","none");
			$("#instaAdd").css("display","none");
			$("#instaDesc").css("display","inline");
		});
		
		// 지도 검색 버튼 클릭
		$("#map_serach_btn").click(function(){
			fnMapSearch();
		});

		// 지도 검색창에 엔터키 입력
		$("#map_search").keypress(function(e){
			if(e.which == 13) {
				fnMapSearch();
			}
		});
		// 현재 지도좌표를 저장
		$("#map").next("button").click(function(){
			var cardIdx = $("#card_idx").val();
			$("#upload").append($("<input>",{
				type: "hidden",
				name: "card",
				value: "map"
			}));
			$("#upload").append($("<input>",{
				type: "hidden",
				name: "card",
				value: cardIdx
			}));
			$("#upload").append($("<input>",{
				type: "hidden",
				name: "card",
				value: $("#map").attr("src")
			}));
		});
		
		$("#submit").click(function(){
			fnSetTheme();
			fnSetOrder();
			fnSetinfo();
			$("#upload").submit();
		});
	});