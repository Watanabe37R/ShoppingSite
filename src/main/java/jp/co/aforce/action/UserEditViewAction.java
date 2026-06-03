package jp.co.aforce.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.bean.Users;
import jp.co.aforce.dao.UsersDAO;

public class UserEditViewAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("loginuser");
		String sId = user.getMemberId();
		UsersDAO dao = new UsersDAO();
		Users userInfo = dao.findById(sId);
		String mode = request.getParameter("mode");
		if (userInfo == null) {
			return "login-in.jsp";
		}
		if (userInfo.getManager() == 1) {
			return "login-in.jsp";
		}
		
		request.setAttribute("userInfo", userInfo);
		// 分岐
		if ("edit".equals(mode)) {
		    return "userEdit-in.jsp"; // 編集画面
		} else {
		    return "userEdit-view.jsp"; // 表示画面
		}
	}

}
