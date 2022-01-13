package member.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.util.DatabaseUtility;

import sun.security.mscapi.PRNG;

public class RegisterMemberServlet extends HttpServlet {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1. 요청파라미터 조회
		 * 2. Business Logic 처리
		 *  - connection
		 *  - preparedstatement
		 *  - sql문 전송 (insert)
		 *  - close
		 * 3. 응답처리
		 *  - 클라이언트로 받은 가입정보를 출력한 뒤, 가입처리 성공 메세지 응답
		 */
		request.setCharacterEncoding("UTF-8");
		String id  = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String register_no1 = request.getParameter("register_no1");
		String register_no2 = request.getParameter("register_no2");
		String registerNumber = register_no1+register_no2;
		int maileage = 1000;
		
		String sql = "insert into member(id,password,name,register_number,maileage) values(?,?,?,?,?)";
		
		ServletContext sct = getServletContext();
		String url = sct.getInitParameter("url");
		String user = sct.getInitParameter("user");
		String DBPwd = sct.getInitParameter("password");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		
		try {
			connection = DriverManager.getConnection(url,user,DBPwd);
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, registerNumber);
			preparedStatement.setInt(5, maileage);
			int cnt = preparedStatement.executeUpdate();
			System.out.println(cnt + "개의 주소가 입력되었습니다.");
			
			printWriter.println("<html><head><title>개인 정보</title></head><body>");
			printWriter.println("가입 정보" + "<hr>");
			printWriter.println("<table><tr><td>ID " + id + "<br></td></tr>");
			printWriter.println("<tr><td>Password " + password + "<br></td></tr>");
			printWriter.println("<tr><td>이름 " + name + "<br></td></tr>");
			printWriter.println("<tr><td>주민번호 " + register_no1 + " - " + register_no2 + "<br></td></tr>");
			printWriter.println("<tr><td>마일리지 " + maileage + "<br></td></tr>");
			printWriter.println("</table>");
			printWriter.println(name + "님 가입이 되었습니다~! 짝짝짝");
		} catch (SQLException e) {
			printWriter.println("가입시 오류가 발생했습니다.<br>");
			printWriter.println("오류 메세지 : "+e.getMessage()+"<br>");
			printWriter.println("<br><a href = '/member_servlet/register_form.html'>회원가입으로 가기</a>");
		} finally {
			DatabaseUtility.close(preparedStatement, connection);
		}
		printWriter.println("</body></html>");	
	}
}
