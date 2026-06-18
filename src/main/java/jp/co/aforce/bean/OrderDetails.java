package jp.co.aforce.bean;

import java.io.Serializable;

public class OrderDetails implements Serializable {
	private int orderId;
	private String productId;
	private String productName;
	private int quantity;
	private int price;
	private String imageUrl;

	public int getOrderId() {
		return orderId;
	}

	public String getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getPrice() {
		return price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
