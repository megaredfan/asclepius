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
                    hospitalName: {
                        required: true,
                        minlength: 2
                    },
                    content: {
                        required: true,
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
            <li><a href="index.html"><span class="glyphicon glyphicon-home"></span></a></li>
            <li class="active">回复留言</li>
        </ol>
    </div><!--/.row-->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">回复</div>
                <div class="panel-body">
                    <div class="col-md-6">
                        <div class="header">
                            <strong class="pull-left primary-font">${message.author}</strong><br/>
                            <small class="text-muted">${message.createTime }</small>
                        </div>
                        <p>${message.content}</p>
                        <form id="editForm" role="form">
                            <input type="hidden" name="opType" value="reply">
                            <input type="hidden" name="pid" value="${param.id}">
                            <div class="form-group">
                                <label>输入回复内容：</label>
                                <textarea id="content" name="content" class="form-control" rows="3" maxlength="450"></textarea>
                            </div>
                            <button type="submit" class="btn btn-primary">回复</button>

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
