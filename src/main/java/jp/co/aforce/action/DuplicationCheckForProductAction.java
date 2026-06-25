package jp.co.aforce.action;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.dao.ProductDAO;

public class DuplicationCheckForProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if (id == null) {
			id = "";
		}
		ProductDAO dao = new ProductDAO();
		boolean idExists = false;

		try {
			idExists = dao.check(id);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			PrintWriter out = response.getWriter();
			out.write("{\"idExists\":" + idExists + "}");
		} catch (Exception e) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			PrintWriter out = response.getWriter();
			out.write("{\"idExists\":false}");
		}
		return null;
	}
}