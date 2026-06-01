<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/membersstyle.css">
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン失敗</title>
</head>
<body>
	<Div class="form">
		<h2>ログインに失敗しました。</h2>
		${loginerrormessage}
		<c:choose>
			<c:when test="${requestScope.errorType=='sessionConflict'}">
				<p>
					<a href="${pageContext.request.contextPath}/views/login-in.jsp">ログイン画面に戻る</a>
				</p>


				<a
					href="${pageContext.request.contextPath}/views/login-in.jsp?force=true">
					強制ログインを行う </a>

			</c:when>
			<c:otherwise>
				<p>
					<a href="${pageContext.request.contextPath}/views/login-in.jsp">ログイン画面に戻る</a>
				</p>
			</c:otherwise>
		</c:choose>
	</Div>
</body>
</html>