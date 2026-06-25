package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.bean.Makers;

public class MakerDAO extends DAO {
	public List<Makers> findAll() throws Exception {
		List<Makers> list = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT * FROM maker");) {
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					Makers maker = new Makers();
					maker.setMakerId(rs.getString("MAKER_ID"));
					maker.setMakerName(rs.getString("MAKER_NAME"));
					list.add(maker);
				}
			}
		}
		return list;
	}
	
	public List<Makers> findByKeyword(String keyword) throws Exception {
		List<Makers> list = new ArrayList<>();
		boolean isKeyword = false;
		String SQL = "SELECT * FROM maker WHERE 1=1";
		int index = 1;
		String[] keywords=null;
		if (keyword != null && !keyword.isEmpty()) {
			keywords = keyword.split(" ");
			SQL += " AND (";
			for (int i = 0; i < keywords.length; i++) {
				SQL += "MAKER_NAME LIKE ?";
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
					Makers maker = new Makers();
					maker.setMakerId(rs.getString("MAKER_ID"));
					maker.setMakerName(rs.getString("MAKER_NAME"));
					list.add(maker);
				}
			}
		}
		return list;
	}
	
	public String findById(String id) throws Exception {
		String name=null;
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT MAKER_NAME FROM maker WHERE MAKER_ID=?");) {
			ps.setString(1, id);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					name=rs.getString("MAKER_NAME");
				}
			}
		}
		return name;
	}
	/*
	 * 登録処理
	 */
	public int insert(Makers maker)throws Exception{
		String sql="INSERT INTO maker (MAKER_ID, MAKER_NAME) VALUES (?, ?)";
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);){

			ps.setString(1, maker.getMakerId());
			ps.setString(2, maker.getMakerName());

			return ps.executeUpdate();
		}
	}
	/*
	 * 更新処理
	 */
	public int update(String id, String name)throws Exception{
		String sql="UPDATE maker SET MAKER_NAME = ? WHERE MAKER_ID = ?";
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
		String sql="DELETE FROM maker WHERE MAKER_ID = ?";
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);){

			ps.setString(1, id);

			return ps.executeUpdate();
		}
	}
}
