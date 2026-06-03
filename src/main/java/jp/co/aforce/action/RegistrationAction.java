package jp.co.aforce.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.Users;
import jp.co.aforce.dao.UsersDAO;

public class RegistrationAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String mode = request.getParameter("mode");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String lname = request.getParameter("lname");
		String fname = request.getParameter("fname");
		String address = request.getParameter("address");
		String mail = request.getParameter("mail");

		Users setUser = new Users();
		setUser.setMemberId(id);
		setUser.setPassword(pw);
		setUser.setLastName(lname);
		setUser.setFirstName(fname);
		setUser.setAddress(address);
		setUser.setMailAddress(mail);

		if ("register".equals(mode)) {
			// 登録処理
			UsersDAO dao = new UsersDAO();
			if (dao.registration(setUser) == 1) {
				return "registration-out.jsp";
			} else {
				request.setAttribute("errorMessage", "登録に失敗しました。"
						+ "<br>恐れ入りますが、登録をやり直してください。");
				return "registration-error.jsp";
			}
		}
		if ("back".equals(mode)) {
			request.setAttribute("setUser", setUser);
			return "registration-in.jsp";
		}
		if ("cancel".equals(mode)) {
			return "user-menu.jsp";
		}
		request.setAttribute("errorMessage", "処理にエラーが発生しました。"
				+ "<br>恐れ入りますが、登録をやり直してください。");
		return "registration-error.jsp";
	}

}
