package member.servlet;

import java.io.IOException;
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
import javax.servlet.http.HttpSession;

import member.util.DatabaseUtility;
import msmber.dto.MemberDTO;

public class RemoveMemberServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		String url = context.getInitParameter("url");
		String user = context.getInitParameter("user");
		String DBPwd = context.getInitParameter("password");
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "delete from member where id=?";
		
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("login_info");
		
		String resURL = null;
		
		if(dto!=null){
			try {
				connection = DriverManager.getConnection(url,user,DBPwd);
				
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, dto.getId());
				int cnt = preparedStatement.executeUpdate();
				System.out.println(cnt + " 개의 정보가 삭제되었습니다.");
				
				session.invalidate();
				resURL = "/index.html";
				
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("error_message", e.getMessage());
				resURL = "/res/error.jsp";
			} finally {
				DatabaseUtility.close(preparedStatement, connection);
			}
		} else {
			request.setAttribute("error_message", "로그인을 해야 탈퇴할 수 있습니다.");
			resURL = "/login_form.jsp";
		}
		RequestDispatcher rdp = request.getRequestDispatcher(resURL);
		rdp.forward(request, response);
	}

}
