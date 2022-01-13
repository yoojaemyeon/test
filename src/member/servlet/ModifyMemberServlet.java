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

public class ModifyMemberServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String registerNumber = request.getParameter("register_no1")
				+ request.getParameter("register_no2");

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "update member set password=?, name=?, register_number=? where id=?";

		ServletContext sct = getServletContext();
		String url = sct.getInitParameter("url");
		String user = sct.getInitParameter("user");
		String DBPwd = sct.getInitParameter("password");

		String resURL = null;

		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO) session.getAttribute("login_info");

		if (dto != null) {
			try {

				connection = DriverManager.getConnection(url, user, DBPwd);

				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, password);
				preparedStatement.setString(2, name);
				preparedStatement.setString(3, registerNumber);
				preparedStatement.setString(4, dto.getId());

				int cnt = preparedStatement.executeUpdate();
				System.out.println(cnt + " 개의 정보가 수정되었습니다.");
				MemberDTO mdto = new MemberDTO(dto.getId(), password, name,
						registerNumber, dto.getMileage());
				request.setAttribute("login_info", mdto);
				resURL = "/res/member_detail.jsp";

			} catch (SQLException e) {
				request.setAttribute("error_message", e.getMessage());
				resURL = "/res/error.jsp";
			} finally {
				DatabaseUtility.close(preparedStatement, connection);
			}
		} else {
			request.setAttribute("error_message","로그인을 해야 수정된 정보가 출력됩니다.");
			resURL = "/login_form.jsp";
			
		}
		RequestDispatcher rdp = request.getRequestDispatcher(resURL);
		rdp.forward(request, response);
	}

}
