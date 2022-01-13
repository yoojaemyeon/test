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
<h2>회원정보</h2><p>
아이디 : <%=dto.getId() %> <br>
이름 : <%=dto.getName() %> <br>
주민번호 : <%=dto.getRegisterNumber().substring(0,6) %> - <%=dto.getRegisterNumber().substring(6) %> <br>
마일리지 : <%=dto.getMileage() %>
<p>
<input type="button" value="회원정보수정" onclick="location.href='/member_servlet/modifyForm'">
<input type="button" value="회원탈퇴" onclick="location.href='/member_servlet/removeMember'">
</body>
</html>