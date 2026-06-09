package jp.co.aforce.action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.bean.Users;
import jp.co.aforce.dao.UsersDAO;
import jp.co.aforce.validator.UsersValidatorSet;

public class UserPWEditAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//セッションからIDを取得
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("loginuser");
		String sId = user.getMemberId();
		
		//旧パスワードバリデーション
		String pwOld = request.getParameter("pwOld");
		Users loginusercheck =new Users();
		loginusercheck.setMemberId(sId);
		loginusercheck.setPassword(pwOld);
		UsersValidatorSet validate = new UsersValidatorSet();
		List<String> errors=validate.pwEditValidate(loginusercheck);
		//エラーがある場合エラー画面へ
		if (!errors.isEmpty()) {
			errors.add("<br>対象：旧パスワード");
		}
		//新パスワードバリデーション
		String pw = request.getParameter("pw");
		Users editUser = new Users();
		editUser.setPassword(pw);
		editUser.setMemberId(sId);
		errors.addAll(validate.pwEditValidate(editUser));
		//エラーがある場合エラー画面へ
		if (!errors.isEmpty()) {
			errors.add("<br>対象：新パスワード");
			request.setAttribute("errors", errors);
			return "userPWEdit-error.jsp";
		}
		//旧パスワードチェック
		UsersDAO dao = new UsersDAO();
		Users loginuser = dao.search(sId, pwOld);
		if(loginuser==null) {
			errors.add("旧パスワードが間違っています。");
			request.setAttribute("errors", errors);
			return "userPWEdit-error.jsp";
		}
		//全てのチェックを超えた時、DB更新
		try {
			if(dao.pwUpdate(sId,editUser)==1) {

				return "userPWEdit-out.jsp";
			}else {
				errors.add("更新に失敗しました。<br>更新をやり直してください。");
				return "userPWEdit-error.jsp";
			}
		}catch(Exception e) {
			errors.add("システムエラーが発生しました。<br>時間をおいて再度お試しください。");
			return "userPWEdit-error.jsp";
		}
		
	}

}
