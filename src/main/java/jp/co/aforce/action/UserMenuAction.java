package jp.co.aforce.action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.bean.Orders;
import jp.co.aforce.bean.Users;
import jp.co.aforce.dao.OrderDAO;

public class UserMenuAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("loginuser");
		//DAOにアクセスしてIDに紐づく履歴を取得
		try {
			OrderDAO dao = new OrderDAO();
			List<Orders> list = dao.getOrders(user.getMemberId());
			request.setAttribute("orderList", list);
			return "user-menu.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
