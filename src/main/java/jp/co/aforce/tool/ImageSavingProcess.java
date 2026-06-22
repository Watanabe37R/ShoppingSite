package jp.co.aforce.tool;

import java.io.File;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class ImageSavingProcess {

	public String saveImage(Part filePart, String productId, HttpServletRequest request) throws Exception {

		String fileName = filePart.getSubmittedFileName();

		if (fileName == null || fileName.isEmpty()) {
			return null;
		}

		// 拡張子取得
		String ext = fileName.substring(fileName.lastIndexOf("."));

		// ファイル名生成
		String newFileName = "product-" + productId + ext;

		// 保存先
		String uploadPath = request.getServletContext().getRealPath("/img");
		System.out.println("保存先=" + uploadPath);
		// 保存
		filePart.write(uploadPath + File.separator + newFileName);

		// DB用パス返す
		return "img/" + newFileName;
	}

}
