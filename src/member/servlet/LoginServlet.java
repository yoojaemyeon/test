package member.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 요청 파라미터 조회
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		//Business Login 처리
		ServletContext sct = getServletContext();
		String url = sct.getInitParameter("url");
		String user = sct.getInitParameter("user");
		String DBPwd = sct.getInitParameter("password");
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "select id,password,name,register_number,mileage from member where id=?";
		
		String resURL = null;
		
		try {
			connection =DriverManager.getConnection(url,user,DBPwd);
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, id);
			
			resultSet = preparedStatement.executeQuery();
			//아이디가 있으면
			if(resultSet.next()){//아이디가 있는 경우 실행
				//패스워드 비교
				if(password.equals(resultSet.getString(2))){//ID와Password가 맞는 경우
					HttpSession session = request.getSession();
					MemberDTO dto = new MemberDTO(id, password, resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5));
					
					session.setAttribute("login_info", dto);
					response.sendRedirect("/member_servlet/res/login_success.jsp");
				} else{//패스워드가 틀린 경우
					request.setAttribute("error_message", "패스워드가 틀렸습니다. 체크 다시 로그인 하세요.");
					resURL = "/login_form.jsp";
				} 
				
			} else{//id가 없는 경우
				request.setAttribute("error_message", id+"는 없는 ID입니다. 체크 다시 로그인 하세요.");
				resURL = "/login_form.jsp";
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error_message", e.getMessage());
			resURL = "/res/error.jsp";
		} finally {
			DatabaseUtility.close(resultSet, preparedStatement, connection);
		}
		if(resURL!=null){
			RequestDispatcher rdp = request.getRequestDispatcher(resURL);
			rdp.forward(request, response);
		}
	}
}
