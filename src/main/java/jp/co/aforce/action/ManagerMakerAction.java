package jp.co.aforce.action;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.Makers;
import jp.co.aforce.dao.MakerDAO;
import jp.co.aforce.validator.MasterValidator;

public class ManagerMakerAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mode = request.getParameter("mode");
		String id = request.getParameter("makerId");
		String name = request.getParameter("makerName");
		List<String> errors = new ArrayList<>();
		MasterValidator validate = new MasterValidator();
		if (mode.equals("create")) {

		} else if (mode.equals("edit") || mode.equals("delete")) {
			MakerDAO dao = new MakerDAO();
			try{
				name = dao.findById(id);
			}catch(Exception e) {
				e.printStackTrace();
				errors.add("システムエラーが発生しました。<br>操作をやり直してください");
				request.setAttribute("errors", errors);
				return "manager-error.jsp";
			}
			request.setAttribute("makerName", name);
		} else if (mode.equals("deleteExecute")) {
			MakerDAO dao = new MakerDAO();
			try {
				if (dao.delete(id) != 1) {
					errors.add("DBエラーが発生しました。<br>操作をやり直してください");
					request.setAttribute("errors", errors);
					return "manager-error.jsp";
				}
				response.sendRedirect(request.getContextPath() + "/ManagerMakerList.action?mode=view");
			} catch (Exception e) {
				e.printStackTrace();
				errors.add("システムエラーが発生しました。<br>操作をやり直してください");
				request.setAttribute("errors", errors);
				return "manager-error.jsp";
			}
		} else if (mode.equals("insert")) {
			MakerDAO dao = new MakerDAO();
			Makers maker = new Makers();
			maker.setMakerId(id);
			maker.setMakerName(name);
			//バリデーション
			errors = validate.validateIdAndName(id, name);
			//エラーがある場合エラー画面へ
			if (!errors.isEmpty()) {
				request.setAttribute("errors", errors);
				return "master-error.jsp";
			}
			try {
				if (dao.insert(maker) != 1) {
					errors.add("DBエラーが発生しました。<br>操作をやり直してください");
					request.setAttribute("errors", errors);
					return "manager-error.jsp";
				}
				response.sendRedirect(request.getContextPath() + "/ManagerMakerList.action?mode=view");
			} catch (Exception e) {
				e.printStackTrace();
				errors.add("システムエラーが発生しました。<br>操作をやり直してください");
				request.setAttribute("errors", errors);
				return "manager-error.jsp";
			}
		} else if (mode.equals("update")) {
			MakerDAO dao = new MakerDAO();
			//バリデーション
			errors = validate.validateIdAndName(id, name);
			//エラーがある場合エラー画面へ
			if (!errors.isEmpty()) {
				request.setAttribute("errors", errors);
				return "master-error.jsp";
			}
			try {
				if (dao.update(id, name) != 1) {
					errors.add("DBエラーが発生しました。<br>操作をやり直してください");
					request.setAttribute("errors", errors);
					return "manager-error.jsp";
				}
				response.sendRedirect(request.getContextPath() + "/ManagerMakerList.action?mode=view");
			} catch (Exception e) {
				e.printStackTrace();
				errors.add("システムエラーが発生しました。<br>操作をやり直してください");
				request.setAttribute("errors", errors);
				return "manager-error.jsp";
			}
		}
		request.setAttribute("mode", mode);
		request.setAttribute("makerId", id);

		return "managerMakerList-view.jsp";
	}

}
