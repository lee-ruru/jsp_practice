<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<p>페이지 누적 요청수</p>
	<p>매 10번째 방문자에게는 기프티콘을 드립니다.</b></p>
	<p>당첨 되셨습니다.</p>
	<h3>랜덤 구구단</h3>
	
	<% 
		for(int i= 1; i < 10; i++){
			out.println("<h1>" + i + "단<h1/>");	 		
			for (int j = 1; j < 10; j++){
				out.println(i + "*" + j + " = " + (i * j) + "<br/>");
			}
			out.println("-----------------------<br/>");
		}
	%>




</body>
</html>