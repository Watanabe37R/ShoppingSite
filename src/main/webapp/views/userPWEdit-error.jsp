<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/accountstyle.css">
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/headerStyle.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワード変更時エラー</title>
</head>
<body>
	<header class="top-header-layout">
		<jsp:include page="/contents/headerNoSearchbar.jsp" />
	</header>
	<main>
		<div class="account-container">
			<h2>変更時にエラーが発生しました。</h2>
	
			<c:forEach var="error" items="${errors}">
				<p style="color: red">${error}</p>
			</c:forEach>
	
			<p><a href="${pageContext.request.contextPath}/views/userPWEdit-in.jsp">パスワード変更画面に戻る</a></p>
			<p><a href="${pageContext.request.contextPath}/views/top.jsp">トップに戻る</a></p>
		</div>
	</main>
</body>
</html>