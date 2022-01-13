package member.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.util.DatabaseUtility;
import msmber.dto.MemberDTO;

public class RegisterMemberServlet2 extends HttpServlet {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id  = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String register_no1 = request.getParameter("register_no1");
		String register_no2 = request.getParameter("register_no2");
		String registerNumber = register_no1+register_no2;
		int maileage = 1000;
		
		String sql = "insert into member values(?,?,?,?,?)";
		
		ServletContext sct = getServletContext();
		String url = sct.getInitParameter("url");
		String user = sct.getInitParameter("user");
		String DBPwd = sct.getInitParameter("password");
	
		MemberDTO dto = new MemberDTO(id,password,name,registerNumber,maileage);
		String resURL=null;
		
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
			request.setAttribute("mdto", dto);
			resURL = "/res/register_success.jsp";
//			RequestDispatcher rdp = request.getRequestDispatcher("/res/register_success.jsp");
//			rdp.forward(request, response);
			
		} catch (SQLException e) {
			request.setAttribute("error_message", e.getMessage());
			resURL = "/res/error.jsp";
//			RequestDispatcher rdp = request.getRequestDispatcher("/res/error.jsp");
//			rdp.forward(request, response);

		} finally {
			DatabaseUtility.close(preparedStatement, connection);
		}
		RequestDispatcher rdp = request.getRequestDispatcher(resURL);
		rdp.forward(request, response);
	}
}
