<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/membersstyle.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト完了</title>
</head>
<body>
	<Div class="form">
		<h2>ログアウトしました。</h2>
		<p>三秒後に戻ります</p>
		<meta http-equiv="refresh" content="3;url=user-menu.jsp">
		<p><a href="user-menu.jsp">すぐ戻る</a></p>
	</Div>
</body>
</html>