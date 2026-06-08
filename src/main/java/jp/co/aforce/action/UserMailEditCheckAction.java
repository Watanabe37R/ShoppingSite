package jp.co.aforce.action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.bean.Users;
import jp.co.aforce.validator.UsersValidatorSet;

public class UserMailEditCheckAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//セッションからIDを取得
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("loginuser");
		String sId = user.getMemberId();
		
		String oldMail =request.getParameter("oldMail");
		Users userInfo = new Users();
		userInfo.setMailAddress(oldMail);
		
		String mail=request.getParameter("mail");
		Users editUser = new Users();
		//IDはセッションから
		editUser.setMemberId(sId);
		//メールは新しいメールから
		editUser.setMailAddress(mail);
		//バリデーションで今使ってるメールアドレスもはじける
		UsersValidatorSet validate = new UsersValidatorSet();
		List<String> errors=validate.mailEditValidate(editUser);
		//エラーがある場合エラー画面へ
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			return "userMailEdit-error.jsp";
		}
		request.setAttribute("userInfo", userInfo);
		request.setAttribute("editUser", editUser);
		return "userMailEdit-check.jsp";
		
	}

}
