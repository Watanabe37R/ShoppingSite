<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/accountstyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/headerStyle.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント情報</title>
</head>

<body>
	<header class="top-header-layout">
		<jsp:include page="/contents/headerNoSearchbar.jsp" />
	</header>
	<main>
		<div class="account-container">
			<h2>ユーザアカウント情報</h2>

			<p class="login-id">ログインID：${sessionScope.loginuser.memberId}</p>

			<h3>登録情報</h3>
			<div class="link-buttons">
				<a href="${pageContext.request.contextPath}/UserEditView.action?mode=view">基本情報</a>
				<a href="${pageContext.request.contextPath}/UserLoginInfo.action?mode=view">ログイン情報</a>
			</div>

			<h3>利用履歴</h3>
			<div class="history-empty">
				<c:forEach var="o" items="${orderList}">
					<div class="cart-item">
						<c:choose>
							<c:when test="${o.status == 0}">注文済</c:when>
							<c:when test="${o.status == 1}">支払済</c:when>
							<c:when test="${o.status == 2}">発送済</c:when>
							<c:when test="${o.status == 3}">店舗受取済</c:when>
							<c:when test="${o.status == 9}">キャンセル済み</c:when>
							<c:otherwise>完了</c:otherwise>
						</c:choose>
						<div class="item-info">
							<div>
								<a href="${pageContext.request.contextPath}/UserOrderHistory.action?mode=history&orderId=${o.orderId}">
								<fmt:formatDate value="${o.orderDate}" pattern="yyyy/MM/dd HH:mm"/>
								</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</main>
</body>

</html>