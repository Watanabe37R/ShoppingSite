package jp.co.aforce.action;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.Notices;
import jp.co.aforce.dao.NoticeDAO;
import jp.co.aforce.validator.MasterValidator;

public class ManagerNoticeAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mode = request.getParameter("mode");
		String idStr = request.getParameter("id");
		System.out.println(mode);
		int id = 0;
		if (idStr != null && !idStr.isEmpty()) {
			id = Integer.parseInt(idStr);
		}
		List<String> errors = new ArrayList<>();
		MasterValidator validate = new MasterValidator();
		NoticeDAO dao = new NoticeDAO();
		Notices notice = new Notices();

		if ("edit".equals(mode) || "delete".equals(mode) || "view".equals(mode)) {
			try {
				notice = dao.findById(id);
				request.setAttribute("notice", notice);
			} catch (Exception e) {
				e.printStackTrace();
				errors.add("システムエラーが発生しました。<br>操作をやり直してください。");
				return "master-error.jsp";
			}
		}
		request.setAttribute("mode", mode);
		return "managerNotice-in.jsp";
	}

}
