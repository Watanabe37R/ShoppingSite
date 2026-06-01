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
			request.setAttribute("errorType", "normal");
			request.setAttribute("loginerrormessage", "ユーザIDが空白です。");
			return "login-error.jsp";
		}
		if (pw == null || pw.trim().isEmpty()) {
			request.setAttribute("errorType", "normal");
			request.setAttribute("loginerrormessage", "パスワードが空白です。");
			return "login-error.jsp";
		}

		UsersDAO dao = new UsersDAO();
		Users loginuser = dao.search(id, pw);

		boolean force = "true".equals(request.getParameter("force"));
		if (loginuser != null) {
			if (UserFrontController.usersSession.containsKey(id)) {
				//旧セッション
				HttpSession oldSession = UserFrontController.usersSession.get(id);
				//強制ログインではない場合
				if (!force) {
					request.setAttribute("loginerrormessage",
							"他のブラウザでログインをしているため、ログイン処理を中断いたしました。"
									+ "<br>当該ブラウザでログアウトするか、強制ログインを行ってください"
									+ "<br>※強制ログインを行うと、現在のログイン状態を解除いたします。");
					request.setAttribute("errorType", "sessionConflict");
					return "login-error.jsp";
				}
				//旧セッションがあるなら殺す
				if (oldSession != null) {
					try {
						oldSession.invalidate();
					} catch (IllegalStateException e) {
						// 既に死んでるなら無視
					}
				}
			}
			UserFrontController.usersSession.put(id, session);
			session.setAttribute("loginuser", loginuser);
			if (loginuser.getManager() == 0) {
				return "login-out.jsp";
			} else {
				return "login-out-manager.jsp";
			}
		}
		request.setAttribute("errorType", "normal");
		request.setAttribute("loginerrormessage", "ユーザIDかパスワードが間違っています。");
		return "login-error.jsp";
	}
}
