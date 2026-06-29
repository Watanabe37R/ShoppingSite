package jp.co.aforce.action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.Users;
import jp.co.aforce.dao.UsersDAO;
import jp.co.aforce.validator.UsersValidatorSet;

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
		//登録前までのバリデーション
		UsersValidatorSet validate = new UsersValidatorSet();
		List<String> errors = validate.registrationValidate(setUser);
		//エラーがある場合エラー画面へ
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			return "registration-error.jsp";
		}
		if ("register".equals(mode)) {
			// 登録処理
			try {
				UsersDAO dao = new UsersDAO();
				if (dao.registration(setUser) == 1) {
					return "registration-out.jsp";
				} else {
					errors.add("登録に失敗しました。"
							+ "<br>登録をやり直してください。");
					request.setAttribute("errors", errors);
					return "registration-error.jsp";
				}
			} catch (Exception e) {
				e.printStackTrace();
				errors.add("システムエラーが発生しました。"
						+ "<br>時間をおいて再度お試しください。");
				request.setAttribute("errors", errors);
				return "registration-error.jsp";
			}
		//他のボタン操作時
		} else if ("back".equals(mode)) {
			request.setAttribute("setUser", setUser);
			return "registration-in.jsp";
		} else if ("cancel".equals(mode)) {
			return "Top.action";
		} else {
			errors.add("システムエラーが発生しました。"
					+ "<br>登録をやり直してください。");
			request.setAttribute("errors", errors);
			return "registration-error.jsp";
		}
	}

}
