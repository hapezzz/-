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
<c:set var="article" value="${requestScope.article }"></c:set>
标题：${article.getTitle() }<br>
内容：${article.getContent() }<br>
文章评分：${requestScope.avg }
<c:set var="like_flag" value="${requestScope.like_flag }"></c:set>
<c:set var="report_flag" value="${requestScope.report_flag }"></c:set>
<c:if test="${like_flag==0 }"><a href="${pageContext.request.contextPath}/like/${article.getArticle_id()}">点赞</a></c:if>
<c:if test="${like_flag==1 }">你已经赞过该文章了</c:if>
<br>
<c:if test="${report_flag==0 }"><a href="${pageContext.request.contextPath}/report/${article.getArticle_id()}">举报</a></c:if>
<c:if test="${report_flag==1 }">你已经举报过该文章了</c:if>
<br>
点赞数：${requestScope.like_num }    被举报数：${requestScope.report_num }<br>
标签：${article.getTag1() } ${article.getTag2() } ${article.getTag3() } ${article.getTag4() } ${article.getTag5() }
<form action="${pageContext.request.contextPath}/comment" >
输入你的评论：<input type="text" name="content"/>
<input type="hidden" name="art_id" value="${article.getArticle_id() }"/>
<input type="submit" value="提交" />
</form>
<br>
评论区：
<c:forEach items="${requestScope.comments }" var="comment">
	<br><a href="${pageContext.request.contextPath}/find_user/${comment.getCommentator_nickname() }">${comment.getCommentator_nickname() }</a>: 
	${comment.getContent() } 发表时间：${comment.getComment_time() }
</c:forEach>

</body>
</html>