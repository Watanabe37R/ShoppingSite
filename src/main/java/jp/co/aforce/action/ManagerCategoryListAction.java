package jp.co.aforce.action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.Categorys;
import jp.co.aforce.dao.CategoryDAO;

public class ManagerCategoryListAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CategoryDAO dao = new CategoryDAO();
		String keyword = request.getParameter("keyword");
		if (keyword == null) {
			keyword = "";
		}
		keyword = keyword.replace("　", " ");
		try {
			List<Categorys> list = dao.findByKeyword(keyword);
			request.setAttribute("CategoryList", list);
			request.setAttribute("keyword", keyword);
			return "managerCategoryList-view.jsp";
		} catch (Exception e) {
			//エラー文言
			e.printStackTrace();
			return "master-error.jsp";
		}
	}
}
