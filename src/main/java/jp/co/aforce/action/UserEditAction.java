package jp.co.aforce.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.bean.Users;
import jp.co.aforce.dao.UsersDAO;

public class UserEditAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("loginuser");
		String sId = user.getMemberId();
		
		String lname=request.getParameter("lname");
		String fname=request.getParameter("fname");
		String address=request.getParameter("address");
		Users editUser =new Users();
		editUser.setLastName(lname);
		editUser.setFirstName(fname);
		editUser.setAddress(address);
		
		UsersDAO dao=new UsersDAO();
		if(dao.update(sId, editUser)==1) {
			request.setAttribute("editUser", editUser);
			//セッション更新
			user.setLastName(lname);
			return "userEdit-out.jsp";
		}
		request.setAttribute("errmessage", "更新処理中にエラーが発生しました。"
				+ "<br>お手数をおかけしますがやり直しをお願いします。");
		return "userEdit-error.jsp";
	}

}
