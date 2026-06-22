package jp.co.aforce.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import jp.co.aforce.bean.Products;
import jp.co.aforce.dao.ProductDAO;
import jp.co.aforce.tool.ImageSavingProcess;

public class ManagerProductProcessAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mode = request.getParameter("processMode");
		String productId = request.getParameter("productId");
		String imageUrl = request.getParameter("currentImage");
		Products product = new Products();
		System.out.println(productId);
		if ("insert".equals(mode) || "update".equals(mode)) {
			//画像の受け取り
			if (productId == null || productId.isEmpty()) {
				response.sendRedirect(request.getContextPath() + "/ManagerProductList.action");
				return null;
			}
			Part filePart = request.getPart("imageFile");
			ImageSavingProcess ImageUtil = new ImageSavingProcess();
			String imagePath = ImageUtil.saveImage(filePart, productId, request);
			if (imagePath != null) {
				product.setImageUrl(imagePath);
			} else {
				product.setImageUrl(imageUrl);
			}

			product.setProductId(request.getParameter("productId"));
			product.setProductName(request.getParameter("productName"));
			product.setMakerId(request.getParameter("makerId"));
			product.setMakerName(request.getParameter("makerName"));
			String priceStr = request.getParameter("price");
			if (priceStr != null && !priceStr.isEmpty()) {
				product.setPrice(Integer.parseInt(priceStr));
			}
			product.setTaxClass(Integer.parseInt(request.getParameter("taxClass")));
			String taxStr = request.getParameter("tax");
			if (taxStr != null && !taxStr.isEmpty()) {
				product.setTax(Integer.parseInt(taxStr));
			}
			product.setCategoryId(request.getParameter("categoryId"));
			product.setCategoryName(request.getParameter("categoryName"));
			product.setAbstractText(request.getParameter("abstractText"));
			product.setOnSale("1".equals(request.getParameter("isOnSale")));
			String stockStr = request.getParameter("stock");
			if (stockStr != null && !stockStr.isEmpty()) {
				product.setStock(Integer.parseInt(stockStr));
			}
		}
		ProductDAO dao = new ProductDAO();
		if ("insert".equals(mode)) {
			// 登録
			try {
				dao.insertProduct(product);
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect(
					request.getContextPath() +
							"/ManagerProduct.action?mode=view&productId=" + product.getProductId());
		} else if ("update".equals(mode)) {
			// 更新
			try {
				dao.updateProduct(product);
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect(
					request.getContextPath() +
							"/ManagerProduct.action?mode=view&productId=" + product.getProductId());
		} else if ("deleteExecute".equals(mode)) {
			// 削除
			if (productId != null && !productId.isEmpty()) {
				try {
					dao.deleteProduct(productId);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			response.sendRedirect(request.getContextPath() + "/ManagerProductList.action");
		}
		return null;
	}

}
