<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="/WEB-INF/lib/tld/c.tld" %>
<label>选择医生</label>
<select class="form-control" name="doctorId">
	<option selected>---请选择---</option>
	<c:forEach var="doctor" items="${doctors}">
		<option value="${doctor.doctorId}">${doctor.name}</option>
	</c:forEach>
</select>
