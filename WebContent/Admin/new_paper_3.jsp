<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page import="com.alex.onlinetest.admin.service.NavigatorManagement"%>
<%@ page import="com.alex.onlinetest.hbm.Navigator"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html class="no-js">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>在线考试系统后台管理</title>
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/Admin/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/Admin/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/Admin/vendors/easypiechart/jquery.easy-pie-chart.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/Admin/assets/styles.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/site.css" rel="stylesheet" media="screen">
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
<script src="${pageContext.request.contextPath}/Admin/vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
</head>

<body>
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="#">后台管理</a>
				<div class="nav-collapse collapse">
					<ul class="nav pull-right">
						<li class="dropdown"><a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-user"></i> <s:property value="#session.username"></s:property><i class="caret"></i>

						</a>
							<ul class="dropdown-menu">
								<li><a tabindex="-1" href="#">Profile</a></li>
								<li class="divider"></li>
								<li><a tabindex="-1" href="logout">Logout</a></li>
							</ul></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3" id="sidebar">
				<ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
					<%
						WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
								                        NavigatorManagement navmagement = (NavigatorManagement)wac.getBean("navManagement");
								                        List<Navigator> navs = navmagement.getAllNavigator();
								                        for (int i=0; i<navs.size(); i++) {
								                        	if (navs.get(i).getParentId() == 0) {
					%>
					<li><a href="#"><i class="icon-chevron-down"></i><strong><%=navs.get(i).getNodeName()%></strong></a></li>
					<%
						} else {
					%>
					<li><a href="<%=navs.get(i).getUrl()%>"><i class="icon-chevron-right"></i>&nbsp;&nbsp;&nbsp;&nbsp;<%=navs.get(i).getNodeName()%></a></li>
					<%
						}
								                        }
					%>
				</ul>
			</div>
			<div class="span9" id="content">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3>创建新的考试试卷</h3>
					</div>
					<div class="panel-body">
						<form action="query" method="post">
							<div class="span12">
								<div class="span6">
									<strong style="font-size: 20px">试卷名称：</strong> <input value="<s:property value="#request.papername" />" type="text" readonly="readonly">
								</div>
								<div class="span6">
									<strong style="font-size: 20px">考试时长（分钟）：</strong> <input value="<s:property value="#request.paperduration" />" type="text" readonly="readonly">
								</div>
							</div>
							
						</form>
						<!-- 分隔线 -->
						<div class="span12">
							<ul class="nav nav-list">
								<li class="divider"></li>
							</ul>
						</div>
						<form action="submitpaper" method="post">
							<div>
								<!-- 可供选择的列表 -->
								<div class="span12">
									<strong style="font-size: 20px">已选择的试卷问题：</strong>
									<s:iterator value="#request.paper.questions" id="qus">
										<div class="bs-docs-example">
											<label class="checkbox"><input type="checkbox" value="<s:property value="value.id" />" name="questionaddtopaper">
											<s:property value="value.description" /></label>
										</div>
									</s:iterator>

								</div>
							</div>
							<!-- 分隔线 -->
							<div class="span12">
								<ul class="nav nav-list">
									<li class="divider"></li>
								</ul>
							</div>
							<div class="span12 text-right">
								<button class="btn btn-primary" type="button" id="btnPrevious">
									上一步<i class="icon-chevron-left icon-white"></i>
								</button>
								<button class="btn btn-primary" type="submit" id="btnSubmit">
									提交试卷<i class="icon-chevron-right icon-white"></i>
								</button>
							</div>
						</form>
					</div>
				</div>

			</div>

		</div>
	</div>
	<hr>
	<footer>
		<p>&copy; Alex 2015</p>
	</footer>
	</div>
	<!--/.fluid-container-->
	<script src="${pageContext.request.contextPath}/Admin/vendors/jquery-1.9.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/Admin/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/Admin/vendors/easypiechart/jquery.easy-pie-chart.js"></script>
	<script src="${pageContext.request.contextPath}/Admin/assets/scripts.js"></script>
	<script>
		
	</script>
</body>

</html>