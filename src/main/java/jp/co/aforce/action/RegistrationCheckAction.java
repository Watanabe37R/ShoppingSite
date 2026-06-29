package jp.co.aforce.action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.Users;
import jp.co.aforce.validator.UsersValidatorSet;

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

		//バリデーション
		UsersValidatorSet validate =new UsersValidatorSet();
		List<String> errors=validate.registrationValidate(setUser);
		//全部含めてエラーへ
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			return "registration-error.jsp";
		}
		// 正常(登録確認画面へ)
		request.setAttribute("setUser", setUser);
		return "registration-check.jsp";

	}

}
