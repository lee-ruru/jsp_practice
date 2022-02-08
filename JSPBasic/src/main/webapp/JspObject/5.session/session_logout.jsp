<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    // 세션전부파기
    session.invalidate();
    
    // 로그인 페이지로 리다이렉트
    response.sendRedirect("session_login.jsp");
    
    
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>