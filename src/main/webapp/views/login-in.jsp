<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/membersstyle.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
	<Div class="form">
		<form action="${pageContext.request.contextPath}/Login.action" method="post">
			<h2>ログイン情報を入力してください</h2>
			<p>ログインID<input type="text" name="id" required></p>
			<p>パスワード<input type="password" name="pw" required></p>
			<input type="hidden" name="force" value="${param.force}">
			<input type="submit" value="ログイン">
		</form>
		<hr>
		始めてご利用の方
		<form action="${pageContext.request.contextPath}/Registration.action" method="post">
			<input type="submit" value="新規登録">
		</form>
	</Div>
</body>
</html>