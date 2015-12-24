<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="/WEB-INF/lib/tld/c.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Real esate a wedding Category Flat Bootstarp Resposive Website Template | Home</title>
    <%@ include file="../include/head.jsp" %>
    <script src="../js/validate/jquery.validate.js"></script>
    <script src="../js/validate/additional-methods.js"></script>
    <script src="../js/validate/localization/messages_zh.min.js"></script>
    <script src="../js/responsiveslides.min.js"></script>
    <script>

        $().ready(function() {
            $("#userName").blur(function(){
                checkUserName($("#userName").val());
            });

            $("#editForm").validate({
                rules: {
                    userName: {
                        required: true,
                        minlength: 2
                    },
                    realName:{required:true},
                    idNo:{required:true}
                }
            });
        });
    </script>
    <script>
        function checkUserName(username){
            htmlobj=$.ajax({url:"../checkUserName.html?userName=" + username,type:"POST",async:true,success:function(){
                $("#restext").html(htmlobj.responseText);
            }});
        }
    </script>
</head>
<body>
<%@ include file="../include/header.jsp" %>
<div class="content">
    <div class="main-1">
        <div class="container">
            <h2>编辑资料</h2>
			<div class="register register-top-grid" >
				<form id="editForm" method="post" action="editProfile.html">
				<input type="hidden" name="opType" value="editProfile" />
				<div>
                	<label for="userName">用户名*</label>
                    <input type="text" id="userName" name="userName" value="${userInSession.userName}" />
                </div>
                <div>
                    <label for="realName">真实姓名*</label>
                    <input type="text" id="realName" name="realName" value="${userInSession.realName}">
                </div>
                <div>
                	<label for="sex">性别</label>
                	<select id="sex" name="sex">
                		<option value="male" <c:if test="${userInSession.sex=='male'}">selectd</c:if>>男</option>
                		<option value="female" <c:if test="${userInSession.sex=='female'}">selectd</c:if>>女</option>
                	</select>
                </div>
                <div>
                    <label for="idNo">身份证号*</label>
                    <input type="text" id="idNo" name="idNo" maxlength="18" value="${userInSession.idNo}">
                </div>
                <ul>
                	<c:forEach var="error" items="${fieldErrors}">
                		<li>${error}</li>
                	</c:forEach>
                </ul>
                <br/>
                <input type="submit" value="保存">
                <input type="reset" value="重置">
            </form>
			</div>
        </div>

    </div>
    <hr/>
    <%@ include file="../include/resource.jsp" %>
</div>
<%@ include file="../include/footer.jsp" %>
</body>
</html>