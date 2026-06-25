package jp.co.aforce.validator;

import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.bean.Products;

public class MasterValidator {
	public boolean isIdInputValid(String id) {
		return id != null && (!id.matches("^[a-zA-Z0-9]+$") || id.length() > 10);
	}

	public boolean isRagioInputValid(int select) {
		if (select == 1 || select == 0) {
			return false;
		}
		return true;
	}

	public boolean isNameInputValid(String name) {
		return name != null && (name.matches(".*[<>\"'&].*") || name.length() > 32);
	}

	public boolean isAbstractInputValid(String name) {
		return name != null && (name.matches(".*[<>\"'&].*") || name.length() > 1000);
	}

	public boolean isImageURLInputValid(String name) {
		return name != null && (name.matches(".*[<>\"'&].*") || name.length() > 128);
	}

	public boolean isIntegerInputvalid(int i) {
		if (i < 0 || i > 2147483647) {
			return true;
		}
		return false;
	}

	public List<String> validateIdAndName(String id, String name) {
		List<String> errors = new ArrayList<>();
		boolean idError = id == null || id.isEmpty();
		boolean nameError = name == null || name.isEmpty();
		//String系nullチェック
		if (idError || nameError) {
			errors.add("必須項目に未入力があります。");
		}
		//ID入力値チェック(10文字以下＋英数)
		if (isIdInputValid(id)) {
			errors.add("使用できない文字が含まれているか、文字数規定違反です。<br>IDに入力できる文字は10文字以内の半角英数のみです。");
		}
		//Name入力チェック(32文字以下＋危険文字排除)
		if (isNameInputValid(name)) {
			errors.add("使用できない文字が含まれているか、文字数規定違反です。<br>名称に入力できる文字は32文字以内かつ一部の記号のみです。");
		}
		return errors;
	}

	public List<String> productValidate(Products product) {
		List<String> errors = new ArrayList<>();

		String productId = product.getProductId();
		String productName = product.getProductName();
		String makerId = product.getMakerId();
		int price = product.getPrice();
		int taxClass = product.getTaxClass();
		int tax = product.getTax();
		String categoryId = product.getCategoryId();
		String abstractText = product.getAbstractText();
		String imageUrl = product.getImageUrl();
		int stock = product.getStock();

		boolean productIdError = productId == null || productId.isEmpty();
		boolean productNameError = productName == null || productName.isEmpty();
		boolean makerIdError = makerId == null || makerId.isEmpty();
		boolean categoryIdError = categoryId == null || categoryId.isEmpty();

		//String系nullチェック
		if (productIdError || productNameError || makerIdError || categoryIdError) {
			errors.add("必須項目に未入力があります。");
		}
		//ID入力値チェック(10文字以下＋英数)
		if (isIdInputValid(productId) || isIdInputValid(makerId) || isIdInputValid(categoryId)) {
			errors.add("使用できない文字が含まれているか、文字数規定違反です。<br>各IDに入力できる文字は10文字以内の半角英数のみです。");
		}
		//Name入力チェック(32文字以下＋危険文字排除)
		if (isNameInputValid(productName)) {
			errors.add("使用できない文字が含まれているか、文字数規定違反です。<br>商品名に入力できる文字は32文字以内かつ一部の記号のみです。");
		}
		//Int入力チェック(0~2147483647)
		if (isIntegerInputvalid(price) || isIntegerInputvalid(stock)) {
			errors.add("価格、在庫に入力できるのは、0～2,147,483,647までの整数です。");
		}
		//課税区分
		if (isRagioInputValid(taxClass)) {
			errors.add("税率区分が不正です。");
		}
		//税
		if (tax < 0 || tax > 127) {
			errors.add("税率に入力できるのは、0～127までの整数です。");
		}
		//画像
		if (isImageURLInputValid(imageUrl)) {
			errors.add("使用できない文字が含まれているか、文字数規定違反です。<br>画像ファイルに指定できる文字は128文字以内かつ一部の記号のみです。");
		}
		//摘要
		if (isAbstractInputValid(abstractText)) {
			errors.add("使用できない文字が含まれているか、文字数規定違反です。<br>商品名に入力できる文字は1000文字以内かつ一部の記号のみです。");
		}
		return errors;
	}
}
