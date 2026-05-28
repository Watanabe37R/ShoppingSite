package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.action.LogoutAction;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/Logout.action")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LogoutAction action = new LogoutAction();
		String pass;
		try {
			pass = action.execute(request, response);
			response.sendRedirect(request.getContextPath() + "/views/" + pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
