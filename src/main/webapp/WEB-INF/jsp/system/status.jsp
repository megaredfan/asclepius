<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="/WEB-INF/lib/tld/c.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>打印详情</title>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">	
	<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="../css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
	<link href="../css/templatemo_style_1.css" rel="stylesheet" type="text/css">
</head>
<body class="">
	<div class="container">
		<div class="col-md-12 table-responsive">
		<h2 >详细信息</h2>
     				<table class="table table-bordered">
     					<tr>
     						<td>预约ID</td>
     						<td>${appointment.appointmentId}</td>
     					</tr>
     					<tr>
     						<td>患者姓名</td>
     						<td>${appointment.patientName}</td>
     					</tr>
     					<tr>
     						<td>患者年龄</td>
     						<td>${appointment.patientAge}</td>
     					</tr>
     					<tr>
     						<td>创建时间</td>
     						<td>${appointment.time}</td>
     					</tr>
     					<tr>
     						<td>医保卡号</td>
     						<td>${appointment.patientInsuranceNo}</td>
     					</tr>
     					<tr>
     						<td>预约医院</td>
     						<td>${hospital}</td>
     					</tr>
     					<tr>
     						<td>预约科室</td>
     						<td>${department}</td>
     					</tr>
     					<tr>
     						<td>预约医生</td>
     						<td>${doctor}</td>
     					</tr>
     					<tr>
     						<td>就诊时间</td>
     						<td>${appointmentDetail.date}&nbsp;
     							<c:if test="${appointmentDetail.time=='morning'}">上午</c:if>
     							<c:if test="${appointmentDetail.time=='afternoon'}">下午</c:if>
     						</td>
     					</tr>
     					<tr>
     						<td>预约费用</td>
     						<td>${appointmentDetail.cost}</td>
     					</tr>
     					<%-- <tr>
     						<td>预约状态</td>
     						<td>
	                    		<c:choose>
		                    		<c:when test="${app.status ==1}">待支付</c:when>
		                    		<c:when test="${app.status ==2}">未支付</c:when>
		                    		<c:when test="${app.status ==3}">已支付</c:when>
		                    		<c:when test="${app.status ==4}">已打印</c:when>
		                    		<c:when test="${app.status ==5}">未打印</c:when>
	                    		</c:choose>
                    		</td>
     					</tr> --%>
     				</table>
     				<a class="button" href="print.html">打印</a>
     			</div>
     			<hr/>
     			
	</div>
</body>
</html>