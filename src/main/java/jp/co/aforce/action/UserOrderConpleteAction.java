package jp.co.aforce.action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.bean.Carts;
import jp.co.aforce.bean.Users;
import jp.co.aforce.dao.CartDAO;
import jp.co.aforce.dao.OrderDAO;

public class UserOrderConpleteAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("loginuser");

		String memberId = user.getMemberId();

		CartDAO cartDao = new CartDAO();
		OrderDAO orderDao = new OrderDAO();

		try {
			//カート取得
			List<Carts> cartList = cartDao.getCartByUser(memberId);

			if (cartList == null || cartList.isEmpty()) {
				// カート空なら戻す
				response.sendRedirect("UserCartView.action");
				return null;
			}

			//注文作成（ID取得）
			int orderId = orderDao.createOrder(memberId);

			//明細追加
			for (Carts item : cartList) {
				orderDao.insertOrderDetail(orderId, item);
			}

			//カート削除
			cartDao.clearCart(memberId);

			//完了画面へ
			response.sendRedirect(request.getContextPath() + "/UserOrderHistory.action?mode=complete&orderId="+orderId);
			return null;
		} catch (Exception e) {
			//エラー
			e.printStackTrace();
			return "cart-error.jsp";
		}
	}
}
