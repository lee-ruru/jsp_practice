<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%
     // 한글데이터 깨지지 않게 인코딩 설정 바꾸기
     request.setCharacterEncoding("utf-8");
     //form에서 보내준 데이터 받아오기
     
    String id = request.getParameter("id");
    String pw = request.getParameter("pw");
    String nickname = request.getParameter("nickname");
     System.out.println(id);
     System.out.println(pw);
     System.out.println(nickname);
     
    String loginCheck = (String)session.getAttribute("session_id");
    if(loginCheck != null){
   		response.sendRedirect("session_welcom.jsp");
   	} 
     
     //로그인 로직 작성
     if(id.equals("kkk1234") && pw.equals("1111")){
     	session.setAttribute("session_id", id);
     	session.setAttribute("session_pw", pw);
     	session.setAttribute("session_nick", nickname);
     	response.sendRedirect("session_welcom.jsp");
     }
     
 
     
 	 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="session_login.jsp">로그인에 실패했습니다. 누르면 로그인창으로 돌아갑니다.</a>
</body>
</html>