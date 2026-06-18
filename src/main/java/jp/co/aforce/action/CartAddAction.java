package jp.co.aforce.action;

import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.bean.Users;
import jp.co.aforce.dao.CartDAO;

public class CartAddAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

	    HttpSession session = request.getSession();

	    Users user = (Users) session.getAttribute("loginuser");
	    boolean loginStatus = (user != null);

	    String productId = request.getParameter("productId");

	    int quantity = 1;
	    try {
	        quantity = Integer.parseInt(request.getParameter("quantity"));
	    } catch (Exception e) {
	        quantity = 1;
	    }

	    if (loginStatus) {
	        //既ログイン時：DB
	        CartDAO dao = new CartDAO();
	        dao.upsertCart(user.getMemberId(), productId, quantity);

	    } else {
	        //未ログイン時：セッション
	        Map<String, Integer> cart =
	            (Map<String, Integer>) session.getAttribute("cart");

	        if (cart == null) {
	            cart = new HashMap<>();
	        }

	        cart.put(productId, cart.getOrDefault(productId, 0) + quantity);

	        session.setAttribute("cart", cart);
	        
			//元のURLを保持
			String referer = request.getHeader("Referer");
			session = request.getSession();
			if (session.getAttribute("redirect") == null && referer != null) {
				session.setAttribute("redirect", referer);
			}

			response.sendRedirect(request.getContextPath() + "/views/login-in.jsp");
			return null;
	    }

	    //元のページへ戻す
	    String referer = request.getHeader("referer");
	    if (referer != null) {
	        response.sendRedirect(referer);
	        return null;
	    }

	    return "ProductList.action";
	}
}
