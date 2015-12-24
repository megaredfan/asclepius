<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="/WEB-INF/lib/tld/c.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
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
                    amount: {
                        required: true,
                        digits: true,
                        min: 0
                    },
                    cost: {
                    	required: true,
                    	min: 1
                    }
                }
            });
        });
    </script>
	<script>
        function getDepartmentList(){
        	var hospitalId = $("select[name='hospitalId']").val();
        	htmlobj=$.ajax({url:"appointmentPoolEdit.html?hospitalId=" + hospitalId,type:"POST",async:false});
            $("#departmentlist").html(htmlobj.responseText);
        }
        function getDoctorList(){
        	var departmentId = $("select[name='departmentId']").val();
            htmlobj=$.ajax({url:"appointmentPoolEdit.html?departmentId=" + departmentId,type:"POST",async:false});
            $("#doctorlist").html(htmlobj.responseText);
        }
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
                <div class="panel-heading">放号管理</div>
                <div class="panel-body">
                    <form id="editForm" method="post" action="appointmentPoolEdit.html" role="form">
                    <input type="hidden" name="opType" value="edit"/>
                    <div class="col-md-4">
                            <div class="form-group">
                                <label>选择医院</label>
                                <select class="form-control" name="hospitalId" onchange=getDepartmentList()>
                                    <option selected>---请选择---</option>
                                    <c:forEach var="hospital" items="${hospitals}">
                                    <option value="${hospital.hospitalId}">${hospital.hospitalName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div id="departmentlist" class="form-group">
                                <label>选择科室</label>
                                <select class="form-control" name="hospitalId" onchange="getDoctorList()">
                                    <option selected>---请选择---</option>
                                </select>
                            </div>
                            <div id="doctorlist" class="form-group">
                                <label>选择医生</label>
                                <select class="form-control" name="doctorId">
                                    <option selected>---请选择---</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>选择时间</label>
                                <select class="form-control" name="day">
                                    <option>---请选择---</option>
                                    <option value="mon">周一</option>
                                    <option value="tue">周二</option>
                                    <option value="wed">周三</option>
                                    <option value="thur">周四</option>
                                    <option value="fri">周五</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>选择上下午</label>
                                <select class="form-control" name="time">
                                    <option>---请选择---</option>
                                    <option value="morning">上午</option>
                                    <option value="afternoon">下午</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">保存</button>
                            <button type="reset" class="btn btn-default">重置</button>

                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <label>设置数量</label>
                            <input class="form-control" type="number" name="amount">
                        </div>
                    </div><div class="col-md-4">
                    <div class="form-group">
                            <label>设置价格</label>
                            <input class="form-control" type="number" name="cost">
                    </div>
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div><!-- /.col-->
</div><!-- /.row -->

</div><!--/.main-->

</body>

</html>
