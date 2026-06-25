<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/GeneralMasterListStyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/headerStyle.css">
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>お知らせ一覧</title>
</head>
<body>
	<header class="top-header-layout">
		<jsp:include page="/contents/headerNoSearchbar.jsp" />
	</header>
	<main>
		<div class="top-bar">
			<!-- 新規登録 -->
			<a href="ManagerNotice.action?mode=create" class="btn">新規登録</a>
			<h2>お知らせ一覧表示</h2>
		</div>
		<!-- 一覧 -->
		<table class="master-table">
			<tr>
				<th>タイトル</th>
				<th>内容</th>
				<th>操作</th>
			</tr>
			<c:forEach var="n" items="${noticeList}">
				<tr>
					<td>
						<c:if test="${n.noticeType == '重要'}">
							<span style="color:red;">【重要】</span>
						</c:if>
						<c:if test="${n.noticeType == 'キャンペーン'}">
							<span style="color:orange;">【キャンペーン】</span>
						</c:if>				
						${n.title}</td>
					<td>
						<c:choose>
							<c:when test="${fn:length(n.content) > 20}">
								${fn:substring(n.content, 0, 20)}...
							</c:when>
							<c:otherwise>
								${n.content}
							</c:otherwise>
						</c:choose>
					</td>
					<td><a class="btn-edit"
						href="ManagerNotice.action?mode=edit&id=${n.id}"
						class="edit">編集</a> <a class="btn-delete"
						href="ManagerNotice.action?mode=delete&id=${n.id}"
						class="delete">削除</a></td>
				</tr>
			</c:forEach>
		</table>
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