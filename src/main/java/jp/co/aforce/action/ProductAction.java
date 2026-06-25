package jp.co.aforce.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.Products;
import jp.co.aforce.dao.ProductDAO;

public class ProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String productId = request.getParameter("productId");
		Products products = new Products();

		ProductDAO dao = new ProductDAO();
		products = dao.findProductInfoById(productId);

		request.setAttribute("products", products);
		return "product-view.jsp";
	}

}
