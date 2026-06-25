package jp.co.aforce.validator;

import java.util.ArrayList;
import java.util.List;

public class SearchValidator {
	public List<String> serarchValidate(String keyword) {
		List<String> errors=new ArrayList<>();
		//文字数チェック
		if (!keyword.matches("^[^<>\"'&]+$")) {
			errors.add("使用できない文字が含まれています。");
		}
		return errors;
	}
}
