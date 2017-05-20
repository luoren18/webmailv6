<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="images/email_icon.ico" type="image/x-icon">
<link rel="shortcut icon" href="images/email_icon.ico"
	type="image/x-icon">
<title>开开邮箱翻译</title>
</head>
<body>
	<table border="0px" cellspacing="10px" cellpadding="10px">
		<caption><strong>开开邮箱翻译</strong></caption>
		<thead>
			<tr>
				<th>句子翻译</th>
				<th>单词翻译</th>
			</tr>
			
		</thead>
		<tr>
			<td>
				<div id="YOUDAO_FANYIER_WRAPPER"
					style="margin: 0; border: 0; padding: 0; width: 640px; height: 480px;"></div>

				<script type="text/javascript"
					src="http://fanyi.youdao.com/openapi.do?keyfrom=webmail&key=1224148747&type=fanyier&version=1.2&select=on"
					charset="utf-8">
					
				</script>
			</td>
			<td>
				<div id="YOUDAO_DICTER_WRAPPER"
					style="margin: 0; border: 0; padding: 0; width: 640px; height: 480px;"></div>
				<script type="text/javascript"
					src="http://fanyi.youdao.com/openapi.do?keyfrom=webmail&key=1224148747&type=dicter&version=1.2&select=on&translate=on"
					charset="utf-8">
					
				</script>
			</td>
		</tr>
	</table>
</body>
</html>