package jp.co.aforce.servlet;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.action.Action;

/**
 * Servlet implementation class UserFrontController
 */
@WebServlet("*.action")
public class UserFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Map<String, HttpSession> usersSession = new ConcurrentHashMap<>();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String actionPass;
		String actionName;
		String pass;
		try {
			//アクションの種類を取得
			//頭の"/"を削る
			actionPass = request.getServletPath().substring(1);
			//名前の.actionをActionにする準備
			String raw = actionPass.replace(".action", "");
			//念のためクラス名に合うように先頭を大文字に+Action付与
			String className = Character.toUpperCase(raw.charAt(0))+raw.substring(1) +"Action";
			//場所を絶対パスで指定
			actionName = "jp.co.aforce.action." + className;
			Action action = (Action) Class.forName(actionName).getDeclaredConstructor().newInstance();
			pass = action.execute(request, response);
			if (pass.endsWith("-error.jsp")||pass.endsWith("-check.jsp")) {
				request.getRequestDispatcher("views/" + pass).forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/views/" + pass);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
