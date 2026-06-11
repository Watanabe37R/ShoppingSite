package jp.co.aforce.action;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.dao.UsersDAO;
import jp.co.aforce.tool.DuplicateResult;

public class DuplicationCheckAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String mail = request.getParameter("mail");

		if (id == null) {
			id = "";
		}
		if (mail == null) {
			mail = "";
		}

		UsersDAO dao = new UsersDAO();

		boolean idExists = false;
		boolean mailExists = false;

		try {
			DuplicateResult result = dao.check(id, mail);
			if (result == DuplicateResult.ID_DUPLICATE || result == DuplicateResult.BOTH_DUPLICATE) {
				// IDエラー
				idExists = true;
			}
			if (result == DuplicateResult.MAIL_DUPLICATE || result == DuplicateResult.BOTH_DUPLICATE) {
				// メールエラー
				mailExists = true;

			}

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			PrintWriter out = response.getWriter();
			out.write("{\"idExists\":" + idExists + ",\"mailExists\":" + mailExists + "}");
		} catch (Exception e) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			PrintWriter out = response.getWriter();
			out.write("{\"idExists\":false,\"mailExists\":false}");
		}
		return null;
	}
}
