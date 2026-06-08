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
<title>メールアドレス修正エラー</title>
</head>
<body>
	<header class="top-header-layout">
		<jsp:include page="/contents/headerNoSearchbar.jsp" />
	</header>
	<main>
		<div class="account-container">
			<h2>更新でエラーが発生しました。</h2>
	
			<c:forEach var="error" items="${errors}">
				<p style="color: red">${error}</p>
			</c:forEach>
	
			<p><a href="${pageContext.request.contextPath}/UserLoginInfo.action?mode=edit">ログイン情報画面に戻る</a></p>
			<p><a href="${pageContext.request.contextPath}/views/top.jsp">トップに戻る</a></p>
		</div>
	</main>
</body>
</html>