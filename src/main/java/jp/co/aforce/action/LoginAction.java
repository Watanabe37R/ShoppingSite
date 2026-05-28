package jp.co.aforce.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.bean.Users;
import jp.co.aforce.dao.UsersDAO;

public class LoginAction extends Action {
	//ログインユーザがいるかどうかチェック
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		UsersDAO dao = new UsersDAO();
		Users loginuser = dao.search(id, pw);

		if (loginuser != null) {
			session.setAttribute("loginuser", loginuser);
			if (loginuser.getManager() == 0) {
				return "login-out.jsp";
			} else {
				return "login-out-manager.jsp";
			}
		}
		return "login-error.jsp";
	}
}
