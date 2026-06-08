package jp.co.aforce.validator;

import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.bean.Users;
import jp.co.aforce.dao.UsersDAO;
import jp.co.aforce.tool.DuplicateResult;

public class UsersValidatorSet {
	/*
	 * ログイン時に使用するバリデータセット
	 */
	public List<String> logInValidate(Users user) {
		UsersValidator validator = new UsersValidator();
		List<String> errors = new ArrayList<>();
		//文字数チェック
		if (!validator.isLoginValid(user)) {
			errors.add("文字数規定違反です。");
		}
		//入力規則チェックその他
		if (!validator.isIdPwInputValid(user)) {
			errors.add("入力文字規定違反です。<br>対象：ID,PW");
		}
		return errors;
	}
	
	/*
	 * 新規登録時に使用するバリデータセット
	 */
	public List<String> registrationValidate(Users user) {
		UsersValidator validator = new UsersValidator();
		List<String> errors = new ArrayList<>();
		//文字数チェック
		if (!validator.isRegistrationValid(user)) {
			errors.add("文字数規定違反です。");
		}
		//入力規則チェックID,PW
		if (!validator.isIdPwInputValid(user)) {
			errors.add("入力文字規定違反です。<br>対象：ID,パスワード");
		}
		//入力規則チェックMail
		if (!validator.isMailInputValid(user)) {
			errors.add("入力文字規定違反です。<br>対象：メールアドレス");
		}
		//入力規則チェックその他
		if (!validator.isOtherInputValid(user)) {
			errors.add("入力文字規定違反です。<br>対象：苗字、名前、住所");
		}

		//今までにえらーがないなら
		if (errors.isEmpty()) {
			//重複チェック
			UsersDAO dao = new UsersDAO();
			try {
				DuplicateResult result = dao.check(user.getMemberId(), user.getMailAddress());

				if (result == DuplicateResult.ID_DUPLICATE || result == DuplicateResult.BOTH_DUPLICATE) {
					// IDエラー
					errors.add("IDが重複しています。"
							+ "<br>違うIDを使用してください");
				}

				if (result == DuplicateResult.MAIL_DUPLICATE || result == DuplicateResult.BOTH_DUPLICATE) {
					// メールエラー
					errors.add("メールアドレスが重複しています。"
							+ "<br>違うメールアドレスを使用してください");
				}
			} catch (Exception e) {
				errors.add("システムエラーが発生しました。"
						+ "<br>時間をおいて再度お試しください。");
			}
		}
		return errors;
	}
	
	/*
	 * 基本情報更新時に使用するバリデータセット
	 */
	public List<String> userEditValidate(Users user) {
		UsersValidator validator = new UsersValidator();
		List<String> errors = new ArrayList<>();
		//文字数チェック
		if (!validator.isUserEditValid(user)) {
			errors.add("文字数規定違反です。");
		}
		//入力規則チェックその他
		if (!validator.isOtherInputValid(user)) {
			errors.add("入力文字規定違反です。<br>対象：苗字、名前、住所");
		}
		return errors;
	}
	
	/*
	 * メアド更新時に使用するバリデータセット
	 */
	public List<String> mailEditValidate(Users user) {
		UsersValidator validator = new UsersValidator();
		List<String> errors = new ArrayList<>();
		//文字数チェック
		if (!validator.isMailEditValid(user)) {
			errors.add("文字数規定違反です。");
		}
		//入力規則チェックMail
		if (!validator.isMailInputValid(user)) {
			errors.add("入力文字規定違反です。<br>対象：メールアドレス");
		}

		//今までにえらーがないなら
		if (errors.isEmpty()) {
			//重複チェック
			UsersDAO dao = new UsersDAO();
			try {
				DuplicateResult result = dao.check(user.getMemberId(), user.getMailAddress());
				//IDcheckは行わない(必ず重複するため)
				if (result == DuplicateResult.MAIL_DUPLICATE || result == DuplicateResult.BOTH_DUPLICATE) {
					// メールエラー
					errors.add("メールアドレスが重複しています。"
							+ "<br>違うメールアドレスを使用してください");
				}
			} catch (Exception e) {
				errors.add("システムエラーが発生しました。"
						+ "<br>時間をおいて再度お試しください。");
			}
		}
		return errors;
	}
}
