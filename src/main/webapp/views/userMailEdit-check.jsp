<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/accountstyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/headerStyle.css">
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>基本情報修正確認</title>
</head>
<body>
	<header class="top-header-layout">
		<jsp:include page="/contents/headerNoSearchbar.jsp" />
	</header>
	<main>
		<div class="account-container">
			<h2>新しいメールアドレスを確認してください</h2>
			<form action="${pageContext.request.contextPath}/UserMailEdit.action" method="post">
				新しいメールアドレス<br>
				<input type="text" name="mail" value="${editUser.mailAddress}" class="look" readonly>
				<input type="hidden" name="oldMail" value="${requestScope.userInfo.mailAddress}">
				<hr>
				<div class="buttons">
					<button type="submit" class="main" name="mode" value="update">登録</button>
		 			<button type="submit" name="mode" value="back">修正</button>
		 			<button type="submit" name="mode" value="cancel">取消</button>
				</div>
			</form>
		</div>
	</main>
</body>
</html>