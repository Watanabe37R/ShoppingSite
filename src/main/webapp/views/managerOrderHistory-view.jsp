<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/managerHistoryStyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/headerStyle.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文履歴一覧</title>
</head>
<body>
	<header class="top-header-layout">
		<jsp:include page="/contents/headerNoSearchbar.jsp" />
	</header>
	<main>
		<div class="main-upper">
			<h1>注文履歴一覧</h1>
		</div>
<table class="order-table">
	<thead>
		<tr>
			<th>ステータス</th>
			<th>会員ID</th>
			<th>注文日時</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="o" items="${orderList}">
			<tr>
				<td>
					<c:choose>
						<c:when test="${o.status == 0}">注文済</c:when>
						<c:when test="${o.status == 1}">支払済</c:when>
						<c:when test="${o.status == 2}">発送済</c:when>
						<c:when test="${o.status == 3}">店舗受取済</c:when>
						<c:when test="${o.status == 9}">キャンセル済み</c:when>
					</c:choose>
				</td>

				<td>${o.memberId}</td>

				<td>
					<a class="date-link"
					   href="${pageContext.request.contextPath}/ManagerOrderHistory.action?mode=view&orderId=${o.orderId}">
						<fmt:formatDate value="${o.orderDateTs}" pattern="yyyy/MM/dd HH:mm"/>
					</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
	</main>
</body>
</html>