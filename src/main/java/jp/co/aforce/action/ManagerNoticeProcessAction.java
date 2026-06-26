package jp.co.aforce.action;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.Notices;
import jp.co.aforce.dao.NoticeDAO;
import jp.co.aforce.validator.MasterValidator;

public class ManagerNoticeProcessAction extends Action {

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
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String noticeType = request.getParameter("noticeType");
		String displyayStr = request.getParameter("display");
		int display = 0;
		if (displyayStr != null && !displyayStr.isEmpty()) {
			display = Integer.parseInt(displyayStr);
		}
		String startStr = request.getParameter("start");
		LocalDateTime start = null;
		if (startStr != null && !startStr.isEmpty()) {
			start = LocalDate.parse(startStr).atStartOfDay();
		}
		String endStr = request.getParameter("end");
		LocalDateTime end = null;
		if (endStr != null && !endStr.isEmpty()) {
			end = LocalDate.parse(endStr).atTime(23, 59, 59);
		}
		notice.setTitle(title);
		notice.setContent(content);
		notice.setNoticeType(noticeType);
		notice.setDisplay(display);
		notice.setStart(start);
		notice.setEnd(end);
		if ("imsert".equals(mode) || "update".equals(mode)) {
			//バリデーション
			errors = validate.noticeValidate(notice);
			//エラーがある場合エラー画面へ
			if (!errors.isEmpty()) {
				request.setAttribute("errors", errors);
				return "master-error.jsp";
			}
		}
		if ("insert".equals(mode)) {
			try {
				int newId = dao.insert(notice);
				if (newId == 0) {
					errors.add("DBエラーが発生しました。<br>操作をやり直してください。");
					request.setAttribute("errors", errors);
					return "master-error.jsp";
				}
				response.sendRedirect("ManagerNotice.action?mode=view&id=" + newId);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				errors.add("システムエラーが発生しました。<br>操作をやり直してください。");
				request.setAttribute("errors", errors);
				return "master-error.jsp";
			}
		} else if ("update".equals(mode)) {
			notice.setId(id);
			System.out.println("あぷで");
			try {
				if (dao.update(notice) != 1) {
					errors.add("DBエラーが発生しました。<br>操作をやり直してください。");
					request.setAttribute("errors", errors);
					return "master-error.jsp";
				}
				response.sendRedirect("ManagerNotice.action?mode=view&id=" + id);
				return null;

			} catch (Exception e) {
				e.printStackTrace();
				errors.add("システムエラーが発生しました。<br>操作をやり直してください。");
				request.setAttribute("errors", errors);
				return "master-error.jsp";
			}
		} else if ("deleteExecute".equals(mode)) {
			try {
				if (dao.delete(id) != 1) {
					errors.add("DBエラーが発生しました。<br>操作をやり直してください。");
					request.setAttribute("errors", errors);
					return "master-error.jsp";
				}
				response.sendRedirect("ManagerNoticeList.action");
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				errors.add("システムエラーが発生しました。<br>操作をやり直してください。");
				request.setAttribute("errors", errors);
				return "master-error.jsp";
			}
		}
		response.sendRedirect("ManagerNoticezList.action");
		return null;
	}

}
