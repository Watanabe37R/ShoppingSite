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
					<input type="text" name="lname" value="${userInfo.lastName}">
					<input type="text" name="fname" value="${userInfo.firstName}">
				</div>
				<br>住所<br>
				<input type="text" name="address" value="${userInfo.address}">
				<br>
				<hr>
				<input type="submit" value="確認">
			</form>
		</div>
	</main>
</body>
</html>