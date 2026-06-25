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
<title>修正内容入力</title>
</head>
<body>
	<header class="top-header-layout">
		<jsp:include page="/contents/headerNoSearchbar.jsp" />
	</header>
	<main>
		<div class="account-container">
			<h2>本人情報修正入力</h2>
			<form action="${pageContext.request.contextPath}/UserEditCheck.action" method="post">
				名前<br>
				<div class="name">
					<input type="text" id="lname" name="lname" value="${userInfo.lastName}" required>
					<input type="text" id="fname" name="fname" value="${userInfo.firstName}" required>
					<span id="lnameM"></span>
					<span id="fnameM"></span>
				</div>
				<br>住所<br>
				<input type="text" id="address" name="address" value="${userInfo.address}" required>
				<span id="addressM"></span>
				<br>
				<hr>
				<input type="submit" id="submitBtn" value="確認">
			</form>
			
			<script src="${pageContext.request.contextPath}/js/userValidation.js"></script>
			<script>
				setupValidation({
				name:true,
				address:true
				});
			</script>
			<a href="${pageContext.request.contextPath}/UserMenu.action">会員情報ページへ戻る</a>
			<hr>
			<a href="${pageContext.request.contextPath}/Top.action">トップページへ戻る</a>
		</div>
	</main>
</body>
</html>