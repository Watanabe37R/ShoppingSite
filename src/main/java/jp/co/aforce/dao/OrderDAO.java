package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.bean.Carts;
import jp.co.aforce.bean.OrderDetails;

public class OrderDAO extends DAO {

	public int createOrder(String memberId) throws Exception {

		String sql = "INSERT INTO orders (MEMBER_ID, STATUS) VALUES (?, ?)";

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, memberId);
			ps.setInt(2, 0); // 初期ステータス
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {
				return rs.getInt(1); // ← ORDER_ID取得
			}
		}
		return -1; // 異常
	}

	public void insertOrderDetail(int orderId, Carts item) throws Exception {

		String sql = "INSERT INTO order_detail (ORDER_ID, PRODUCT_ID, QUANTITY, PRICE) VALUES (?, ?, ?, ?)";

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, orderId);
			ps.setString(2, item.getProductId());
			ps.setInt(3, item.getQuantity());
			ps.setInt(4, item.getPrice());

			ps.executeUpdate();
		}
	}
	/*
	 * 購入商品取得
	 */
	public List<OrderDetails> getOrderDetail(int oID, String uID) throws Exception {
		List<OrderDetails> list = new ArrayList<>();

		String sql = """
				    SELECT
				        od.PRODUCT_ID,
				        p.PRODUCT_NAME,
				        od.QUANTITY,
				        od.PRICE,
				        p.IMAGE_URL
				    FROM order_detail od
				    JOIN orders o ON od.ORDER_ID = o.ORDER_ID
				    JOIN product p ON od.PRODUCT_ID = p.PRODUCT_ID
				    WHERE od.ORDER_ID = ? AND o.MEMBER_ID = ?
				""";

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, oID);
			ps.setString(2, uID);

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					OrderDetails item = new OrderDetails();

					item.setProductId(rs.getString("PRODUCT_ID"));
					item.setProductName(rs.getString("PRODUCT_NAME"));
					item.setQuantity(rs.getInt("QUANTITY"));
					item.setPrice(rs.getInt("PRICE"));
					item.setImageUrl(rs.getString("IMAGE_URL"));

					list.add(item);
				}
			}
		}
		return list;
	}
}
