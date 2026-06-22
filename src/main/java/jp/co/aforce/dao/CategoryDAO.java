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
}
