<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Lumino - Tables</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/datepicker3.css" rel="stylesheet">
    <link href="css/bootstrap-table.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">


    <script src="js/validate/jquery-1.9.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/chart.min.js"></script>
    <script src="js/chart-data.js"></script>
    <script src="js/easypiechart.js"></script>
    <script src="js/easypiechart-data.js"></script>
    <script src="js/bootstrap-datepicker.js"></script>
    <script src="js/bootstrap-table.js"></script>
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

    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    <script>

        $().ready(function () {
            $("#btn-test").click(function(){
                $("input[name='toolbar1']").each(function(){
                    if($(this).is(':checked')){
                        var text = $(this).parent().parent().children("td").eq(1).html();
                        alert("checed id:" +text);
                    }
                });
            });
        });
    </script>
	<script>

        $().ready(function () {
            $("#btn-add").click(function(){
                location.href="hospitalEdit.html?action=add";
            });
            $("#btn-edit").click(function(){
                $("input[name='toolbar1']").each(function(){
                    if($(this).is(':checked')){
                        var text = $(this).parent().parent().children("td").eq(1).html();
                        alert("checed id:" +text);
                        location.href="hospitalEdit.html?action=edit&hospitalId="+text;
                    }
                });
            });
            $("#btn-delete").click(function(){
                $("input[name='toolbar1']").each(function(){
                    if($(this).is(':checked')){
                        var text = $(this).parent().parent().children("td").eq(1).html();
                        alert("checed id:" +text);
                        location.href="hospitalDelete.html?hospitalId="+text;
                    }
                });
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
            <li class="active">医院列表</li>
        </ol>
    </div><!--/.row-->
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">医院列表</div>
				
                <div class="panel-body">
                	<div id="toolbar" class="btn-group">
                        <button type="button" id="btn-add" class="btn btn-default">
                            <i class="glyphicon glyphicon-plus"></i>
                        </button>
                        <button type="button" id="btn-edit" class="btn btn-default">
                            <i class="glyphicon glyphicon-edit"></i>
                        </button>
                        <button type="button" id="btn-delete" class="btn btn-default">
                            <i class="glyphicon glyphicon-trash"></i>
                        </button>
                    </div>
                    <table data-toggle="table" data-url="hospitals.json" data-show-columns="true"
                           data-show-toggle="true" data-show-refresh="true" data-search="true"
                           data-select-item-name="toolbar1" data-pagination="true" data-click-to-select="true"
                           data-sort-order="asc" data-row-style="rowStyle">
                        <thead>
                        <tr>
                            <th data-radio="true"></th>
                            <th data-field="hospitalId" data-sortable="true" data-switchable="false">医院ID</th>
                            <th data-field="hospitalName" data-sortable="true">医院名称</th>
                            <th data-field="description">医院描述</th>
                        </tr>
                        </thead>

                    </table>
                </div>
            </div>
        </div>
    </div><!--/.row-->

</div><!--/.main-->

</body>

</html>
