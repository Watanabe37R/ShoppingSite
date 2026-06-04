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
<title>基本情報修正確認</title>
</head>
<body>
	<header class="top-header-layout">
		<jsp:include page="/contents/headerNoSearchbar.jsp" />
	</header>
	<main>
		<div class="account-container">
			<h2>本人情報修正確認</h2>
			<form action="${pageContext.request.contextPath}/UserEdit.action" method="post">
				名前<br>
				<div class="name">
					<input type="text" name="lname" value="${editUser.lastName}" class="look" readonly>
					<input type="text" name="fname" value="${editUser.firstName}" class="look" readonly>
				</div>
				<br>住所<br>
				<input type="text" name="address" value="${editUser.address}" class="look" readonly>
				<br>
				<hr>
				<div class="buttons">
					<button type="submit" class="main" name="mode" value="update">登録</button>
		 			<button type="submit" name="mode" value="back">修正</button>
		 			<button type="submit" name="mode" value="cancel">取消</button>
				</div>
			</form>
		</div>
	</main>
</body>
</html>