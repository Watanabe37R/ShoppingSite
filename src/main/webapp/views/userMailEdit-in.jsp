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
<title>メールアドレス編集</title>
</head>
<body>
	<header class="top-header-layout">
		<jsp:include page="/contents/headerNoSearchbar.jsp" />
	</header>
	<main>
		<div class="account-container">
			<h2>新しいメールアドレスを入力してください</h2>
			<form action="${pageContext.request.contextPath}/UserMailEditCheck.action" method="post">
				現在ご利用中のメールアドレス<br>
				<p>${requestScope.userInfo.mailAddress}</p>
				<input type="hidden" name="oldMail" value="${requestScope.userInfo.mailAddress}">
				新しいメールアドレス<br>
				<input type="text" name="mail" value="${requestScope.editUser.mailAddress}" required>
				<br>
				メールアドレス(確認)<br>
				<input type="text" name="mailConfirm" required>
				<hr>
				<input type="submit" value="確認">
			</form>
		</div>
	</main>
</body>
</html>