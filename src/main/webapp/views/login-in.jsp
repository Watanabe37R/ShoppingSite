<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/membersstyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/headerStyle.css">
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
	<header class="top-header-layout">
		<jsp:include page="/contents/headerNoSearchbar.jsp" />
	</header>
	<main>
		<Div class="form">
			<form action="${pageContext.request.contextPath}/Login.action" method="post">
				<h2>ログイン情報を入力してください</h2>
				<p>ログインID<input type="text" id="id" name="id" required></p>
				<span id="idM"></span>
				<p>パスワード<input type="password" id="pw" name="pw" required></p>
				<span id="pwM"></span>
				<script src="${pageContext.request.contextPath}/js/userValidation.js" defer></script>
				<input type="hidden" name="force" value="${param.force}">
				<input type="submit" id="submitBtn" value="ログイン">
			</form>
			<script src="${pageContext.request.contextPath}/js/userValidation.js" defer></script>
			<hr>
			始めてご利用の方
			<form action="${pageContext.request.contextPath}/views/registration-in.jsp" method="post">
				<input type="submit" value="新規登録">
			</form>
		</Div>
	</main>
</body>
</html>