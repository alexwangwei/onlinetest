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

<!--[if IE 7]>
		  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

<!-- page specific plugin styles -->

<!-- fonts -->

<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />

<!-- ace styles -->

<link rel="stylesheet" href="${pageContext.request.contextPath}/Student/assets/css/ace.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Student/assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Student/assets/css/ace-skins.min.css" />

<!--[if lte IE 8]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->

<!-- inline styles related to this page -->

<!-- ace settings handler -->

<script src="${pageContext.request.contextPath}/Student/assets/js/ace-extra.min.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lt IE 9]>
		<script src="assets/js/html5shiv.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->
</head>

<body>
	<div class="navbar navbar-default" id="navbar">
		<script type="text/javascript">
			try {
				ace.settings.check('navbar', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="navbar-container" id="navbar-container">
			<div class="navbar-header pull-left">
				<a href="#" class="navbar-brand"> <small> <i class="icon-leaf"></i> 在线考试系统
				</small>
				</a>
				<!-- /.brand -->
			</div>
			<!-- /.navbar-header -->

			<div class="navbar-header pull-right" role="navigation">
				<ul class="nav ace-nav">
					<li class="light-blue"><a data-toggle="dropdown" href="#" class="dropdown-toggle"> <img class="nav-user-photo" src="${pageContext.request.contextPath}/Student/assets/avatars/user.jpg" alt="Jason's Photo" /><span class="user-info"><s:property value="#session.username" /> </span> <i class="icon-caret-down"></i>
					</a>
						<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
							<li><a href="#"> <i class="icon-cog"></i> 设置
							</a></li>
							<li><a href="#"> <i class="icon-user"></i> 个人资料
							</a></li>
							<li class="divider"></li>
							<li><a href="logout"> <i class="icon-off"></i> 退出
							</a></li>
						</ul></li>
				</ul>
				<!-- /.ace-nav -->
			</div>
			<!-- /.navbar-header -->
		</div>
		<!-- /.container -->
	</div>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>


		<div class="main-container-inner">


			<div class="sidebar" id="sidebar">
				<script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'fixed')
					} catch (e) {
					}
				</script>

				<div class="sidebar-shortcuts" id="sidebar-shortcuts">
					<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span> <span class="btn btn-info"></span> <span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
					</div>
				</div>
				<!-- #sidebar-shortcuts -->

				<ul class="nav nav-list">
					<s:set name="num" value="1" />
					<s:iterator value="#request.qlist" id="ql">
						<s:if test="%{#request.qid==#num}">
							<li class="active">
						</s:if>
						<s:else>
							<li>
						</s:else>
						<a href='doexam?tid=<s:property value="#request.tid"/>&qid=<s:property value="#ql"/>'> <i class="icon-lightbulb"></i> <span class="menu-text"> 问题<s:property value="#num" />
						</span>
						</a>
						</li>
						<s:set name="num" value="%{#num+1}" />
					</s:iterator>
				</ul>
				<!-- /.nav-list -->

				<div class="sidebar-collapse" id="sidebar-collapse">
					<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
				</div>

				<script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'collapsed')
					} catch (e) {
					}
				</script>
			</div>

			<div class="main-content">
				<div class="breadcrumbs text-center" id="breadcrumbs" style="font-size: 20px">
					<script type="text/javascript">
						try {
							ace.settings.check('breadcrumbs', 'fixed')
						} catch (e) {
						}
					</script>
					考试剩余时间：
					<samll id="timer">11：10</samll>
					分钟
				</div>
				
				<div class="page-content">
					<div class="page-header">
						<h4>
							问题
							<s:property value="#request.qid" />
						</h4>
					</div>
					<!-- /.page-header -->
					<!-- 以下是问题主体 -->
					<form action="saveexam" method="post" id="dataform" name="dataform">
						<div class="row">
							<div class="span12">
								<div class="span12" style="font-size: 25px">
									<s:property value="#request.ques.description" />
								</div>
							</div>
							<div class="span12" style="font-size: 20px">

								<s:if test='%{#request.ques.questionType.toString()=="MUL"}'>
									<!-- 复选框 -->
									<div class="control-group" style="font-size: 20px">
										<s:set name="seq" value="1" />
										<ol>
										<s:iterator value="#request.ques.options" id="op">
										<li>
											<s:if test="%{#request.answerofuser!=null}">
												<s:if test="%{#request.answerofuser.answer.contains(#seq.toString())}">
													<div class="checkbox">
														<label> <input checked type="checkbox" class="ace" name="multiple" value='<s:property value="#seq"/>'> <span class="lbl"><s:property value="#op.optionDescription" /></span>
														</label>
													</div>
												</s:if>
												<s:else>
													<div class="checkbox">
														<label> <input type="checkbox" class="ace" name="multiple" value='<s:property value="#seq"/>'> <span class="lbl"><s:property value="#op.optionDescription" /></span>
														</label>
													</div>
												</s:else>
											</s:if>
											<s:else>
												<div class="checkbox">
													<label> <input type="checkbox" class="ace" name="multiple" value='<s:property value="#seq"/>'> <span class="lbl"><s:property value="#op.optionDescription" /></span>
													</label>
												</div>
											</s:else>
											<s:set name="seq" value="%{#seq+1}" />
											</li>
										</s:iterator>
										</ol>
									</div>
								</s:if>
								<s:if test='%{#request.ques.questionType.toString()=="SINGLE"}'>
									<!-- 单选框 -->
									<div class="control-group" style="font-size: 20px">
										<s:set name="seq" value="1" />
										<ol>
										<s:iterator value="#request.ques.options" id="op">
											<li>
											<s:if test="%{#request.answerofuser!=null}">
												<s:if test="%{#request.answerofuser.answer.contains(#seq.toString())}">
													<div class="radio">
														<label> <input checked type="radio" class="ace" name="single" value='<s:property value="#seq"/>'> <span class="lbl"><s:property value="#op.optionDescription" /></span>
														</label>
													</div>
												</s:if>
												<s:else>
													<div class="radio">
														<label> <input type="radio" class="ace" name="single" value='<s:property value="#seq"/>'> <span class="lbl"><s:property value="#op.optionDescription" /></span>
														</label>
													</div>
												</s:else>
											</s:if>
											<s:else>
												<div class="radio">
													<label> <input type="radio" class="ace" name="single" value='<s:property value="#seq"/>'> <span class="lbl"><s:property value="#op.optionDescription" /></span>
													</label>
												</div>
											</s:else>
											<s:set name="seq" value="%{#seq+1}" />
											</li>
										</s:iterator>
										</ol>
									</div>
								</s:if>

								<s:if test='%{#request.ques.questionType.toString()=="YESNO"}'>
									<!-- 判断题 -->
									<div class="control-group" style="font-size: 20px">
										<s:if test="%{#request.answerofuser!=null}">
											<s:if test='%{#request.answerofuser.answer.toUpperCase().equals("YES")}'>
												<div class="radio">
													<label> <input checked type="radio" class="ace" name="yesno" value="yes"> <span class="lbl">正确</span>
													</label>
												</div>
											</s:if>
											<s:else>
												<div class="radio">
													<label> <input type="radio" class="ace" name="yesno" value="Y"> <span class="lbl">正确</span>
													</label>
												</div>
											</s:else>
										</s:if>
										<s:else>
											<div class="radio">
												<label> <input type="radio" class="ace" name="yesno" value="Y"> <span class="lbl">正确</span>
												</label>
											</div>
										</s:else>
										<s:if test="%{#request.answerofuser!=null}">
											<s:if test='%{#request.answerofuser.answer.toUpperCase().equals("NO")}'>
												<div class="radio">
													<label> <input checked type="radio" class="ace" name="yesno" value="N"> <span class="lbl">错误</span>
													</label>
												</div>
											</s:if>
											<s:else>
												<div class="radio">
													<label> <input type="radio" class="ace" name="yesno" value="N"> <span class="lbl">错误</span>
													</label>
												</div>
											</s:else>
										</s:if>
										<s:else>
											<div class="radio">
												<label> <input type="radio" class="ace" name="yesno" value="no"> <span class="lbl">错误</span>
												</label>
											</div>
										</s:else>
									</div>
								</s:if>
								<s:if test='%{#request.ques.questionType.toString()=="OPENQUESTION"}'>
									<!-- 输入框 -->
									<s:if test="%{#request.answerofuser!=null}">
										<textarea class="form-control limited" id="form-field-9" maxlength="250" name="openquestion"><s:property value="#request.answerofuser.answer" /></textarea>
									</s:if>
									<s:else>
										<textarea class="form-control limited" id="form-field-9" maxlength="250" name="openquestion"></textarea>
									</s:else>
								</s:if>
							</div>
						</div>
						<!-- 以上是问题主体 -->
						<div class="row">
							<div class="span12 text-center">
								<s:if test='%{#request.isfirst!=1}'>
									<button class="btn btn-primary" id="btnPre" type="button">
										<i class="icon-double-angle-left align-top bigger-125"></i> 上一题
									</button>
								</s:if>
								<s:if test='%{#request.islast!=1}'>

									<button class="btn btn-primary" id="btnNext" type="button">
										<i class="icon-double-angle-right align-top bigger-125"></i> 下一题
									</button>

								</s:if>
								<button class="btn btn-success" type="button" id="btnSubmit">
									<i class="icon-cloud-upload bigger-125 align-top"></i> 交卷
								</button>
							</div>
						</div>
						<input type="hidden" name="tid" id="tid" value="<s:property value="#request.tid" />"> <input type="hidden" name="qid" id="qid" value="<s:property value="#request.qid" />"> <input type="hidden" name="direction" id="direction" value="">
					</form>
				</div>
			</div>
			<!-- /.main-content -->

		</div>
		<!-- /.main-container-inner -->

		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse"> <i class="icon-double-angle-up icon-only bigger-110"></i>
		</a>
	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->

	<!--[if !IE]> -->

	<script src="${pageContext.request.contextPath}/Student/assets/js/jquery-2.0.3.min.js"></script>

	<!-- <![endif]-->

	<!--[if IE]>
<script src="${pageContext.request.contextPath}/Student/assets/js/jquery-2.0.3.min.js"></script>
<![endif]-->

	<!--[if !IE]> -->

	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='${pageContext.request.contextPath}/Student/assets/js/jquery-2.0.3.min.js'>"
								+ "<"+"script>");
	</script>

	<!-- <![endif]-->

	<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>"+"<"+"script>");
</script>
<![endif]-->

	<script type="text/javascript">
		if ("ontouchend" in document)
			document
					.write("<script src='${pageContext.request.contextPath}/Student/assets/js/jquery.mobile.custom.min.js'>"
							+ "<"+"script>");
	</script>
	<script src="${pageContext.request.contextPath}/Student/assets/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/Student/assets/js/typeahead-bs2.min.js"></script>

	<!-- page specific plugin scripts -->

	<!--[if lte IE 8]>
		  <script src="assets/js/excanvas.min.js"></script>
		<![endif]-->

	<script src="${pageContext.request.contextPath}/Student/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
	<script src="${pageContext.request.contextPath}/Student/assets/js/jquery.ui.touch-punch.min.js"></script>
	<script src="${pageContext.request.contextPath}/Student/assets/js/jquery.slimscroll.min.js"></script>
	<script src="${pageContext.request.contextPath}/Student/assets/js/jquery.easy-pie-chart.min.js"></script>
	<script src="${pageContext.request.contextPath}/Student/assets/js/jquery.sparkline.min.js"></script>
	<script src="${pageContext.request.contextPath}/Student/assets/js/flot/jquery.flot.min.js"></script>
	<script src="${pageContext.request.contextPath}/Student/assets/js/flot/jquery.flot.pie.min.js"></script>
	<script src="${pageContext.request.contextPath}/Student/assets/js/flot/jquery.flot.resize.min.js"></script>

	<!-- ace scripts -->

	<script src="${pageContext.request.contextPath}/Student/assets/js/ace-elements.min.js"></script>
	<script src="${pageContext.request.contextPath}/Student/assets/js/ace.min.js"></script>

	<!-- inline scripts related to this page -->

	<script type="text/javascript">
		$('#btnNext').click(function() {
			//alert(Number($('#qid').val())+1);
			var vqid = $('#qid').val();
			var nextqid = Number(vqid);
			//alert(nextqid);
			$('#qid').val(nextqid);
			$('#direction').val('next');
			$('#dataform').submit();
		});
		$('#btnPre').click(function() {
			var preqid = Number($('#qid').val());
			$('#qid').val(preqid);
			$('#direction').val('pre');
			$('#dataform').submit();
		});
		$('#btnSubmit').click(function() {
			$('#dataform').attr('action','submitexam');
			$('#dataform').submit();
		});
		
		function updatetimer(num) {
			if (num<=0)	{
				//交卷
				$('#btnSubmit').click();
			} else {
				num = num - 1;
				$('#timer').text(formatTimmer(num));
				setTimeout(function(){updatetimer(num)},1000);
			}

		}

		function formatTimmer(seconds) {
			var formattime;
			//Hours
			var hours = parseInt(seconds/3600);
			if(hours>0){
				formattime = hours;
			} else{
				formattime = "00";
			}
			
			//Minutes
			var minutes = parseInt((seconds%3600)/60);
			if(minutes>0){
				formattime = formattime + ":" + minutes;
			} else{
				formattime = formattime + ":00";
			}
			
			//Second
			var seconds = (seconds%3600)%60;
			if(seconds>0){
				formattime = formattime + ":" + seconds;
			} else{
				formattime = formattime + ":00";
			}
			
			return formattime;
		}
			
			   $(document).ready(function(){ 
				    updatetimer(<%=request.getAttribute("availableseconds") %>);
			   });
		jQuery(function($) {
			$('.easy-pie-chart.percentage')
					.each(
							function() {
								var $box = $(this).closest('.infobox');
								var barColor = $(this).data('color')
										|| (!$box.hasClass('infobox-dark') ? $box
												.css('color')
												: 'rgba(255,255,255,0.95)');
								var trackColor = barColor == 'rgba(255,255,255,0.95)' ? 'rgba(255,255,255,0.25)'
										: '#E2E2E2';
								var size = parseInt($(this).data('size')) || 50;
								$(this)
										.easyPieChart(
												{
													barColor : barColor,
													trackColor : trackColor,
													scaleColor : false,
													lineCap : 'butt',
													lineWidth : parseInt(size / 10),
													animate : /msie\s*(8|7|6)/
															.test(navigator.userAgent
																	.toLowerCase()) ? false
															: 1000,
													size : size
												});
							})

			$('.sparkline').each(
					function() {
						var $box = $(this).closest('.infobox');
						var barColor = !$box.hasClass('infobox-dark') ? $box
								.css('color') : '#FFF';
						$(this).sparkline('html', {
							tagValuesAttribute : 'data-values',
							type : 'bar',
							barColor : barColor,
							chartRangeMin : $(this).data('min') || 0
						});
					});

			var placeholder = $('#piechart-placeholder').css({
				'width' : '90%',
				'min-height' : '150px'
			});
			var data = [ {
				label : "social networks",
				data : 38.7,
				color : "#68BC31"
			}, {
				label : "search engines",
				data : 24.5,
				color : "#2091CF"
			}, {
				label : "ad campaigns",
				data : 8.2,
				color : "#AF4E96"
			}, {
				label : "direct traffic",
				data : 18.6,
				color : "#DA5430"
			}, {
				label : "other",
				data : 10,
				color : "#FEE074"
			} ]
			function drawPieChart(placeholder, data, position) {
				$.plot(placeholder, data, {
					series : {
						pie : {
							show : true,
							tilt : 0.8,
							highlight : {
								opacity : 0.25
							},
							stroke : {
								color : '#fff',
								width : 2
							},
							startAngle : 2
						}
					},
					legend : {
						show : true,
						position : position || "ne",
						labelBoxBorderColor : null,
						margin : [ -30, 15 ]
					},
					grid : {
						hoverable : true,
						clickable : true
					}
				})
			}
			drawPieChart(placeholder, data);

			/**
			we saved the drawing function and the data to redraw with different position later when switching to RTL mode dynamically
			so that's not needed actually.
			 */
			placeholder.data('chart', data);
			placeholder.data('draw', drawPieChart);

			var $tooltip = $(
					"<div class='tooltip top in'><div class='tooltip-inner'></div></div>")
					.hide().appendTo('body');
			var previousPoint = null;

			placeholder.on('plothover', function(event, pos, item) {
				if (item) {
					if (previousPoint != item.seriesIndex) {
						previousPoint = item.seriesIndex;
						var tip = item.series['label'] + " : "
								+ item.series['percent'] + '%';
						$tooltip.show().children(0).text(tip);
					}
					$tooltip.css({
						top : pos.pageY + 10,
						left : pos.pageX + 10
					});
				} else {
					$tooltip.hide();
					previousPoint = null;
				}

			});

			var d1 = [];
			for (var i = 0; i < Math.PI * 2; i += 0.5) {
				d1.push([ i, Math.sin(i) ]);
			}

			var d2 = [];
			for (var i = 0; i < Math.PI * 2; i += 0.5) {
				d2.push([ i, Math.cos(i) ]);
			}

			var d3 = [];
			for (var i = 0; i < Math.PI * 2; i += 0.2) {
				d3.push([ i, Math.tan(i) ]);
			}

			var sales_charts = $('#sales-charts').css({
				'width' : '100%',
				'height' : '220px'
			});
			$.plot("#sales-charts", [ {
				label : "Domains",
				data : d1
			}, {
				label : "Hosting",
				data : d2
			}, {
				label : "Services",
				data : d3
			} ], {
				hoverable : true,
				shadowSize : 0,
				series : {
					lines : {
						show : true
					},
					points : {
						show : true
					}
				},
				xaxis : {
					tickLength : 0
				},
				yaxis : {
					ticks : 10,
					min : -2,
					max : 2,
					tickDecimals : 3
				},
				grid : {
					backgroundColor : {
						colors : [ "#fff", "#fff" ]
					},
					borderWidth : 1,
					borderColor : '#555'
				}
			});

			$('#recent-box [data-rel="tooltip"]').tooltip({
				placement : tooltip_placement
			});
			function tooltip_placement(context, source) {
				var $source = $(source);
				var $parent = $source.closest('.tab-content')
				var off1 = $parent.offset();
				var w1 = $parent.width();

				var off2 = $source.offset();
				var w2 = $source.width();

				if (parseInt(off2.left) < parseInt(off1.left)
						+ parseInt(w1 / 2))
					return 'right';
				return 'left';
			}

			$('.dialogs,.comments').slimScroll({
				height : '300px'
			});

			//Android's default browser somehow is confused when tapping on label which will lead to dragging the task
			//so disable dragging when clicking on label
			var agent = navigator.userAgent.toLowerCase();
			if ("ontouchstart" in document && /applewebkit/.test(agent)
					&& /android/.test(agent))
				$('#tasks').on('touchstart', function(e) {
					var li = $(e.target).closest('#tasks li');
					if (li.length == 0)
						return;
					var label = li.find('label.inline').get(0);
					if (label == e.target || $.contains(label, e.target))
						e.stopImmediatePropagation();
				});

			$('#tasks').sortable({
				opacity : 0.8,
				revert : true,
				forceHelperSize : true,
				placeholder : 'draggable-placeholder',
				forcePlaceholderSize : true,
				tolerance : 'pointer',
				stop : function(event, ui) {//just for Chrome!!!! so that dropdowns on items don't appear below other items after being moved
					$(ui.item).css('z-index', 'auto');
				}
			});
			$('#tasks').disableSelection();
			$('#tasks input:checkbox').removeAttr('checked').on('click',
					function() {
						if (this.checked)
							$(this).closest('li').addClass('selected');
						else
							$(this).closest('li').removeClass('selected');
					});

		})
	</script>

</body>
</html>

