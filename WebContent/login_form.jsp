<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
로그인 폼<br>
<%
	String errorMessage = (String)request.getAttribute("error_message");
	if(errorMessage!=null){
%>
	<font color="red" size="2"><%=errorMessage %></font>
	
<%
	}
%>

<form action="/member_servlet/login" method="post">
	<table>
		<tr>
			<td>ID</td>
			<td><input type="text" name="id" value="1111" size="20"></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" name="password" value="1111" size="21"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="전송">
			<input type="reset" value="다시작성"></td>
		</tr>
	</table>
</form>
</body>
</html>