<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/reload">首页</a>


用户：<c:forEach items="${requestScope.users }" var="user">
	<br><a href="find_user/${user.getNickname()}">${user.getNickname()}</a>
</c:forEach><br>


文章：<c:forEach items="${requestScope.arts }" var="art">
	<br><a href="${pageContext.request.contextPath}/view_art/${art.getArticle_id() }">${art.getTitle() }</a>
</c:forEach>

</body>
</html>