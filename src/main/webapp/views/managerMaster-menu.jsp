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
<title>マスタ管理情報</title>
</head>
<body>
	<header class="top-header-layout">
		<jsp:include page="/contents/headerNoSearchbar.jsp" />
	</header>
	<main>
		<div class="account-container">
			<h2>マスタ管理情報</h2>
			<h3>商品マスタ</h3>
			<div class="link-buttons">
				<form action="${pageContext.request.contextPath}/ManagerProductList.action" method="get">
				<input type="submit" value="商品マスタ" class="link-btn">
				</form>
			</div>
			
			<h3>メーカーマスタ</h3>
			<div class="link-buttons">
				<form action="${pageContext.request.contextPath}/ManagerMakerList.action" method="get">
				<input type="hidden" name="mode" value="view">
				<input type="submit" value="メーカーマスタ" class="link-btn">
				</form>
			</div>

			<h3>カテゴリマスタ</h3>
			<div class="link-buttons">
				<form action="${pageContext.request.contextPath}/ManagerCategoryList.action" method="get">
				<input type="hidden" name="mode" value="view">
				<input type="submit" value="カテゴリマスタ" class="link-btn">
				</form>
			</div>

			<h3>会員マスタ</h3>
			<div class="link-buttons">
				<form action="${pageContext.request.contextPath}/ManagerMemberList.action" method="get">
				<input type="submit" value="会員マスタ" class="link-btn">
				</form>
			</div>

			<h3>お知らせマスタ</h3>
			<div class="link-buttons">
				<form action="${pageContext.request.contextPath}/ManagerNoticeList.action" method="get">
				<input type="submit" value="お知らせマスタ" class="link-btn">
				</form>
			</div>
		</div>
	</main>
</body>
</html>