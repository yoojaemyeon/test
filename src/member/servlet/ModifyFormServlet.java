package member.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ModifyFormServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션가져오기
		HttpSession session = request.getSession();
		//오브젝트 이름으로 lookup
		Object obj = session.getAttribute("login_info");
		String url = null;
		if(obj==null){
			request.setAttribute("error_message", "로그인 후 회원정보를 수정할 수 있습니다.");
			url = "/login_form.jsp";
		} else {
			url = "/res/modify_form.jsp";
		}
		RequestDispatcher rdp = request.getRequestDispatcher(url);
		rdp.forward(request, response);
		
	}

}
