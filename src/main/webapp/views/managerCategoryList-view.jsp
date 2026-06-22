<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/GeneralMasterListStyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/headerStyle.css">
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カテゴリ一覧</title>
</head>
<body>
	<header class="top-header-layout">
		<jsp:include page="/contents/headerNoSearchbar.jsp" />
	</header>
	<main>
		<div class="top-bar">
		    <!-- 新規登録 -->
		    <a href="ManagerCategory.action?mode=register" class="btn">新規登録</a>
		
		    <!-- 検索 -->
		    <form method="get" action="ManagerCategoryList.action">
		        <input type="text" name="keyword" value="${keyword}">
		        <button type="submit">検索</button>
		    </form>
		</div>
		
		<!-- 一覧 -->
		<table class="master-table">
			<c:if test="${not empty keyword}">
			${keyword}での検索結果
			</c:if>
		    <tr>
		        <th>カテゴリID</th>
		        <th>カテゴリ名</th>
		        <th>操作</th>
		    </tr>
		
		    <c:forEach var="c" items="${CategoryList}">
		        <tr>
		            <td><a class="contents"href="ManagerCategory.action?mode=view&categoryId=${c.categoryId}">${c.categoryId}</a></td>
		            <td><a class="contents"href="ManagerCategory.action?mode=view&categoryId=${c.categoryId}">${c.categoryName}</a></td>
		            <td>
		                <a class="btn-edit" href="ManagerCategory.action?mode=edit&categoryId=${c.categoryId}" class="edit">編集</a>
		                <a class="btn-delete" href="ManagerCategory.action?mode=delete&categoryId=${c.categoryId}" class="delete">削除</a>
		            </td>
		        </tr>
		    </c:forEach>
		</table>
	</main>
</body>
</html>