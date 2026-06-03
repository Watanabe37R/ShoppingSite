<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/accountstyle.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
</body>
</html>