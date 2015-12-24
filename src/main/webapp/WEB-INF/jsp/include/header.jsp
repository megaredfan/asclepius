<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="/WEB-INF/lib/tld/c.tld" %>
<div class="header">
    <div class="container">
        <div class="header-top">
            <div class="top-menu">
                <span class="menu"><img src="../images/nav.png" alt=""/> </span>

                <ul>
                    <li><a href="/Asclepius/index.html">主页</a></li>
                    <li><a href="/Asclepius/about.html">关于</a></li>
                    <li><a href="/Asclepius/general/hospitalList.html">医院列表</a></li>
                    <li><a href="/Asclepius/general/leaveAMessage.html">留言板</a></li>
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
            <c:if test="${userInSession==null}">
            	<div class="buttons">
                <a href="../register.html" class="button">注册</a>
                <a href="../login.html" class="button1">登录</a>
	            </div>
	            <div class="clearfix"></div>
            </c:if>
            <c:if test="${userInSession!=null}">
            	<div class="buttons">
                <span>欢迎！${userInSession.userName}</span>&nbsp;
                <a href="../registed/myAccount.html" class="button">我的账户</a>
                <a href="../login.html?opType=logout" class="button1">注销</a>
	            </div>
	            <div class="clearfix"></div>
            </c:if>
        </div>
        <div class="header-bottom">
            <div class="logo">
                <a href="../index.html"><img src="../images/logo.png"></a>
            </div>
            <!-- <div class="search">
                <form>
                    <input type="text" value="" placeholder="search...">
                    <input type="submit" value="">
                </form>
            </div> -->
            <div class="clearfix"></div>
        </div>
    </div>
</div>