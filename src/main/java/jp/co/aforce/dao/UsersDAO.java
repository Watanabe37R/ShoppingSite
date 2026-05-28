package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.bean.Users;

public class UsersDAO extends DAO {
	public Users search(String id, String pw) throws Exception {
		Users loginUser = null;
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT MEMBER_ID, PASSWORD, MANAGER FROM users WHERE MEMBER_ID=? And PASSWORD=?");) {
			ps.setString(1, id);
			ps.setString(2, pw);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					loginUser = new Users();
					loginUser.setMemberId(rs.getString("MEMBER_ID"));
					loginUser.setPassword(rs.getString("PASSWORD"));
					loginUser.setManager(rs.getInt("MANAGER"));
					//一旦この３つ
				}
			}
		}
		return loginUser;
	}
	
	
}
