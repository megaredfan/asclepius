<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="/WEB-INF/lib/tld/c.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/lib/tld/fn.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Realty Blog</title>
    <link href="../css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
    <link href="../css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="../js/ui/jquery-ui.css" rel="stylesheet" type="text/css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    

    <script src="../js/validate/jquery.js"></script>
    <script src="../js/ui/jquery-ui.js"></script>
    <script src="../js/validate/jquery.validate.js"></script>
    <script src="../js/validate/additional-methods.js"></script>
    <script src="../js/validate/localization/messages_zh.min.js"></script>
    <script src="../js/responsiveslides.min.js"></script>
    
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

<script>
/*        $.validator.setDefaults({
            submitHandler: function() {
                alert("submitted!");
            }
        });*/


        $().ready(function() {
            $("#date").datepicker();
            $("#date").datepicker("option", "dateFormat", "yy-mm-dd");
            $("#book").click(function(){

                $("#bookForm").submit();
            });

            $(function() {
            	
                $.datepicker.regional["zh-CN"] = {
                    monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
                    monthNamesShort: ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"],
                    dayNames: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
                    dayNamesShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
                    dayNamesMin: ["日", "一", "二", "三", "四", "五", "六"],
                    weekHeader: "周",
                    dateFormat: "yy-mm-dd",
                    //minDate: "2015-12-7",
                    //maxDate: "2015-12-11",
                    minDate: "${start}",
                    maxDate: "${end}",
                    firstDay: 7,
                    isRTL: !1,
                    showMonthAfterYear: !0,
                    yearSuffix: "年",
                }


                $.datepicker.setDefaults($.datepicker.regional["zh-CN"]);


            });

            $("#bookForm").validate({
                rules: {
                    date: {
                        required: true
                    },
                    time: {
                        required: true
                    },
                    patientName: {
                        required: true
                    },
                    patientAge: {
                        required: true,
                        digits: true
                    }
                }
            });
        });
    </script>
</head>
<body>
<%@ include file="../include/header.jsp" %>
<div class="content">
    <div class="">
        <div class="container">
        	<br/>
            <h3>预约</h3><br/>

            <div>
                <h4>预约信息</h4>

                <p>医院：${doctor.department.hospital.hospitalName}</p>
                <p>科室${doctor.department.departmentName}</p>
                <p>医生姓名：${doctor.name}</p>
                <p>职称：${doctor.level}</p>
                <div class="register register-top-grid">

                    <form id="bookForm" method="post" action="makeAnAppointment.html">
                        <input type="hidden" name="opType" value="book"/>
                        <input type="hidden" name="hospitalId" value="${doctor.department.hospital.hospitalId}"/>
                        <input type="hidden" name="departmentId" value="${doctor.department.departmentId}"/>
                        <input type="hidden" name="doctorId" value="${doctor.doctorId}"/>

                        <div>
                            <label for="date">日期</label>
                            <input type="text" id="date" name="date" readonly="readonly"/>
                        </div>
                        <div>
                            <label for="time">时间</label>
                            <select id="time" name="time">
                                <option value="morning">上午</option>
                                <option value="afternoon">下午</option>
                            </select>
                        </div>
                        <div>
                            <label for="patientName">患者姓名*</label>
                            <input type="text" id="patientName" name="patientName"/>
                        </div>
                        <div>
                            <label for="patientAge">患者年龄*</label>
                            <input type="text" id="patientAge" name="patientAge">
                        </div>
                        <div>
                            <label for="patientSex">患者性别*</label>
                            <select id="patientSex" name="patientSex">
                                <option value="male">男</option>
                                <option value="female">女</option>
                            </select>
                        </div>
                        <div>
                            <label for="patientInsuranceNo">医保卡号</label>
                            <input type="text" id="patientInsuranceNo" name="patientInsuranceNo">
                        </div>
                    </form>
                    
                    
                     <div class="register-but">
                        <form>
                        	<ul style="color:red">
		                    <c:forEach var="error" items="${fieldErrors}">
		                    	<li>${error}</li>
		                    </c:forEach>
		                    </ul>
                            <input type="button" id="book" value="预约"/>
                            <div class="clearfix"></div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="../include/resource.jsp" %>
</div>
<%@ include file="../include/footer.jsp" %>


</body>
</html>