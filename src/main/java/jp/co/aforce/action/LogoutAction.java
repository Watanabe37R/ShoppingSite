package jp.co.aforce.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.bean.Users;
import jp.co.aforce.servlet.UserFrontController;

public class LogoutAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		
		if(session.getAttribute("loginuser")!=null) {
			//セッションに保存されているUsersの
			Users user = (Users) session.getAttribute("loginuser");
			//idを取り出して
			String id = user.getMemberId();
			//usersSessionから取り除く
			UserFrontController.usersSession.remove(id);
			//こっちは普通にセッションを消す
			session.removeAttribute("loginuser");
			return "logout-out.jsp";
		}
		return "logout-error.jsp";
	}
}
