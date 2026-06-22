<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/productDetailsStyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/headerStyle.css">
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品管理編集画面</title>
</head>
<body>
	<header class="top-header-layout">
		<jsp:include page="/contents/headerForProductOfManager.jsp" />
	</header>
	<main>
		<c:choose>
			<c:when test="${mode=='edit'}">
				<h2 class="edit">商品情報更新</h2>
			</c:when>
			<c:when test="${mode=='delete'}">
				<h2 class="delete">商品情報削除</h2>
			</c:when>
			<c:when test="${mode=='register'}">
				<h2 class="register">商品情報登録</h2>
			</c:when>
			<c:otherwise>
				<h2 class="view">商品情報閲覧</h2>
			</c:otherwise>
		</c:choose>

		<div class="button-area">
			<form method="get"
				action="${pageContext.request.contextPath}/ManagerProduct.action">
				<input type="hidden" name="productId" value="${products.productId}">
				<button type="submit" name="mode"
					value="${mode == 'view' ? 'edit' :
							mode == 'edit' ? 'delete' :
							mode == 'delete' ? 'register' :
							'view'}"
					class="main-btn">モード変更
				</button>
			</form>
			<a href="${pageContext.request.contextPath}/ManagerProductList.action">一覧へ戻る</a>
		</div>
		

		<!--アイテム全体-->
		<div class="item-container">
			<form
				action="${pageContext.request.contextPath}/ManagerProductProcess.action"
				method="post" enctype="multipart/form-data">
				<!-- 上段 -->
				<div class="top-area">
					<!--画像欄：アイテム欄の左側-->
					<div class="image-area">
						<c:choose>
							<c:when test="${mode=='register' || mode=='edit'}">
								<!--登録・編集画面では画像登録の参照-->
								<img id="preview" src="${pageContext.request.contextPath}/${products.imageUrl}">
								<input type="hidden" name="currentImage"value="${products.imageUrl}">
								<!-- ファイル選択（隠す） -->
								<input type="file" id="fileInput" name="imageFile" accept="image/*" style="display:none">
							</c:when>
							<c:otherwise>
								<img src="${pageContext.request.contextPath}/${products.imageUrl}">
							</c:otherwise>
						</c:choose>
					</div>
					<!--そのほか重要コンテンツ：アイテム欄の右側-->
					<div class="summary-grid">
						<div class="summary-item">
							<label>商品ID</label>
							<input type="text" name="productId" value="${mode == 'register' ? '' : products.productId}" ${mode == 'register' ? '' : 'readonly'}>
						</div>
						<div class="summary-item">
							<label>商品名</label>
							<input type="text" name="productName" value="${mode == 'register' ? '' : products.productName}" ${mode == 'view' || mode == 'delete' ? 'readonly' : ''}>
						</div>
						<div class="summary-item">
							<label>商品価格</label>
							<input type="text" name="price" value="${mode == 'register' ? '' : products.price}" ${mode == 'view' || mode == 'delete' ? 'readonly' : ''}>
						</div>
					</div>
				</div>
				<!-- 下段 -->
				<div class="detail-area">
					<div class="detail-grid">
						<div class="detail-item">
							<label>メーカーID</label>
							<input type="text" name="makerId" value="${mode == 'register' ? '' : products.makerId}" ${mode == 'view' || mode == 'delete' ? 'readonly' : ''}>
						</div>
						<div class="detail-item">
							<label>メーカー名</label>
							<input type="text" name="makerName" value="${mode == 'register' ? '' : products.makerName}" ${mode == 'view' || mode == 'delete' ? 'readonly' : ''}>
						</div>
						<div class="detail-item">
							<label>税率区分</label>
							<c:choose>
								<c:when test="${mode=='register' || mode=='edit'}">
									<div class="radio-group">
										<label>
											<input type="radio" name="taxClass" value="1"
											${mode == 'register' ? 'checked' : (products.taxClass == 1 ? 'checked' : '')}>
											標準税率
										</label>
										<label>
											<input type="radio" name="taxClass" value="0"
											${mode == 'register' ? '' : (products.taxClass == 0 ? 'checked' : '')}>
											軽減税率
										</label>
									</div>
								</c:when>
								<c:otherwise>
									<input type="text" value="${products.taxClass == 1 ? '標準税率' : '軽減税率'}" readonly>
									<input type="hidden" name="taxClass" value="${products.taxClass}">
								</c:otherwise>
							</c:choose>
						</div>
						<div class="detail-item">
							<label>税</label>
							<input type="text" name="tax" value="${mode == 'register' ? '' : products.tax}" ${mode == 'view' || mode == 'delete' ? 'readonly' : ''}>
						</div>
						<div class="detail-item">
							<label>カテゴリID</label>
							<input type="text" name="categoryId" value="${mode == 'register' ? '' : products.categoryId}" ${mode == 'view' || mode == 'delete' ? 'readonly' : ''}>
						</div>
						<div class="detail-item">
							<label>カテゴリ名</label>
							<input type="text" name="categoryName" value="${mode == 'register' ? '' : products.categoryName}" ${mode == 'view' || mode == 'delete' ? 'readonly' : ''}>
						</div>
						<div class="detail-item">
							<label>販売区分</label>
							<c:choose>
								<c:when test="${mode=='register' || mode=='edit'}">
									<div class="radio-group">
										<label>
											<input type="radio" name="isOnSale" value="1"
											${mode == 'register' ? 'checked' : (products.onSale ? 'checked' : '')}>
											販売中
										</label>
										<label>
											<input type="radio" name="isOnSale" value="0"
											${mode == 'register' ? '' : (!products.onSale ? 'checked' : '')}>
											販売中止
										</label>
									</div>
								</c:when>
								<c:otherwise>
									<input type="text" value="${products.onSale ? '販売中' : '販売中止'}" readonly>
									<input type="hidden" name="isOnSale" value="${products.onSale ? '1' : '0'}">
								</c:otherwise>
							</c:choose>
						</div>
						<div class="detail-item">
							<label>在庫数</label>
							<input type="text" name="stock" value="${mode == 'register' ? '' : products.stock}" ${mode == 'view' || mode == 'delete' ? 'readonly' : ''}>
						</div>
						<div class="detail-item full">
							<label>摘要</label>
							<textarea name="abstractText" ${mode == 'view' || mode == 'delete' ? 'readonly' : ''}> ${mode == 'register' ? '' : products.abstractText}</textarea>
						</div>
					</div>
					<!-- 閲覧の時はそもそもボタンを出さない -->
					<c:if test="${mode!='view'}">
						<div class="button-area">
							<input type="hidden" name="processMode" value="${mode == 'edit' ? 'update' : mode == 'delete' ? 'deleteExecute' : 'insert'}">
							<button type="submit" name="mode" 
							class="${mode == 'edit' ? 'edit-btn' : mode == 'delete' ? 'delete-btn' : 'register-btn'}">
							${mode == 'edit' ? '更新' : mode == 'delete' ? '削除' : '登録'}</button>							
						</div>
					</c:if>
				</div>
			</form>
		</div>
	</main>
<script src="${pageContext.request.contextPath}/js/imageRegistration.js"></script>
</body>
</html>