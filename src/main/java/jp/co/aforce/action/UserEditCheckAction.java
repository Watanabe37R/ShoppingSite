package jp.co.aforce.action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.Users;
import jp.co.aforce.validator.UsersValidatorSet;

public class UserEditCheckAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String lname=request.getParameter("lname");
		String fname=request.getParameter("fname");
		String address=request.getParameter("address");
		Users editUser = new Users();
		editUser.setLastName(lname);
		editUser.setFirstName(fname);
		editUser.setAddress(address);
		UsersValidatorSet validate = new UsersValidatorSet();
		List<String> errors=validate.userEditValidate(editUser);
		//エラーがある場合エラー画面へ
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			return "userEdit-error.jsp";
		}
		//後で分岐
		request.setAttribute("editUser", editUser);
		return "userEdit-check.jsp";
	}

}
