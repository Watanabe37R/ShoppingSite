<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/membersstyle.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト</title>
</head>
<body>
	<Div class="form">
		<p>ログアウトしますか？</p>
		<p><a href="${pageContext.request.contextPath}/Logout.action">ログアウト</a></p>
		<p><a href="user-menu.jsp">戻る</a></p>
	</Div>
</body>
</html>