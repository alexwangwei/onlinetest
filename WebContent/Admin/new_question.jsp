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
					<div class="panel-heading">创建新的问题</div>
					<div class="panel-body">
						<form action="savequestion" method="post" id="questionform">
							<p>
								问题类型：
								<select name="questiontype" id="sqtype">
									<option value="0">请选择</option>
									<s:iterator value="#request.qType" id="qtype">
										<option value=<s:property value="#qtype" />><s:property value="#qtype" /></option>
									</s:iterator>
								</select>
								&nbsp;&nbsp; 
								问题难易程度：
								<select name="questionlevel" id="level">
									<option value="0">请选择</option>
									<s:iterator value="#request.qLevel" id="qlevel">
										<option value=<s:property value="#qlevel" />><s:property value="#qlevel" /></option>
									</s:iterator>
								</select>
								&nbsp;&nbsp; 
								问题主题：
								<select name="subjectid" id="sub">
									<option value="0">请选择</option>
									<s:iterator value="#request.subject" id="subs">
										<option value=<s:property value="#subs.id" />><s:property value="#subs.subjectName" /></option>
									</s:iterator>
								</select>
							</p>
							<p>
								分值：<input type="number" name="question.value">
								&nbsp;&nbsp; 
								考试时长（分钟）：<input type="number" name="question.duration">
								&nbsp;&nbsp; 
							</p>
							<p>
								问题描述：<textarea rows="8" class="span12" name="question.description"></textarea>
							</p>
							<p>
							<div id="optionblock">
								选项：
								<button class="btn btn-primary" type="button" id="btnAddOption">增加选项</button>
								<button class="btn btn-primary" type="button" id="btnDelOption">删除选项</button>
								<div id="opcontainer">
								<div id="opt1">
									<label for="option1" class=" control-label">选项1</label>
									<div>
										<textarea rows="3" id="option1" name="option1" class="span12"></textarea>
									</div>
								</div>
								
								</div>
							</div>
							</p>
							<p>
								<div>
								参考答案：<textarea rows="5" class="span12" placeholder="如果是多个选项，请以逗号分隔；如果是判断题则请直接标注Y或者N" name="question.refAnswer"></textarea>
								</div>
							</p>
							<button class="btn btn-primary" type="submit" id="btnSave">保存</button>
							<button class="btn btn-primary" type="button" id="btnCancel">取消</button>
							<p>
							<input type="hidden" id="optionCount" name="optioncount" value="1">
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
		$('#sqtype').change(function(){
			var type = $('#sqtype').val();
			switch(type)
			{
			case "单选题":
				$('#optionblock').show();
				break;
			case "多选题":
				$('#optionblock').show();
				break;
			case "判断题":
				$('#optionblock').hide();
				$('#optionCount').attr('value',0);
				break;
			case "问答题":
				$('#optionblock').hide();
				$('#optionCount').attr('value',0);
				break;
			}
		});
		
		$('#btnAddOption').click(function(){
			//alert($('#opcontainer div:last-child').html());
			//alert($('#opcontainer').children().length);
			$('#opcontainer').append($('#opcontainer div:first-child').clone());
			var num = $('#opcontainer').children().length;
			$('#opcontainer div[id]:last-child').attr('id', 'opt'+num);
			selectmark = 'opt' + num;
			//alert($('#'+selectmark).attr('id'));
			$('#'+selectmark +' label').attr('for','option'+num);
			$('#'+selectmark +' label').text('选项'+num);
			$('#'+selectmark +' textarea').attr('id', 'option'+num);
			$('#'+selectmark +' textarea').attr('name', 'option'+num);
			
			$('#optionCount').attr('value',num);
		});
        </script>
    </body>

</html>