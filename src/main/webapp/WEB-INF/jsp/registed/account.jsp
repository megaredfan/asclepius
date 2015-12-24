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

</head>
<body>
<%@ include file="../include/header.jsp" %>
<div class="content">
    <div class="main-1">
        <div class="container" style="text-align: center;">
            <div>
                <br/>
                <h2 >我的账户</h2>
                <hr/>
                <div class="col-md-12" style="text-align:center">
                    <h4>个人资料</h4>
                    <br/>
                    <div class="table-responsive">
                    	<table class="table table-bordered">
							<tr>
								<td>用户ID</td> <td>${userInSession.id}</td>
							</tr>
							<tr>
								<td>用户名</td><td>${userInSession.userName}</td>
							</tr>
							<tr>
								<td>注册时间</td><td>${userInSession.registerTime}</td>
							</tr>
                    		<tr>
                    			<td>上次登录时间</td><td>${userInSession.lastLogin}</td>
                    		</tr>
                    		<tr>
                    			<td>真实姓名</td><td>${userInSession.realName}</td>
                    		</tr>
                    		<tr>
                    			<td>email</td><td>${userInSession.email}</td>
                    		</tr>
                    		<tr>
                    			<td>性别</td><td><c:if test="${userInSession.sex == 'male'}">男</c:if>
                    				<c:if test="${userInSession.sex == 'female'}">女</c:if></td>
                    		</tr>
                    		<tr>
                    			<td>信用积分</td><td>${userInSession.creditLevel}</td>
                    		</tr>	
                    	</table>
                    </div>
                    <hr/>
                    <a class="button" href="editProfile.html">编辑个人资料</a>
                    <hr/>
                </div>
                <div class="col-md-12">
                    <h4><a href="myAppointments.html" >预约记录</a></h4>
                    <br/>
                    <div class="col-md-10 table-responsive">
                    	<table class="table">
                    		<tr>
                    			<th>预约ID</th>
                    			<th>预约状态</th>
                    			<th>预约时间</th>
                    			<th>患者姓名</th>
                    		</tr>
                    	<c:forEach var="app" items="${appointments}">
                    	<tr>
                    		<td>
                    			<a href="appointmentDetail.html?appointmentId=${app.appointmentId}&appointmentDetailId=${app.appointmentDetailId}">
                    				${app.appointmentId}
                    			</a>
                    		</td>
                    		
                    		<td>
	                    		<c:choose>
		                    		<c:when test="${app.status ==1}">待支付</c:when>
		                    		<c:when test="${app.status ==2}">未支付</c:when>
		                    		<c:when test="${app.status ==3}">已支付</c:when>
		                    		<c:when test="${app.status ==4}">已打印</c:when>
		                    		<c:when test="${app.status ==5}">未打印</c:when>
	                    		</c:choose>
                    		</td>
                    		
                    		<td>
                    			${app.time} 
                    		</td>
                    		<td>
                    			${app.patientName}
                    		</td>
                    	</tr>
                    	 
                    </c:forEach>
                    	</table>
                    </div>
                </div>
                
            </div>
        </div>

    </div>
    <hr/>
    <%@ include file="../include/resource.jsp" %>
</div>
<%@ include file="../include/footer.jsp" %>
</body>
</html>