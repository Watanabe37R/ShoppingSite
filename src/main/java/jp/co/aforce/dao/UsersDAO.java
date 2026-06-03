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
					//loginUser.setPassword(rs.getString("PASSWORD"));
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
						duplicateID = true;
					}
					//メアドが重複しているとき
					if (mail.equals(rs.getString("MAIL_ADDRESS"))) {
						duplicateMail = true;
					}
				}
			}
		}
		//受け取り側でswitchする
		if (duplicateID && duplicateMail) {
			return DuplicateResult.BOTH_DUPLICATE;
		}
		if (duplicateID) {
			return DuplicateResult.ID_DUPLICATE;
		}
		if (duplicateMail) {
			return DuplicateResult.MAIL_DUPLICATE;
		}
		return DuplicateResult.OK;
	}

	/*
	 * ユーザ情報登録処理。入力値を全てDBへ
	 * 但し、今はまだ一般ユーザ用。
	 */
	public int registration(Users setUser) throws Exception {
		int line = 0;

		String id = setUser.getMemberId();
		String pw = setUser.getPassword();
		String lname = setUser.getLastName();
		String fname = setUser.getFirstName();
		String address = setUser.getAddress();
		String mail = setUser.getMailAddress();

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(
						"INSERT INTO users(MEMBER_ID,PASSWORD,LAST_NAME,FIRST_NAME,ADDRESS,MAIL_ADDRESS,MANAGER) VALUES (?, ?, ?, ?, ?, ?, 0)");) {
			ps.setString(1, id);
			ps.setString(2, pw);
			ps.setString(3, lname);
			ps.setString(4, fname);
			ps.setString(5, address);
			ps.setString(6, mail);

			line = ps.executeUpdate();
		}

		return line;
	}

	/*
	 * IDに紐づく全カラムデータ取得。キーはユーザID。更新や削除で利用。
	 */
	public Users findById(String id) throws Exception {
		Users userInfo = new Users();
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE MEMBER_ID=?");) {
			ps.setString(1, id);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					userInfo.setPassword(rs.getString("PASSWORD"));
					userInfo.setLastName(rs.getString("LAST_NAME"));
					userInfo.setFirstName(rs.getString("FIRST_NAME"));
					userInfo.setAddress(rs.getString("ADDRESS"));
					userInfo.setMailAddress(rs.getString("MAIL_ADDRESS"));
					userInfo.setManager(rs.getInt("MANAGER"));
				}
			}
		}
		return userInfo;
	}
	
	/*
	 * IDに紐づく基本情報の修正(更新)。キーはユーザID。
	 */
	public int update(String id,Users users) throws Exception{
		int line=0;
		try(Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement("UPDATE users SET LAST_NAME=?,FIRST_NAME=?,ADDRESS=? WHERE MEMBER_ID=?");){
			String lname=users.getLastName();
			String fname=users.getFirstName();
			String address=users.getAddress();
			ps.setString(1, lname);
			ps.setString(2, fname);
			ps.setString(3, address);
			ps.setString(4, id);
			line =ps.executeUpdate();
		}
		return line;
	}
	
	/*
	 * IDに紐づくアカウント情報の削除(DELETE)。キーはユーザID。
	 */
	public int delete(String id) throws Exception{
		int line=0;
		try(Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement("DELETE FROM users WHERE MEMBER_ID=?");){
			ps.setString(1, id);
			line =ps.executeUpdate();
		}
		return line;
	}
}
