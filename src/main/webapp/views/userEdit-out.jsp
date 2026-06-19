<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/accountstyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/headerStyle.css">
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修正完了</title>
</head>
<body>
	<header class="top-header-layout">
		<jsp:include page="/contents/headerNoSearchbar.jsp" />
	</header>
	<main>
		<div class="account-container">
			<h2>会員情報を修正しました。</h2>
			<p><a href="${pageContext.request.contextPath}/UserMenu.action">会員情報ページへ戻る</a></p>
			<p><a href="${pageContext.request.contextPath}/Top.action">ホームへ戻る</a>
		</div>
	</main>
</body>
</html>