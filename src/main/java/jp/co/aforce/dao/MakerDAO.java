package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.bean.Makers;

public class MakerDAO extends DAO {
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
}
