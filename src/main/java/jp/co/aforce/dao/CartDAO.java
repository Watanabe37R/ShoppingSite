package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.bean.Carts;

public class CartDAO extends DAO {

	/*
	 * カートに追加
	 */
	public void upsertCart(String uID, String pID, int quantity) throws Exception {
		//チェックSQL
		String checkSQL = "SELECT QUANTITY FROM cart WHERE MEMBER_ID=? AND PRODUCT_ID=?";
		//新規用SQL
		String insertSQL = "INSERT INTO cart (MEMBER_ID, PRODUCT_ID, QUANTITY) VALUES (?, ?, ?)";
		//更新用SQL
		String updateSql = "UPDATE cart SET QUANTITY=? WHERE MEMBER_ID=? AND PRODUCT_ID=?";

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(checkSQL);) {
			ps.setString(1, uID);
			ps.setString(2, pID);
			//既存チェック
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					//既にある時はUPDATE
					try (PreparedStatement psupd = con.prepareStatement(updateSql);) {
						psupd.setInt(1, quantity);
						psupd.setString(2, uID);
						psupd.setString(3, pID);
						psupd.executeUpdate();
					}
				} else {
					//ないとき
					try (PreparedStatement psins = con.prepareStatement(insertSQL);) {
						psins.setString(1, uID);
						psins.setString(2, pID);
						psins.setInt(3, quantity);
						psins.executeUpdate();
					}
				}
			}
		}
	}

	/*
	 * カートの中身の取得
	 */
	public List<Carts> getCartByUser(String uID) throws Exception {
		List<Carts> list = new ArrayList<>();
		String sql = """
				SELECT
				p.PRODUCT_ID,
				p.PRODUCT_NAME,
				p.PRICE,
				p.IMAGE_URL,
				c.QUANTITY
				FROM cart c
				JOIN product p ON c.PRODUCT_ID = p.PRODUCT_ID
				WHERE c.MEMBER_ID = ?
						    """;

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, uID);
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					Carts item = new Carts();
					item.setProductId(rs.getString("PRODUCT_ID"));
					item.setProductName(rs.getString("PRODUCT_NAME"));
					item.setPrice(rs.getInt("PRICE"));
					item.setImageUrl(rs.getString("IMAGE_URL"));
					item.setQuantity(rs.getInt("QUANTITY"));

					list.add(item);
				}
			}
		}
		return list;
	}

	/*
	 * カート内アイテム数量変更
	 */
	public int updateCart(String uID, String pID, int quantity) throws Exception {
		//更新用SQL
		String sql = "UPDATE cart SET QUANTITY=? WHERE MEMBER_ID=? AND PRODUCT_ID=?";
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, quantity);
			ps.setString(2, uID);
			ps.setString(3, pID);
			int count = ps.executeUpdate();
			return count;
		}
	}

	/*
	 * カート内アイテム削除
	 */
	public int deleteCart(String uID, String pID) throws Exception {
		//削除用SQL
		String sql = "DELETE FROM cart WHERE MEMBER_ID=? AND PRODUCT_ID=?";

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, uID);
			ps.setString(2, pID);
			int count = ps.executeUpdate();
			return count;
		}
	}
	
	/*
	 * カート内自体削除
	 */
	public int clearCart(String uID) throws Exception {
		//削除用SQL
		String sql = "DELETE FROM cart WHERE MEMBER_ID=?";

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, uID);
			int count = ps.executeUpdate();
			return count;
		}
	}
	
}
