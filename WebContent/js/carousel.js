//가변값 
var width = 520;
var padding = 20;
var move = width + padding;
var start = 615;
var farLeft = -590;


$(document).ready(function() {
	console.log("document ready");
	var currentRow = $(".in-current-row");  //현재 보여지는 horizontal-narrative-section
	/*
    if(currentRow.length==0 || currentRow.length ==1) { //부가정보가 없거나 1개일때 화살표를 없앤다
    	$(".left").remove();
    	$(".right").remove();
    }
	*/
    $(".left").click(function() { //오른쪽 클릭시 상세카드들의 left값을 변환
    	var currentRow = $(".in-current-row");  //현재 보여지는 horizontal-narrative-section
        var selected = $(".in-current-row.selected"); //현재 selected
        var selectedIndex = selected.attr("data-index"); //현재 selected의 index저장
        var farRight = start + (currentRow.length-2)*move; //현재 row의 가장 오른쪽 카드의 위치 
        console.log("current selected: " + selectedIndex);
        
        selected.removeClass("selected");//selected를 없앤다

        for(var i=0; i<currentRow.length; i++) {
          var row = $(currentRow[i]);
          var left = parseInt(row.css("left"), 10);
          
          if(selectedIndex==0){
            //현재 카드가 0이면 왼쪽의 마지막 index카드가 selected자리로 와야함
            if(i==currentRow.length-1) {
              row.css("left", start);
              row.addClass("selected");
              //console.log(i + " : " + start);
            }
            else if(i==currentRow.length-2) {
              row.css("left", farLeft);
              //console.log(i + " : " + farLeft);
            }
            else {
              var newLeft = left+move;
              row.css("left", newLeft);
              //console.log(i + " : " + newLeft);
            }
          }
          else if(selectedIndex==1) {
          //현재 카드가 1이면 왼쪽의 0이 오른쪽으로 오면서 맨오른쪽에 있던 마지막 index 카드가 맨 왼쪽으로 와야함 
            if(i==0) {
              row.css("left", start);
              row.addClass("selected");
              //console.log(i + " : " + start);
            }
            else if(i==currentRow.length-1) {
              row.css("left", farLeft);
              //console.log(i + " : " + farLeft);
            }
            else {
              var newLeft = left+move;
              row.css("left", newLeft);
              //console.log(i + " : " + newLeft);
            }
          }
          else {
            //0과 1이 아닌 애들 
            if(i==selectedIndex-1) {
              row.css("left", start);
              row.addClass("selected");
              //console.log(i + " : " + start);
            }
            else if(i==selectedIndex-2) {
              row.css("left", farLeft);
              //console.log(i + " : " + farLeft);
            }
            else {
              var newLeft = left+move;
              row.css("left", newLeft);
              //console.log(i + " : " + newLeft);
            }
          }
                   
        };
    });  


    $(".right").click(function() { //오른쪽 클릭시 상세카드들의 left값을 변환
    	var currentRow = $(".in-current-row");
        var selected = $(".in-current-row.selected");
        var selectedIndex = parseInt(selected.attr("data-index")); 
        console.log("current selected: " + selectedIndex);
        var farRight = 650 + (currentRow.length-2)*move;
        
        selected.removeClass("selected");//selected를 없앤다

        for(var i=0; i<currentRow.length; i++) {
          var row = $(currentRow[i]);
          var left = parseInt(row.css("left"), 10);
          
          if(currentRow.length==2) {
            //총갯수가 2개뿐일때 
        	
            if(selectedIndex==0) {
              if(i==0) {
                row.css("left", farLeft);
              }
              else {
                row.css("left", start);
                row.addClass("selected")
              }
            }
            else {
              if(i==0) {
                row.css("left", start);
                row.addClass("selected")
              }
              else {
                row.css("left", farLeft);
              }
            }
          } 
          else {
              if(selectedIndex==0) {
              //현재 카드 인덱스가 0이라면 맨왼쪽에 있던 마지막 인덱스가 맨 오른쪽으로 가야함
              if(i==currentRow.length-1) { //마지막
                row.css("left", farRight);
              }
              else if(i==selectedIndex) { //0
                row.css("left", farLeft);
              }
              else if(i==selectedIndex+1) { //1
                //var newLeft = left-move;
                row.css("left", start);
                row.addClass("selected");
              }
              else {
                var newLeft = left-move;
                row.css("left", newLeft);
              }
            }
            else if(selectedIndex==1) {
              //현재 카드 인덱스가 1이라면 0이 맨 오른쪽으로 가야함
              if(i==selectedIndex-1) { //0
                row.css("left", farRight);
              }
              else if(i==selectedIndex) { //0
                row.css("left", farLeft);
              }
              else if(i==selectedIndex+1) { //1
                //var newLeft = left-move;
                row.css("left", start);
                row.addClass("selected");
              }
              else {
                var newLeft = left-move;
                row.css("left", newLeft);
              }
            }
            else if(selectedIndex==currentRow.length-1) {
              //현재 카드 인덱스가 마지막 순번이라면 0이 selected로 가야함
              if(i==selectedIndex-1) { //두번째로 큰수
                row.css("left", farRight);
              }
              else if(i==selectedIndex) { //가장큰수
                row.css("left", farLeft);
              }
              else if(i==0) { //0
                //var newLeft = left-move;
                row.css("left", start);
                row.addClass("selected");
              }
              else {
                var newLeft = left-move;
                row.css("left", newLeft);
              }
            }
            else {
              //0과 1이 아닌애들
              if(i==selectedIndex+1) {
                row.css("left", start);
                row.addClass("selected");
                //console.log(i + " : " + start);
              }
              else if(i==selectedIndex) {
                row.css("left", farLeft);
                //console.log(i + " : " + farLeft);
              }
              else if(i==selectedIndex-1) {
                row.css("left", farRight);
                //console.log(i + " : " + farRight);
              }
              else {
                var newLeft = left-move;
                row.css("left", newLeft);
                //console.log(i + " : " + newLeft);
              }
            }
          }
        };
    });   
});