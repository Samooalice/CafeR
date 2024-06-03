<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Son Main</title>
<link rel="stylesheet" type="text/css" href="/cafe/css/w3.css">
<link rel="stylesheet" type="text/css" href="/cafe/css/user.css">
<script type="text/javascript" src="/cafe/js/jquery-3.7.1.min.js"></script>
<style type="text/css">

</style>
<script type="text/javascript">
	$(document).ready(function(){
		$("#logout").click(function(){
			$(location).attr("href", "/cafe/member/logout.cafe");
		});

		$("#login").click(function(){
			$(location).attr("href", "/cafe/member/login.cafe");
		});

		$("#join").click(function(){
			$(location).attr("href", "/cafe/member/join.cafe");
		});

		$("#reboard").click(function(){
			$(location).attr("href", "/cafe/reboard/reboard.cafe");
		});
	});
</script>
</head>
<body>
	<form method="POST" id="frm">
	</form>
	
	<div class="w3-content mxw650 w3-center">
<c:if test="${empty SID }">
		<h1 class="w3-deep-purple w3-padding w3-card-4">Cafe Main</h1>
</c:if>
<c:if test="${not empty SID }">
		<h1 class="w3-deep-purple w3-padding w3-card-4">[ ${SID} ]님 환영합니다</h1>
</c:if>
		<div class="w3-col mgt10">
<!-- 로그인에 성공하면 session에 SID라는 속성으로 아이디를 기억시켜 놓을 것이므로 -->
			<div class="w3-btn w3-small w3-red w3-left" id="reboard">댓글게시판</div>
			<div class="w3-btn w3-small w3-green w3-left" id="gallery">갤러리 </div>
			
<c:if test="${not empty SID}">
			<div class="w3-btn w3-small w3-orange w3-right" id="logout">로그아웃</div>
</c:if>
<c:if test="${empty SID}">
			<div class="w3-btn w3-small w3-blue w3-right" id="join">회원가입</div>
			<div class="w3-btn w3-small w3-green w3-right" id="login">로그인</div>
</c:if>
		</div>
	</div>
</body>
</html>