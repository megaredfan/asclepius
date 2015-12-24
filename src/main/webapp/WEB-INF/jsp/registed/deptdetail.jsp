<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="/WEB-INF/lib/tld/c.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Home</title>
    <%@ include file="../include/head.jsp" %>
</head>
<body>
<%@ include file="../include/header.jsp" %>
<div class="banner-section">
    <div class="container">
        <div class="col-md-5 banner-gridimg">
<!--             <img src="../images/img2.jpg"> -->
        </div>
        <div class="col-md-7 banner-grid">
        	<h2>${department.departmentName}</h2>
        	<p>${department.description}</p>
        	
        </div>
        <div class="clearfix"></div>
    </div>
</div>
<div class="content">

    <div class="join"></div>
    <div class="RecentPosts-section">
        <div class="container">
            <h3>医生列表</h3>
				<c:forEach var="doctor" items="${doctors}">
					<div class="col-md-3 post-grid1">
		                <h4>${doctor.name}</h4>
		                <p class="date">${doctor.level}</p>
		                <p class="text">${doctor.description}</p>
		                <a href="makeAnAppointment.html?hospitalId=${param.hospitalId}&departmentId=${param.departmentId}&doctorId=${doctor.doctorId}" class="button5">预约</a>
	            	</div>				
				</c:forEach>
        </div>
    </div>

    <%@ include file="../include/resource.jsp" %>
</div>
	<%@ include file="../include/footer.jsp" %>
</body>
</html>