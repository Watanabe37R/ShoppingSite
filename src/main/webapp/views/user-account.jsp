<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="jakarta.tags.core"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/accountstyle.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント情報</title>
</head>

<body>
    <div class="account-container">
        <h2>ユーザアカウント情報</h2>

        <p class="login-id">
            ログインID：${sessionScope.loginuser.memberId}
        </p>

        <h3>登録情報</h3>
        <div class="link-buttons">
            <a href="${pageContext.request.contextPath}/UserEditView.action?mode=view">基本情報</a>
            <a href="user-loginInfo.jsp">ログイン情報</a>
        </div>

        <h3>利用履歴</h3>
        <div class="history-empty">
            現在、利用履歴はありません
        </div>
    </div>
</body>

</html>