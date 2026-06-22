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
<title>メーカー一覧</title>
</head>
<body>
	<header class="top-header-layout">
		<jsp:include page="/contents/headerNoSearchbar.jsp" />
	</header>
	<main>
		<div class="top-bar">
		    <!-- 新規登録 -->
		    <a href="ManagerMaker.action?mode=register" class="btn">新規登録</a>
		
		    <!-- 検索 -->
		    <form method="get" action="ManagerMakerList.action">
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
		
		    <c:forEach var="m" items="${MakerList}">
		        <tr>
		            <td><a class="contents" href="ManagerMaker.action?mode=view&categoryId=${m.makerId}">${m.makerId}</a></td>
		            <td><a class="contents" href="ManagerMaker.action?mode=view&categoryId=${m.makerId}">${m.makerName}</a></td>
		            <td>
		                <a class="btn-edit" href="ManagerMaker.action?mode=edit&categoryId=${m.makerId}" class="edit">編集</a>
		                <a class="btn-delete" href="ManagerMaker.action?mode=delete&categoryId=${c.makerId}" class="delete">削除</a>
		            </td>
		        </tr>
		    </c:forEach>
		</table>
	</main>
</body>
</html>