package jp.co.aforce.action;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.OrderDetails;
import jp.co.aforce.dao.OrderDAO;

public class ManagerOrderHistoryAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mode = request.getParameter("mode");
		List<String> errors = new ArrayList<>();
		int orderId = 0;
		int status = 0;
		try {
			String orderIdStr = request.getParameter("orderId");
			if (orderIdStr != null && !orderIdStr.isEmpty()) {
				orderId = Integer.parseInt(orderIdStr);
			}
			String statusStr = request.getParameter("status");
			if (statusStr != null && !statusStr.isEmpty()) {
				status = Integer.parseInt(statusStr);
			}
		} catch (NumberFormatException e) {
			// 数値じゃなかった場合の処理
			e.printStackTrace();
			errors.add("数値以外が入力されています。");
			request.setAttribute("errors", errors);
			return "master-error.jsp";
		}
		if (status != 9 && (status < 0 || status > 3)) {
			//バリデーション
			errors.add("状態が不正です。");
			request.setAttribute("errors", errors);
			return "master-error.jsp";
		}
		if ("view".equals(mode) || "edit".equals(mode)) {
			request.setAttribute("mode", mode);

			OrderDAO dao = new OrderDAO();
			List<OrderDetails> list = dao.getOrderDetailById(orderId);
			request.setAttribute("orderList", list);
			request.setAttribute("orderId", orderId);
		} else if ("update".equals(mode)) {
			OrderDAO dao = new OrderDAO();
			//orderのSTATUSを更新
			try {
				if (dao.updateStatus(status, orderId) != 1) {
					errors.add("DBエラーです。<br>操作をやり直してください。");
					request.setAttribute("errors", errors);
					return "master-error.jsp";
				}
				response.sendRedirect(
						request.getContextPath() + "/ManagerOrderHistory.action?mode=view&orderId=" + orderId);
			} catch (Exception e) {
				e.printStackTrace();
				errors.add("システムエラーです。<br>操作をやり直してください。");
				request.setAttribute("errors", errors);
				return "master-error.jsp";
			}
		}

		return "managerOrderHistory-in.jsp";
	}

}
