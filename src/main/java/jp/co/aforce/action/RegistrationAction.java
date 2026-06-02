package jp.co.aforce.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.Users;
import jp.co.aforce.dao.UsersDAO;

public class RegistrationAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String mode = request.getParameter("mode");
		Users setUser = (Users) request.getAttribute("setUser");
		if ("register".equals(mode)) {
			// 登録処理
			
			String id = setUser.getMemberId();
			String pw = setUser.getPassword();
			String lname = setUser.getLastName();
			String fname = setUser.getFirstName();
			String address = setUser.getAddress();
			String mail = setUser.getMailAddress();
			
			UsersDAO dao = new UsersDAO();
	
			return "";
		}
		if ("back".equals(mode)) {
			request.setAttribute("setUser", setUser);
			return "registration-in.jsp";
		}
		if ("cancel".equals(mode)) {
			return "user-menu.jsp";
		}
		return "registration-error.jsp";
	}

}
