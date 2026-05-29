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
	<header>
		<div class="upper">
			<div class="title">
				<h2>ログイン実装</h2>
			</div>
			<div class="contents">
				<c:choose>
					<c:when test="${not empty sessionScope.loginuser}">
						<c:choose>
							<c:when test="${loginuser.manager == 1}">
								<a href="#">お知らせ</a>｜<a href="#">管理情報</a>
							</c:when>
							<c:otherwise>
								<a href="#">お知らせ</a>｜<a href="#">カート</a>｜<a href="#">会員情報</a>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<a href="#">お知らせ</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<c:choose>
			<c:when test="${empty sessionScope.loginuser}">
				<div class="bottom">
					<div class="left">
						<a href="login-in.jsp">ログイン</a>
					</div>
					<div class="right"><a href="#">新規登録</a></div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="bottom">
					<div class="left">ようこそ、${sessionScope.loginuser.lastName}さん！</div>
					<div class="right">
						<a href="logout-in.jsp">ログアウト</a>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
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