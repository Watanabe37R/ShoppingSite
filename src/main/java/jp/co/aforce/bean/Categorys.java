package jp.co.aforce.bean;

import java.io.Serializable;

public class Categorys implements Serializable {
	private String categoryId;
	private String categoryName;

	public String getCategoryId() {
		return categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
