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
				<input type="text" id="mail" name="mail" value="${requestScope.editUser.mailAddress}" required>
				<span id="mailM"></span>
				<br>
				メールアドレス(確認)<br>
				<input type="text" id="mailConfirm" name="mailConfirm" required>
				<span id="mailConfirmM"></span>
				<hr>
				<input type="submit" id="submitBtn" value="確認">
			</form>
			<script>
				const contextPath = "${pageContext.request.contextPath}";
			</script>
			<script src="${pageContext.request.contextPath}/js/userValidation.js"></script>
			<script>
				setupValidation({
				mailConfirm: true,
				duplicate:true
				});
			</script>
			<a href="${pageContext.request.contextPath}/UserMenu.action">会員情報ページへ戻る</a>
			<hr>
			<a href="${pageContext.request.contextPath}/Top.action">トップページへ戻る</a>
		</div>
	</main>
</body>
</html>