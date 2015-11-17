<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/lib/tld/c.tld"%>
<%@ page isELIgnored = "false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../inc/include_javascript.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
$().ready(function() {
	 $("#signupForm").validate({
	   rules: {
	   userName: "required",
	   realName: "required",
	   idNo: {
		required: true,
	 	minlength:18,
	 	maxlength:18
	   },
	   password: {
	    required: true,
	    minlength: 6
	   },
	   confirmPassword: {
	    required: true,
	    minlength: 6,
	    equalTo: "#password"
	   }
	  }
	    });
	});
</script>
<script>
function checkUserName(userName){
	  htmlobj=$.ajax({url:"checkUserName.html?userName="+userName,type:"POST",async:true,success:function(){$("#restext").html(htmlobj.responseText);}});
	  
	  }
</script>
<title>注册</title>

</head>
<body>
	<form id="signupForm" method="post" action="register.html">
		<input type="hidden" name="opType" value="register" />
		<label for="userName">用户名：</label><input type="text" id="userName" name="userName" onblur="checkUserName($('#userName').val())"/><span id="restext"></span><br/>
		<label for="password">密码：</label><input type="password" id="password" name="password"/><br/>
		<label for="confirmPassword">密码：</label><input type="password" id="confirmPassword" name="confirmPassword"/><br/>
		<label for="realName">姓名：</label><input type="text" id="realName" name="realName"/><br/>
		<label for="sex">性别：</label>
			<select id="sex" name="sex">
				<option value="男">男</option>
				<option value="女">女</option>
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