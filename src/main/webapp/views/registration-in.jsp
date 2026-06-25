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
<title>新規登録</title>
</head>
<body>
	<header class="top-header-layout">
		<jsp:include page="/contents/headerNoSearchbar.jsp" />
	</header>
	<main>
		<Div class="form">
			<form action="${pageContext.request.contextPath}/RegistrationCheck.action"
				method="post">
				<h2>登録するログインID、メールアドレス、パスワードを設定してください。</h2>
				<p>ログインID<input type="text" id="id" name="id" value="${setUser.memberId}" required></p>
				<span id="idM"></span>
				<p>メールアドレス<input type="text" id="mail" name="mail" value="${setUser.mailAddress}" required></p>
				<span id="mailM"></span>
				<p>メールアドレス(確認)<input type="text" id="mailConfirm" name="mailConfirm" value="${setUser.mailAddress}" required></p>
				<span id="mailConfirmM"></span>
				<p>パスワード<input type="password" id="pw" name="pw" required></p>
				<span id="pwM"></span>
				<p>パスワード(確認)<input type="password" id="pwConfirm" name="confirm" required></p>
				<span id="pwConfirmM"></span>
				<div class="names">
					<span>苗字</span>
					<span>名前</span>
					<input type="text" id="lname" name="lname" value="${setUser.lastName}" required>
					<input type="text" id="fname" name="fname" value="${setUser.firstName}" required>
					<span id="lnameM"></span>
					<span id="fnameM"></span>
				</div>
				<p>住所<input type="text" id="address" name="address" value="${setUser.address}" required></p>
				<span id="addressM"></span>
				<input type="submit" id="submitBtn" value="次へ">

				<script>
					const contextPath = "${pageContext.request.contextPath}";
				</script>
				<script src="${pageContext.request.contextPath}/js/userValidation.js"></script>
				<script>
					setupValidation({
					id: true,
					mailConfirm: true,
					pwConfirm:true,
					name:true,
					address:true,
					duplicate:true
					});
				</script>
			</form>
		</Div>
	</main>
</body>
</html>