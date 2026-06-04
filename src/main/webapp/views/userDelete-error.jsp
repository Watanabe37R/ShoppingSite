<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/accountstyle.css">
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="account-container">
		<h2>アカウント削除に失敗しました。</h2>

		<c:forEach var="error" items="${errors}">
			<p style="color: red">${error}</p>
		</c:forEach>

		<p>
			<a href="${pageContext.request.contextPath}/views/user-menu.jsp">会員情報ページに戻る</a>
		</p>
	</div>
</body>
</html>