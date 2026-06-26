<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/ProductListStyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/headerStyle.css">
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧</title>
</head>
<body>
<header class="top-header-layout">
	<jsp:include page="/contents/header.jsp" />
</header>
<main>
	<div class="container">
	
		<!-- 左側 -->
		<div class="sidebar">
			<h3>カテゴリ</h3>
			<ul>
				<li><a href="${pageContext.request.contextPath}/ProductList.action">すべて</a></li>
				<c:forEach var="c" items="${applicationScope.categoryList}">
					<li><a href="${pageContext.request.contextPath}/ProductList.action?categoryId=${c.categoryId}">${c.categoryName}</a></li>
				</c:forEach>
			</ul>
		
			<h3>並び替え</h3>
			
			<form action="${pageContext.request.contextPath}/ProductList.action" method="get">

	 			<!-- 検索条件保持 -->
				<input type="hidden" name="keyword" value="${keyword}">
				<input type="hidden" name="categoryId" value="${selectedCategory}">
	
				<!-- 並び替え -->
				<select name="order" onchange="this.form.submit()">
					<option value="">並び替え</option>
					<option value="lowprice">価格が安い順</option>
					<option value="highprice">価格が高い順</option>
					<option value="new">新着順</option>
					<option value="evaluation">評価順</option>
				</select>
			</form>
		</div>
	
		<!-- 右側 -->
		<div class="main">
			<c:choose>
				<c:when test="${not empty keyword}">
					<h2>「${keyword}」の検索結果</h2>
				</c:when>
				<c:otherwise>
					<h2>商品一覧</h2>
				</c:otherwise>
			</c:choose>
			
			<p>${count}件ヒット</p>
				<c:if test="${not empty categoryName}">
					<p>カテゴリ：<a href="${pageContext.request.contextPath}/ProductList.action?categoryId=${c.categoryId}">${categoryName}</a></p>
				</c:if>
			<div class="products">
				<c:forEach var="p" items="${productList}">
					<c:if test="${p.stock > 0 && p.onSale}">
						<div class="card">
							<div class="img"><a href="Product.action?productId=${p.productId}"><img src="${pageContext.request.contextPath}/${p.imageUrl}"></a></div>
							<p><a href="Product.action?productId=${p.productId}">${p.productName}</a></p>
							<p>${p.makerName}</p>
							<c:choose>
								<c:when test="${p.reviewCount == 0}">
									<p><span class="stars" style="--rating: 0;"></span>(0件)</p>
								</c:when>
								<c:otherwise>
									<p>
									<span class="stars" style="--rating: ${p.avgRating};"></span>
									<fmt:formatNumber value="${p.avgRating}" minFractionDigits="1" maxFractionDigits="1" />
									（${p.reviewCount}件）
									</p>
								</c:otherwise>
							</c:choose>
							<p>
								<fmt:formatNumber value="${p.price}" pattern="#,###" />円(税込)
							</p>
							<form action="${pageContext.request.contextPath}/CartAdd.action" method="post" onsubmit="saveState()">
								<input type="hidden" name="productId" value="${p.productId}">
								<label>個数</label>
								<select name="quantity">
									<c:forEach begin="1" end="${p.stock}" var="i">
										<option value="${i}">${i}</option>
									</c:forEach>
								</select>
								<input type="submit" value="カートに追加">
							</form>
							<script src="${pageContext.request.contextPath}/js/toastView.js"></script>
							<div id="toast" class="toast">追加しました</div>
						</div>
					</c:if>
					
				</c:forEach>
			</div>
		</div>
	
	</div>
</main>
</body>
</html>