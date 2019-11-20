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
	
头像：<img src="${pageContext.request.contextPath}/${requestScope.f_user.getPortrait_path()}" alt=""/><br>
	<c:set var="flag" value="${requestScope.flag }"/>
	
	<c:choose>
		<c:when test="${flag==0 }"><a href="${pageContext.request.contextPath}/follow/${requestScope.f_user.getUser_id() }">关注${requestScope.f_user.getNickname() }</a></c:when>
		<c:when test="${flag==1 }" ><a href="${pageContext.request.contextPath}/cancle_follow/${requestScope.f_user.getUser_id() }">取消关注${requestScope.f_user.getNickname() }</a></c:when>
	</c:choose>
他的关注：${requestScope.f_user.getFollowing_num()}<br>
他的粉丝：${requestScope.f_user.getFollower_num()}<br>


</body>
</html>