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
		<h2>本人情報</h2>
		<form action="${pageContext.request.contextPath}/UserEditView.action" method="post">
			名前<br>
			<div class="name">
				<input type="text" name="lname" value="${userInfo.lastName}" class="look" readonly>
				<input type="text" name="fname" value="${userInfo.firstName}" class="look" readonly>
			</div>
			<br>住所<br>
			<input type="text" name="address" value="${userInfo.address}" class="look" readonly>
			<br>
			<hr>
			<input type="hidden" name="mode" value="edit">
			<input type="submit" value="本人情報の編集">
		</form>
	</div>
</body>
</html>