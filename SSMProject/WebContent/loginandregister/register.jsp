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
	<script type="text/javascript">
		function getUrlData() {
			let url = window.location.search; //url中?之后的部分
			url = url.substring(1); //去掉?
			let dataObj = {};

			if (url.indexOf('&') > -1) {

				url = url.split('&');
				for (let i = 0; i < url.length; i++) {
					let arr = url[i].split('=');
					dataObj[arr[0]] = arr[1];
				}
			} else {
				url = url.split('=');

				if (url[1] == "emailerror") {
					alert("邮箱已经被注册");
				} else if (url[1] == "accounterror") {
					alert("用户账号已经存在");
				} else if (url[1] == "nameterror") {
					alert("用户名已经存在");
				}
			}
		}
		getUrlData();
	</script>
	<img
		src="${pageContext.request.contextPath}/image/portrait/default.png">
	<form action="${pageContext.request.contextPath}/register"
		enctype="multipart/form-data" method="post">
		上传头像：<input type="file" name="portrait" /> <br>
		用户名：<input type="text" name="name" /> <br>
		账号：<input type="text" name="account"> <br>
		密码：<input type="password" name="pwd" /> <br>
		邮箱：<input type="text" name="email" /><br>

		<input type="submit" value="提交" />
	</form>
</body>
</html>