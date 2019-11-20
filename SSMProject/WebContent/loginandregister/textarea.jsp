<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="js/jquery.min.js">
	
</script>
<script
	src="https://cdn.tiny.cloud/1/no-api-key/tinymce/5/tinymce.min.js"
	referrerpolicy="origin"></script>
<script>
	tinymce.init({
		selector : 'textarea'
	});
	var limit = 2,num = 0;
	function check(obj) {    
	     obj.checked?num++:num--;
	     if(num > limit){
	         obj.checked = false;
	         alert("最多选择2项");
	         num--;
	    }
	 };
</script>
<body>
	
	<form
		action="${pageContext.request.contextPath}/create_article"
		id="uploadForm" enctype="multipart/form-data" method="post">
		<!--选择板块 单选框  -->
		<label><input type="radio" name="blockname" value="篮球">篮球</label>
		<label><input type="radio" name="blockname" value="足球">足球</label>
		<br>
		<input type="hidden" value="${sessionScope.uuid}" name="uuid">
		标题 ：<input type="text" name="title">
		正文 ：<textarea name="content"></textarea>  
		
		<!-- 选择标签 多选框 -->
		<label><input type="checkbox" name="tag" value="比赛" onclick="check(this)">比赛</label>
		<label><input type="checkbox" name="tag" value="明星" onclick="check(this)">明星</label>
		<label><input type="checkbox" name="tag" value="热门" onclick="check(this)">热门</label>
		<input type="submit" value="提交">
	</form>
</body>
</html>