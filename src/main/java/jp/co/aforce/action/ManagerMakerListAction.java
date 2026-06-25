package jp.co.aforce.action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.Makers;
import jp.co.aforce.dao.MakerDAO;
import jp.co.aforce.validator.SearchValidator;

public class ManagerMakerListAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MakerDAO dao = new MakerDAO();
		String mode ="view";
		String modeParam = request.getParameter("mode");

		if (modeParam != null && !modeParam.isEmpty()) {
		    mode = modeParam;
		}
		String keyword = request.getParameter("keyword");
		if (keyword == null) {
			keyword = "";
		}
		if (!keyword.isEmpty()) {
			SearchValidator validate = new SearchValidator();
			List<String> errors = validate.serarchValidate(keyword);
			//エラーがある場合エラー画面へ
			if (!errors.isEmpty()) {
				request.setAttribute("errors", errors);
				return "cart-error.jsp";
			}
		}
		keyword = keyword.replace("　", " ");
		try {
			List<Makers> list = dao.findByKeyword(keyword);
			request.setAttribute("MakerList", list);
			request.setAttribute("keyword", keyword);
			request.setAttribute("mode", mode);
			return "managerMakerList-view.jsp";
		} catch (Exception e) {
			//エラー文言
			e.printStackTrace();
			return "master-error.jsp";
		}

	}

}
