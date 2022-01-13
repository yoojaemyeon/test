package member.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.listener.DriverLoadingListener;
import member.util.DatabaseUtility;
import msmber.dto.MemberDTO;

public class GetAllMemberInfoServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//초기 파라미터 가져오기
		ServletContext context = getServletContext();
		String url = context.getInitParameter("url");
		String user = context.getInitParameter("user");
		String DBPwd = context.getInitParameter("password");
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "select id,password,name,register_number,mileage from member";
		
		String resURL = null;
		
		//한글처리
		request.setCharacterEncoding("utf-8");
		
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		
		//sql처리
		try {
			connection = DriverManager.getConnection(url,user,DBPwd);
			
			preparedStatement = connection.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				String id = resultSet.getString(1);
				String password = resultSet.getString(2);
				String name = resultSet.getString(3);
				String registerNumber = resultSet.getString(4);
				int mileage = resultSet.getInt(5);
				
				list.add(new MemberDTO(id, password, name, registerNumber, mileage));
			}
			request.setAttribute("list", list);
			resURL = "/res/member_list.jsp";
			
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error_message", e.getMessage());
			resURL = "/res/error.jsp";
			
		} finally {
			DatabaseUtility.close(resultSet, preparedStatement, connection);
		}
		RequestDispatcher rdp = request.getRequestDispatcher(resURL);
		rdp.forward(request, response);
	}

}
