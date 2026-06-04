<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/membersstyle.css">
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<Div class="form">
		<h2>新規登録でエラーが発生しました。</h2>

		<c:forEach var="error" items="${errors}">
			<p style="color: red">${error}</p>
		</c:forEach>

		<p>
			<a
				href="${pageContext.request.contextPath}/views/registration-in.jsp">新規登録画面に戻る</a>
		</p>
	</Div>
</body>
</html>