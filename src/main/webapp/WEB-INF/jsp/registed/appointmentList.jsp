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
        <div class="container">
           <div class="col-md-12">
           		<br/>
               <h3>预约详细记录</h3>
               <hr/>
               <c:forEach var="app" items="${appointments}">
               		<div class="col-md-12">
                    	<a href="appointmentDetail.html?appointmentId=${app.appointmentId}&appointmentDetailId=${app.appointmentDetailId}">
                    		预约时间：${app.time} | 
                    	<c:choose>
                    		<c:when test="${app.status ==1}">待支付</c:when>
                    		<c:when test="${app.status ==2}">未支付</c:when>
                    		<c:when test="${app.status ==3}">已支付</c:when>
                    		<c:when test="${app.status ==4}">已打印</c:when>
                    		<c:when test="${app.status ==5}">为打印</c:when>
                    	</c:choose>
                    	 | 患者：${app.patientName} 
                    	</a>
               		<c:if test="${app.status ==1}">
	               		<a class="button" href="#">支付</a>
	               		&nbsp;<a class="button" href="#">取消</a>
               		</c:if>
               		<c:if test="${app.status ==3}">
	               		<a class="button" href="#">查看详情</a>
               		</c:if>
               		</div>
               		<div class="col-md-12" style="height:2em"></div>
               </c:forEach>
           </div>
        </div>

    </div>
    <hr/>
    <%@ include file="../include/resource.jsp" %>
</div>
<%@ include file="../include/footer.jsp" %>


</body>
</html>