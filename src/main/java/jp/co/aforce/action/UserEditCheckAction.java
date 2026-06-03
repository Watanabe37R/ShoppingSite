package jp.co.aforce.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.Users;

public class UserEditCheckAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String lname=request.getParameter("lname");
		String fname=request.getParameter("fname");
		String address=request.getParameter("address");
		Users editUser = new Users();
		editUser.setLastName(lname);
		editUser.setFirstName(fname);
		editUser.setAddress(address);
		
		//後で分岐
		request.setAttribute("editUser", editUser);
		return "userEdit-check.jsp";
	}

}
