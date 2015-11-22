<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/lib/tld/c.tld"%>
<%@ page isELIgnored = "false" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<%@ include file="inc/include_css.jsp" %>
	<%@ include file="inc/include_javascript.jsp" %>
</head>
<body>
	<!-- Top Panel -->
	<div class="top_panel">
		<div class="wrapper">
		</div>
	</div>

	<div class="wrapper contents_wrapper">
		
		<div class="login">
			<div class="widget_header">
				<h4 class="widget_header_title wwIcon i_16_login">登录</h4>
			</div>
			<div class="widget_contents lgNoPadding">
				<form method="post" action="auth.html">
				<div class="line_grid">
					<div class="g_2 g_2M"><span class="label">User</span></div>
					<div class="g_10 g_10M">
						<input class="simple_field tooltip" id="userName" name="userName" type="text" placeholder="Username"></div>
					<div class="clear"></div>
				</div>
				<div class="line_grid">
					<div class="g_2 g_2M"><span class="label">Pass</span></div>
					<div class="g_10 g_10M">
						<input class="simple_field tooltip" id="password" name="password" type="password" value="password">
					</div>
					<div class="clear"></div>
				</div>
				<div class="line_grid">
					<div class="g_6"><input class="submitIt simple_buttons" value="登录" type="submit">
					</div>
					<div class="clear"></div>
				</div>
				</form>
			</div>
		</div>

	</div>
</body>
</html>