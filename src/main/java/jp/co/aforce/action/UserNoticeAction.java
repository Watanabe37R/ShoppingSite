package jp.co.aforce.action;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.Notices;
import jp.co.aforce.dao.NoticeDAO;

public class UserNoticeAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String idStr = request.getParameter("id");
		int id = 0;
		List<String> errors = new ArrayList<>();
		if (idStr == null || idStr.isEmpty()) {
			errors.add("入力値がnullです。");
		} else {
			try {
				id = Integer.parseInt(idStr);
			} catch (NumberFormatException e) {
				errors.add("入力値が数値ではありません。");
			}
		}
		//エラーがある場合エラー画面へ
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			return "user-error.jsp";
		}
		NoticeDAO dao = new NoticeDAO();
		Notices notice = new Notices();
		try {
			notice = dao.findById(id);
			request.setAttribute("notice", notice);
			return "userNotice-view.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			errors.add("DBエラーです。<br>操作をやり直してください。");
			request.setAttribute("errors", errors);
			return "user-error.jsp";
		}
	}
}
