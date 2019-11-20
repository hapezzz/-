<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/update_user" enctype="multipart/form-data" method="post">
	上传头像：<input type="file" name="portrait" /> <br>
	昵称：<input name="nickname" /><br>
	邮箱：<input name="email" /><br>
	<input type="submit" value="确认修改"/>
</form> 
<form action="${pageContext.request.contextPath}/update_password">
	旧密码：<input name="old_password" type="password"/><br>
	新密码：<input name="password" type="password"/><br>
	确认密码：<input name="password" type="password"/><br>
	<input type="submit" value="确认修改"/>
</form>
</body>
</html>