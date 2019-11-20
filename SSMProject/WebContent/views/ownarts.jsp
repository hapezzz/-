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
<c:set var="arts" value="${requestScope.ownarts }"></c:set>
<c:forEach items="${arts }" var="art">
	<a href="${pageContext.request.contextPath}/view_art/${art.getArticle_id()}">${art.getTitle() }</a>
	${art.getContent() }<br>
</c:forEach>
</body>
</html>