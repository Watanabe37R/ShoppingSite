package jp.co.aforce.action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.Products;
import jp.co.aforce.dao.ProductDAO;
import jp.co.aforce.validator.SearchValidator;

public class ProductListAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductDAO DAO = new ProductDAO();
		String keyword = request.getParameter("keyword");
		if (keyword == null) {
			keyword = "";
		}
		if (!keyword.isEmpty()) {
			SearchValidator validate = new SearchValidator();
			List<String> errors = validate.serarchValidate(keyword);
			//エラーがある場合エラー画面へ
			if (!errors.isEmpty()) {
				request.setAttribute("errors", errors);
				return "cart-error.jsp";
			}
		}
		keyword = keyword.replace("　", " ");
		String order = request.getParameter("order");
		if (order == null) {
			order = "";
		}
		String categoryId = request.getParameter("categoryId");
		String categoryName = null;
		try {
			List<Products> list = DAO.findByKeyWord(categoryId, keyword, order);

			if (categoryId != null && !categoryId.isEmpty()) {
				categoryName = DAO.findById(categoryId);
			}

			request.setAttribute("productList", list);
			request.setAttribute("count", list.size());
			request.setAttribute("keyword", keyword);
			request.setAttribute("categoryName", categoryName);

			request.setAttribute("selectedCategory", categoryId);
			request.setAttribute("order", order);

			return "productList-view.jsp";
		} catch (Exception e) {
			//エラー文言
			e.printStackTrace();
			return "Cart-error.jsp";
		}

	}

}
