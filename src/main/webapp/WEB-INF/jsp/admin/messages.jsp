<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="/WEB-INF/lib/tld/c.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Lumino - Widgets</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/datepicker3.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">

    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->

</head>

<body>
<%@ include file="outer.jsp" %>

<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
    <div class="row">
        <ol class="breadcrumb">
            <li><a href="index.html"><span class="glyphicon glyphicon-home"></span></a></li>
            <li class="active">留言板</li>
        </ol>
    </div><!--/.row-->

    <div class="row">
        <div class="col-md-8">
            <div class="panel panel-default chat">
                <div class="panel-heading" id="accordion"><span class="glyphicon glyphicon-comment"></span> Chat</div>

                <div class="">
                    <ul>
                        <c:forEach var="message" items="${messages}">
                        	<li class="clearfix">
                            <div class="chat-body clearfix">
                                <div class="header">
                                    <strong class="pull-left primary-font">${message.author}</strong><br/>
                                    <small class="text-muted">${message.createTime }</small>
                                </div>
                                <p><c:out value="${message.content}" /></p>
                            </div>
                            <hr/>
                            <span class="input-group-btn">
									<button class="btn btn-success btn-md" onclick='location.href="reply.html?id=${message.id}"'>回复</button>
                            </span>
                        </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>

        </div><!--/.col-->

        <div class="col-md-4">

            <div class="panel panel-red">
                <div class="panel-heading dark-overlay"><span class="glyphicon glyphicon-calendar"></span>Calendar</div>
                <div class="panel-body">
                    <div id="calendar"></div>
                </div>
            </div>
        </div><!--/.col-->
    </div><!--/.row-->
</div>    <!--/.main-->


<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/chart.min.js"></script>
<script src="js/chart-data.js"></script>
<script src="js/easypiechart.js"></script>
<script src="js/easypiechart-data.js"></script>
<script src="js/bootstrap-datepicker.js"></script>
<script>
    $('#calendar').datepicker({});

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
</body>

</html>
