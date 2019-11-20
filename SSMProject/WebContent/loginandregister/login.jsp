<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.UUID"%>
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
	       
	        if(url[1]=="accounterror"){
	        	alert("账号不存在");
	        }else if(url[1]=="passworderror"){
	        	alert("密码错误");
	        }else if(url[1]=="logouterror"){
	        	alert("请先登录")
	        }else if(url[1]=="toomany"){
	        	alert("当前浏览器已经登陆一个账号");
	        }
	    }
	}
 getUrlData();
 </script>
<body>

<form action="${pageContext.request.contextPath}/check" method="post"> 
	账户：<input type="text" name="account"/>
	密码：<input type="password" name="pwd"/>
	
	<input type="submit" value="登陆"/>
</form>
</body>

</html>