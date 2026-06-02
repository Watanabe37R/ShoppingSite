package jp.co.aforce.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.Users;
import jp.co.aforce.dao.UsersDAO;

public class RegistrationCheckAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

		UsersDAO dao = new UsersDAO();

		switch (dao.check(id, mail)) {
		case ID_DUPLICATE:
			// IDエラー
			request.setAttribute("errorMessage", "IDが重複しています。"
					+ "<br>違うIDを使用してください");
			return "registration-error.jsp";
			
		case MAIL_DUPLICATE:
			// メールエラー
			request.setAttribute("errorMessage", "メールアドレスが重複しています。"
					+ "<br>違うメールアドレスを使用してください");
			return "registration-error.jsp";
			
		case BOTH_DUPLICATE:
			// 両方エラー
			request.setAttribute("errorMessage", "ID,メールアドレスが重複しています。"
					+ "<br>違うID、メールアドレスを使用してください");
			return "registration-error.jsp";
			
		case OK:
			// 正常(登録確認画面へ)
			request.setAttribute("setUser", setUser);
			return "registration-check.jsp";

		default:
			request.setAttribute("errorMessage", "処理中にエラーが発生いたしました。"
					+ "<br>恐れ入りますが、最初からやり直してください。");
			return "registration-error.jsp";
		}

	}

}
