<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/Login.action" method="post">
		<h2>ログイン情報を入力してください</h2>
		<p>ログインID<input type="text" name="id"></p>
		<p>パスワード<input type="password" name="pw"></p>
		<input type="submit" value="ログイン">
	</form>
</body>
</html>