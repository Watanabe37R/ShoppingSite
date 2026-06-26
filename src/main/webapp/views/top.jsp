<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/headerStyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/topstyle.css">

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報登録ホーム</title>
</head>
<body>
	<header class="top-header-layout">
		<jsp:include page="/contents/header.jsp" />
	</header>
	<main>
		<c:choose>
			<c:when test="${loginuser.manager == 1}">
				<jsp:include page="/contents/adminmenu.jsp" />
			</c:when>
			<c:otherwise>
				<jsp:include page="/contents/generalmenu.jsp" />
			</c:otherwise>
		</c:choose>
	</main>
</body>
</html>