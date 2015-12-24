<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="/WEB-INF/lib/tld/c.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/lib/tld/fn.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Realty Single</title>
    <%@ include file="../include/head.jsp" %>

</head>
<body>
	<%@ include file="../include/header.jsp" %>
<div class="content">
    <div class="single">
        <div class="container">
            <p><c:out value="${hospital.hospitalName}" /></p>
            <div class="single-section">
                <div class="single-pic">
                    <img src="../images/hospital/${hospital.hospitalId}.jpg" alt=""/>
                </div>
                <p><c:out value="${hospital.description}" /></p>
            </div>
            <c:forEach var="department" items="${departments}">
            	<div class="service-grids">
                <div class="col-md-2 service-grid">
                    <h5><c:out value="${department.departmentName}" /></h5>
                    <p><c:out value="${department.description}" /></p>
                    <a href="departmentDetail.html?hospitalId=${hospital.hospitalId}&departmentId=${department.departmentId}" class="button5"> 预约</a>
                </div>
            </div>
            </c:forEach>
            <!-- <div class="service-grids">
                <div class="col-md-2 service-grid">
                    <h5>科室1</h5>
                    <p>科室1的描述</p>
                    <a href="deptdetail.html" class="button5"> 预约</a>
                </div>
            </div> -->
            <div class="clearfix"></div>
        </div>
        </div>
</div>

<%@ include file="../include/resource.jsp" %>

<%@ include file="../include/footer.jsp" %>


</body>
</html>