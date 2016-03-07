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
					<div class="panel-heading">用户组管理</div>
					<div class="panel-body">
						<form action="updateusergroup" method="post" id="ugform">
							<p>
								组别：
								<select name="sgid" id="sgid">
									<option value="0">请选择</option>
									<s:iterator value="#request.usergroup" id="group">
										<s:if test="%{#group.id==#session.selectedgpid}">
											<option selected value=<s:property value="#group.id" />><s:property value="#group.groupName" /></option>
										</s:if>
										<s:else>
											<option value=<s:property value="#group.id" />><s:property value="#group.groupName" /></option>
										</s:else>
									</s:iterator>
								</select>
							</p>
							<p>
							<button class="btn btn-primary" type="button" id="btnAll">全选</button>
							<button class="btn btn-primary" type="button" id="btnRAll">全取消</button>
							<button class="btn btn-primary" type="button" id="btnSave">保存</button>
							<p>
								<s:iterator value="#request.userlist" id="usrlst">
									
									<s:if test="%{#usrlst.group!=null&&#usrlst.group.size()>0}">
										<s:iterator value="#usrlst.group" id="usrgrp">
											<s:if test="%{#usrgrp.id==#session.selectedgpid}">
												<label class="checkbox"><input type="checkbox" name ="selectedUser" checked value=<s:property value="#usrlst.loginId" />><s:property value="#usrlst.loginId" /></label>
											</s:if>
											<s:else>
												<label class="checkbox"><input type="checkbox" name ="selectedUser" value=<s:property value="#usrlst.loginId" />><s:property value="#usrlst.loginId" /></label>
											</s:else>
										</s:iterator>
									</s:if>
									<s:else>
										<label class="checkbox"><input type="checkbox" name ="selectedUser" value=<s:property value="#usrlst.loginId" />><s:property value="#usrlst.loginId" /></label>
									</s:else>
								</s:iterator>
							<input type="hidden" name="gid" id="gid" value="" />
						</form>


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
        $('#sgid').change(function() {
        	var url = 'listusergroup?gid=' + $('#sgid').find("option:selected").val();
        	location.href=url;
        });
        $('#btnSave').click(function() {
        	$('#gid').val($('#sgid').find("option:selected").val());
        	$('#ugform').submit();
        });
        </script>
    </body>

</html>