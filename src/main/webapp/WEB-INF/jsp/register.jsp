<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="/WEB-INF/lib/tld/c.tld" %>
<!DOCTYPE PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Real esate a wedding Category Flat Bootstarp Resposive Website Template | Home</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <script type="application/x-javascript">
        addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
        }, false);
        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>

    <script src="js/validate/jquery.js"></script>
    <script src="js/validate/jquery.validate.js"></script>
    <script src="js/validate/additional-methods.js"></script>
    <script src="js/validate/localization/messages_zh.min.js"></script>
    <script src="js/responsiveslides.min.js"></script>
    <script>
       /*  $.validator.setDefaults({
            submitHandler: function() {
                alert("submitted!");
				$().submit();
            }
        });
 */

        $().ready(function() {
            $("#signupButton").click(function(){
                if($("#signupForm").valid())
                    $("#signupForm").submit();
            });

            $("#userName").blur(function(){
                checkUserName($("#userName").val());
            });

            $("#signupForm").validate({
                rules: {
                    userName: {
                        required: true,
                        minlength: 2
                    },
                    password: {
                        required: true,
                        minlength: 5
                    },
                    passwordConfirm:{equalTo:'#password'},
                    realName:{required:true},
                    email:{
                        email:true,
                        required:true
                    },
                    idNo:{required:true},
                    readAndAccept:{required:true}
                }
            });
        });
    </script>
    <script>
        function checkUserName(username){
            htmlobj=$.ajax({url:"checkUserName.html?userName=" + username,type:"POST",async:true,success:function(){
                $("#restext").html(htmlobj.responseText);
            }});
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
                <a href="register.html" class="button">注册</a>
                <a href="login.html" class="button1">登录</a>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="header-bottom">
            <div class="logo">
                <a href="index.html"><img src="images/logo.png"></a>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<div class="content">
    <!-- registration -->
    <div class="main-1">
        <div class="container">
            <div class="register">
                <form id="signupForm" method="post" action="register.html">
                	<input type="hidden" name="opType" value="register" />
                    <div class="register-top-grid">
                        <h3>注册新用户</h3>

                        <div class="col-md-7">
                            <label for="userName">用户名</label>
                            <input type="text" id="userName" name="userName"/>
                            <span id="restext"></span>
                            <br/>

                            <label for="realName">真实姓名</label>
                            <input type="text" id="realName" name="realName" />
                            <br/>

                            <label for="email">email</label>
                            <input type="email" id="email" name="email" />
                            <br/>

                            <label for="idNo">身份证号</label>
                            <input type="text" id="idNo" name="idNo" />
                            <br/>

                            <label for="sex">性别</label>
                            <select id="sex" name="sex">
                                <option value="male">男</option>
                                <option value="female">女</option>
                            </select>
                            <br/>

                            <label for="password">密码</label>
                            <input type="password" id="password" name="password" />
                            <br/>

                            <label for="passwordConfirm">确认密码</label>
                            <input type="password" id="passwordConfirm" name="passwordConfirm" />
                            <br/>
                        </div>
                        <div class="col-md-5" style="text-align:center">
                        <h3>已有账户？</h3><br/>
                        <p>
			                <a href="login.html" class="button1">登录</a>
            			</p>
                        </div>
                        <div class="col-md-5">
							<ul>
							<c:forEach var="error" items="${fieldErrors}">
								<li>${error}</li>
							</c:forEach>
							</ul>
						</div>
                    </div>
                    <div class="clearfix"></div>
                    <a class="news-letter" href="#">
                        <label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i> </i>同意协议</label>
                    </a>
                    <input type="submit" style="visibility: hidden;">
                </form>
                <div class="clearfix"> </div>
                <div class="register-but">
                    <form>
                        <input type="button" id="signupButton" value="注册"/>
                        <div class="clearfix"> </div>
                    </form>
                </div>
            </div>
            </div>
        </div>
    </div>
    <!-- registration -->

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