<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/membersstyle.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン失敗</title>
</head>
<body>
	<Div class="form">
		<h2>ログインに失敗しました。</h2>
		${loginerrormessage}
		<p>１０秒後に戻ります</p>
		<meta http-equiv="refresh" content="10;url=${pageContext.request.contextPath}/views/login-in.jsp">
		<p><a href="${pageContext.request.contextPath}/views/login-in.jsp">すぐ戻る</a></p>
	</Div>
</body>
</html>