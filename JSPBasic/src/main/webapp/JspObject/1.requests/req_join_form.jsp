<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action = "req_join_result.jsp" method="post">
	아이디 : <input type="text" name="id" size="12px"/><br/>
	비밀번호 :<input type="password" name="pw" size="12px"/><br/>
	이름 : <input type="text" name="name" size="12px"/><br/>
	나이 :<input type="number" name="age"/><br/> 
	
	<input type = "submit" value="회원가입하기"/> &nbsp;
	<input type = "reset" value = "초기화하기"/>	
	</form> 
</body>
</html>