package jp.co.aforce.action;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.bean.Carts;
import jp.co.aforce.bean.Users;
import jp.co.aforce.dao.CartDAO;

public class UserOrderConfirmAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("loginuser");

		CartDAO dao = new CartDAO();
		List<Carts> list = new ArrayList<>();

		list = dao.getCartByUser(user.getMemberId());
		request.setAttribute("cartList", list);

		return "userOrder-view.jsp";

	}

}
