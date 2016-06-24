<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="model.BoardDto"%>
<%@page import="model.CardDto"%>
<%@page import="model.AddDto"%>
<%@page import="model.ReplyDto"%>
<%
	request.setCharacterEncoding("UTF-8");
%>


<div id="horizontal-wrapper">

	<c:forEach items="${ReadDto.cardList }" var="card">
		<div id="hori-section${card.card_num }"
			class="hori horizontal-context horizontal-read ">

			<c:if test="${card.card_add_cnt>=0}">

				<c:forEach items="${card.addList }" var="add">
				
					<c:if test="${add.add_type == 'image'}">
					<div
						class="narrative-section horizontal-narrative-section selected "
						data-index="${add.add_index}"
						style="width: 520px; left: ${(add.add_index)*540 + 615}px;">
						<section>
							<div
								class="display-context-section image-section">
								<img src="${add.add_content }" />
								<div class="text-content add-img" dir="auto">${add.add_title }</div>
							</div>
						</section>
					</div>
					</c:if>
					
					<c:if test="${add.add_type == 'map'}">
					<div
						class="narrative-section horizontal-narrative-section selected "
						data-index="${add.add_index}"
						style="width: 520px; left: ${(add.add_index)*540 + 615}px;">
						<section>
							<div class="display-context-section image-section">
								<iframe width="520" height="340" frameborder="0" style="border:0"
										src="${add.add_content }" allowfullscreen></iframe>
								<div class="text-content add-img" dir="auto">${add.add_title }</div>
							</div>
						</section>
					</div>
					</c:if>
					
					<c:if test="${add.add_type == 'youtube'}">
					<div
						class="narrative-section horizontal-narrative-section selected "
						data-index="${add.add_index}"
						style="width: 520px; left: ${(add.add_index)*540 + 615}px;">
						<section>
							<div
								class="display-context-section image-section">
								<iframe width="520" height="340"
										src="${add.add_content }"></iframe>
								<div class="text-content add-img" dir="auto">${add.add_title }</div>
							</div>
						</section>
					</div>
					</c:if>
					
					<c:if test="${add.add_type == 'insta'}">
					<div
						class="narrative-section horizontal-narrative-section selected "
						data-index="${add.add_index}"
						style="width: 520px; left: ${(add.add_index)*540 + 615}px;">
						<section>
							<div
								class="display-context-section image-section">
								<img src="${add.add_content }" />
								<div class="text-content add-img" dir="auto">${add.add_title }</div>
							</div>
						</section>
					</div>
					</c:if>
					
				</c:forEach>
				<c:if test="${card.card_add_cnt>=2}">
				<div class="left story-browser" index="${card.card_num }">
					<svg version="1.1" class="browse-arrow-left browse-arrow icon"
						xmlns="http://www.w3.org/2000/svg"
						xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
						width="8px" height="15px" viewBox="0 0 8 15"
						enable-background="new 0 0 8 15" xml:space="preserve"> <polygon
							class="fg" points="7.516,14.532 0.484,7.5 7.516,0.468 "></polygon>
						</svg>
				</div>
				<div class="right story-browser" index="${card.card_num }">
					<svg version="1.1" class="browse-arrow-right browse-arrow icon"
						xmlns="http://www.w3.org/2000/svg"
						xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
						width="8px" height="15px" viewBox="0 0 8 15"
						enable-background="new 0 0 8 15" xml:space="preserve"> <polygon
							class="fg" points="0.484,0.468 7.516,7.5 0.484,14.532 "></polygon>
						</svg>
				</div>
				</c:if>
			</c:if>
		</div>
	</c:forEach>

</div>