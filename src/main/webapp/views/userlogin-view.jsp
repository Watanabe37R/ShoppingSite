<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/headerStyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/accountstyle.css">

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
				<form action="${pageContext.request.contextPath}/UserLoginInfo.action" method="post">
				<p>${requestScope.userInfo.mailAddress}</p>
				<input type="hidden" name="mode" value="edit">
				<input type="submit" value="メールアドレスの変更" class="link-btn">
				</form>
			</div>

			<h3>パスワード</h3>
			<div class="link-buttons">
				<a href="${pageContext.request.contextPath}/views/userPWEdit-in.jsp">パスワードの変更</a>
			</div>
			<br>
			<hr>
			<h3 style="color: red;">アカウントの削除</h3>
			<div class="link-buttons">
				<%--${pageContext.request.contextPath}--%>
				<a class="important" href="${pageContext.request.contextPath}/views/userDelete-in.jsp">アカウントの削除</a>
			</div>
		</div>
	</main>
</body>
</html>