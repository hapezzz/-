<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<input id="mes" type="hidden" value="${requestScope.block_mes }" />
<body>
	<c:set var="flag" value= "${sessionScope.user.getManager_flag() }"></c:set>
	<c:if test="${flag==1 }">
	修改板块:
	<form action="${pageContext.request.contextPath}/update_block/${requestScope.block.getBlock_name()}">
		新名字：<input type="text" name="new_name"/>
		新管理员的用户名或者账号：<input type="text" name="manager"/>
		<input type="submit" value="提交"/>
	</form>
	</c:if>
	
	板块名字：${requestScope.block.getBlock_name()}<br>
	创建时间：${requestScope.block.getCreate_time()}<br>
	管理员：<a href="${pageContext.request.contextPath}/find_user/${requestScope.manager.getNickname()}">${requestScope.manager.getNickname()}</a><br>
	
	<c:set var="arts" value="${requestScope.arts }"></c:set>
	<c:forEach items="${arts }" var="art">
		${art.getTitle() }<br>
		${art.getContent() }<br>
	</c:forEach>
	${requestScope.message }<br>
</body>
 <script type="text/javascript">
 function getMes(){
	 var mes = document.getElementById("mes").value;
	 if(mes=="user_notexist"){
		 alert("用户不存在");
	 }else if(mes=="user_notmanager"){
		 alert("用户不是管理员");
	 }else if(mes=="name_exist"){
		 alert("板块名已存在");
	 }
	}
 getMes();
 </script>
</html>