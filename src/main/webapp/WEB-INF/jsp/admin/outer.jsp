<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.html"><span>Lumino</span>Admin</a>
				<ul class="user-menu">
					<li class="dropdown pull-right">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> User <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
							<li><a href="#"><span class="glyphicon glyphicon-cog"></span> Settings</a></li>
							<li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
						</ul>
					</li>
				</ul>
			</div>
							
		</div><!-- /.container-fluid -->
	</nav>
		
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<form role="search">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search">
			</div>
		</form>
		<ul class="nav menu">
			<li class="parent ">
				<a href="#">
					<span class="glyphicon glyphicon-list"></span> 信息管理 <span data-toggle="collapse" href="#sub-item-1" class="icon pull-right"><em class="glyphicon glyphicon-s glyphicon-plus"></em></span>
				</a>
				<ul class="children collapse" id="sub-item-1">
					<li>
						<a class="" href="userList.html">
							<span class="glyphicon glyphicon-share-alt"></span> 用户列表
						</a>
					</li>
					<li>
						<a class="" href="hospitalList.html">
							<span class="glyphicon glyphicon-share-alt"></span> 医院列表
						</a>
					</li>
					<li>
						<a class="" href="departmentList.html">
							<span class="glyphicon glyphicon-share-alt"></span> 科室列表
						</a>
					</li>
					<li>
						<a class="" href="doctorList.html">
							<span class="glyphicon glyphicon-share-alt"></span> 医生列表
						</a>
					</li>
					<li>
						<a class="" href="appointmentDetailList.html">
							<span class="glyphicon glyphicon-share-alt"></span> 预约信息列表
						</a>
					</li>
				</ul>
			</li>
			<li>
				<a href="appointmentPoolEdit.html">
					<span class="glyphicon glyphicon-calendar"></span> 放号管理
				</a>
			</li>
			<li>
				<a href="messages.html">
					<span class="glyphicon glyphicon-calendar"></span> 留言板
				</a>
			</li>
			<li role="presentation" class="divider"></li>
		</ul>
		<div class="attribution">Template by <a href="http://www.medialoot.com/item/lumino-admin-bootstrap-template/">Medialoot</a></div>
	</div><!--/.sidebar-->