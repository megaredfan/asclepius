<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="/WEB-INF/lib/tld/c.tld" %>
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
            <p>留言板</p>
            <div class="comments">
                <h3>历史留言</h3>

                <div class="comment-info1">
                    <div class="comment-info" style="background:#EEE">
                        <c:forEach var="message" items="${messages}">
                        	
                            <p>内容：${message.content}</p>
                            <h4>作者：${message.author }</h4>
                            <h5>时间：On ${message.createTime}</h5>
                            <!-- <a href="#">Reply</a> -->
                            <hr/>
                            <c:forEach var="child" items="${childMessages}">
                            	<c:if test="${child.pid==message.id}">
                            		<h4>RE：${message.author }</h4>
		                            <p>内容：${child.content}</p>
		                            <h5>时间：On ${child.createTime}</h5>
                            		<!-- <a href="#">Reply</a> -->
                            	</c:if>
                            </c:forEach>
                        </c:forEach>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="container">
    <ul class="pagination">
    	<li><a href="leaveAMessage.html?pageNo=0">&lt;</a></li>
    	<c:if test="${totalPages!=1}">
    		<c:choose>
    			<c:when test="${pageNo<=4}">
    				<c:forEach var="i" begin="0" end="${pageNo}">
    					<li><a href="leaveAMessage.html?pageNo=${i}">${i+1}</a></li>
    				</c:forEach>
    			</c:when>
    			<c:otherwise>
    			 <li><a>...&nbsp;</a></li>  
    				<c:forEach var="i" begin="${pageNo-2}" end="${pageNo}">
    					<li><a href="leaveAMessage.html?pageNo=${i}">${i+1}</a></li>
    				</c:forEach>
    			</c:otherwise>
    		</c:choose>
    		<c:choose>
    			<c:when test="${pageNo>=totalPages-5||totalPages-2<=0}">
    				<c:forEach var="i" begin="${pageNo+1}" end="${totalPages-1}">
    					<li><a href="leaveAMessage.html?pageNo=${i}">${i+1}</a></li>
    				</c:forEach>
    			</c:when>
    			<c:otherwise>
    				<c:forEach var="i" begin="${pageNo+1}" end="${pageNo+3}">
    					<li><a href="leaveAMessage.html?pageNo=${i}">${i+1}</a></li>
    				</c:forEach>
    				<li><a>...&nbsp;</a></li>  
    					<li><a href="leaveAMessage.html?pageNo=${totalPages-1}">${totalPages}</a></li>
    			</c:otherwise>
    		</c:choose>
    	</c:if>
    	<li><a href="leaveAMessage.html?pageNo=${totalPages-1}">&gt;</a></li>
    </ul>
    </div>
            </div>
            <div class="content-form">
                <h3>添加留言</h3>
                <form id="commentForm" method="post" action="leaveAMessage.html">
                	<input type="hidden" name="opType" value="leaveamessage" />
                    <textarea id="content" name="content" placeholder="输入内容。。。"> </textarea>
                    <input id="submitComment" type="submit" value="提交"/>
                </form>
            </div>
        </div>
    </div>
    <%@ include file="../include/resource.jsp" %>
</div>
<%@ include file="../include/footer.jsp" %>


</body>
</html>