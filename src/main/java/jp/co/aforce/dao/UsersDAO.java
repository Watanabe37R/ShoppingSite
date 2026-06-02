package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.bean.Users;
import jp.co.aforce.tool.DuplicateResult;

/*
 *ID,PW整合性チェック。ログインに使用 
 *将来的にメアドでもチェックできるようにしてもいいかも
 */
public class UsersDAO extends DAO {
	public Users search(String id, String pw) throws Exception {
		Users loginUser = null;
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(
						"SELECT MEMBER_ID, PASSWORD, MANAGER, LAST_NAME FROM users WHERE MEMBER_ID=? And PASSWORD=?");) {
			ps.setString(1, id);
			ps.setString(2, pw);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					loginUser = new Users();
					loginUser.setMemberId(rs.getString("MEMBER_ID"));
					loginUser.setPassword(rs.getString("PASSWORD"));
					loginUser.setLastName(rs.getString("LAST_NAME"));
					loginUser.setManager(rs.getInt("MANAGER"));
					//一旦この４つ
				}
			}
		}
		return loginUser;
	}
	
	/*
	 * ID,PW重複チェック。登録、更新で使用
	 */
	public DuplicateResult check(String id, String mail) throws Exception {
		boolean duplicateID = false;
		boolean duplicateMail = false;
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(
						"SELECT MEMBER_ID, MAIL_ADDRESS FROM users WHERE MEMBER_ID = ? OR MAIL_ADDRESS = ?");) {
			ps.setString(1, id);
			ps.setString(2, mail);
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {					
					//IDが重複しているとき
					if (id.equals(rs.getString("MEMBER_ID"))) {
						duplicateID=true;
					}
					//メアドが重複しているとき
					if (mail.equals(rs.getString("MAIL_ADDRESS"))) {
						duplicateMail=true;
					}
				}
			}
		}
		//受け取り側でswitchする
		if(duplicateID&&duplicateMail) {
			return DuplicateResult.BOTH_DUPLICATE;
		}
		if(duplicateID) {
			return DuplicateResult.ID_DUPLICATE;
		}
		if(duplicateMail) {
			return DuplicateResult.MAIL_DUPLICATE;
		}
		return DuplicateResult.OK;
	}

}
