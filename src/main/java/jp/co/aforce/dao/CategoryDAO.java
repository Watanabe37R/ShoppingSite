package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.bean.Categorys;

public class CategoryDAO extends DAO {

	public List<Categorys> findAll() throws Exception {
		List<Categorys> list = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT * FROM category");) {
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					Categorys category = new Categorys();
					category.setCategoryId(rs.getString("CATEGORY_ID"));
					category.setCategoryName(rs.getString("CATEGORY_NAME"));
					list.add(category);
				}
			}
		}
		return list;
	}

	public List<Categorys> findByKeyword(String keyword) throws Exception {
		List<Categorys> list = new ArrayList<>();
		boolean isKeyword = false;
		String SQL = "SELECT * FROM category WHERE 1=1";
		int index = 1;
		String[] keywords=null;
		if (keyword != null && !keyword.isEmpty()) {
			keywords = keyword.split(" ");
			SQL += " AND (";
			for (int i = 0; i < keywords.length; i++) {
				SQL += "CATEGORY_NAME LIKE ?";
				if (i < keywords.length - 1) {
					SQL += " OR ";
				}
			}
			SQL += ")";
			isKeyword = true;
		}
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(SQL);) {
			if (isKeyword) {
				for (String k : keywords) {
					ps.setString(index++, "%" + k + "%");
				}
			}
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					Categorys category = new Categorys();
					category.setCategoryId(rs.getString("CATEGORY_ID"));
					category.setCategoryName(rs.getString("CATEGORY_NAME"));
					list.add(category);
				}
			}
		}
		return list;
	}
	
	public String findById(String id) throws Exception {
		String name=null;
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT CATEGORY_NAME FROM category WHERE CATEGORY_ID=?");) {
			ps.setString(1, id);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					name=rs.getString("CATEGORY_NAME");
				}
			}
		}
		return name;
	}
	/*
	 * 登録処理
	 */
	public int insert(Categorys category)throws Exception{
		String sql="INSERT INTO category (CATEGORY_ID, CATEGORY_NAME) VALUES (?, ?)";
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);){

			ps.setString(1, category.getCategoryId());
			ps.setString(2, category.getCategoryName());

			return ps.executeUpdate();
		}
	}
	/*
	 * 更新処理
	 */
	public int update(String id, String name)throws Exception{
		String sql="UPDATE category SET CATEGORY_NAME = ? WHERE CATEGORY_ID = ?";
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);){

			ps.setString(1, name);
			ps.setString(2, id);

			return ps.executeUpdate();
		}
	}
	/*
	 * 削除処理
	 */
	public int delete(String id)throws Exception{
		String sql="DELETE FROM category WHERE CATEGORY_ID = ?";
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);){

			ps.setString(1, id);

			return ps.executeUpdate();
		}
	}
}
