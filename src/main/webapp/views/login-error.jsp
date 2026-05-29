<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン失敗</title>
</head>
<body>
<h2>ログインに失敗しました。</h2>
${loginerrormessage}
	<p>１０秒後に戻ります</p>
	<meta http-equiv="refresh" content="10;url=${pageContext.request.contextPath}/views/user-menu.jsp">
	<p><a href="${pageContext.request.contextPath}/views/user-menu.jsp">すぐ戻る</a></p>
</body>
</html>