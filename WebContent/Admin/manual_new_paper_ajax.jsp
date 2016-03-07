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
						<form action="savepaper" method="post" id="paperform">
							<div class="span12">
								<div class="span6">
									<strong style="font-size: 20px">试卷名称：</strong> <input name="paperName" value="" type="text">
								</div>
								<div class="span6">
									<strong style="font-size: 20px">考试时长（分钟）：</strong> <input name="duration" value="" type="text">
								</div>
							</div>
							<input type="hidden" value="" name="questionids" id="questionids">
							<!-- 分隔线 -->
							<div class="span12">
								<ul class="nav nav-list">
									<li class="divider"></li>
								</ul>
							</div>
							<div>
								<div class="span4">
									<strong style="font-size: 20px">主题：</strong> <select name="query.subjectName" id="sub">
										<option value="0">请选择</option>
										<s:iterator value="#request.subject" id="subs">
											<option value=<s:property value="#subs.subjectName" />><s:property value="#subs.subjectName" /></option>
										</s:iterator>
									</select>
								</div>
								<div class="span4">
									<strong style="font-size: 20px">类型：</strong> <select name="query.questionType" id="sqtype">
										<option value="0">请选择</option>
										<s:iterator value="#request.qType" id="qtype">
											<option value=<s:property value="#qtype" />><s:property value="#qtype" /></option>
										</s:iterator>
									</select>
								</div>
								<div class="span4">
									<strong style="font-size: 20px">难易程度：</strong> <select name="query.questionLevel" id="level">
										<option value="0">请选择</option>
										<s:iterator value="#request.qLevel" id="qlevel">
											<option value=<s:property value="#qlevel" />><s:property value="#qlevel" /></option>
										</s:iterator>
									</select>
								</div>
								<div class="text-right">
									<button class="btn btn-primary" type="button" id="btnQuery">
										查询<i class="icon-search icon-white"></i>
									</button>
								</div>

								<!-- 分隔线 -->
								<div class="span12">
									<ul class="nav nav-list">
										<li class="divider"></li>
									</ul>
								</div>

								<div>
									<!-- 可供选择的列表 -->
									<div class="span12">
										<strong style="font-size: 20px">可供选择的问题：</strong>
											<div class="bs-docs-example" id="avlabelcontainer">
												
											</div>
									</div>
									<button class="btn btn-primary" type="button" id="btnAddtoPaper">
										添加到试卷<i class="icon-plus icon-white"></i>
									</button>
								</div>
								<!-- 分隔线 -->
								<div class="span12">
									<ul class="nav nav-list">
										<li class="divider"></li>
									</ul>
								</div>
								<div>
								<div class="span12">
									<strong style="font-size: 20px">已选择的问题：</strong>
									<div class="accordion" id="accordion2">
										<s:set name="num" value="1" />
										<s:iterator value="#request.subject" id="sb">
											<div class="accordion-group">
												<div class="accordion-heading">
													<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href='<s:property value="%{'#'+#sb.subjectName}"/>'><s:property value="#sb.subjectName" />
														<span class="badge badge-info" id="sp<s:property value="#sb.subjectName"/>">0</span>
													</a>
												</div>
												<div id='<s:property value="#sb.subjectName"/>' class="accordion-body collapse in">
													<ol>
														
													</ol>
												</div>
											</div>
											<s:set name="num" value="%{#num+1}" />
										</s:iterator>
									</div>
									
									<button class="btn btn-primary" type="submit" id="btnNext">
										提交<i class="icon-chevron-right icon-white"></i>
									</button>
								</div>
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
	$('#sub').change(function(){
		$('#sqtype option').eq(0).attr("selected",true);
		$('#level option').eq(0).attr("selected",true);
	});
	$('#sqtype').change(function(){
		$('#sub option').eq(0).attr("selected",true);
		$('#level option').eq(0).attr("selected",true);
	});
	$('#level').change(function(){
		$('#sqtype option').eq(0).attr("selected",true);
		$('#sub option').eq(0).attr("selected",true);
	});
	$('#btnAddtoPaper').click(function(){
		$('input[name="questionaddtopaper"]:checked').each(function(){ 
			
			//分析每一个选项，并逐一添加到页面
			var str = $(this).val();	//选项的value id/subject/description
			var strval = str.split('/');
			var id = strval[0];
			var subject = strval[1];
			var desc = strval[2];
			
			//寻找对应的Subject节点
			var html = '<div class="accordion-inner"><li>'+desc+'</li></div>';
			if (!isExisted(subject,desc)) {
				$('#'+subject+' ol').append(html);
			}
			
			//更新徽标
			var count=0
			$('#'+subject+' li').each(function(){
				count = count +1;
			});
			//alert(count);
			$('#sp'+subject).text(count);
			
			//更新表单数据
			var qids = $('#questionids').val();
			if (qids.length<=0){
				qids = qids + id;
			} else {
				qids = qids + "," + id;
			}
			
			$('#questionids').val(qids)
			});

		
	});
	function isExisted(subject,desc) {
		var flag = false;
		$('#'+subject+' li').each(function(){
			//alert($(this).text());
			if ($(this).text()==desc) {
				flag = true;
			}
		});
		return flag;
	}
	$('#btnQuery').click(function(){
		
		$('#avlabelcontainer').empty();
		
		var ajaxurl = '/OnlineTestSSH/admin/json/';
		
		if ($('#sub').val()!=0){
			//按Subject过滤
			ajaxurl = ajaxurl + 'getallquestionbysubject?questionSubject=' + $('#sub').val();
			//alert(ajaxurl);
		} else if ($('#sqtype').val()!=0){
			//按Type过滤
			ajaxurl = ajaxurl + 'getallquestionbytype?questionType=' + $('#sqtype').val();
			//alert(ajaxurl);
		} else if ($('#level').val()!=0){
			//按Level过滤
			ajaxurl = ajaxurl + 'getallquestionbylevel?questionLevel=' + $('#level').val();
			//alert(ajaxurl);
		} else {
			//All
			ajaxurl = ajaxurl + 'getallquestion';
			//alert(ajaxurl);
		}
		//发送Ajax请求
    	$.ajax({
    		url:ajaxurl,
    		type:'post',
    		cache:false,
    		dataType:'json',
    		success:function(data){
    			//alert(data.total);
    			//用数据来生成UI
    			if (data.total>0) {
    				//alert('记录数：' + data.total);
    				for(var i=1; i<=data.total; i++){
    					//alert(data.ID1.description);
    					var description = 'data.ID' + i + '.description';
    					var id = 'data.ID' + i + '.id';
    					var subject = 'data.ID' + i + '.subject.subjectName';
    					var html = '<label class="checkbox"><input type="checkbox" value='+ eval(id) +'/' + eval(subject) + '/' + eval(description) +' name="questionaddtopaper">' + eval(description) +'</label>';
    					$('#avlabelcontainer').append(html);
    				}
    				
    			}
    		},
    		error:function(){
    			alert('ajax request error!');
    		}
    	});
	});
	</script>
</body>

</html>