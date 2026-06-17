package jp.co.aforce.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Products implements Serializable {
	private String productId;
	private String productName;
	private String makerId;
	private String makerName;
	private int price;
	private int taxClass;
	private int tax;
	private String categoryId;
	private String categoryName;
	private String abstractText;
	private String imageUrl;
	private boolean isOnSale;
	private int stock;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	private double avgRating;
	private int reviewCount;
	private int quantity;

	public String getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public String getMakerId() {
		return makerId;
	}

	public String getMakerName() {
		return makerName;
	}

	public int getPrice() {
		return price;
	}

	public int getTaxClass() {
		return taxClass;
	}

	public int getTax() {
		return tax;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public String getAbstractText() {
		return abstractText;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public boolean isOnSale() {
		return isOnSale;
	}

	public int getStock() {
		return stock;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public double getAvgRating() {
		return avgRating;
	}

	public int getReviewCount() {
		return reviewCount;
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

	public void setMakerId(String makerId) {
		this.makerId = makerId;
	}

	public void setMakerName(String makerName) {
		this.makerName = makerName;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setTaxClass(int taxClass) {
		this.taxClass = taxClass;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setAbstractText(String abstractText) {
		this.abstractText = abstractText;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setOnSale(boolean onSale) {
		this.isOnSale = onSale;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
