<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/accountstyle.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント削除確認</title>
</head>
<body>
	<div class="account-container">
		<h2 style="color: red;">アカウントの削除</h2>
		<p>本当に削除していいですか？</p>
		<p><a class="main" href="${pageContext.request.contextPath}/views/user-menu.jsp">会員情報ページへ戻る</a></p> 
		<p><a class="main" href="${pageContext.request.contextPath}/views/top.jsp">トップへ戻る</a></p>
		<hr>
		<p><a class="important" href="${pageContext.request.contextPath}/UserDelete.action">アカウントの削除</a></p>
	</div>
</body>
</html>