<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="/WEB-INF/lib/tld/c.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/lib/tld/fn.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Realty Services</title>
    <%@ include file="../include/head.jsp" %>
</head>
<body>
	<%@ include file="../include/header.jsp" %>

<div class="content">
    <div class="service-section">
        <div class="container">
            <h3>医院列表</h3>

            <div class="service-grids">
                <c:forEach var="hospital" items="${hospitals}">
                    <div class="service-grids">
		                <div class="col-md-9 service-grid">
		                    <div class="col-md-3"><img src="../images/hospital/${hospital.hospitalId}.jpg" class="img-responsive" alt="医院1的图" /></div>
		                    <div class="col-md-9">
		                        <h5><c:out value="${hospital.hospitalName}"/></h5>
		                        <p>
		                        	<c:if test="${fn:length(hospital.description)>100}">
		                        		<c:out value="${fn:substring(hospital.description,0,99)}"/>...
		                        	</c:if>
		                        	<c:if test="${fn:length(hospital.description)<=100}">
		                        		<c:out value="${hospital.description}"/>
		                        	</c:if>
                        		</p>
		                        <a href="../registed/hospitalDetail.html?hospitalId=${hospital.hospitalId}" class="button5"> 预约</a>
		                    </div>
		                </div>
            		</div>
            		<div class="clearfix"></div>
                </c:forEach>
                <div class="clearfix"></div>
            </div>
        </div>
    
    <ul class="pagination">
    	<li><a href="hospitalList.html?pageNo=0">&lt;</a></li>
    	<c:if test="${totalPages!=1}">
    		<c:choose>
    			<c:when test="${pageNo<=4}">
    				<c:forEach var="i" begin="0" end="${pageNo}">
    					<li><a href="hospitalList.html?pageNo=${i}">${i+1}</a></li>
    				</c:forEach>
    			</c:when>
    			<c:otherwise>
    			 <li><a>...&nbsp;</a></li>  
    				<c:forEach var="i" begin="${pageNo-2}" end="${pageNo}">
    					<li><a href="hospitalList.html?pageNo=${i}">${i+1}</a></li>
    				</c:forEach>
    			</c:otherwise>
    		</c:choose>
    		<c:choose>
    			<c:when test="${pageNo>=totalPages-5||totalPages-2<=0}">
    				<c:forEach var="i" begin="${pageNo+1}" end="${totalPages-1}">
    					<li><a href="hospitalList.html?pageNo=${i}">${i+1}</a></li>
    				</c:forEach>
    			</c:when>
    			<c:otherwise>
    				<c:forEach var="i" begin="${pageNo+1}" end="${pageNo+3}">
    					<li><a href="hospitalList.html?pageNo=${i}">${i+1}</a></li>
    				</c:forEach>
    				<li><a>...&nbsp;</a></li>  
    					<li><a href="hospitalList.html?pageNo=${totalPages-1}">${totalPages}</a></li>
    			</c:otherwise>
    		</c:choose>
    	</c:if>
    	<li><a href="hospitalList.html?pageNo=${totalPages-1}">&gt;</a></li>
    </ul>
    </div>

    <%@ include file="../include/resource.jsp" %>
</div>

	<%@ include file="../include/footer.jsp" %>

</body>
</html>