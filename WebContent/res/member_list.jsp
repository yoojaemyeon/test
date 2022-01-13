<%@page import="msmber.dto.MemberDTO"%>
<%@page import="java.util.ArrayList"%>
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
	ArrayList<MemberDTO> list = (ArrayList<MemberDTO>)request.getAttribute("list");
%>
<h2>전체조회</h2>
<hr>
<table width="500" border="1" cellspacing="0" >
<tr>
	<td>순번</td>
	<td>ID</td>
	<td>Password</td>
	<td>이름</td>
	<td>주민번호</td>
	<td>마일리지</td>
</tr>
<%
	for(int idx=0; idx<list.size();idx++){
%>

<tr>
	<td><%=idx+1 %></td>
	<td><%=list.get(idx).getId() %></td>
	<td><%=list.get(idx).getPassword() %></td>
	<td>
		<a href="/servlet_member/searchMemberByID?id=<%=list.get(idx).getId()%>">
			<%=list.get(idx).getName() %>
		</a>
	</td>
	
	<td><%=list.get(idx).getRegisterNumber().substring(0, 6) %> - 
	<%=list.get(idx).getRegisterNumber().substring(6) %></td>
	<td><%=list.get(idx).getMileage() %></td>
</tr>

<% } %>
</table><p>
<input type="button" value="메인페이지" onclick="location.href='/member_servlet/index.html'">
</body>
</html>