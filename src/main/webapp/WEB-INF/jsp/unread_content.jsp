<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/unreadmailload.css" rel="stylesheet" type="text/css">
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.css">
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
	type="text/javascript" charset="utf-8"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="js/data_load.js" type="text/javascript" charset="utf-8"></script>
<title></title>
</head>
<body>
	<div id="content">
		<div style="width: 100%">
			<h4 align="center">
				未读邮件 <a href="syncmail" id="syn" onclick="javascript:texiao()"
					class="btn btn-success btn-sm active" role="button"> <span
					class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
				</a>
			</h4>
		</div>
		<table id="table" data-search="true">
		</table>
	</div>
	<div id="loding">
		<div class="spinner">
		  <div class="cube1"></div>
		  <div class="cube2"></div>
		</div>
	</div>
</body>
<script type="text/javascript">
		unread_inbox_load();
		function texiao(){
			$('#loding').show();
			$('#loding').css('z-index', 1000);
			return true;
		}
</script>
</html>
