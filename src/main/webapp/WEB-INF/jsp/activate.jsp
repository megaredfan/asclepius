<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="/WEB-INF/lib/tld/c.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Real esate a wedding Category Flat Bootstarp Resposive Website Template | Home</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>

    <script src="js/validate/jquery.js"></script>
    <script src="js/validate/jquery.validate.js"></script>
    <script src="js/validate/additional-methods.js"></script>
    <script src="js/validate/localization/messages_zh.min.js"></script>
    <script src="js/responsiveslides.min.js"></script>
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
                }/*,
                 messages: {
                 username: {
                 required: "Please enter a username",
                 minlength: "Your username must consist of at least 2 characters"
                 },
                 password: {
                 required: "Please provide a password",
                 minlength: "Your password must be at least 5 characters long"
                 }
                 }*/
            });
        });
    </script>

    <script type="application/x-javascript">
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);
        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>

    <script>
        $(function () {
            $("#slider").responsiveSlides({
                auto: true,
                nav: true,
                speed: 500,
                namespace: "callbacks",
                pager: true,
            });
        });
    </script>
    <!---- start-smoth-scrolling---->
    <script type="text/javascript" src="js/move-top.js"></script>
    <script type="text/javascript" src="js/easing.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function ($) {
            $(".scroll").click(function (event) {
                event.preventDefault();
                $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1200);
            });
        });
    </script>
    <!---End-smoth-scrolling---->
    <link rel="stylesheet" href="css/swipebox.css">
    <script src="js/jquery.swipebox.min.js"></script>
    <script type="text/javascript">
        jQuery(function ($) {
            $(".swipebox").swipebox();
        });
    </script>


</head>
<body>
<div class="header">
    <div class="container">
        <div class="header-top">
            <div class="top-menu">
                <span class="menu"><img src="images/nav.png" alt=""/> </span>
                <ul>
                    <li><a href="/Asclepius/index.html">主页</a></li>
                    <li><a href="/Asclepius/about.html">关于</a></li>
                    <li><a href="/Asclepius/general/hospitalList.html">医院列表</a></li>
                    <li><a href="/Asclepius/general/leaveAMessage.html">留言板</a></li>
                    <li><a href="/Asclepius/admin/index.html">后台入口</a>
                    <!-- <li><a href="../blog.html">blog</a></li>
                    <li><a href="../contact.html">Contact</a></li> -->
                </ul>
                <!-- script for menu -->

                <script>
                    $("span.menu").click(function () {
                        $(".top-menu ul").slideToggle("slow", function () {
                        });
                    });
                </script>

                <!-- //script for menu -->

            </div>
            <div class="buttons">
            	<c:if test="${userInSession==null}">
            		<a href="register.html" class="button">注册</a>
                	<a href="login.html" class="button1">登录</a>
            	</c:if>
                <c:if test="${userInSession!=null}">
                	<span>欢迎！${userInSession.userName}</span>&nbsp;
	                <a href="registed/myAccount.html" class="button">我的账户</a>
	                <a href="login.html?opType=logout" class="button1">注销</a>
                </c:if>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="header-bottom">
            <div class="logo">
                <a href="deptdetail.html"><img src="images/logo.png"></a>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<div class="Features-section">
	<div class="container">请检查邮箱</div>
</div>
<div class="footer-section">
    <div class="container">
        <div class="footer-top">
            <p>Copyright &copy; 2015.Company name All rights reserved.</p>
        </div>
        <script type="text/javascript">
            $(document).ready(function () {
                /*
                 var defaults = {
                 containerID: 'toTop', // fading element id
                 containerHoverID: 'toTopHover', // fading element hover id
                 scrollSpeed: 1200,
                 easingType: 'linear'
                 };
                 */

                $().UItoTop({easingType: 'easeOutQuart'});

            });
        </script>
        <a href="#" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>


    </div>
</div>

</body>
</html>