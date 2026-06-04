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
<title>登録情報確認</title>
</head>
<body>
	<header class="top-header-layout">
		<jsp:include page="/contents/headerNoSearchbar.jsp" />
	</header>
	<main>
		<Div class="form">
			<form action="${pageContext.request.contextPath}/Registration.action" method="post">
				<h2>入力情報確認</h2>
				<p>ログインID<input type="text" name="id" value="${setUser.memberId}" readonly></p>
				<p>メールアドレス<input type="text" name="mail" value="${setUser.mailAddress}" readonly></p>
				<p>パスワード<input type="password" name="pw" value="${setUser.password}" readonly></p>
				<Div class="names">
					<span>苗字</span>
					<span>名前</span>
					<input type="text" name="lname" value="${setUser.lastName}" readonly>
					<input type="text" name="fname" value="${setUser.firstName}" readonly>
				</Div>
				<p>住所<input type="text" name="address" value="${setUser.address}" readonly></p>
				<div class="buttons">
					<button type="submit" class="main" name="mode" value="register">登録</button>
		 			<button type="submit" name="mode" value="back">修正</button>
		 			<button type="submit" name="mode" value="cancel">取消</button>
				</div>
			</form>
		</Div>
	</main>
</body>
</html>