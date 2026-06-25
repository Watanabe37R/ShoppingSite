package jp.co.aforce.action;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.Notices;
import jp.co.aforce.dao.NoticeDAO;

public class ManagerNoticeListAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		NoticeDAO dao = new NoticeDAO();
		List<Notices> list = new ArrayList<>();
		List<String> errors = new ArrayList<>();
		try {
			list = dao.findAll();
			request.setAttribute("noticeList", list);
			return "managernoticeList-view.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			errors.add("システムエラーが発生しました。<br>操作をやり直してください。");
			return "master-error.jsp";
		}
	}
}
