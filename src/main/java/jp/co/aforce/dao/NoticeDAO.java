package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.bean.Notices;

public class NoticeDAO extends DAO {
	public List<Notices> findAllForCustomer() throws Exception {
		List<Notices> list = new ArrayList<>();
		String SQL = """
				SELECT * FROM notice
				WHERE DISPLAY_FLAG = 1
				AND (START_DATE IS NULL OR START_DATE <= NOW())
				AND (END_DATE IS NULL OR END_DATE >= NOW())
				ORDER BY
				CASE
				  WHEN NOTICE_TYPE = '重要' THEN 1
				  WHEN NOTICE_TYPE = 'キャンペーン' THEN 2
				  ELSE 3
				END,
				CREATE_DATE DESC
								""";
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(SQL);) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Notices notice = new Notices();
					notice.setId(rs.getInt("NOTICE_ID"));
					notice.setTitle(rs.getString("TITLE"));
					notice.setContent(rs.getString("CONTENT"));
					notice.setNoticeType(rs.getString("NOTICE_TYPE"));
					String displayStr = (rs.getString("DISPLAY_FLAG"));
					notice.setDisplay(Integer.parseInt(displayStr));
					notice.setStart(
							rs.getTimestamp("START_DATE") != null
									? rs.getTimestamp("START_DATE").toLocalDateTime()
									: null);
					notice.setEnd(
							rs.getTimestamp("END_DATE") != null
									? rs.getTimestamp("END_DATE").toLocalDateTime()
									: null);
					notice.setCreate(
							rs.getTimestamp("CREATE_DATE").toLocalDateTime());
					notice.setUpdate(
							rs.getTimestamp("UPDATE_DATE").toLocalDateTime());
					list.add(notice);
				}
			}
		}
		return list;
	}


	public List<Notices> findAll() throws Exception {
		List<Notices> list = new ArrayList<>();
		String SQL = """
				SELECT * FROM notice
				ORDER BY
				CASE
				  WHEN NOTICE_TYPE = '重要' THEN 1
				  WHEN NOTICE_TYPE = 'キャンペーン' THEN 2
				  ELSE 3
				END,
				CREATE_DATE DESC
								""";
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(SQL);) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Notices notice = new Notices();
					notice.setId(rs.getInt("NOTICE_ID"));
					notice.setTitle(rs.getString("TITLE"));
					notice.setContent(rs.getString("CONTENT"));
					notice.setNoticeType(rs.getString("NOTICE_TYPE"));
					String displayStr = (rs.getString("DISPLAY_FLAG"));
					notice.setDisplay(Integer.parseInt(displayStr));
					notice.setStart(
							rs.getTimestamp("START_DATE") != null
									? rs.getTimestamp("START_DATE").toLocalDateTime()
									: null);
					notice.setEnd(
							rs.getTimestamp("END_DATE") != null
									? rs.getTimestamp("END_DATE").toLocalDateTime()
									: null);
					notice.setCreate(
							rs.getTimestamp("CREATE_DATE").toLocalDateTime());
					notice.setUpdate(
							rs.getTimestamp("UPDATE_DATE").toLocalDateTime());
					list.add(notice);
				}
			}
		}
		return list;
	}
	
	public Notices findById(int id) throws Exception {
		Notices notice = new Notices();
		String SQL = """
				SELECT * FROM notice
				WHERE NOTICE_ID = ?
								""";
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(SQL);) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					notice.setId(rs.getInt("NOTICE_ID"));
					notice.setTitle(rs.getString("TITLE"));
					notice.setContent(rs.getString("CONTENT"));
					notice.setNoticeType(rs.getString("NOTICE_TYPE"));
					String displayStr = (rs.getString("DISPLAY_FLAG"));
					notice.setDisplay(Integer.parseInt(displayStr));
					notice.setStart(
							rs.getTimestamp("START_DATE") != null
									? rs.getTimestamp("START_DATE").toLocalDateTime()
									: null);
					notice.setEnd(
							rs.getTimestamp("END_DATE") != null
									? rs.getTimestamp("END_DATE").toLocalDateTime()
									: null);
					notice.setCreate(
							rs.getTimestamp("CREATE_DATE").toLocalDateTime());
					notice.setUpdate(
							rs.getTimestamp("UPDATE_DATE").toLocalDateTime());

					if (notice.getStart() != null) {
						notice.setStartStr(notice.getStart().toLocalDate().toString());
					}

					if (notice.getEnd() != null) {
						notice.setEndStr(notice.getEnd().toLocalDate().toString());

					}
					if (notice.getCreate() != null) {
						notice.setCreateStr(notice.getCreate().toLocalDate().toString());
					}

					if (notice.getUpdate() != null) {
						notice.setUpdateStr(notice.getUpdate().toLocalDate().toString());

					}

				}
			}
		}
		return notice;
	}

	public int insert(Notices notice) throws Exception {
		String sql = """
				    INSERT INTO notice
				    (TITLE, CONTENT, NOTICE_TYPE, DISPLAY_FLAG, START_DATE, END_DATE)
				    VALUES (?, ?, ?, ?, ?, ?)
				""";
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			ps.setString(1, notice.getTitle());
			ps.setString(2, notice.getContent());
			ps.setString(3, notice.getNoticeType());
			ps.setInt(4, notice.getDisplay());
			ps.setTimestamp(5, notice.getStart() != null ? Timestamp.valueOf(notice.getStart()) : null);
			ps.setTimestamp(6, notice.getEnd() != null ? Timestamp.valueOf(notice.getEnd()) : null);

			ps.executeUpdate();

			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		}
		return 0;

	}

	public int update(Notices notice) throws Exception {
		String sql = """
				    UPDATE notice
				    SET TITLE = ?, CONTENT = ?, NOTICE_TYPE = ?, DISPLAY_FLAG = ?, START_DATE = ?, END_DATE = ?
				    WHERE NOTICE_ID = ?
				""";
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, notice.getTitle());
			ps.setString(2, notice.getContent());
			ps.setString(3, notice.getNoticeType());
			ps.setInt(4, notice.getDisplay());
			ps.setTimestamp(5, notice.getStart() != null ? Timestamp.valueOf(notice.getStart()) : null);
			ps.setTimestamp(6, notice.getEnd() != null ? Timestamp.valueOf(notice.getEnd()) : null);
			ps.setInt(7, notice.getId());

			return ps.executeUpdate();
		}
	}

	public int delete(int id) throws Exception {
		String sql = "DELETE FROM notice WHERE NOTICE_ID = ?";
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);

			return ps.executeUpdate();
		}
	}
}
