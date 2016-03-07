<!DOCTYPE html>
<html>

<head>
<title>Calendar</title>
</head>

<body>
	test
	<div id="data">
	</div>
	<button type="button" id="btnOK">Click Me!</button>
	<script src="${pageContext.request.contextPath}/Admin/vendors/jquery-1.9.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/Admin/vendors/jquery-ui-1.10.3.js"></script>
	<script>
    $(document).ready(function(){
    	$.ajax({
    		url:'./json1/getallquestion',
    		type:'post',
    		cache:false,
    		dataType:'json',
    		success:function(data){
    			alert(data);
    		}
    	});
    });
    $('#btnOK').click(function(){
    	$.ajax({
    		url:'http://localhost:8080/OnlineTestSH/admin/json/getallquestion',
    		type:'post',
    		cache:false,
    		dataType:'json',
    		success:function(data){
    			alert(data[0].createDTime);
    		},
    		error:function(){
    			alert('error');
    		}
    	});
    });
    </script>
</body>

</html>