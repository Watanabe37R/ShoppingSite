package jp.co.aforce.action;

import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.bean.Users;
import jp.co.aforce.dao.CartDAO;
import jp.co.aforce.dao.UsersDAO;
import jp.co.aforce.servlet.UserFrontController;
import jp.co.aforce.validator.UsersValidatorSet;

public class LoginAction extends Action {
	//ログインユーザがいるかどうかチェック
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		// セッションカート取得
		Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		Users loginusercheck = new Users();
		loginusercheck.setMemberId(id);
		loginusercheck.setPassword(pw);
		UsersValidatorSet validate = new UsersValidatorSet();
		List<String> errors = validate.logInValidate(loginusercheck);
		//エラーがある場合エラー画面へ
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
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
					errors.add(
							"他のブラウザでログインをしているため、ログイン処理を中断いたしました。"
									+ "<br>当該ブラウザでログアウトするか、強制ログインを行ってください"
									+ "<br>※強制ログインを行うと、現在のログイン状態を解除いたします。");
					request.setAttribute("errorType", "sessionConflict");
					request.setAttribute("errors", errors);
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
			String redirect = (String) session.getAttribute("redirect");
			//セッション再生成
			session.invalidate();
			session = request.getSession(true);
			session.setAttribute("loginuser", loginuser);
			UserFrontController.usersSession.put(id, session);
			if (loginuser.getManager() == 0) {
				//フィルターで飛ばされた場合
				if (redirect != null) {
					session.removeAttribute("redirect"); // 使い終わったら消す

					//カートDBにsessionカートをマージ
					if (cart != null) {
						CartDAO cdao = new CartDAO();
						for (String productId : cart.keySet()) {
							int quantity = cart.get(productId);
							cdao.upsertCart(loginuser.getMemberId(), productId, quantity);
						}
						//マージ後カートセッション削除
						session.removeAttribute("cart");
					}

					response.sendRedirect(redirect);
					return null;
				}
				return "login-out.jsp";
			} else {
				return "login-out-manager.jsp";
			}
		}
		request.setAttribute("errorType", "normal");
		errors.add("ユーザIDかパスワードが間違っています。");
		request.setAttribute("errors", errors);
		return "login-error.jsp";
	}
}
