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
		<h2>更新でエラーが発生しました。</h2>

		<c:forEach var="error" items="${errors}">
			<p style="color: red">${error}</p>
		</c:forEach>

		<p>
			<a
				href="${pageContext.request.contextPath}/UserEditView.action?mode=edit">基本情報更新画面に戻る</a>
		</p>
	</div>
</body>
</html>