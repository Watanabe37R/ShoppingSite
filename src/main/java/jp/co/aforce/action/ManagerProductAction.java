package jp.co.aforce.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.bean.Products;
import jp.co.aforce.dao.ProductDAO;

public class ManagerProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mode=request.getParameter("mode");
		String productId=request.getParameter("productId");
		Products products = new Products();
		if(!"register".equals(mode)) {
			ProductDAO dao =new ProductDAO();
			products =dao.findProductInfoByIdForManager(productId);
		}else {
			products.setProductId(productId);
			products.setImageUrl("img/noimage.png");
		}
		
		request.setAttribute("products", products);
		request.setAttribute("mode", mode);
		return "managerproduct-in.jsp";
	}

}
