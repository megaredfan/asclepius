<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/lib/tld/c.tld"%>
<%@ page isELIgnored = "false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="inc/include_javascript.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
$().ready(function() {
	 $("#loginForm").validate({
	   rules: {
	   userName: "required",
	   password: {
	    required: true,
	    minlength: 6
	   }
	  }
	    });
	});
</script>
<title>注册</title>

</head>
<body>
	<form id="loginForm" method="post" action="login.html">
		<input type="hidden" name="opType" value="register" />
		<label for="userName">用户名：</label><input type="text" id="userName" name="userName" /><br/>
		<label for="password">密码：</label><input type="password" id="password" name="password"/><br/>
		<input type="submit" value="登录"/>
	</form>
	<div style="color:red">
    		<c:out value="${message}"/><br/>
	</div>
</body>
</html>