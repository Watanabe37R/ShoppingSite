package jp.co.aforce.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.bean.Users;
import jp.co.aforce.dao.CartDAO;

public class UserCartDeleteAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("loginuser");
		String pID=request.getParameter("productId");
		CartDAO dao = new CartDAO();
		if(dao.deleteCart(user.getMemberId(),pID)==0) {
			//エラー
		}
		response.sendRedirect(request.getContextPath() + "/UserCartView.action");
		return null;
	}

}
