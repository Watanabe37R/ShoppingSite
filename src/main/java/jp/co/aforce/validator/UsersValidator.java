package jp.co.aforce.validator;

import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.bean.Users;

public class UsersValidator {
	/*
	 * 文字数が足りない際の判定。
	 * validateで返されるリストにエラーが
	 * 含まれているならfalse
	 * 含まれていないならtrue
	 * これはログイン用
	 */
	public boolean isLoginValid(Users user) {
		List<String> errors = new ArrayList<>();
		errors.addAll(idValidate(user));
		errors.addAll(pwValidate(user));
		return errors.isEmpty();
	}
	
	/*
	 * 新規登録用
	 */
	public boolean isRegistrationValid(Users user) {
		List<String> errors = new ArrayList<>();
		errors.addAll(idValidate(user));
		errors.addAll(pwValidate(user));
		errors.addAll(otherValidate(user));
		errors.addAll(mailValidate(user));
		return errors.isEmpty();
	}

	/*
	 * 基本情報更新用
	 */
	public boolean isUserEditValid(Users user) {
		List<String> errors = new ArrayList<>();
		//idはセッションから取るので不要
		errors.addAll(otherValidate(user));
		return errors.isEmpty();
	}

	/*
	 * メールアドレス更新用
	 */
	public boolean isMailEditValid(Users user) {
		List<String> errors = new ArrayList<>();
		//idはセッションから取るので不要
		errors.addAll(mailValidate(user));
		return errors.isEmpty();
	}

	/*
	 * パスワード更新用
	 */
	public boolean isPassWordEditValid(Users user) {
		List<String> errors = new ArrayList<>();
		//idはセッションから取るので不要
		errors.addAll(pwValidate(user));
		return errors.isEmpty();
	}
	
	

	/*
	 * エラー判定の実態。これはIDチェック。
	 */
	public List<String> idValidate(Users user) {
		List<String> errors = new ArrayList<>();

		//IDは1~10 NOTNULL
		if (user.getMemberId() == null || user.getMemberId().length() > 10) {
			errors.add("IDエラー");
		}
		return errors;
	}

	/*
	 * パスワードチェック
	 */
	public List<String> pwValidate(Users user) {
		List<String> errors = new ArrayList<>();

		//PWは8~32 NOTNULL
		if (user.getPassword() == null || user.getPassword().length() < 8 || user.getPassword().length() > 32) {
			errors.add("PWエラー");
		}
		return errors;
	}

	/*
	 * そのほか
	 */
	public List<String> otherValidate(Users user) {
		List<String> errors = new ArrayList<>();
		//LNameは1~32 NOTNULL
		if (user.getLastName() == null || user.getLastName().length() > 32) {
			errors.add("苗字エラー");
		}
		//FNameは1~32 NOTNULL
		if (user.getFirstName() == null || user.getFirstName().length() > 32) {
			errors.add("名前エラー");
		}
		//ADDRESSは1~128 NOTNULL
		if (user.getAddress() == null || user.getAddress().length() > 128) {
			errors.add("住所エラー");
		}
		return errors;
	}

	/*
	 * メールアドレス
	 */
	public List<String> mailValidate(Users user) {
		List<String> errors = new ArrayList<>();
		//Mailは1~128 NOTNULL
		if (user.getMailAddress() == null || user.getMailAddress().length() > 128) {
			errors.add("メールエラー");
		}
		return errors;
	}

	/*
	 * 英数字チェック(ID,PW用)
	 */
	public boolean isIdPwInputValid(Users user) {

		return user.getMemberId().matches("^[a-zA-Z0-9]+$")
				&& user.getPassword().matches("^[a-zA-Z0-9]+$");
	}

	/*
	 * 記号チェック(その他)
	 */
	public boolean isOtherInputValid(Users user) {
		String lastName = user.getLastName().trim();
		String firstName = user.getFirstName().trim();
		String address = user.getAddress().trim();
		return lastName.matches("^[^<>\"'&]+$")
				&& firstName.matches("^[^<>\"'&]+$")
				&& address.matches("^[^<>\"'&]+$");
	}

	/*
	 * メール正規チェック
	 */
	public boolean isMailInputValid(Users user) {
		return user.getMailAddress() != null
				&& user.getMailAddress().matches("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$");

	}
}
