<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/ProductListStyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/headerStyle.css">
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧</title>
</head>
<body>
	<header class="top-header-layout">
		<jsp:include page="/contents/headerForProductOfManager.jsp" />
	</header>
	<main>
		<div class="container">
			<!-- 左側 -->
			<div class="sidebar">
				<h3>カテゴリ</h3>
				<ul>
					<li><a
						href="${pageContext.request.contextPath}/ManagerProductList.action">すべて</a></li>
					<c:forEach var="c" items="${applicationScope.categoryList}">
						<li><a
							href="${pageContext.request.contextPath}/ManagerProductList.action?categoryId=${c.categoryId}">${c.categoryName}</a></li>
					</c:forEach>
				</ul>

				<h3>並び替え</h3>

				<form action="${pageContext.request.contextPath}/ManagerProductList.action"
					method="get">

					<!-- 検索条件保持 -->
					<input type="hidden" name="keyword" value="${keyword}"> <input
						type="hidden" name="categoryId" value="${selectedCategory}">

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

				<p>${count}件の商品</p>
				<c:if test="${not empty categoryName}">
					<p>
						カテゴリ：<a
							href="${pageContext.request.contextPath}/ManagerProductList.action?categoryId=${c.categoryId}">${categoryName}</a>
					</p>
				</c:if>
				<div class="buttons">
					<p><a class="btn register-btn"
						href="${pageContext.request.contextPath}/ManagerProduct.action?mode=register">新規商品登録</a></p>
				</div>
				<div class="products">
					<c:forEach var="p" items="${productList}">
						<div class="card">
							<div class="img">
								<img src="${pageContext.request.contextPath}/${p.imageUrl}">
							</div>
							<label><a
								href="${pageContext.request.contextPath}/ManagerProduct.action?mode=view&productId=${p.productId}">${p.productName}</a><br></label>
							<label>${p.makerName}<br></label>
							<label> <fmt:formatNumber value="${p.price}" pattern="#,###" />円(税込)<br></label> 
							<label>在庫：${p.stock}個</label>
							<div class="buttons">
								<a class="btn edit-btn"
									href="${pageContext.request.contextPath}/ManagerProduct.action?mode=edit&productId=${p.productId}">編集</a>
								<a class="btn delete-btn"
									href="${pageContext.request.contextPath}/ManagerProduct.action?mode=delete&productId=${p.productId}">削除</a>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>

		</div>
	</main>
</body>
</html>