<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>首页</title>
</head>
<body>
<a href="loginandregister/login.jsp">登陆</a>
<a href="loginandregister/register.jsp">注册</a>
<a href="${pageContext.request.contextPath}/query_block/足球">足球</a>
<a href="${pageContext.request.contextPath}/query_block/篮球">篮球</a>
<div class="container">
    <form action="find" class="parent">
        <input type="text" class="search" placeholder="搜索" name="name">
        <input type="submit" value="搜索">
    </form>
</div>
	<!--  
		${requestScope.student.getStuNo()}
		${requestScope.student.getStuName()}
		${requestScope.student.getStuAge()}
		<c:set var="user" value="${requestScope.users}" />
		<table>
			<tr>
				<th>名字</th>
				<th>账号</th>
			</tr>
			<c:forEach items="${user}" var="info">
				<tr>
					<td>${info.getUserName()}</td>
					<td>${info.getUserId()}</td>
				</tr>
			</c:forEach>
		</table>

		<c:set var="cPage" value="${requestScope.currentPage}" />
		<c:set var="tPage" value="${requestScope.totalPage}" />
		<form action="">
			<a href="pagetest?currentPage=1">首页</a>
			<c:if test="${cPage>1}">
				<a href="pagetest?currentPage=${requestScope.currentPage -1}">上一页</a>
			</c:if>
			<select name="currentPage">
			<c:forEach begin="1" end="${tPage}" var="i">
				<option value="${i}">${i}</option>
			</c:forEach>
			</select>
			<input type="submit" value="go" />

			<c:if test="${cPage<tPage}">
				<a href="pagetest?currentPage=${requestScope.currentPage +1}">下一页</a>
			</c:if>
			<a href="pagetest?currentPage=${tPage} ">尾页</a>
		</form>
	-->
</body>
<script type="text/javascript">
	$(function() {
		$('#test').click(function() {
			$.ajax({
				type : 'GET',
				url : '/fabulous',
				data : formData,
				cache : false,
				processData : false,
				contentType : false,
			}).success(function(data) {
				alert(data);
			}).error(function() {
				alert("上传失败");
			});
		});
	});
</script>
</html>