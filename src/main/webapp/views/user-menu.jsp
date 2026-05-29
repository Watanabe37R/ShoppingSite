<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="jakarta.tags.core"%>
	<link rel="stylesheet" href="../css/topstyle.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header> 
		<h2>ログイン実装</h2>
		<hr>
		<c:choose>
			<c:when test="${sessionScope.loginuser.memberId==null}" >
				<div class="bottom">
					<div class="left"><a href="login-in.jsp">ログイン</a></div>
					<div class="right">新規登録</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="bottom">
					<div class="left">ようこそ、${sessionScope.loginuser.lastName}さん！</div>
					<div class="right"><a href="logout-in.jsp">ログアウト</a></div>
				</div>
			</c:otherwise>
		</c:choose>
	</header>
	<main>
		めいんこんてんちゅ
	</main>
</body>
</html>