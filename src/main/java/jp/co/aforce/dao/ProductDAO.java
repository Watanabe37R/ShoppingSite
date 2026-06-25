package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.bean.Products;

public class ProductDAO extends DAO {

	public List<Products> findByKeyWord(String categoryId, String keyword, String order) throws Exception {
		List<Products> list = new ArrayList<>();

		boolean isCategory = false;
		boolean isKeyword = false;
		int index = 1;

		String sql = "SELECT p.*, m.MAKER_NAME, c.CATEGORY_NAME, " +
				"AVG(e.RATING) AS avg_rating, COUNT(e.RATING) AS review_count " +
				"FROM product p " +
				"JOIN maker m ON p.MAKER_ID = m.MAKER_ID " +
				"JOIN category c ON p.CATEGORY_ID = c.CATEGORY_ID " +
				"LEFT JOIN evaluation e ON p.PRODUCT_ID = e.PRODUCT_ID WHERE 1=1 ";

		if (categoryId != null && !categoryId.isEmpty()) {
			sql += "AND p.CATEGORY_ID = ? ";
			isCategory = true;
		}

		if (keyword != null && !keyword.isEmpty()) {
			String[] keywords = keyword.split(" ");
			for (String k : keywords) {
				sql += "AND (p.PRODUCT_NAME LIKE ? OR p.ABSTRACT LIKE ?) ";
			}
			isKeyword = true;
		}

		sql += "GROUP BY p.PRODUCT_ID ";

		if (order != null && !order.isEmpty()) {

			if ("lowprice".equals(order)) {
				sql += "ORDER BY p.PRICE ASC ";
			} else if ("highprice".equals(order)) {
				sql += "ORDER BY p.PRICE DESC ";
			} else if ("new".equals(order)) {
				sql += "ORDER BY p.CREATE_DATE DESC ";
			} else if ("evaluation".equals(order)) {
				sql += "ORDER BY avg_rating DESC ";
			}

		}

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			if (isCategory) {
				ps.setString(index++, categoryId);
			}
			if (isKeyword) {
				String[] keywords = keyword.split(" ");

				for (String k : keywords) {
					ps.setString(index++, "%" + k + "%");
					ps.setString(index++, "%" + k + "%");
				}
			}
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					Products productItems = new Products();
					productItems.setProductId(rs.getString("PRODUCT_ID"));
					productItems.setProductName(rs.getString("PRODUCT_NAME"));
					productItems.setMakerId(rs.getString("MAKER_ID"));
					productItems.setMakerName(rs.getString("MAKER_NAME"));
					productItems.setPrice(rs.getInt("PRICE"));
					productItems.setTaxClass(rs.getInt("TAX_CLASS"));
					productItems.setTax(rs.getInt("TAX"));
					productItems.setCategoryId(rs.getString("CATEGORY_ID"));
					productItems.setCategoryName(rs.getString("CATEGORY_NAME"));
					productItems.setAbstractText(rs.getString("ABSTRACT"));
					productItems.setImageUrl(rs.getString("IMAGE_URL"));
					productItems.setOnSale(rs.getBoolean("ON_SALE"));
					productItems.setStock(rs.getInt("STOCK"));

					//日付変換
					productItems.setCreateDate(
							rs.getTimestamp("CREATE_DATE").toLocalDateTime());
					productItems.setUpdateDate(
							rs.getTimestamp("UPDATE_DATE").toLocalDateTime());

					//評価がない場合
					double avg = rs.getDouble("avg_rating");
					if (rs.wasNull()) {
						avg = 0.0;
					}
					productItems.setAvgRating(avg);
					productItems.setReviewCount(rs.getInt("review_count"));

					list.add(productItems);
				}

			}
		}
		return list;
	}

	public String findById(String id) throws Exception {
		String categoryName = null;
		try (Connection con = getConnection();
				PreparedStatement ps = con
						.prepareStatement("SELECT CATEGORY_NAME FROM category WHERE CATEGORY_ID=?");) {
			ps.setString(1, id);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					categoryName = rs.getString("CATEGORY_NAME");
				}
			}
		}

		return categoryName;
	}

	public Products findProductInfoById(String productId) throws Exception {

		Products product = null;

		String sql = "SELECT p.*, m.MAKER_NAME, c.CATEGORY_NAME, " +
				"AVG(e.RATING) AS avg_rating, COUNT(e.RATING) AS review_count " +
				"FROM product p " +
				"JOIN maker m ON p.MAKER_ID = m.MAKER_ID " +
				"JOIN category c ON p.CATEGORY_ID = c.CATEGORY_ID " +
				"LEFT JOIN evaluation e ON p.PRODUCT_ID = e.PRODUCT_ID " +
				"WHERE p.PRODUCT_ID = ? " +
				"GROUP BY p.PRODUCT_ID, m.MAKER_NAME, c.CATEGORY_NAME";

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, productId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
				    product = new Products();
				    product.setProductId(rs.getString("PRODUCT_ID"));
				    product.setProductName(rs.getString("PRODUCT_NAME"));
				    product.setMakerId(rs.getString("MAKER_ID"));
				    product.setMakerName(rs.getString("MAKER_NAME"));
				    product.setPrice(rs.getInt("PRICE"));
				    product.setTaxClass(rs.getInt("TAX_CLASS"));
				    product.setTax(rs.getInt("TAX"));
				    product.setCategoryId(rs.getString("CATEGORY_ID"));
				    product.setCategoryName(rs.getString("CATEGORY_NAME"));
				    product.setAbstractText(rs.getString("ABSTRACT"));
				    product.setImageUrl(rs.getString("IMAGE_URL"));
				    product.setOnSale(rs.getBoolean("ON_SALE"));
				    product.setStock(rs.getInt("STOCK"));

				    product.setCreateDate(
				        rs.getTimestamp("CREATE_DATE").toLocalDateTime());
				    product.setUpdateDate(
				        rs.getTimestamp("UPDATE_DATE").toLocalDateTime());

				    // 評価
				    double avg = rs.getDouble("avg_rating");
				    if (rs.wasNull()) {
				        avg = 0.0;
				    }
				    product.setAvgRating(avg);

				    product.setReviewCount(rs.getInt("review_count"));
				}
			}
		}
		return product;
	}

	public Products findProductInfoByIdForManager(String productId) throws Exception {

		Products product = null;
		String sql = """
				SELECT p.*, m.MAKER_NAME, c.CATEGORY_NAME
				FROM product p
				JOIN maker m ON p.MAKER_ID = m.MAKER_ID
				JOIN category c ON p.CATEGORY_ID = c.CATEGORY_ID
				WHERE p.PRODUCT_ID = ?
								""";

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, productId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					product = new Products();
					product.setProductId(rs.getString("PRODUCT_ID"));
					product.setProductName(rs.getString("PRODUCT_NAME"));
					product.setPrice(rs.getInt("PRICE"));
					product.setMakerId(rs.getString("MAKER_ID"));
					product.setMakerName(rs.getString("MAKER_NAME"));
					product.setTaxClass(rs.getInt("TAX_CLASS"));
					product.setTax(rs.getInt("TAX"));
					product.setCategoryId(rs.getString("CATEGORY_ID"));
					product.setCategoryName(rs.getString("CATEGORY_NAME"));
					product.setImageUrl(rs.getString("IMAGE_URL"));
					product.setOnSale(rs.getInt("ON_SALE") == 1);
					product.setStock(rs.getInt("STOCK"));
					product.setAbstractText(rs.getString("ABSTRACT"));
				}
			}
		}
		return product;
	}

	public void insertProduct(Products product) throws Exception {

		String sql = "INSERT INTO product (" +
				"PRODUCT_ID, PRODUCT_NAME, MAKER_ID, PRICE, " +
				"TAX_CLASS, TAX, CATEGORY_ID, IMAGE_URL, " +
				"ON_SALE, STOCK, ABSTRACT" +
				") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {

			int i = 1;

			ps.setString(i++, product.getProductId());
			ps.setString(i++, product.getProductName());
			ps.setString(i++, product.getMakerId());
			ps.setInt(i++, product.getPrice());
			ps.setInt(i++, product.getTaxClass());
			ps.setInt(i++, product.getTax());
			ps.setString(i++, product.getCategoryId());
			ps.setString(i++, product.getImageUrl());
			ps.setInt(i++, product.isOnSale() ? 1 : 0);
			ps.setInt(i++, product.getStock());
			ps.setString(i++, product.getAbstractText());

			ps.executeUpdate();
		}
	}

	public void updateProduct(Products product) throws Exception {

		String sql = "UPDATE product SET " +
				"PRODUCT_NAME = ?, MAKER_ID = ?, PRICE = ?, " +
				"TAX_CLASS = ?, TAX = ?, CATEGORY_ID = ?, " +
				"IMAGE_URL = ?, ON_SALE = ?, STOCK = ?, ABSTRACT = ? " +
				"WHERE PRODUCT_ID = ?";

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {

			int i = 1;

			ps.setString(i++, product.getProductName());
			ps.setString(i++, product.getMakerId());
			ps.setInt(i++, product.getPrice());
			ps.setInt(i++, product.getTaxClass());
			ps.setInt(i++, product.getTax());
			ps.setString(i++, product.getCategoryId());
			ps.setString(i++, product.getImageUrl());
			ps.setInt(i++, product.isOnSale() ? 1 : 0);
			ps.setInt(i++, product.getStock());
			ps.setString(i++, product.getAbstractText());

			ps.setString(i++, product.getProductId());

			ps.executeUpdate();
		}
	}

	public void deleteProduct(String productId) throws Exception {

		String sql = "DELETE FROM product WHERE PRODUCT_ID = ?";

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, productId);

			ps.executeUpdate();
		}
	}

	public boolean check(String id) throws Exception {
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(
						"SELECT PRODUCT_ID FROM product WHERE PRODUCT_ID = ?");) {
			ps.setString(1, id);
			try (ResultSet rs = ps.executeQuery();) {
				return rs.next();
			}
		}
	}
}
