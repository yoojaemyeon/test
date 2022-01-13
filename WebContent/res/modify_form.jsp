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
		MemberDTO dto = (MemberDTO) session.getAttribute("login_info");
	%>
	<form action="/member_servlet/modifyMember" method="post">
		<table>
			<tr>
				<td><h2>회원정보수정</h2></td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id" disabled="disabled"
					value="<%=dto.getId()%>"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="text" name="password" value="<%=dto.getPassword()%>"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value="<%=dto.getName()%>"></td>
			</tr>
			<tr>
				<td>주민번호</td>
				<td><input type="text" name="register_no1"
					value="<%=dto.getRegisterNumber().substring(0, 6)%>" maxlength="6"> - <input
					type="text" name="register_no2" value="<%=dto.getRegisterNumber().substring(6)%>" maxlength="7"></td>
			</tr>
			<tr>
				<td>마일리지</td>
				<td><input type="text" name="maileage" disabled="disabled"
					value="<%=dto.getMileage()%>"></td>
			</tr>
			<tr>
				<td><input type="submit" value="수정">
				<input type="reset" value="다시작성"></td>
			</tr>
		</table>
	</form>
</body>
</html>