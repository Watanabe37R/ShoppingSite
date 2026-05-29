package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.action.LoginAction;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login.action")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		LoginAction action =new LoginAction();
		String pass;
		try {
			pass=action.execute(request,response);
			if("login-error.jsp".equals(pass)) {
				request.getRequestDispatcher("views/"+pass).forward(request, response);
			}else {
				response.sendRedirect(request.getContextPath() + "/views/" + pass);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
