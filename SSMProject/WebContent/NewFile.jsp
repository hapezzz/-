<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="js/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>



  <script src="https://cdn.tiny.cloud/1/no-api-key/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
  <script>tinymce.init({selector:'textarea'});</script>
</head>
<body>
 



<!-- js中document.write(encodeURI("download?filename=应用.exe"))查看编码后的地址 -->
	<a href="test/query/2">查询</a>
	<a href="test2/pagetest?currentPage=1">查询2</a>
	<a href=" download?filename=%E5%BA%94%E7%94%A8.exe">下载</a>
	<form action ="text" id="uploadForm" enctype="multipart/form-data" method="post">
		
		 <textarea name="text"></textarea>
		 <input type="submit" value="提交">
	</form>
	<br>
	<input type="button" id="upload1" value="上传"/>
	aa:<input type="text" />
</body>

<script type="text/javascript">
	$(function() {
		$('#upload1').click(function() {
			var formData = new FormData($('#uploadForm')[0]);
			$.ajax({
				type : 'POST',
				url : 'test/upload',
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