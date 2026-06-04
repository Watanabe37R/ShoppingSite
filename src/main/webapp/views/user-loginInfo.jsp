<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/accountstyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/headerStyle.css">
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン情報</title>
</head>
<body>
	<header class="top-header-layout">
		<jsp:include page="/contents/headerNoSearchbar.jsp" />
	</header>
	<main>
		<div class="account-container">
			<h2>ユーザログイン情報</h2>

			<p class="login-id">ログインID：${sessionScope.loginuser.memberId}</p>

			<h3>メールアドレス</h3>
			<div class="link-buttons">
				<%--${pageContext.request.contextPath}--%>
				<a href="#">メールアドレスの変更</a>
			</div>

			<h3>パスワード</h3>
			<div class="link-buttons">
				<%--${pageContext.request.contextPath}--%>
				<a href="#">パスワードの変更</a>
			</div>
			<br>
			<hr>
			<h3 style="color: red;">アカウントの削除</h3>
			<div class="link-buttons">
				<%--${pageContext.request.contextPath}--%>
				<a class="important" href="userDelete-in.jsp">アカウントの削除</a>
			</div>
		</div>
	</main>
</body>
</html>