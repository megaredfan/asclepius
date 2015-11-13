<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/lib/tld/c.tld"%>
<%@ page isELIgnored = "false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
</head>
<body>
	<form method="post" action="register.html">
		<input type="hidden" name="opType" value="register" />
		<label for="userName">用户名：</label><input type="text" id="userName" name="userName"/><br/>
		<label for="password">密码：</label><input type="text" id="password" name="password"/><br/>
		<label for="realName">姓名：</label><input type="text" id="realName" name="realName"/><br/>
		<label for="sex">性别：</label>
			<select id="sex" name="sex">
				<option value="male">男</option>
				<option value="female">女</option>
			</select><br/>
		<label for="idNo">身份证号：</label><input type="text" id="idNo" name="idNo"/><br/>
		<input type="submit"/>
	</form>
	<div style="color:red">
		<c:forEach var="item" items="${fieldErrors}">
    		<c:out value="${item}"/><br/>
		</c:forEach>
	</div>
</body>
</html>