<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="/WEB-INF/lib/tld/c.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/lib/tld/fn.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Realty Services</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="../css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
    <link href="../css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link href='http://fonts.useso.com/css?family=Exo+2:400,900italic,900,800italic,800,700italic,700,600italic,600,500italic,500,400italic,300italic,300,200italic,200'
          rel='stylesheet' type='text/css'>
    <link href='http://fonts.useso.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
          rel='stylesheet' type='text/css'>

    <script src="../js/jquery-1.11.1.min.js"></script>
    <script src="../js/responsiveslides.min.js"></script>
    <script src="../js/services_js.js"></script>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
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
    <script type="text/javascript" src="../js/move-top.js"></script>
    <script type="text/javascript" src="../js/easing.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function ($) {
            $(".scroll").click(function (event) {
                event.preventDefault();
                $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1200);
            });
        });
    </script>
    <!---End-smoth-scrolling---->
    <link rel="stylesheet" href="../css/swipebox.css">
    <script src="../js/jquery.swipebox.min.js"></script>
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
                <span class="menu"><img src="../images/nav.png" alt=""/> </span>

                <ul>
                    <li><a href="../index.html">home</a></li>
                    <li><a href="../about.html">About</a></li>
                    <li><a href="services.html" class="active">Services</a></li>
                    <li><a href="../projects.html">projects</a></li>
                    <li><a href="../blog.html">blog</a></li>
                    <li><a href="../contact.html">Contact</a></li>
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
                <a href="../register.html" class="button">注册</a>
                <a href="../login.html" class="button1">登录</a>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="header-bottom">
            <div class="logo">
                <a href="../index.html"><img src="../images/logo.png"></a>
            </div>
            <div class="search">
                <form>
                    <input type="text" value="" placeholder="search...">
                    <input type="submit" value="">
                </form>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>

<div class="content">
    <div class="service-section">
        <div class="container">
            <h3> our services</h3>

            <div class="service-grids">
                <div class="service1">
                <c:forEach var="hospital" items="${hospitals}">
                <div class="col-md-4 service-grid">
                		<p><c:out value="${pageNo}"/></p>
                        <h5><c:out value="${hospital.hospitalName}"/></h5>
                        <p>
                        	<c:if test="${fn:length(hospital.description)>100}">
                        		<c:out value="${fn:substring(hospital.description,0,99)}"/>...
                        	</c:if>
                        	<c:if test="${fn:length(hospital.description)<=100}">
                        		<c:out value="${hospital.description}"/>
                        	</c:if>
                        </p>
                        <a href="../single.html" class="button5"> 预约</a>
                    </div>
                </c:forEach>
                
                    <!-- <div class="col-md-2 service-grid">

                        <h5>医院1</h5>

                        <p>医院1的描述</p>
                        <a href="../single.html" class="button5"> 预约</a>
                    </div>
                    <div class="col-md-2 service-grid">

                        <h5>医院1</h5>

                        <p>医院1的描述</p>
                        <a href="../single.html" class="button5"> 预约</a>
                    </div>
                    <div class="col-md-2 service-grid">
                        <h5>医院1</h5>

                        <p>医院1的描述</p>
                        <a href="../single.html" class="button5"> 预约</a>
                    </div>
                    <div class="col-md-2 service-grid">
                        <h5>医院1</h5>

                        <p>医院1的描述</p>
                        <a href="../single.html" class="button5"> 预约</a>
                    </div>
                    <div class="col-md-2 service-grid">
                        <h5>医院1</h5>

                        <p>医院1的描述</p>
                        <a href="../single.html" class="button5"> 预约</a>
                    </div>
                    <div class="col-md-2 service-grid">
                        <h5>医院1</h5>

                        <p>医院1的描述</p>
                        <a href="../single.html" class="button5"> 预约</a>
                    </div>
                    <div class="col-md-2 service-grid">
                        <h5>医院1</h5>

                        <p>医院1的描述</p>
                        <a href="../single.html" class="button5"> 预约</a>
                    </div>
                    <div class="col-md-2 service-grid">
                        <h5>医院1</h5>

                        <p>医院1的描述</p>
                        <a href="../single.html" class="button5"> 预约</a>
                    </div>
 -->
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
    <div class="top-menu" style="float:none;text-align:center">
    <ul>
    	<li><a href="#pageNo=0" onclick='toPage("byHospital",0)'>&lt;</a></li>
    	<c:if test="${totalPages!=1}">
    		<c:choose>
    			<c:when test="${pageNo<=4}">
    				<c:forEach var="i" begin="0" end="${pageNo}">
    					<li><a href="#pageNo=${i}" onclick='toPage("byHospital",${i})'>${i+1}</a></li>
    				</c:forEach>
    			</c:when>
    			<c:otherwise>
    			 ...&nbsp;  
    				<c:forEach var="i" begin="${pageNo-2}" end="${pageNo}">
    					<li><a href="#pageNo=${i}" onclick='toPage("byHospital",${i})'>${i+1}</a></li>
    				</c:forEach>
    			</c:otherwise>
    		</c:choose>
    		<c:choose>
    			<c:when test="${pageNo>=totalPages-5||totalPages-2<=0}">
    				<c:forEach var="i" begin="${pageNo+1}" end="${totalPages-1}">
    					<li><a href="#pageNo=${i}" onclick='toPage("byHospital",${i})'>${i+1}</a></li>
    				</c:forEach>
    			</c:when>
    			<c:otherwise>
    				<c:forEach var="i" begin="${pageNo+1}" end="${pageNo+3}">
    					<li><a href="#pageNo=${i}" onclick='toPage("byHospital",${i})'>${i+1}</a></li>
    				</c:forEach>
    				...&nbsp;  
    					<li><a href="#pageNo=${totalPages-1}" onclick='toPage("byHospital",${totalPages-1})'>${totalPages}</a></li>
    			</c:otherwise>
    		</c:choose>
    	</c:if>
    	<li><a href="#pageNo=${totalPages-1}">&gt;</a></li>
    </ul>
    </div>
    
    <!--div class="services-section1">
        <div class="container">
            <div class="services-grids">
                <div class="col-md-3 services-grid">
                    <img src="../images/service-1.png" class="img-responsive" alt="">
                    <h4>nulla nec ligula risus</h4>

                    <p>lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque cursus, sem eget sagittis
                        sagittis, nisl magna sodales eros, ut feugiat velit velit non turpis.</p>
                    <a href="#" class="button4">more info</a>
                </div>
                <div class="col-md-3 services-grid">
                    <img src="../images/service-4.png" class="img-responsive" alt="">
                    <h4>massa as laorretum</h4>

                    <p>lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque cursus, sem eget sagittis
                        sagittis, nisl magna sodales eros, ut feugiat velit velit non turpis.</p>
                    <a href="#" class="button4">more info</a>
                </div>
                <div class="col-md-3 services-grid">
                    <img src="../images/service-3.png" class="img-responsive" alt="">
                    <h4>lorem ipsum dolor est </h4>

                    <p>lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque cursus, sem eget sagittis
                        sagittis, nisl magna sodales eros, ut feugiat velit velit non turpis.</p>
                    <a href="#" class="button4">more info</a>
                </div>
                <div class="col-md-3 services-grid">
                    <img src="../images/service-5.png" class="img-responsive" alt="">
                    <h4>lorem ipsum dolor est </h4>

                    <p>lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque cursus, sem eget sagittis
                        sagittis, nisl magna sodales eros, ut feugiat velit velit non turpis.</p>
                    <a href="#" class="button4">more info</a>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div-->
    <div class="Resources-section">
        <div class="container">
            <div class="col-md-3 Resources">
                <h3>about</h3>

                <p>Morbi pretium gravida justo nec ultrices. Ut et facilisis justo. Fusce ac turpis eros, vel molestie
                    lectus.feugiat velit velit non turpis</p>
            </div>
            <div class="col-md-3 Resources1">
                <h3>resources</h3>
                <ul>
                    <li>New Listing Sign-Up</li>
                    <li>Consectetur adipiscing</li>
                    <li>Integer molestie lorem</li>
                    <li>Facilisis in pretium nisl</li>
                </ul>
            </div>
            <div class="col-md-3 Resources1">
                <h3>Owners</h3>
                <ul>
                    <li>Integer molestie lorem</li>
                    <li>Integer molestie lorem</li>
                    <li>Consectetur adipiscing</li>
                    <li>Lorem ipsum dolor sit</li>
                </ul>
            </div>
            <div class="col-md-3 Resources1">
                <h3>social</h3>
                <ul>
                    <li>facebook</li>
                    <li>twitter</li>
                    <li>google</li>
                    <li>viemo</li>
                </ul>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<div class="footer-section">
    <div class="container">
        <div class="footer-top">
            <p>Copyright &copy; 2015.Company name All rights reserved.More Templates <a href="http://www.cssmoban.com/"
                                                                                        target="_blank" title="模板之家">模板之家</a>
                - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></p>
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