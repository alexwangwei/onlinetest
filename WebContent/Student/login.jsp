<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>在线考试系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- basic styles -->

<link href="${pageContext.request.contextPath}/Student/assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Student/assets/css/font-awesome.min.css" />
<link href="${pageContext.request.contextPath}/site.css" rel="stylesheet" media="screen">


<!-- ace styles -->

<link rel="stylesheet" href="${pageContext.request.contextPath}/Student/assets/css/ace.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Student/assets/css/ace-rtl.min.css" />
<link rel="shortcut icon" href="${pageContext.request.contextPath}/haski_dog_16px.ico" type="image/x-icon"/>

</head>

<body class="login-layout">
	<div class="main-container">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<div class="login-container">
						<div class="center">
							<h1>
								<i class="icon-leaf green"></i> <span class="red">Online</span> <span class="white">Testing</span>
							</h1>
							<h4 class="blue">&copy; Alex 2015</h4>
						</div>

						<div class="space-6"></div>

						<div class="position-relative">
							<div id="login-box" class="login-box visible widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header blue lighter bigger">
											<i class="icon-coffee green"></i> 请输入登录信息
										</h4>

										<div class="space-6"></div>

										<form method="post" action="login">
											<fieldset>
												<label class="block clearfix"> <span class="block input-icon input-icon-right"> <input type="text" name="userid" class="form-control" placeholder="用户名" /> <i class="icon-user"></i>
												</span>
												</label> <label class="block clearfix"> <span class="block input-icon input-icon-right"> <input type="password" name="password" class="form-control" placeholder="密码" /> <i class="icon-lock"></i>
												</span>
												</label>

												<div class="space"></div>

												<div class="clearfix">
													<button type="submit" class="width-35 pull-right btn btn-sm btn-primary">
														<i class="icon-key"></i> 登录
													</button>
												</div>

												<div class="space-4"></div>
											</fieldset>
										</form>
									</div>
									<!-- /widget-main -->

								</div>
								<!-- /widget-body -->
							</div>
							<!-- /login-box -->
						</div>
						<!-- /position-relative -->
					</div>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->


	<!--[if !IE]> -->

	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='assets/js/jquery-2.0.3.min.js'>"
								+ "<"+"/script>");
	</script>

	<!-- <![endif]-->

	<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

	<script type="text/javascript">
		if ("ontouchend" in document)
			document
					.write("<script src='assets/js/jquery.mobile.custom.min.js'>"
							+ "<"+"/script>");
	</script>

	<!-- inline scripts related to this page -->

	<script type="text/javascript">
		function show_box(id) {
			jQuery('.widget-box.visible').removeClass('visible');
			jQuery('#' + id).addClass('visible');
		}
	</script>

</body>
</html>
