package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.bean.Categorys;

public class CategoryDAO extends DAO {

	public List<Categorys> findAll()throws Exception {
		List<Categorys> list =new ArrayList<>();
		try(Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT * FROM category");){
			try(ResultSet rs = ps.executeQuery();){
				while (rs.next()){
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
