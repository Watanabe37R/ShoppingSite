package jp.co.aforce.action;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.Categorys;
import jp.co.aforce.dao.CategoryDAO;
import jp.co.aforce.validator.MasterValidator;

public class ManagerCategoryAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mode = request.getParameter("mode");
		String id = request.getParameter("categoryId");
		String name = request.getParameter("categoryName");
		List<String> errors = new ArrayList<>();
		MasterValidator validate = new MasterValidator();
		if (mode.equals("create")) {

		} else if (mode.equals("edit") || mode.equals("delete")) {
			CategoryDAO dao = new CategoryDAO();
			try{
				name = dao.findById(id);
			}catch(Exception e) {
				e.printStackTrace();
				errors.add("システムエラーが発生しました。<br>操作をやり直してください");
				request.setAttribute("errors", errors);
				return "manager-error.jsp";
			}
			request.setAttribute("categoryName", name);
		} else if (mode.equals("deleteExecute")) {
			CategoryDAO dao = new CategoryDAO();
			try {
				if (dao.delete(id) != 1) {
					errors.add("DBエラーが発生しました。<br>操作をやり直してください");
					request.setAttribute("errors", errors);
					return "manager-error.jsp";
				}
				response.sendRedirect(request.getContextPath() + "/ManagerCategoryList.action?mode=view");
			} catch (Exception e) {
				e.printStackTrace();
				errors.add("システムエラーが発生しました。<br>操作をやり直してください");
				request.setAttribute("errors", errors);
				return "manager-error.jsp";
			}
		} else if (mode.equals("insert")) {
			CategoryDAO dao = new CategoryDAO();
			Categorys category = new Categorys();
			category.setCategoryId(id);
			category.setCategoryName(name);
			//バリデーション
			errors = validate.validateIdAndName(id, name);
			//エラーがある場合エラー画面へ
			if (!errors.isEmpty()) {
				request.setAttribute("errors", errors);
				return "master-error.jsp";
			}
			try {
				if (dao.insert(category) != 1) {
					errors.add("DBエラーが発生しました。<br>操作をやり直してください");
					request.setAttribute("errors", errors);
					return "manager-error.jsp";
				}
				response.sendRedirect(request.getContextPath() + "/ManagerCategoryList.action?mode=view");
			} catch (Exception e) {
				e.printStackTrace();
				errors.add("システムエラーが発生しました。<br>操作をやり直してください");
				request.setAttribute("errors", errors);
				return "manager-error.jsp";
			}
		} else if (mode.equals("update")) {
			CategoryDAO dao = new CategoryDAO();
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
				response.sendRedirect(request.getContextPath() + "/ManagerCategoryList.action?mode=view");
			} catch (Exception e) {
				e.printStackTrace();
				errors.add("システムエラーが発生しました。<br>操作をやり直してください");
				request.setAttribute("errors", errors);
				return "manager-error.jsp";
			}
		}
		request.setAttribute("mode", mode);
		request.setAttribute("categoryId", id);

		return "managerCategoryList-view.jsp";
	}

}
