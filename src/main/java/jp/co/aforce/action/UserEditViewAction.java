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
		String mode = request.getParameter("mode");
		try {
			Users userInfo = dao.findById(sId);
			if (userInfo == null) {
				return "Top.action";
			}
			if (userInfo.getManager() == 1) {
				return "Top.action";
			}

			request.setAttribute("userInfo", userInfo);
			// 分岐
			if ("edit".equals(mode)) {
				return "userEdit-in.jsp"; // 編集画面
			} else {
				return "userEdit-view.jsp"; // 表示画面
			}
		} catch (Exception e) {
			return "Top.action";
		}
	}

}
