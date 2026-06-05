package jp.co.aforce.action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.bean.Users;
import jp.co.aforce.dao.UsersDAO;
import jp.co.aforce.validator.UsersValidatorSet;

public class UserEditAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("loginuser");
		String sId = user.getMemberId();

		String mode = request.getParameter("mode");
		String lname = request.getParameter("lname");
		String fname = request.getParameter("fname");
		String address = request.getParameter("address");
		Users editUser = new Users();
		editUser.setLastName(lname);
		editUser.setFirstName(fname);
		editUser.setAddress(address);

		//バリデーション
		UsersValidatorSet validate = new UsersValidatorSet();
		List<String> errors = validate.userEditValidate(editUser);
		//エラーがある場合エラー画面へ
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			return "userEdit-error.jsp";
		}

		if ("update".equals(mode)) {
			//更新処理
			try {
				UsersDAO dao = new UsersDAO();
				if (dao.update(sId, editUser) == 1) {
					request.setAttribute("editUser", editUser);
					//セッション更新
					user.setLastName(lname);
					return "userEdit-out.jsp";
				} else {
					errors.add("更新処理中にエラーが発生しました。"
							+ "<br>操作をやり直してください。");
				}
			} catch (Exception e) {
				errors.add("システムエラーが発生しました。"
						+ "<br>操作をやり直してください。");
			}

		//他のボタン操作時
		} else if ("back".equals(mode)) {
			request.setAttribute("userInfo", editUser);
			return "userEdit-in.jsp";
		} else if ("cancel".equals(mode)) {
			return "top.jsp";
		}
		errors.add("システムエラーが発生しました。"
				+ "<br>操作をやり直してください。");
		request.setAttribute("errors", errors);
		return "userEdit-error.jsp";
	}
}
