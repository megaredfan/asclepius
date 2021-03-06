<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="/WEB-INF/lib/tld/c.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Lumino - Forms</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/datepicker3.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">

    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    <script src="js/validate/jquery.js"></script>
    <script src="js/validate/jquery.validate.js"></script>
    <script src="js/validate/additional-methods.js"></script>
    <script src="js/validate/localization/messages_zh.min.js"></script>

    <script src="js/bootstrap.min.js"></script>
    <script src="js/chart.min.js"></script>
    <script src="js/chart-data.js"></script>
    <script src="js/easypiechart.js"></script>
    <script src="js/easypiechart-data.js"></script>
    <script src="js/bootstrap-datepicker.js"></script>
    <script>
        !function ($) {
            $(document).on("click", "ul.nav li.parent > a > span.icon", function () {
                $(this).find('em:first').toggleClass("glyphicon-minus");
            });
            $(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
        }(window.jQuery);

        $(window).on('resize', function () {
            if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
        })
        $(window).on('resize', function () {
            if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
        })
    </script>

    <script>
        $.validator.setDefaults({
            submitHandler: function() {
                    alert("submitted!");
                    $.submit();
            }
        });

        $().ready(function() {
            $("#editForm").validate({
                rules: {
                    departmentName: {
                        required: true,
                        minlength: 2
                    },
                    description: {
                        required: true,
                        minlength: 5,
                        maxlength: 450
                    }
                }
            });
        });
    </script>

</head>

<body>
<%@ include file="outer.jsp" %>

<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
    <div class="row">
        <ol class="breadcrumb">
            <li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
            <li class="active">Forms</li>
        </ol>
    </div><!--/.row-->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">编辑科室</div>
                <div class="panel-body">
                    <div class="col-md-6">
                        <form id="editForm" method="post" action="departmentEdit.html" role="form">
                            <c:if test="${param.action=='edit'}">
                            	<input type="hidden" name="opType" value="edit">
                            	<input type="hidden" name="departmentId" value="${department.departmentId}">
                            </c:if>
                            <c:if test="${param.action=='add'}">
                            	<input type="hidden" name="opType" value="add">
                            </c:if>
                            <div class="form-group">
                                <label>科室名称</label>
                                <input class="form-control" name="departmentName" type="text" value="${department.departmentName}">
                            </div>

                            <div class="form-group">
                                <label>科室描述</label>
                                <textarea id="description" name="description" class="form-control" rows="3" maxlength="450">${department.description}</textarea>
                            </div>
                            <div class="form-group">
                                <label>所属医院</label>
                                <select class="form-control" name="hospitalId">
                                    <c:forEach var="option" items="${hospitals}">
                                    	<c:choose>
                                    		<c:when test="${option.hospitalId==belongsHospital.hospitalId}">
                                    			<option value="${option.hospitalId}" selected>${option.hospitalName}</option>
                                    		</c:when>
                                    		<c:otherwise>
                                    			<option value="${option.hospitalId}">${option.hospitalName}</option>
                                    		</c:otherwise>
                                    	</c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                            <c:if test="${param.action=='add'}">
                            	<button type="submit" class="btn btn-primary">添加</button>
                            </c:if>
                            <c:if test="${param.action=='edit'}">
                            	<button type="submit" class="btn btn-primary">保存</button>
                            </c:if>
                            <button type="reset" class="btn btn-default">重置</button>
                        </form>
                    </div>
                </div>
                </form>
            </div>
        </div>
    </div><!-- /.col-->
</div><!-- /.row -->

</div><!--/.main-->

</body>

</html>
