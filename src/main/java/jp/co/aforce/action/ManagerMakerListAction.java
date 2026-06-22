package jp.co.aforce.action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.Makers;
import jp.co.aforce.dao.MakerDAO;

public class ManagerMakerListAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MakerDAO dao = new MakerDAO();
		String keyword = request.getParameter("keyword");
		if (keyword == null) {
			keyword = "";
		}
		keyword = keyword.replace("　", " ");
		try {
			List<Makers> list = dao.findByKeyword(keyword);
			request.setAttribute("MakerList", list);
			request.setAttribute("keyword", keyword);
			return "managerMakerList-view.jsp";
		} catch (Exception e) {
			//エラー文言
			e.printStackTrace();
			return "master-error.jsp";
		}

	}

}
