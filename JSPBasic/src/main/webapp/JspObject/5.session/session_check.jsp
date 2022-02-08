<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*
		- 세션에 저장된 데이터는 브라우저 창이 종료될 때까지, 혹은 세션의
		  유효시간이 만료되기 전까지 웹어플리케이션의 모든 JSP펭지에서 사용할 수 있습니다.
		  
		- 세션에저장된 데이터를 가져오려면 session 객체의 getAttribute() 메서드를
		  사용하며, 매개값으로 가져올 데이터의 세션 이름을 적어줍니다ㅏ. 
	*/
	String userId = (String)session.getAttribute("id_session");
	out.println(userId + "<br/>");
	// 나머지 세션도 화면에 다 표시해보세요.
	String name = (String)session.getAttribute("name_session");
	out.println(name + "<br/>");
	
	String jsp = (String)session.getAttribute("JSP");
	out.println(jsp + "<br/>");
	out.println("----------------------------------<br/>");
	
	// 세션의 유효시간은 아래의 메서드를 사용합니다. 
	// 기본 시간은 30분으로 세팅되어있고(디폴트) 바군다면 초단위로 바꿀 수 있습니다. 
	session.setMaxInactiveInterval(1800);
	// getMaxInactiveInterval()로 남은 시간 조회 가능
	int sTime = session.getMaxInactiveInterval();
	out.println("세션의 유효시간 : "+ sTime + "초<br/>");
	out.println("----------------------------------<br/>");
	
	// 특정 세션 데이터만 삭제하기
	name = (String)session.getAttribute("name_session");
	out.println("name_session 삭제 전 : "  + name + "<br/>");
	
	// 특정한 세션만 삭제시는 .removeAttribute("세션이름"); 을 이용해 삭제합니다. 
	// name_session 삭제 후 삭제가 잘 되었는지 화면에 찍어서 확인까지 해 보세요.
	session.removeAttribute("name_session");
	
	name = (String)session.getAttribute("name_session");
	out.println("name_session 삭제 후 : " + name + "<br/>");
	
	// 세션 일괄적으로 삭제하기
	userId = (String)session.getAttribute("id_session");
	jsp = (String)session.getAttribute("JSP");
	out.println("id_session 삭제 전 : " + userId + "<br/>");
	out.println("JSP 삭제 전 : " + jsp + "<br/>");

	// 세션전체적으로 다 삭제
	session.invalidate();
	// 유효세션여부 조회하기
	// request.isRequestedSessionIdValid()
	if(request.isRequestedSessionIdValid()){
		out.println("유효한 세션 존재");
	}else {
		out.println("세션이 파기되어 조회할 수 없습니다.<br/>");
	}
	out.println("-----------------------------<br/>");
	

	
	
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