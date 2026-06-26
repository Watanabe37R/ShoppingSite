<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/CartListStyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/headerStyle.css">
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品決済確認</title>
</head>
<body>
	<header class="top-header-layout">
		<jsp:include page="/contents/header.jsp" />
	</header>
	<main>
		<div class="cart-container">
			<c:choose>
				<c:when test="${empty cartList}">
					<p>カートに商品が入っておりません</p>
				</c:when>
				<c:otherwise>
					<div class="cart-left">
						<c:forEach var="p" items="${cartList}">
							<div class="cart-item">
								<!-- 中：商品情報 -->
								<div class="item-info">
									<div>${p.productId}</div>
									<div>${p.productName}</div>
								</div>
								<!-- 右：価格 -->
								<div class="item-price">
								<fmt:formatNumber value="${p.price * p.quantity}" pattern="#,###" />円
								<c:if test="${p.quantity>=2}">
									<div class="sub">
										(<fmt:formatNumber value="${p.price}" pattern="#,###" />円 × ${p.quantity})
									</div>
								</c:if>
								</div>
								<!-- 下：操作 -->
								<div class="item-controls">
									<input type="hidden" name="productId" value="${p.productId}">
									<span>個数</span>
									${p.quantity}
								</div>
							</div>
						</c:forEach>
						<a href="${pageContext.request.contextPath}/Top.action">トップへ戻る</a>
					</div>
					<!-- 右：合計 -->
					<div class="cart-right">
						<c:set var="total" value="0" />
						<c:forEach var="p" items="${cartList}">
							<c:set var="total" value="${total + (p.price * p.quantity)}" />
						</c:forEach>
						<p class="total">
							合計：<fmt:formatNumber value="${total}" pattern="#,###" />円(税込)
						</p>
						
						<form action="UserOrderConplete.action" method="post">
							<button class="checkout-btn">購入する</button>
						</form>

					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</main>
</body>
</html>