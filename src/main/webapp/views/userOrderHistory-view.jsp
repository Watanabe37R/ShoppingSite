<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/CartListStyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/headerStyle.css">
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>履歴確認</title>
</head>
<body>
	<header class="top-header-layout">
		<jsp:include page="/contents/header.jsp" />
	</header>
	<main>
		<c:choose>
			<c:when test="${mode == 'complete'}">
				<h2>ご注文ありがとうございました！</h2>
			</c:when>
			<c:otherwise>
				<h2>ご注文履歴</h2>
			</c:otherwise>
		</c:choose>
		<div class="cart-container">
			<c:forEach var="p" items="${orderList}">
				<div class="cart-item">
					<!-- 左：画像 -->
					<div class="item-img">
						<img src="${pageContext.request.contextPath}/${p.imageUrl}">
					</div>
					<!-- 中：商品情報 -->
					<div class="item-info">
						<div>${p.productId}</div>
						<div>${p.productName}</div>
					</div>
					<!-- 右：価格 -->
					<div class="item-price">
						<fmt:formatNumber value="${p.price * p.quantity}" pattern="#,###" />
						円
						<c:if test="${p.quantity>=2}">
							<div class="sub">
								(
								<fmt:formatNumber value="${p.price}" pattern="#,###" />
								円 × ${p.quantity})
							</div>
						</c:if>
					</div>
					<!-- 下 -->
					<div class="item-controls">
						<span>個数</span> ${p.quantity}
					</div>
				</div>
			</c:forEach>
		</div>
	</main>
</body>
</html>