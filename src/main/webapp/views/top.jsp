<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="jakarta.tags.core"%>
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
					<div class="left">ログイン</div>
					<div class="right">新規登録</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="bottomsingle">
					ようこそ、${sessionScope.loginuser.lastName}さん！
				</div>
			</c:otherwise>
		</c:choose>
	</header>
	<main>
		めいんこんてんちゅ
	</main>
</body>
</html>