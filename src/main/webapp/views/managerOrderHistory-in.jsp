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
		<div class="page-header">
			<div class="title">
				<c:choose>
					<c:when test="${mode == 'view'}">
						<h2>閲覧モード</h2>
					</c:when>
					<c:otherwise>
						<h2>状態変更更新モード</h2>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="actions">
				<a href="${pageContext.request.contextPath}/ManagerOrderHistory.action?orderId=${orderId}&mode=${mode == 'view' ? 'edit' : 'view'}" class="btn switch-btn">モード変更</a>
				<a href="${pageContext.request.contextPath}/ManagerMemberList.action" class="btn back-btn">一覧に戻る</a>
			</div>
		</div>
		<div class="status">
			<p>ユーザーID：${orderList[0].memberId}</p>
			<c:choose>
				<c:when test="${mode=='edit'}">
					<form action="${pageContext.request.contextPath}/ManagerOrderHistory.action" method="post">
						<label>状態：
							<select name="status">
								<option value="0" ${orderList[0].status == 0 ? "selected" : ""}>注文済</option>
								<option value="1" ${orderList[0].status == 1 ? "selected" : ""}>支払済</option>
								<option value="2" ${orderList[0].status == 2 ? "selected" : ""}>発送済</option>
								<option value="3" ${orderList[0].status == 3 ? "selected" : ""}>店舗受取済</option>
								<option value="9" ${orderList[0].status == 9 ? "selected" : ""}>キャンセル済み</option>
							</select>
						</label>
					
						<input type="hidden" name="orderId" value="${orderId}">
						<input type="hidden" name="mode" value="update">
						<button type="submit">更新</button>
					</form>
				</c:when>
				<c:otherwise>
					<label>状態：
						<c:choose>
							<c:when test="${orderList[0].status == 0}">注文済</c:when>
							<c:when test="${orderList[0].status == 1}">支払済</c:when>
							<c:when test="${orderList[0].status == 2}">発送済</c:when>
							<c:when test="${orderList[0].status == 3}">店舗受取済</c:when>
							<c:when test="${orderList[0].status == 9}">キャンセル済み</c:when>
							<c:otherwise>完了</c:otherwise>
						</c:choose>
					</label>
				</c:otherwise>
			</c:choose>
		</div>
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