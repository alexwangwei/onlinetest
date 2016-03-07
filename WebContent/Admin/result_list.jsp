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
						<li class="dropdown"><a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-user"></i>
							<s:property value="#session.username"></s:property><i class="caret"></i>

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
					<li><a href="#"><i class="icon-chevron-down"></i><strong><%=navs.get(i).getNodeName() %></strong></a></li>
					<%
                        	} else {
                        %>
					<li><a href="<%=navs.get(i).getUrl() %>"><i class="icon-chevron-right"></i>&nbsp;&nbsp;&nbsp;&nbsp;<%=navs.get(i).getNodeName() %></a></li>
					<%		
                        	}
                        }
                        %>
				</ul>
			</div>
			<div class="span9" id="content">
				<div class="panel panel-primary">
					<div class="panel-heading">考试成绩一览表</div>
					<div class="panel-body">
						<form action="resultview" method="post">
							<div class="span12">
								<div class="span4">
									考试排期：
									<select name="scheduleid">
										<s:iterator value="#request.schedulelist" id="res">
											<s:if test="%{#res.id==#request.selectsid}" >
												<option selected value='<s:property value="#res.id" />'><s:property value="#res.name" /></option>
											</s:if>
											<s:else>
												<option value='<s:property value="#res.id" />'><s:property value="#res.name" /></option>
											</s:else>
										</s:iterator>
									</select>
								</div>
								<div class="span4">
									<button class="btn btn-primary" type="submit" id="btnQuery">查询</button>
								</div>
							</div>
						</form>
						<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="categorylist">
							<thead>
								<tr>
									<th width="10%">试卷名称</th>
									<th width="5%">用户</th>
									<th width="10%">实际完成时间</th>
									<th width="5%">题目总数</th>
									<th width="5%">回答正确数</th>
									<th width="5%">试卷满分</th>
									<th width="5%">实际得分</th>
									<th width="5%">正确率%</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="#request.rrlist" id="res">
									<tr>
										<td><s:property value="#res.task.taskName" /></td>
										<td><s:property value="#res.task.owner" /></td>
										<td><s:date name="#res.finishDate" format="yyyy-MM-dd HH:mm:ss" /></td>
										<td><s:property value="#res.amountOfQuestion" /></td>
										<td><s:property value="#res.amountOfRight" /></td>
										<td><s:property value="#res.totalValue" /></td>
										<td><s:property value="#res.actualValue" /></td>
										<td><s:property value="#res.percentageOfPass" />%</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>

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
        $(function() {
            // Easy pie charts
            $('.chart').easyPieChart({animate: 1000});
        });
        </script>
</body>

</html>