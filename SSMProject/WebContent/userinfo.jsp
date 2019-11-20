<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
 <script type="text/javascript">
 function getUrlData(){
	    let url = window.location.search;  //url中?之后的部分
	    url = url.substring(1);    //去掉?
	    let dataObj = {};
	    
	    if(url.indexOf('&')>-1){
	    	
	        url = url.split('&');
	        for(let i=0; i<url.length; i++){
	            let arr = url[i].split('=');
	            dataObj[arr[0]] = arr[1];
	        }
	    }else{
	        url = url.split('=');
	       
	        if(url[1]=="success"){
	        	alert("创建成功，请重新登陆进行查看");
	        }else if(url[1]=="exist"){
	        	alert("名字重复，请重新创建");
	        }
	    }
	}
 getUrlData();
 </script>
<body>
<a href="${pageContext.request.contextPath}/logout">退出登陆</a>
<c:set var="last_time" value="${sessionScope.user.getLast_time()}"></c:set>
<c:if test="${last_time!=null }">上次登陆时间：${last_time}</c:if>
头像：<img src="${sessionScope.user.getPortrait_path()}" alt=""/><br>
用户昵称：${sessionScope.user.getNickname()}<br>
注册时间：${sessionScope.user.getRegister_time()}<br>
<a href="${pageContext.request.contextPath}/queryfollow/following?followcount=${sessionScope.user.getFollowing_num()}">关注的人</a>：${sessionScope.user.getFollowing_num()}<br>
<a href="${pageContext.request.contextPath}/queryfollow/follower?followcount=${sessionScope.user.getFollower_num()}">粉丝</a>：${sessionScope.user.getFollower_num()}<br>
<a href="${pageContext.request.contextPath}/loginandregister/textarea.jsp">写帖子</a>
<div class="container">
    <form action="find" class="parent">
        <input type="text" class="search" placeholder="搜索" name="name">
        <input type="submit" value="搜索">
    </form>
</div>
<a href="${pageContext.request.contextPath}/view_ownarts">查看自己的文章</a><br>
<a href="loginandregister/update_user.jsp">修改信息</a><br>
<c:set var="arts" value="${sessionScope.ownarts }"></c:set>
<c:forEach items="${arts }" var="art">
	<a href="${pageContext.request.contextPath}/view_art/${art.getArticle_id()}">${art.getTitle() }</a>
	${art.getContent() }<br>
</c:forEach>
<c:set var="manager" value="${sessionScope.user.getManager_flag()}"></c:set>
<c:if test="${manager==1 }">
	创建板块:
	<form action="create_block">
		板块名字：<input type="text" name="block_name"/>
		<input type="hidden" name="manager" value="${sessionScope.user.getUser_id()}"/>
		<input type="submit" value="提交"/>
	</form>
	
	管理的板块：
	<c:forEach items="${sessionScope.blocks }" var="name" >
		<a href="query_block/${name }"><c:out value="${name }"/></a>
	</c:forEach>
	
</c:if>
</body>
</html>