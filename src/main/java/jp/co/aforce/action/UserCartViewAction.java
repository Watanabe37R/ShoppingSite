package jp.co.aforce.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.bean.Products;
import jp.co.aforce.dao.ProductDAO;

public class UserCartViewAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");

		List<Products> list = new ArrayList<>();

		if (cart != null) {
			ProductDAO dao = new ProductDAO();

			for (String productId : cart.keySet()) {
				Products product = dao.findProductInfoById(productId);
				//数量セット
				product.setQuantity(cart.get(productId)); 
				list.add(product);
			}
		}

		request.setAttribute("cartList", list);

		return "userCart-view.jsp";

	}

}
