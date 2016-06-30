<%@page import="model.BoardDao"%>
<%@page import="java.util.Vector"%>
<%@page import="model.BoardDto"%>
<%@page import="model.GetBoardCommand"%>
<%@page import="dbcp.DBConnectionMgr"%>
<%@page import="java.util.Vector"%>
<%@ page contentType="text/html; charset=utf-8"%>
<html>

<meta charset="utf-8" />
<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/card3.css">
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script src="js/menu.js"></script>
<script src="jquery-2.2.4.min.js"></script>
<script src="http://code.jquery.com/jquery-2.2.4.min.js"
   integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
   crossorigin="anonymous">
   
</script>


<%
   request.setCharacterEncoding("utf-8");
%>
<script>
   function check() {
      /*if (document.search.keyWord.value == "") {
         alert("검색어를 입력하세요.");
         document.search.keyWord.focus();
         return;
      }*/
      document.search.submit();
   }
</script>
<script>
   $(document).ready(function() {
      $(".tags").click(function() { //tagas 라는 a태그 클릭시 발생
         var tag = $(this).attr("id");

         $("#keyWord").val(tag);
         $("#keyField").val("tags");

         $("#search").submit;
         $("#search-btn").trigger("click");

      });

   });
</script>


<style>
.text_search {
   background: yellow;
}

@font-face {
   font-family: 'Nanum'; /* 폰트 패밀리 이름 주기*/
   src: url('font/NANUMBARUNGOTHIC.TTF'); /*폰트 파일 주소*/
}

.main {
   height: 500px;
   background-image: url("images/Beach02.jpg");
   background-repeat: no-repeat;
   background-size: cover;
}

.main_title {
   font-family: Nanum;
   color: #FFFFFF;
   background-color: #000000;
   margin-top: 200px;
   text-align: center;
   filter: Alpha(opacity : 50);
   overflow: hidden;
   opacity: 0.7;
}

.main_tag {
   font-family: Nanum;
   color: #FFFFFF;
   padding: 10px; background-color : #000000;
   filter: Alpha(opacity : 50);
   text-align: center;
   overflow: hidden;
   background-color: #000000; filter : Alpha( opacity : 50); text-align :
   center; overflow : hidden;
   opacity: 0.7;
}

.main_link_div {
   margin-top: 50px;
}

.main_link {
   filter: Alpha(opacity : 50);
   font-family: Nanum;
   color: #FFFFFF;
   overflow: hidden;
   text-align: center;
   background-color: #C239F0;
   font-size: 15pt;
   opacity: 0.8;
}

.highlight {
   background-color: yellow;
}

.body_background {
   
}


.category {
   font-family: Nanum;
   padding-left: 100px;
   font-color: 000000;
}
.btn_hj{
margin-left : 400px;
}
.icon_like{
   width:15px;
   height:15px;
}
.icon_tag{
   margin-right:30px;
   width:25px;
   height:25px;
}
</style>

<body class="body_background">

   <div>
      <jsp:include page="header.jsp" flush="false" />
   </div>
   <div class="container ">


      <div class="main row">
         <div class="main_title col-lg-6 col-lg-offset-3 text-center">
            <h2>
               <strong>여름 휴가 계획, 미리미리 세우세요!</strong>
            </h2>
         </div>
         <div class="main_tag col-lg-6 col-lg-offset-3 text-center">
            <!-- <h3>#진해,#커플여행,#당일치기,#꽃눈이내리네,#사람반꽃반,#기차여행</h3>-->
            <%
               String[] main_tag = "#휴가,#바다,#산림욕,#계곡,#혼자여행,#당일치기".split(",");

               for (int i = 0; i < main_tag.length; i++) {
            %>
            <a href="# " style="color: white; font-size: 20px;" class="tags"
               id="<%=main_tag[i]%>"><%=main_tag[i]%></a>
            <%
               }
            %>
         </div>
         <div class="main_link_div col-lg-4">
            <form method="post" action="write.do">
               <input type="hidden" name="param" value="write_log" /> 
               <input type="submit" class="main_link btn btn-default btn-lg btn_hj" value="다녀왔으니 여행로그로 자랑하러 가기">
            </form>
         </div>
      </div>
      <br />



      <%
         try {
            String keyWord = request.getParameter("keyWord");
            String keyField = request.getParameter("keyField");
            String tags = request.getParameter("tags");

            String[] param_region = request.getParameterValues("region");
            String[] param_theme = request.getParameterValues("theme");
            String[] param_transport = request.getParameterValues("transport");
            String[] param_stay = request.getParameterValues("stay");

            if (keyField == null) {
               keyField = "title+content";
               keyWord = "";
               tags = "";
            }
            if (param_region == null) {
               param_region = new String[] { "서울", "인천", "경기도", "충청북도", "충청남도", "경상북도", "경상남도", "전라북도", "전라남도",
                     "강원도", "제주도" };
            }
            if (param_theme == null) {
               param_theme = new String[] { "커플", "가족", "단체", "나홀로", "힐링", "저가여행", "바다", "계곡", "등산", "반려동물" };
            }
            if (param_transport == null) {
               param_transport = new String[] { "자동차", "비행기", "배", "기차", "지하철", "버스", "도보" };
            }
            if (param_stay == null) {
               param_stay = new String[] { "펜션", "호텔", "모텔", "게스트하우스" };
            }
      %>


      <form id="search" name="search" method="post" action="controller.do"
         class="form-horizontal" style="border:1px solid lightgray">
         <div style="text-align: center">
            <br /> <input type="hidden" name="param" value="getBoard">
            <select class="select" id="keyField" name="keyField" size="1">


               <option value="title+content"
                  <%if (keyField.equals("title+content")) {%> selected="selected"
                  <%}%>>제목+내용
               <option value="writer" <%if (keyField.equals("writer")) {%>
                  selected="selected" <%}%>>작가명
               <option value="tags" <%if (keyField.equals("tags")) {%>
                  selected="selected" <%}%>>태그명
            </select> <input class="keyWord" placeholder="검색어를 입력해주세요." type="text"
               size="20" id="keyWord" name="keyWord" value="<%=keyWord%>">
            <button class="btn btn-default" id="search-btn" value="찾기"
               onClick="check()">
               <i class="glyphicon glyphicon-search"></i>
            </button>
         </div>
         
         <hr />
         <div class="form-group category " >
            <div class="col-sm-3" style="text-align: left">
               <label class="r_chk"><h4>지역</h4></label>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="region" value="서울">서울</label>
               </div>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="region" value="인천">인천</label>
               </div>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="region" value="경기도">경기도</label>
               </div>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="region" value="충청북도">충청북도</label>
               </div>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="region" value="충청남도">충청남도</label>
               </div>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="region" value="전라북도">전라북도</label>
               </div>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="region" value="전라남도">전라남도</label>
               </div>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="region" value="경상북도">경상북도</label>
               </div>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="region" value="경상남도">경상남도</label>
               </div>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="region" value="강원도">강원도</label>
               </div>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="region" value="제주도">제주도</label>
               </div>
            </div>
            <div class="col-sm-3" style="text-align: left">
               <label class="r_chk"><h4>테마</h4></label>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="theme" value="커플">커플</label>
               </div>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="theme" value="가족">가족</label>
               </div>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="theme" value="단체">단체</label>
               </div>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="theme" value="나홀로">나홀로</label>
               </div>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="theme" value="힐링">힐링</label>
               </div>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="theme" value="저가여행">저가여행</label>
               </div>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="theme" value="바다">바다</label>
               </div>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="theme" value="계곡">계곡</label>
               </div>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="theme" value="등산">등산</label>
               </div>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="theme" value="반려동물">반려동물</label>
               </div>
            </div>
            <div class="col-sm-3" style="text-align: left">
               <label class="r_chk"><h4>교통</h4></label>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="transport" value="자동차">자동차</label>
               </div>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="transport" value="비행기">비행기</label>
               </div>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="transport" value="배">배</label>
               </div>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="transport'" value="기차">기차</label>
               </div>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="transport" value="지하철">지하철</label>
               </div>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="transport" value="버스">버스</label>
               </div>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="transport'" value="도보">도보</label>
               </div>
            </div>
            <div class="col-sm-3" style="text-align: left">
               <label class="r_chk"><h4>숙박</h4></label>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="stay"
                     value="펜션">펜션</label>
               </div>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="stay"
                     value="호텔">호텔</label>
               </div>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="stay"
                     value="모텔">모텔</label>
               </div>
               <div class="r_chk checkbox">
                  <label><input type="checkbox" name="stay"
                     value="게스트하우스">게스트하우스</label>
               </div>
            </div>
         </div>
         <br />
      </form>
      <br /> <br /> <br />


      <%
         BoardDao dao = new BoardDao();
            Vector<BoardDto> list = (Vector<BoardDto>) dao.getBoardList(keyField, keyWord, param_region,
                  param_theme, param_transport, param_stay);

            //Vector<BoardDto>  list = (Vector<BoardDto>)session.getAttribute("list");
      %>



<form method="post" action="read.do">
      <input type="hidden" name="param" value="read" />
      <div class="demo-card">
         <div class="card-deck-wrapper">
            <div class="ui link cards centered">
               <%
                  if (list == null) {
                        out.println("검색결과가 없습니다.");
                     } else {
                        for (int i = 0; i < list.size(); i++) {
                           BoardDto dto2 = (BoardDto) list.get(i);
               %>

               <%
               
                  out.println("<div class='ui card'>");
                           out.println("<div class='image'>");
                           out.print("<img src='/semiProject/upload/");%><%=dto2.getBoard_num()%><%out.print("/");%><%=dto2.getBoard_header()%><% out.println("'>");
                           out.println("</div>");
                           out.println("<div class='content'>");
                           out.println("<div id = 'title' class='header' style='font-family :Nanum;'>");
               %>
               <a href="read.do?param=read&board_num=<%=dto2.getBoard_num() %> " style="color:000000">
               <%=dto2.getBoard_title().replace(keyWord.toUpperCase(),
                        "<span style='background:yellow'>" + keyWord.toUpperCase() + "</span>")%></a>
               <%
                  out.println("</div>");
                           out.println(
                                 "<div id='content' style ='height : 200px;text-overflow:ellipsis; overflow:hidden; font-family :Nanum;' class='description'>");
               %>

               <%=dto2.getSearch_card().replace(keyWord.toUpperCase(),
                        "<span style='background:yellow'>" + keyWord.toUpperCase() + "</span>")%>

               <%
                  out.println("</div>");
                           out.println("</div>");

                           out.println("<div class='extra content'>");
                           String[] array = dto2.getSearch_tag().split(",");

                           for (int j = 0; j < array.length; j++) {
               %>
               <a href="#" style="color: blue;" id="<%=array[j]%>" class="tags">
                  <%=array[j].replace(keyWord.toUpperCase(),
                           "<span style='background:yellow'>" + keyWord.toUpperCase() + "</span>")%></a>

               <%
                  }
                           out.println("<hr>");
                           out.println("<div style='margin-left: 20px; text-align:center' >");
                           %>
                           <%
                           out.print("<a href='read.do?param=read&board_num=");%><%=dto2.getBoard_num()%><%out.print("#section1'><img src='images/transport.png' class='icon_tag'></a>");%>
                           <%
                           out.print("<a href='read.do?param=read&board_num=");%>%=dto2.getBoard_num()%><%out.print("#section2'><img src='images/home.png' class='icon_tag'></a>");%>
                           <%
                           out.print("<a href='read.do?param=read&board_num=");%><%=dto2.getBoard_num()%><%out.print("#section3'><img src='images/pen.png' class='icon_tag'></a>");%>
                           <%
                           out.print("<a href='read.do?param=read&board_num=");%><%=dto2.getBoard_num()%><%out.print("#section4'><img src='images/food.png' class='icon_tag'></a>");%><%
                           out.println("</div>");
                           out.println("<hr/>");
                           out.println("<div>");
                           out.println("<img src='img/like.png' class='icon_like'>");
               %>
               좋아요 <%=dto2.getBoard_like()%>개

               <%
                  out.println("<div style='text-align : right'>");
               %>
               작성자 :
               <%=dto2.getUser_nick().replace(keyWord,
                        "<span style='background:yellow'>" + keyWord + "</span>")%>

               <%
                  out.println("</div>");
                           out.println("</div>");
                           out.println("</div>");
                           out.println("</div>");

                        }

                     }
                  } catch (Exception err) {
                     System.out.print(err);
                  }
               %>

            </div>
         </div>



      </div>
</form>

   </div>


</body>


</html>