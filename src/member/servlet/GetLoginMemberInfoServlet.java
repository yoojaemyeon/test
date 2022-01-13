package member.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import msmber.dto.MemberDTO;

public class GetLoginMemberInfoServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Session scope에 지금까지 클라이언트가 보낸 text들을 출력
		// 세션가져오기
		HttpSession session = request.getSession();
		// 오브젝트 이름으로 lookup
		Object obj = session.getAttribute("login_info");
		String url = null;
		if (obj == null) {
			request.setAttribute("error_message", "로그인 후 회원정보를 조회하십시오.");
			url = "/login_form.jsp";
		} else {
			url = "/res/member_detail.jsp";
		}
		RequestDispatcher rdp = request.getRequestDispatcher(url);
		rdp.forward(request, response);
	}

}
