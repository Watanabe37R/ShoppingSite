<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/CartListStyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/headerStyle.css">
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
									${p.price * p.quantity}円
									<div class="sub">(${p.price}円 × ${p.quantity})</div>
								</div>
								<!-- 下：操作 -->
								<div class="item-controls">
									<span>個数</span> <select>
										<c:forEach begin="1" end="10" var="i">
											<option ${i == p.quantity ? "selected" : ""}>${i}</option>
										</c:forEach>
									</select>
									<button>削除</button>
								</div>
							</div>
						</c:forEach>
					</div>
					<!-- 右：合計 -->
					<div class="cart-right">
						<c:set var="total" value="0" />
						<c:forEach var="p" items="${cartList}">
							<c:set var="total" value="${total + (p.price * p.quantity)}" />
						</c:forEach>
						<p class="total">合計：${total}円</p>
						<button>購入手続きに進む</button>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</main>
</body>
</html>