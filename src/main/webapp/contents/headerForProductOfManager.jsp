<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--左：タイトル--%>
<div class="title">
	<a href="${pageContext.request.contextPath}/Top.action"><img src="${pageContext.request.contextPath}/img/ECsiteLogo.png"></a>
</div>

<%--中央：検索(管理者専用)--%>
<div class="search">
	<c:if
		test="${empty sessionScope.loginuser || sessionScope.loginuser.manager != 1}">
		<form action="${pageContext.request.contextPath}/ManagerProductList.action" method="get">
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
	<span class="user-name"> ようこそ、${loginuser.lastName}さん </span>
	<div class="menu">
		<a href="#">お知らせ</a>
		<a href="${pageContext.request.contextPath}/views/managerMaster-menu.jsp">管理情報</a>
		<a href="${pageContext.request.contextPath}/views/logout-in.jsp" class="btn-highlight">ログアウト</a>
	</div>
</div>