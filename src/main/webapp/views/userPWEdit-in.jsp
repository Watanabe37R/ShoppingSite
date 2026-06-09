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
<title>パスワード変更</title>
</head>
<body>
	<header class="top-header-layout">
		<jsp:include page="/contents/headerNoSearchbar.jsp" />
	</header>
	<main>
		<div class="account-container">
			<h2>古いパスワードと新しいパスワードを入力してください</h2>
			<form action="${pageContext.request.contextPath}/UserPWEdit.action" method="post">
				現在のパスワード<br>
				<input type="password" name="pwOld" required>
				<hr>
				新しいパスワード<br>
				<input type="password" id="pw" name="pw" required>
				<span id="pwM"></span>
				<br>
				新しいパスワード(確認)<br>
				<input type="password" id="pwConfirm" name="pwConfirm" required>
				<span id="pwConfirmM"></span>
				<script src="${pageContext.request.contextPath}/js/userValidation.js" defer></script>
				<hr>
				<input type="submit" id="submitBtn" value="確認">
			</form>
		</div>
	</main>
</body>
</html>