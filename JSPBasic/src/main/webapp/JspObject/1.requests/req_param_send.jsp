<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--아래에 form을 만들고 내부에 input 4개를 만들어주세요.
	input태그의 type속성은 하나는 text, 하나는 password
	name 속성은 하나는 apple. 하나는 banana로 해주세요.
	reset버튼과 submit버튼도 각각 하나씩 만들어주세요. 
	
	form의 action이 바로 목적지를 적는 부분임.
	웹개발에서는 받을 페이지 화면의 주소를 적어줍니다.--%>

	<form action="req_param_getpost.jsp" method="get">
		<input type="text" name="apple" placeholder="apple" /><br/>
		<input type="password" name="banana" placeholder="banana" /><br/>
		
		취미 : 
		<input type = "checkbox" name="hobby" value="요리" >요리&nbsp;
		<input type = "checkbox" name="hobby" value="춤" >춤&nbsp;
		<input type = "checkbox" name="hobby" value="노래" >노래&nbsp;
		<input type = "checkbox" name="hobby" value="게임" >게임&nbsp;
		<input type = "checkbox" name="hobby" value="그림그리기" >그림그리기&nbsp;<br/>

		전공 : 
		
		<input type="radio" name="major" value="컴퓨터공학"/>컴공
		<input type="radio" name="major" value="태국어"/>태국어
		<input type="radio" name="major" value="경영학"/>경영
		<input type="radio" name="major" value="서양미술학"/>서양미술<br/>
		
		<input type="reset" />
		<input type="submit" />
	</form>
</body>
</html>