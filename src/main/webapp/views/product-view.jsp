<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/productDetailsCustomerStyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/headerStyle.css">
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細</title>
</head>
<body>
	<header class="top-header-layout">
		<jsp:include page="/contents/header.jsp" />
	</header>
	<main>
		<h2 class="view">商品詳細情報</h2>
		<div class="button-area">
			<a href="${pageContext.request.contextPath}/ManagerProductList.action">
				一覧へ戻る
			</a>
		</div>
		<div class="item-container">
			<!-- 上段 -->
			<div class="top-area">
				<!-- 左：画像 -->
				<div class="image-area">
					<img src="${pageContext.request.contextPath}/${products.imageUrl}">
				</div>
				<!-- 右：情報 -->
				<div class="summary-area">
					<!-- 商品名 -->
					<h3 class="product-title">${products.productName}</h3>
					<!-- 価格 -->
					<div class="price">
						<fmt:formatNumber value="${products.price}" pattern="#,###" />円
					</div>
					<!-- 評価 -->
					<div class="rating">
						<span class="stars" style="--rating: ${products.avgRating};"></span>
						<span class="rating-value">
							<fmt:formatNumber value="${products.avgRating}" minFractionDigits="1" maxFractionDigits="1"/>
						</span>
						<span>（${products.reviewCount}件）</span>
					</div>
					<!-- メーカー -->
					<div class="maker">
						メーカー：${products.makerName}
					</div>
					<!-- 購入エリア -->
					<form class="buy-box"
						  action="${pageContext.request.contextPath}/CartAdd.action"
						  method="post" onsubmit="saveState()">
						<input type="hidden" name="productId" value="${products.productId}">
						<label>数量</label>
						<select name="quantity">
							<c:forEach begin="1" end="${products.stock > 20 ? 20 : p.stock}" var="i">
								<option value="${i}">${i}</option>
							</c:forEach>
						</select>
						<input type="submit" value="カートに追加">
					</form>
						<script src="${pageContext.request.contextPath}/js/toastView.js"></script>
					<div id="toast" class="toast">追加しました</div>
				</div>
			</div>
			<!-- 下段 -->
			<div class="detail-area">
				<!-- 商品説明 -->
				<div class="description">
					<h4>商品概要</h4>
					<p>${products.abstractText}</p>
				</div>
				<!-- レビュー -->
				<div class="reviews">
					<h4>レビュー</h4>
					<c:forEach var="r" items="${reviewList}">
						<div class="review-card">
							<span class="stars" style="--rating: ${r.rating};"></span>
							<p class="review-text">${r.comment}</p>
							<span class="review-date">${r.createdata}</span>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</main>
</body>
</html>