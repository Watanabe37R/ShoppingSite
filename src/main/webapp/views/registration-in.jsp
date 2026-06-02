<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/membersstyle.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<Div class="form">
		<form action="${pageContext.request.contextPath}/RegistrationCheck.action"
			method="post">
			<h2>登録するログインID、メールアドレス、パスワードを設定してください。</h2>
			<p>ログインID<input type="text" id="id" name="id" value="${setUser.memberId}" required></p>
			<p>メールアドレス<input type="text" id="mail" name="mail" value="${setUser.mailAddress}" required></p>
			<span id="messageMail1"></span>
			<p>メールアドレス(確認)<input type="text" id="mailConfirm" name="mailConfirm" value="${setUser.mailAddress}" required></p>
			<span id="messageMail2"></span>
			<p>パスワード<input type="password" id="pw" name="pw" required></p>
			<span id="message1"></span>
			<p>パスワード(確認)<input type="password" id="confirm" name="confirm" required></p>
			<span id="message2"></span>
			<Div class="names">
				<span>苗字</span>
				<span>名前</span>
				<input type="text" id="lname" name="lname" value="${setUser.lastName}" required>
				<input type="text" id="fname" name="fname" value="${setUser.firstName}" required>
			</Div>
			<p>住所<input type="text" id="address" name="address" value="${setUser.address}" required></p>
			<input type="submit" id="submitBtn" value="次へ">
			<script src="${pageContext.request.contextPath}/js/userValidation.js" defer></script>
		</form>
	</Div>
</body>
</html>