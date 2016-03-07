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
                                        <a tabindex="-1" href="login.html">Logout</a>
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
					<div class="panel-heading">创建新的考试试卷</div>
					<div class="panel-body">
						<form action="query" method="post" id="newpaperform">
							<p>
								试卷名称：
								<input type="text" name="paper.paperName" value="<s:property value="#session.pName" />">
								&nbsp;&nbsp; 
								考试时长（分钟）：
								<input type="number" name="durationmin" value="<s:property value="#session.pDuration" />">
								&nbsp;&nbsp; 
							</p>
							<p>
								问题主题：
								<select name="query.subjectName" id="sub">
									<option value="0">请选择</option>
									<s:iterator value="#request.subject" id="subs">
										<option value=<s:property value="#subs.id" />><s:property value="#subs.subjectName" /></option>
									</s:iterator>
								</select>
								&nbsp;&nbsp; 
								问题类型：
								<select name="query.questionType" id="sqtype">
									<option value="0">请选择</option>
									<s:iterator value="#request.qType" id="qtype">
										<option value=<s:property value="#qtype" />><s:property value="#qtype" /></option>
									</s:iterator>
								</select>
								&nbsp;&nbsp; 
								问题难易程度：
								<select name="query.questionLevel" id="level">
									<option value="0">请选择</option>
									<s:iterator value="#request.qLevel" id="qlevel">
										<option value=<s:property value="#qlevel" />><s:property value="#qlevel" /></option>
									</s:iterator>
								</select>
							</p>
							<p>
								分值：
								<input type="number" name="query.questionValue">
							</p>
							<button class="btn btn-primary" type="submit" id="btnSave">查询</button>
							<button class="btn btn-primary" type="button" id="btnCancel">添加到试卷</button>
							<button class="btn btn-primary" type="button" id="btnCancel">保存</button>
							<p>
							<div class="panel-body">
								可供选择的问题列表：
									<s:iterator value="#session.questions" id="ques">
										<label class="checkbox"><input type="checkbox" name="questionaddtopaper" value="<s:property value="#ques.id" />"><s:property value="#ques.description" /></label>
									</s:iterator>
							</div>
							<div class="accordion" id="accordion2">
								试卷问题列表：
								<s:iterator value="#request.subject" id="subs">
									<div class="accordion-group">
										<div class="accordion-heading">
											<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#<s:property value="#subs.id" />"><s:property value="#subs.subjectName" /></a>
										</div>
										<div id="<s:property value="#subs.id" />" class="accordion-body collapse in">
											<div class="accordion-inner">Anim pariatur cliche...<a href="#"><button class="btn btn-primary" type="button" id="btn#">删除</button></a></div>
											<div class="accordion-inner">Anim pariatur cliche...<a href="#"><button class="btn btn-primary" type="button" id="btn#">删除</button></a></div>
										</div>
									</div>
								</s:iterator>

							</div>
						</form>
						

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