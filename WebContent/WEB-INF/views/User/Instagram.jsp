<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<title>Instagram Login</title>
</head>
<body>
<script>
	$(document).ready(function(){
		var token = document.location.href.split("token=");
		
		/*var form = $("<form>",{
			method : "POST",
			action : "Write_google.jsp",
		}).append($("<input>",{
			type : "hidden",
			name : "token",
			value : token[1]
			}));
		form.submit();*/
		opener.document.getElementById("token").value = token[1];
		alert("인증되었습니다.");
		window.close();
	});
</script>
</body>
</html>