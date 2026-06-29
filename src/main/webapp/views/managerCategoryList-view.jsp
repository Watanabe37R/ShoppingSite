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
<title>カテゴリー一覧</title>
</head>
<body>
	<header class="top-header-layout">
		<c:if test="${mode!='reference'}">
			<jsp:include page="/contents/headerNoSearchbar.jsp" />
		</c:if>
	</header>
	<main>
		<div class="top-bar">
			<!-- 新規登録 -->
			<c:if test="${mode!='reference'}">
				<a href="ManagerCategory.action?mode=create" class="btn">新規登録</a>
			</c:if>
			<!-- モード表示 -->
			<c:choose>
				<c:when test="${mode == 'edit'}">
					<h2 class="edit">更新モード</h2>
				</c:when>
				<c:when test="${mode == 'delete'}">
					<h2 class="delete">削除モード</h2>
				</c:when>
				<c:when test="${mode == 'create'}">
					<h2 class="create">登録モード</h2>
				</c:when>
				<c:when test="${mode == 'reference'}">
					<h2 class="view">参照モード</h2>
				</c:when>
				<c:otherwise>
					<h2 class="view">閲覧モード</h2>
				</c:otherwise>
			</c:choose>

			<!-- 検索 -->
			<form method="get" action="ManagerCategoryList.action">
				<input type="text" id="search" name="keyword" value="${keyword}">
				<c:if test="${mode == 'reference'}">
					<input type="hidden" name="mode" value="reference">
				</c:if>
				<button type="submit">検索</button>
			</form>
			<script
				src="${pageContext.request.contextPath}/js/searchValidation.js"
				defer></script>
		</div>

		<!-- 一覧 -->
		<c:if test="${mode=='create'}">
			<form action="ManagerCategory.action" method="post">
				<table class="master-table">
					<tr>
						<th>カテゴリーID</th>
						<th>カテゴリー名</th>
						<th>操作</th>
					</tr>
					<tr>
						<input type="hidden" name="mode" value="insert">
						<td><input type="text" id="id" name="categoryId"> <span
							id="idM"></span></td>
						<td><input type="text" id="name" name="categoryName"> <span
							id="nameM"></span></td>
						<td><button id="submitBtn" class="btn-create" onclick="return confirm('新たにデータを登録してよろしいですか？')">新規登録</button> <a
							class="btn-view" href="ManagerCategoryList.action?mode=view"
							class="view">キャンセル</a></td>
					</tr>
				</table>
			</form>
		</c:if>
		<c:if test="${mode=='edit'}">
			<form action="ManagerCategory.action" method="post">
				<table class="master-table">
					<tr>
						<th>カテゴリーID</th>
						<th>カテゴリー名</th>
						<th>操作</th>
					</tr>
					<tr>
						<input type="hidden" name="mode" value="update">
						<td><input type="hidden" name="categoryId" value="${categoryId}">
							${categoryId}</td>
						<td><input type="text" id="name" name="categoryName"
							value="${categoryName}"> <span id="nameM"></span></td>
						<td><button id="" class="btn-edit" onclick="return confirm('データを更新しますか？')">更新</button> <a
							class="btn-view" href="ManagerCategoryList.action?mode=view"
							class="view">キャンセル</a></td>
					</tr>
				</table>
			</form>
		</c:if>
		<c:if test="${mode=='delete'}">
			<form action="ManagerCategory.action" method="post">
				<table class="master-table">
					<tr>
						<th>カテゴリーID</th>
						<th>カテゴリー名</th>
						<th>操作</th>
					</tr>
					<tr>
						<input type="hidden" name="mode" value="deleteExecute">
						<td><input type="hidden" name="categoryId" value="${categoryId}">
							${categoryId}</td>
						<td><input type="hidden" name="categoryName"
							value="${categoryName}"> ${categoryName}</td>
						<td><a class="btn-view"
							href="ManagerCategoryList.action?mode=view" class="view">キャンセル</a>
							<button class="btn-delete" onclick="return confirm('本当に削除しますか？')">
								削除</a></td>
					</tr>
				</table>
			</form>
		</c:if>
		<c:if test="${mode=='view'}">
			<table class="master-table">
				<c:if test="${not empty keyword}">
				${keyword}での検索結果　<a href="ManagerCategoryList.action?mode=view">一覧表示に戻る</a>
				</c:if>
				<tr>
					<th>カテゴリーID</th>
					<th>カテゴリー名</th>
					<th>操作</th>
				</tr>
				<c:forEach var="c" items="${CategoryList}">
					<tr>
						<td>${c.categoryId}</td>
						<td>${c.categoryName}</td>
						<td><a class="btn-edit"
							href="ManagerCategory.action?mode=edit&categoryId=${c.categoryId}"
							class="edit">編集</a> <a class="btn-delete"
							href="ManagerCategory.action?mode=delete&categoryId=${c.categoryId}"
							class="delete">削除</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<c:if test="${mode=='reference'}">
			<table class="master-table">
				<c:if test="${not empty keyword}">
				${keyword}での検索結果　<a href="ManagerCategoryList.action?mode=reference">一覧表示に戻る</a>
				</c:if>
				<tr>
					<th>カテゴリーID</th>
					<th>カテゴリー名</th>
				</tr>
				<c:forEach var="c" items="${CategoryList}">
					<tr onclick="selectCategory('${c.categoryId}', '${c.categoryName}')">
						<td>${c.categoryId}</td>
						<td>${c.categoryName}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</main>
	<script>
		const mode = "${mode}";
	</script>
	<script
		src="${pageContext.request.contextPath}/js/makerAndCategoryValidation.js"></script>
	<script src="${pageContext.request.contextPath}/js/referrenceData.js"></script>
	<script>
		setupValidationByMode(mode);
	</script>

</body>
</html>