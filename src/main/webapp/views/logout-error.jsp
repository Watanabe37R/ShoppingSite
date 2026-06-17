<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/membersstyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/headerStyle.css">
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト</title>
</head>
<body>
	<header class="top-header-layout">
		<jsp:include page="/contents/headerNoSearchbar.jsp" />
	</header>
	<main>
		<Div class="form">
			すでにログアウト済みです。
			<p>三秒後に戻ります</p>
			<meta http-equiv="refresh" content="3;url=${pageContext.request.contextPath}/views/Top.action">
			<p><a href="${pageContext.request.contextPath}/Top.action">すぐ戻る</a></p>
		</Div>
	</main>
</body>
</html>