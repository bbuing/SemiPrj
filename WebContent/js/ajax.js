/**
 * ajax.js
 */

var httpRequest = null;

function getXMLHttpRequest(){
	return new XMLHttpRequest();
}

function sendRequest(method, url, params, callback){ // 실제 일처리를 하는 메서드
	httpRequest = getXMLHttpRequest();
	
	var httpMethod = method ? method : "GET";
	
	if(httpMethod != "GET" && httpMethod != "POST"){ // 전달된 메서드 값이 비어있으면 GET방식으로 설정
		httpMethod = "GET";
	}
	
	var httpParam = (params == null || params == "")? null : params; // 전달된 params이 비어있으면 null로
	
	var httpUrl = url;
	if(httpMethod == "GET" && httpParam != null){
		httpUrl = httpUrl + "?" + httpParam;
	}
	
	httpRequest.open(httpMethod, httpUrl, true);
	httpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
	// post방식을 사용하기 위한 설정
	httpRequest.onreadystatechange = callback;
	httpRequest.send(httpMethod=="POST"?httpParam:null);
}

function refreshReply(method, params, callback){ // 실제 일처리를 하는 메서드
	httpRequest = getXMLHttpRequest();
	
	var httpMethod = method ? method : "GET";
	
	if(httpMethod != "GET" && httpMethod != "POST"){ // 전달된 메서드 값이 비어있으면 GET방식으로 설정
		httpMethod = "GET";
	}
	
	var httpParam = (params == null || params == "")? null : params; // 전달된 params이 비어있으면 null로
	
	/*
	var httpUrl = url;
	if(httpMethod == "GET" && httpParam != null){
		httpUrl = httpUrl + "?" + httpParam;
	}
	*/
	
	httpRequest.open(httpMethod, true);
	httpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
	// post방식을 사용하기 위한 설정
	httpRequest.onreadystatechange = callback;
	httpRequest.send(httpMethod=="POST"?httpParam:null);
}


















