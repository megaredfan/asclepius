<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="/WEB-INF/lib/tld/c.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>打印系统</title>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">	
	<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="../css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
	<link href="../css/templatemo_style_1.css" rel="stylesheet" type="text/css">
</head>
<body class="templatemo-bg-image-2">
	<div class="container">
		<div class="col-md-12">			
			<form class="form-horizontal templatemo-contact-form-1" role="form" action="print.html" method="post">
				<input type="hidden" name="opType" value="print">
				<div class="form-group">
					<div class="col-md-12">
						<h1 class="margin-bottom-15">打印预约单</h1>
						<p>输入您的预约单号，点击打印</p>
					</div>
				</div>				
		        <div class="form-group">
		          <div class="col-md-12">
		          	<label for="userId" class="control-label">用户编号</label>
		            <div class="templatemo-input-icon-container">
		            	<i class="fa fa-user"></i>
		            	<input type="text" class="form-control" id="userId" name="userId">
		            </div>		          	
		            <label for="name" class="control-label">预约单号</label>
		            <div class="templatemo-input-icon-container">
		            	<i class="fa fa-user"></i>
		            	<input type="text" class="form-control" id="appointmentId" name="appointmentId">
		            </div>		            		            		            
		          </div>              
		        </div>
		        <div class="form-group">
		        	${message}
		        </div>
		        <div class="form-group">
		          <div class="col-md-12">
		            <input type="submit" value="打印" class="btn btn-success pull-right">
		          </div>
		        </div>		    	
		      </form>		      
		</div>
	</div>
</body>
</html>