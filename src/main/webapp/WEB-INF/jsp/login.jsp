<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%  String path=request.getContextPath();  %>
<!DOCTYPE html>
<html lang="en" class="no-js">

<head>

<meta charset="utf-8">
<title>EMail Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- CSS -->
<link rel="stylesheet" href="<%=path%>/css/reset.css">
<link rel="stylesheet" href="<%=path%>/css/supersized.css">
<link rel="stylesheet" href="<%=path%>/css/style.css">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

</head>
<body oncontextmenu="return false">

	<div class="page-container">
		<h1>邮箱登陆</h1>
		<form action="loginCheck" method="post">
			<div>
				<input type="text" name="email" class="email" placeholder="E-mail"
					autocomplete="off" />
			</div>
			<div>
				<input type="password" name="password" class="password"
					placeholder="Password" oncontextmenu="return false"
					onpaste="return false" />
			</div>
			<button id="submit" type="submit">Sign in</button>	
		</form>
		<div class="connect">
			<p>If we can only encounter each other rather than stay with each
				other,then I wish we had never encountered.</p>
			<p style="margin-top: 20px;">如果只是遇见，不能停留，不如不遇见。</p>
		</div>
	</div>
	<div class="alert" style="display: none">
		<h2>消息</h2>
		<div class="alert_con">
			<p id="ts"></p>
			<p style="line-height: 70px">
				<a class="btn">确定</a>
			</p>
		</div>
	</div>

	<!-- Javascript -->
	<script src="http://apps.bdimg.com/libs/jquery/1.6.4/jquery.min.js"
		type="text/javascript"></script>
	<script src="js/supersized.3.2.7.min.js"></script>
	<script src="js/supersized-init.js"></script>
	<script>
		$(".btn").click(function(){
			is_hide();
		})
		var u = $("input[name=email]");
		var p = $("input[name=password]");
		$("#submit").live('click',function(){
			if(u.val().trim() == '' || p.val().trim() =='')
			{
				$("#ts").html("邮箱或密码不能为空~");
				is_show();
				return false;
			}
			else{
				var reg = /^([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+.[a-zA-Z]{2,4}$/;
				if(!reg.exec(u.val()))
				{
					$("#ts").html("错误的邮箱格式");
					is_show();
					return false;
				}
			}
			$("form").submit();
		});
		window.onload = function()
		{
			$(".connect p").eq(0).animate({"left":"0%"}, 600);
			$(".connect p").eq(1).animate({"left":"0%"}, 400);
		}
		function is_hide(){ $(".alert").animate({"top":"-40%"}, 300) }
		function is_show(){ $(".alert").show().animate({"top":"45%"}, 300) }
		</script>
</body>

</html>

