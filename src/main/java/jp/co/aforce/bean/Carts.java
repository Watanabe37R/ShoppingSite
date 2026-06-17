package jp.co.aforce.bean;

import java.io.Serializable;

public class Carts implements Serializable {
	private String productId;
	private String productName;
	private int price;
	private String imageUrl;
	private int quantity;

	public String getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public int getPrice() {
		return price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
