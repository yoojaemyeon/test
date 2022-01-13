<%@page import="msmber.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
	MemberDTO dto = (MemberDTO)session.getAttribute("login_info"); 
%>
<h2><%=dto.getName() %>님 로그인 되었습니다.</h2><br>
<input type="button" value="로그아웃" onclick="location.href='/member_servlet/logout'"><p>
<input type="button" value="회원정보 조회" onclick="location.href='/member_servlet/getMemberInfo'">
</body>
</html>