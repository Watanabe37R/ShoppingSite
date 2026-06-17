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
			<h2>ログアウトしますか？</h2>
			<p><a href="${pageContext.request.contextPath}/Logout.action">ログアウト</a></p>
			<p><a href="${pageContext.request.contextPath}/Top.action">戻る</a></p>
		</Div>
	</main>
</body>
</html>