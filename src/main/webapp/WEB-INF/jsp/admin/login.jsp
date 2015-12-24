<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="/WEB-INF/lib/tld/c.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登录</title>

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
    <script>
        $.validator.setDefaults({
            submitHandler: function() {
                alert("submitted!");
                $.submit();
            }
        });

        $().ready(function() {
            $("#loginForm").validate({
                rules: {
                    userName: {
                        required: true,
                        minlength: 2
                    },
                    password: {
                        required: true,
                        minlength: 5
                    }
                }
            });
        });
    </script>
</head>

<body>

<div class="row">
    <div class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-4">
        <div class="login-panel panel panel-default">
            <div class="panel-heading">Log in</div>
            <div class="panel-body">
                <form id="loginForm" method="post" action="auth.html" role="form">
                	<input type="hidden" name="opType" value="login"/>
                    <fieldset>
                        <div class="form-group">
                            <input class="form-control" placeholder="用户名" name="userName" type="text" autofocus="">
                        </div>
                        <div class="form-group">
                            <input class="form-control" placeholder="密码" name="password" type="password" value="">
                        </div>
						<c:out value="${message}" /><br/>
                        <input type="submit" value="登录" class="btn btn-primary" />
                    </fieldset>
                </form>
            </div>
        </div>
    </div><!-- /.col-->
</div><!-- /.row -->

</body>

</html>

