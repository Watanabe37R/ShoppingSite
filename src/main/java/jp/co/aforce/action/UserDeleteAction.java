package jp.co.aforce.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.bean.Users;
import jp.co.aforce.dao.UsersDAO;
import jp.co.aforce.servlet.UserFrontController;

public class UserDeleteAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("loginuser");
		String sId = user.getMemberId();
		UsersDAO dao=new UsersDAO();
		if(dao.delete(sId)==1) {
			//usersSessionから取り除く
			UserFrontController.usersSession.remove(sId);
			//セッションを消す
			session.removeAttribute("loginuser");
			return "userDelete-out.jsp";
		}
		
		return "userDelete-error.jsp";
	}

}
