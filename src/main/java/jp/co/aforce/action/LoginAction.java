package jp.co.aforce.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.bean.Users;
import jp.co.aforce.dao.UsersDAO;
import jp.co.aforce.servlet.UserFrontController;

public class LoginAction extends Action {
	//ログインユーザがいるかどうかチェック
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		//空文字チェック
		if (id == null || id.trim().isEmpty()) {
			request.setAttribute("loginerrormessage", "ユーザIDが空白です。");
			return "login-error.jsp";
		}
		if (pw == null || pw.trim().isEmpty()) {
			request.setAttribute("loginerrormessage", "パスワードが空白です。");
			return "login-error.jsp";
		}

		UsersDAO dao = new UsersDAO();
		Users loginuser = dao.search(id, pw);

		if (loginuser != null) {
			if (UserFrontController.usersSession.containsKey(id)) {
				request.setAttribute("loginerrormessage", "他のブラウザでログインをしているため、ログイン処理を中断いたしました。<br>ログアウトしてからご利用ください。");
				return "login-error.jsp";
			}
			UserFrontController.usersSession.put(id, session);
			session.setAttribute("loginuser", loginuser);
			if (loginuser.getManager() == 0) {
				return "login-out.jsp";
			} else {
				return "login-out-manager.jsp";
			}
		}
		request.setAttribute("loginerrormessage", "ユーザIDかパスワードが間違っています。");
		return "login-error.jsp";
	}
}
