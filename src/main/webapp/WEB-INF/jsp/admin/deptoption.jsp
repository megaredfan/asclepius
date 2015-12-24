<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="/WEB-INF/lib/tld/c.tld" %>
<label>选择科室</label>
<select class="form-control" name="departmentId" onchange="getDoctorList()">
	<option selected>---请选择---</option>
	<c:forEach var="department" items="${departments}">
		<option value="${department.departmentId}">${department.departmentName}</option>
	</c:forEach>
</select>
