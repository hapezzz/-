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
	
	<c:set var="follow" value="${requestScope.follow}"/>
	<c:forEach items="${follow }" var="user">
		<a href="${pageContext.request.contextPath}/find_user/${user.getNickname() }">${user.getNickname() }</a><br>
	</c:forEach>
	
		<c:set var="cPage" value="${requestScope.currentPage}" />
		<c:set var="tPage" value="${requestScope.totalPage}" />
		<form action="${requestScope.key }">
			<a href="${requestScope.key }?currentPage=1&followcount=${requestScope.followcount}">首页</a>
			<c:if test="${cPage>1}">
				<a href="${requestScope.key }?currentPage=${requestScope.currentPage -1}&followcount=${requestScope.followcount}">上一页</a>
			</c:if>
			<select name="currentPage">
			<c:forEach begin="1" end="${tPage}" var="i">
				<option value="${i}">${i}</option>
			</c:forEach>
			</select>
			<input type="hidden" value="${requestScope.followcount}" name="followcount"/>
			<input type="submit" value="go" />

			<c:if test="${cPage<tPage}">
				<a href="${requestScope.key }?currentPage=${requestScope.currentPage +1}&followcount=${requestScope.followcount}">下一页</a>
			</c:if>
			<a href="${requestScope.key }?currentPage=${tPage}&followcount=${requestScope.followcount}">尾页</a>
		</form>

	
	
</body>
</html>