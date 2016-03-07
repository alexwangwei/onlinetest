<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>在线考试系统-后台管理</title>
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/Admin/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/Admin/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/Admin/assets/styles.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/site.css" rel="stylesheet" media="screen">
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<script src="js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>

<link rel="shortcut icon" href="rich_dog_128px.ico" type="image/x-icon"/>
</head>
<body id="login">
	<div class="container">
		<form class="form-signin" action="login" method="post">
			<h2 class="form-signin-heading">后台管理入口</h2>
			<input type="text" class="input-block-level" placeholder="用户名" name="userid"> <input type="password" class="input-block-level" placeholder="密码" name="password">
			
			<s:if test="%{errmsg!=null}">
			<div class="alert alert-warning alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong><s:property value="errmsg" /></strong>
			</div>
			</s:if>
			
			<button class="btn btn-large btn-primary" type="submit">登录</button>
		</form>

	</div>
	<script src="${pageContext.request.contextPath}/Admin/vendors/jquery-1.9.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/Admin/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>