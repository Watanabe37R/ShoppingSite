package jp.co.aforce.action;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.Orders;
import jp.co.aforce.dao.OrderDAO;

public class ManagerMemberListAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//DAOにアクセスして全ての履歴を取得
		OrderDAO dao=new OrderDAO();
		List<String> errors = new ArrayList<>();
		try{
			List<Orders> list = dao.getOrdersAll();	
			request.setAttribute("orderList", list);
			return "managerOrderHistory-view.jsp";
		}catch(Exception e) {
			e.printStackTrace();
			errors.add("システムエラーが発生しました。<br>操作をやり直してください。");
			request.setAttribute("errors", errors);
			return "master-error.jsp";
		}
	}

}
