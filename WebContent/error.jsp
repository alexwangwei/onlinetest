<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/site.css" rel="stylesheet" media="screen">
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    
<title>出错啦</title>
</head>
<body>

	<div class="container-fluid">
		<div class="row" style="margin-top: 100px; margin-bottom: 25px">
			<div class="col-md-12 text-center">
				<img alt="" src="${pageContext.request.contextPath}/err.png">
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 text-center" style="margin-bottom: 10px">
				<h4>服务器错误，无法访问你请求的资源......</h4>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 text-center">
				<a href="${pageContext.request.contextPath}" ><button type="button" class="btn btn-primary">返回首页</button></a>
			</div>
		</div>
	</div>
<script src="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/js/jquery-2.1.4.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
	
</body>
</html>