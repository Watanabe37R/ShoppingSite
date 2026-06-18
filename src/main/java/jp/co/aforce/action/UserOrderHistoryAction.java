package jp.co.aforce.action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.bean.OrderDetails;
import jp.co.aforce.bean.Users;
import jp.co.aforce.dao.OrderDAO;

public class UserOrderHistoryAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("loginuser");
		
		String mode = request.getParameter("mode");
		int orderId = Integer.parseInt(request.getParameter("orderId"));

		if ("complete".equals(mode)) {
			request.setAttribute("mode", "complete");
		}
		OrderDAO dao = new OrderDAO();
		List<OrderDetails> list = dao.getOrderDetail(orderId, user.getMemberId());
		request.setAttribute("orderList", list);

		return "userOrderHistory-view.jsp";
	}

}
