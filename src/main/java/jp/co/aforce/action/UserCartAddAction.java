package jp.co.aforce.action;

import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserCartAddAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String productId = request.getParameter("productId");

		int quantity = 1;
		try {
			quantity = Integer.parseInt(request.getParameter("quantity"));
		} catch (Exception e) {
			quantity = 1;
		}

		HttpSession session = request.getSession();

		Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");

		if (cart == null) {
			cart = new HashMap<>();
		}

		if (cart.containsKey(productId)) {
			cart.put(productId, cart.get(productId) + quantity);
		} else {
			cart.put(productId, quantity);
		}

		session.setAttribute("cart", cart);

		// 🔥 元のページに戻す
		String referer = request.getHeader("referer");
		if (referer != null) {
			response.sendRedirect(referer);
			return null;
		}

		return "ProductList.action";

	}

}
