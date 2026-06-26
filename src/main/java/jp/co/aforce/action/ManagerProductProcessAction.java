package jp.co.aforce.action;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import jp.co.aforce.bean.Products;
import jp.co.aforce.dao.ProductDAO;
import jp.co.aforce.tool.ImageSavingProcess;
import jp.co.aforce.validator.MasterValidator;

public class ManagerProductProcessAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mode = request.getParameter("processMode");
		String productId = request.getParameter("productId");
		String imageUrl = request.getParameter("currentImage");
		Products product = new Products();
		MasterValidator validate = new MasterValidator();
		List<String> errors = new ArrayList<>();
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
			//他の値取得
			product.setProductName(request.getParameter("productName"));
			product.setMakerId(request.getParameter("makerId"));
			product.setMakerName(request.getParameter("makerName"));
			String priceStr = request.getParameter("price");
			if (priceStr != null && !priceStr.isEmpty()) {
				try {
					product.setPrice(Integer.parseInt(priceStr));
				} catch (NumberFormatException e) {
					errors.add("価格は数値で入力してください。");
				}
			} else {
				errors.add("価格が未入力です。");
			}
			String taxClassStr = request.getParameter("taxClass");
			if (taxClassStr != null && !taxClassStr.isEmpty()) {
				try {
					product.setTaxClass(Integer.parseInt(taxClassStr));
				} catch (NumberFormatException e) {
					errors.add("税率は数値で入力してください。");
				}
			} else {
				errors.add("税率区分が未入力です。");
			}
			String taxStr = request.getParameter("tax");
			if (taxStr != null && !taxStr.isEmpty()) {
				try {
					product.setTax(Integer.parseInt(taxStr));
				} catch (NumberFormatException e) {
					errors.add("税率は数値で入力してください。");
				}
			} else {
				errors.add("税率が未入力です。");
			}
			product.setCategoryId(request.getParameter("categoryId"));
			product.setCategoryName(request.getParameter("categoryName"));
			product.setAbstractText(request.getParameter("abstractText"));
			String isOnSaleParam = request.getParameter("isOnSale");
			if (!"0".equals(isOnSaleParam) && !"1".equals(isOnSaleParam)) {
				errors.add("販売状態が不正です。");
			}
			product.setOnSale("1".equals(isOnSaleParam));
			String stockStr = request.getParameter("stock");
			if (stockStr != null && !stockStr.isEmpty()) {
				try {
					product.setStock(Integer.parseInt(stockStr));
				} catch (NumberFormatException e) {
					errors.add("税率は数値で入力してください。");
				}
			} else {
				errors.add("税率が未入力です。");
			}
			if ("insert".equals(mode)) {
				product.setProductId(request.getParameter("productId"));
			} else if ("update".equals(mode)) {
				product.setProductId(productId);
			}

			//バリデーション
			errors.addAll(validate.productValidate(product));
			//エラーがある場合エラー画面へ
			if (!errors.isEmpty()) {
				request.setAttribute("errors", errors);
				return "master-error.jsp";
			}
		}

		ProductDAO dao = new ProductDAO();
		if ("insert".equals(mode)) {
			// 登録
			try {
				dao.insertProduct(product);
			} catch (Exception e) {
				e.printStackTrace();
				errors.add("システムエラーが発生しました。<br>操作をやり直してください");
				request.setAttribute("errors", errors);
				return "master-error.jsp";
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
				errors.add("システムエラーが発生しました。<br>操作をやり直してください");
				request.setAttribute("errors", errors);
				return "master-error.jsp";
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
					errors.add("システムエラーが発生しました。<br>操作をやり直してください");
					request.setAttribute("errors", errors);
					return "master-error.jsp";
				}
			}
			response.sendRedirect(request.getContextPath() + "/ManagerProductList.action");
		}
		return null;
	}

}
