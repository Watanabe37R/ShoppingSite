<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="hero admin-hero">
    <div class="hero-inner">
        <h1>管理者ダッシュボード</h1>

        <p class="catch">
            サイトの管理・運営を行います
        </p>

        <p class="sub">
            商品管理・注文確認・ユーザー管理など、<br>
            必要な操作をこちらから行えます。
        </p>

        <a href="${pageContext.request.contextPath}/views/managerMaster-menu.jsp" class="hero-btn admin-btn">
            管理マスタ一覧へ
        </a>
    </div>