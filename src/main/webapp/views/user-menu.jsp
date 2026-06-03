<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/topstyle.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報登録ホーム</title>
</head>
<body>
	<header class="top-header-layout">

		<%--左：タイトル--%>
		<div class="title">
			<h2>EC-Site</h2>
		</div>

		<%--中央：検索(管理者以外)--%>
		<div class="search">
			<c:if
				test="${empty sessionScope.loginuser || loginuser.manager != 1}">
				<form action="search" method="post">
					<input type="text" name="name">
					<input type="submit" value="検索">
				</form>
			</c:if>
		</div>

		<%--ユーザー名+メニュー--%>
		<div class="contents">
			<c:choose>
				<c:when test="${not empty sessionScope.loginuser}">
					<span class="user-name"> ようこそ、${loginuser.lastName}さん </span>
					<c:choose>
						<c:when test="${loginuser.manager == 1}">
							<div class="menu">
								<a href="#">お知らせ</a> <a href="#">管理情報</a>
								<a href="logout-in.jsp" class="btn-highlight">ログアウト</a>
							</div>
						</c:when>
						<c:otherwise>
							<div class="menu">
								<a href="#">お知らせ</a> <a href="#">カート</a> <a href="user-account.jsp">会員情報</a>
								<a href="logout-in.jsp" class="btn-highlight">ログアウト</a>
							</div>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<div class="menu">
						<a href="login-in.jsp" class="btn-highlight">ログイン</a> <a href="registration-in.jsp" class="btn-highlight">新規登録</a>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</header>
	<main>
		<c:choose>
			<c:when test="${loginuser.manager == 1}">
				<%@ include file="adminmenu.jsp"%>
			</c:when>
			<c:otherwise>
				<%@ include file="generalmenu.jsp"%>
			</c:otherwise>
		</c:choose>
	</main>
</body>
</html>