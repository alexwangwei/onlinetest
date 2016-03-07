<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>在线考试系统</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!-- basic styles -->
	
	<link href="${pageContext.request.contextPath}/Student/assets/css/bootstrap.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/Student/assets/css/font-awesome.min.css" />
	<!-- fonts -->
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />
	<!-- ace styles -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/Student/assets/css/ace.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/Student/assets/css/ace-rtl.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/Student/assets/css/ace-skins.min.css" />
	<!-- ace settings handler -->
	<script src="${pageContext.request.contextPath}/Student/assets/js/ace-extra.min.js"></script>
	
	<link rel="shortcut icon" href="rich_dog_128px.ico" type="image/x-icon"/>
</head>
<body>

	<div class="main-container" id="main-container">

		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> <span class="menu-text"></span>
			</a>
			<div class="main-content">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->

							<div class="error-container">
								<div class="well">
									<h1 class="grey lighter smaller">
										<span class="blue bigger-125"> <i class="icon-sitemap"></i> 404
										</span> Page Not Found
									</h1>
									<hr />
									<h3 class="lighter smaller">We looked everywhere but we couldn't find it!</h3>

									<div>
										<form class="form-search">
											<span class="input-icon align-middle"> <i class="icon-search"></i> <input type="text" class="search-query" placeholder="Give it a search..." />
											</span>
											<button class="btn btn-sm" onclick="return false;">Go!</button>
										</form>

										<div class="space"></div>
										<h4 class="smaller">Try one of the following:</h4>

										<ul class="list-unstyled spaced inline bigger-110 margin-15">
											<li><i class="icon-hand-right blue"></i> Re-check the url for typos</li>

											<li><i class="icon-hand-right blue"></i> Read the faq</li>

											<li><i class="icon-hand-right blue"></i> Tell us about it</li>
										</ul>
									</div>

									<hr />
									<div class="space"></div>

									<div class="center">
										<a href="#" class="btn btn-grey"> <i class="icon-arrow-left"></i> Go Back
										</a> <a href="#" class="btn btn-primary"> <i class="icon-dashboard"></i> Dashboard
										</a>
									</div>
								</div>
							</div>
							<!-- PAGE CONTENT ENDS -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
			<!-- /.main-content -->

		</div>
		<!-- /.main-container-inner -->

		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse"> <i class="icon-double-angle-up icon-only bigger-110"></i>
		</a>
	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->

	<script type="text/javascript">
		if ("ontouchend" in document)
			document
					.write("<script src='assets/js/jquery.mobile.custom.min.js'>"
							+ "<"+"/script>");
	</script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/typeahead-bs2.min.js"></script>

	<!-- ace scripts -->

	<script src="${pageContext.request.contextPath}/Student/assets/js/ace-elements.min.js"></script>
	<script src="${pageContext.request.contextPath}/Student/assets/js/ace.min.js"></script>

</body>
</html>
