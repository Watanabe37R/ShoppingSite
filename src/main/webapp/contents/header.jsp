<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--左：タイトル--%>
<div class="title">
	<a href="${pageContext.request.contextPath}/Top.action"><img src="${pageContext.request.contextPath}/img/ECsiteLogo.png"></a>
</div>

<%--中央：検索(管理者以外)--%>
<div class="search">
	<c:if
		test="${empty sessionScope.loginuser || sessionScope.loginuser.manager != 1}">
		<form action="${pageContext.request.contextPath}/ProductList.action" method="get">
			<select name="categoryId">
				<option value=""
				${empty selectedCategory ? 'selected' : ''}>
				すべて
				</option>
			
				<c:forEach var="c" items="${applicationScope.categoryList}">
					<option value="${c.categoryId}"
					${c.categoryId == selectedCategory ? 'selected ' : ''}>
					${c.categoryName}
					</option>
				</c:forEach>
			</select>
			<input type="text" name="keyword" value="${keyword}">
			<input type="submit" value="検索">
		</form>
	</c:if>
</div>

<%--ユーザー名+メニュー--%>
<div class="contents">
	<c:choose>
		<c:when test="${not empty sessionScope.loginuser}">
			<span class="user-name"> ようこそ、${loginuser.lastName}さん </span>
			<c:choose>
				<c:when test="${loginuser.manager == 1}">
					<div class="menu">
						<a href="#">お知らせ</a>
						<a href="#">管理情報</a>
						<a href="${pageContext.request.contextPath}/views/logout-in.jsp" class="btn-highlight">ログアウト</a>
					</div>
				</c:when>
				<c:otherwise>
					<div class="menu">
						<a href="#">お知らせ</a>
						<a href="${pageContext.request.contextPath}/UserCartView.action">カート</a>
						<a href="${pageContext.request.contextPath}/views/user-menu.jsp">会員情報</a>
						<a href="${pageContext.request.contextPath}/views/logout-in.jsp" class="btn-highlight">ログアウト</a>
					</div>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<div class="menu">
				<a href="${pageContext.request.contextPath}/views/login-in.jsp" class="btn-highlight">ログイン</a>
				<a href="${pageContext.request.contextPath}/views/registration-in.jsp" class="btn-highlight">新規登録</a>
			</div>
		</c:otherwise>
	</c:choose>
</div>