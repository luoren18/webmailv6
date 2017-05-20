<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="UTF-8">
		<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<title></title>
	</head>

	<body>
		<nav class="page-header">
			<h1><small>消息提示页面</small></h1>
		</nav>

		<div class="alert alert-error col-sm-4" role="alert">
			${mesg.msg}
			<br>
			<a href="email_main" target="_top" class="alert-link"><h5>返回首页...</h5></a>
		</div>
	</body>
</html>