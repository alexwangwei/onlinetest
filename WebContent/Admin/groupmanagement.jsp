<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="com.alex.onlinetest.admin.service.NavigatorManagement" %>
<%@ page import="com.alex.onlinetest.hbm.Navigator" %>
<%@ page import="java.util.*" %>
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
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span>
                     <span class="icon-bar"></span>
                     <span class="icon-bar"></span>
                    </a>
                    <a class="brand" href="#">后台管理</a>
                    <div class="nav-collapse collapse">
                        <ul class="nav pull-right">
                            <li class="dropdown">
                                <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-user"></i><s:property value="#session.username"></s:property><i class="caret"></i>

                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a tabindex="-1" href="#">Profile</a>
                                    </li>
                                    <li class="divider"></li>
                                    <li>
                                        <a tabindex="-1" href="logout">Logout</a>
                                    </li>
                                </ul>
                            </li>
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
                        <li>
                            <a href="#"><i class="icon-chevron-down"></i><strong><%=navs.get(i).getNodeName() %></strong></a>
                        </li>
                        <%
                        	} else {
                        %>
                        <li>
                            <a href="<%=navs.get(i).getUrl() %>"><i class="icon-chevron-right"></i>&nbsp;&nbsp;&nbsp;&nbsp;<%=navs.get(i).getNodeName() %></a>
                        </li>
                        <%		
                        	}
                        }
                        %>
                    </ul>
                </div>
			<div class="span9" id="content">
				<div class="panel panel-primary">
					<div class="panel-heading">组名维护</div>
					<div class="panel-body">
						<form action="addgroup" method="post" id="groupform">
							<p>
								组名编号：<input type="text" class="form-control" id="groupId" name="groupId" placeholder="系统自动生成">&nbsp;&nbsp; 组名：<input type="text" class="form-control" id="groupName" name="groupName" placeholder="">
							</p>
							<button class="btn btn-primary" type="button" id="btnQuery">查询</button>
							<button class="btn btn-primary" type="submit" id="btnSave">保存</button>
							<p>
						</form>
						<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="categorylist">
							<thead>
								<tr>
									<th width="25%">组名编号</th>
									<th width="35%">组名</th>
									<th width="40%">操作</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="#request.usergroup.result" id="res">
									<tr>
										<td><s:property value="#res.id" /></td>
										<td><s:property value="#res.groupName" /></td>
										<td class="center">
											<button class="btn btn-warning">
												修改 <i class="icon-pencil icon-white"></i>
											</button> <a href="./delgroup?groupId=<s:property value="id" />"><button class="btn btn-danger">
													删除 <i class="icon-minus icon-white"></i>
												</button></a>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<div class="pagination pagination-centered">
							<ul>

								<li><a href="#">Prev</a></li>
								
								<s:bean name="org.apache.struts2.util.Counter" id="counter">
									<s:param name="first" value="1" />
									<s:param name="last" value="%{#request.usergroup.pageNum}" />
									<s:iterator>
										<li><a href="./listgroup?pageIndex=<s:property />"><s:property /></a></li>
									</s:iterator>
								</s:bean>

								<li><a href="#">Next</a></li>
							</ul>
						</div>

					</div>

				</div>

			</div>
		</div>
            <hr>
            <footer>
                <p>&copy; Vincent Gabriel 2013</p>
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