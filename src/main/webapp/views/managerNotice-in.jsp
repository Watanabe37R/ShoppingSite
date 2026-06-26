<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/noticeStyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/headerStyle.css">
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>お知らせ詳細・編集画面</title>
</head>
<body>
	<header class="top-header-layout">
		<jsp:include page="/contents/headerNoSearchbar.jsp" />
	</header>
	<main>
		<c:choose>
			<c:when test="${mode == 'create' || mode == 'edit'}">
				<div class="top-bar">
					<!-- 新規登録 -->
					<a href="ManagerNotice.action?mode=create" class="btn">新規登録</a>
					<c:choose>
						<c:when test="${mode == 'create'}">
							<h2 class="create">登録モード</h2>
						</c:when>
						<c:otherwise>
							<h2 class="edit">編集モード</h2>
						</c:otherwise>
					</c:choose>
					<div class="buttons">
						<c:if test="${mode == 'edit'}">
							<a class="change" href="ManagerNotice.action?mode=delete&id=${notice.id}">モード切替</a>
						</c:if>
							<a class="return" href="ManagerNoticeList.action">一覧に戻る</a>
						</div>
					</div>
				<form action="ManagerNoticeProcess.action" method="post">
					<c:choose>
						<c:when test="${mode == 'edit'}">
							<input type="hidden" name="id" value="${notice.id}">
							<input type="hidden" name="mode" value="update">
						</c:when>
						<c:otherwise>
							<input type="hidden" name="mode" value="insert">
						</c:otherwise>
					</c:choose>

					<div class="form-area">
						<div class="form-row">
							<label>タイトル</label>
							<input type="text" id="title" name="title" value="${notice.title}">
							<span id="titleM"></span>
						</div>
						<div class="form-row">
							<label>内容</label>
							<Textarea id="content" name="content">${notice.content}</Textarea>
							<span id="contentM"></span>
						</div>
						<div class="form-row">
							<label>お知らせタイプ</label>
							<div class="radio-group">
								<label>	
									<input type="radio" name="noticeType" value="重要"
									${mode == 'create' ? '' : (notice.noticeType == '重要' ? 'checked' : '')}>
									重要
								</label>
								<label>
									<input type="radio" name="noticeType" value="キャンペーン"
									${mode == 'create' ? '' : (notice.noticeType == 'キャンペーン' ? 'checked' : '')}>
									キャンペーン
								</label>
								<label>
									<input type="radio" name="noticeType" value="通常" 
									${mode == 'create' ? 'checked' : (notice.noticeType == '通常' ? 'checked' : '')}>
									通常
								</label>
							</div>
						</div>
						<div class="form-row">
							<label>掲載有無</label>
							<div class="radio-group">
								<label>	
									<input type="radio" name="display" value="1"
									${mode == 'create' ? 'checked' : (notice.display == '1' ? 'checked' : '')}>
									掲載
								</label>
								<label>
									<input type="radio" name="display" value="0"
									${mode == 'create' ? '' : (notice.display == '0' ? 'checked' : '')}>
									非掲載
								</label>
							</div>
						</div>
						<div class="form-row">
							<label>掲載開始日</label>
							<input type="date" name="start" value="${notice.startStr}">
						</div>
						<div class="form-row">
							<label>掲載終了日</label>
							<input type="date" name="end" value="${notice.endStr}">
						</div>
					</div>
					<input type="submit" id="submitBtn" class="${mode == 'create' ? 'btn register-btn' : 'btn edit-btn'}" value="${mode == 'create' ? '登録' : '更新'}">
				</form>
					<script
						src="${pageContext.request.contextPath}/js/noticeValidation.js"></script>
					<script>
						setupValidationByMode();
					</script>
			</c:when>
			<c:otherwise>
				<div class="top-bar">
					<!-- 新規登録 -->
					<a href="ManagerNotice.action?mode=create" class="btn">新規登録</a>
					<c:choose>
						<c:when test="${mode == 'delete'}">
							<h2 class="delete">削除モード</h2>
						</c:when>
						<c:otherwise>
							<h2 class="view">閲覧モード</h2>
						</c:otherwise>
					</c:choose>
					<div class="buttons">
						<c:choose>
							<c:when test="${mode == 'view'}">
								<a class="change" href="ManagerNotice.action?mode=edit&id=${notice.id}">モード切替</a>
							</c:when>
							<c:otherwise>
								<a class="change" href="ManagerNotice.action?mode=view&id=${notice.id}">モード切替</a>
							</c:otherwise>
						</c:choose>
						<a class="return" href="ManagerNoticeList.action">一覧に戻る</a>
					</div>
				</div>
				<form action="ManagerNoticeProcess.action" method="post">
					<c:if test="${mode == 'delete'}">
						<input type="hidden" name="id" value="${notice.id}">
						<input type="hidden" name="mode" value="deleteExecute">
					</c:if>
					<div class="form-area">
						<div class="form-row">
							<label>タイトル</label>
							<input type="text" id="title" name="title" value="${notice.title}" readonly>
						</div>
						<div class="form-row">
							<label>内容</label>
							<textarea name="content" readonly>${notice.content}</textarea>
						</div>
						<div class="form-row">
							<label>お知らせタイプ</label>
							<input type="text" name="noticeType" value="${notice.noticeType}" readonly>
						</div>
						<div class="form-row">
							<label>掲載有無</label>
							<input type="text" name="noticeType" value="${notice.display==1 ? '掲載' : '非掲載'}" readonly>
						</div>
							<div class="form-row">
							<label>掲載開始日</label>
							<input type="date" name="start" value="${notice.startStr}" readonly>
						</div>
						<div class="form-row">
							<label>掲載終了日</label>
							<input type="date" name="end" value="${notice.endStr}" readonly>
						</div>
					</div>
					<c:if test="${mode == 'delete'}">
						<input type="submit" value="削除" class="btn delete-btn" onclick="return confirm('本当に削除しますか？');">
					</c:if>
				</form>
			</c:otherwise>
		</c:choose>
	</main>
</body>
</html>