<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.css">
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
	type="text/javascript" charset="utf-8"></script>

<title></title>
<style type="text/css">
body, div, span, img, article, a, b, textarea {
	margin: 0;
	padding: 0;
}

div {
	margin: 5px 0;
}

hr {
	color: gray;
	background: gray;
}

.container {
	width: 90%;
	position: absolute;
	margin: 10px 10px;
	height: 100%;
}

.subject {
	font-size: 14px;
	font-weight: bold;
	background: #F5B8B8;
}

.top2 {
	width: 100%;
	font-size: 12px;
}

.top2 span {
	margin-right: 5px;
	font-family: "Arial";
}

.top2 .from, top to {
	font-weight: 700;
}

.top2 .senddate {
	color: gray;
}

.top2 .others {
	margin-left: 10px;
}

.top2 .others a {
	text-decoration: none;
	color: blue;
}

.top2 .details {
	float: right;
}

.bottom1 {
	font-size: 12px;
	border-bottom: 1px dashed gray;
}

.bottom1 a {
	text-decoration: none;
	color: blue;
}

.bottom1 span.adds {
	margin-right: 20px;
}

.files .filesImg {
	border: 1px solid red;
	width: 40px;
	height: 40px;
	float: left;
}

.files .filesRight {
	float: left;
	font-size: 14px;
	margin-left: 10px;
}

.files .filesRight a {
	text-decoration: none;
	color: black;
	margin-right: 10px;
}

.files .filesRight>span:FIRST-CHILD {
	font-size: 12px;
}

.files .filesRight>span {
	width: 100%;
	display: block;
	font-size
}

.footer {
	clear: both;
}

.footer textarea {
	width: 100%;
	border: 1px solid gray;
}
</style>
</head>
<body>
	<div class="container">
		<div class="subject">
			<h3>${email.subject }</h3>
			<span><img alt="" src=""></span>
		</div>
		<div class="top2">
			<span class="from">${email.addresser}</span> <span class="senddate">${email.senddate }发送给</span>
			<span class="to">${email.receiver }</span> <span class="others"><a
				href="#"><b style="color: black">@</b><!-- 查看一个附件 --></a></span> <span
				class="details">详细信息<i>~</i></span>
		</div>
		<hr>
		<article>
			<div class="bodytext"></div>
			${email.bodytext }
		</article>
		<hr>
		<div>
			<%-- <a href="${email.attachment}">附件</a> --%>
		</div>
		<!-- <div class="bottom1">
			<span class="adds">@附件(<span class="numOfFiles"></span>个)
			</span>
			附件个数
			<span> <a href="">全部下载</a></span>
		</div> -->

		<div class="files">
			<!-- <div class="filesImg">
				<img src="" alt="p">
			</div>
			<div class="filesRight">
				<span><a>我是文件名字</a>(<a>文件大小</a>)</span> <span><a href="">查看并下载</a><a
					href="">转存到网盘</a><a href="">转存到文件中转站</a> </span>
			</div> -->
			<a href="sendmailByEmail?email=${email.addresser}" class="btn btn-danger active" role="button">回复他</a>
		</div>
		<!--<div class="footer">
			<textarea rows="1" cols="">快速回复哇咔咔</textarea>
		</div>-->
	</div>
	<div id="YOUDAO_SELECTOR_WRAPPER"
		style="display: none; margin: 0; border: 0; padding: 0; width: 420px; height: 340px;"></div>
	<script type="text/javascript"
		src="http://fanyi.youdao.com/openapi.do?keyfrom=webmailv6&key=414089525&type=selector&version=1.2&translate=on"
		charset="utf-8"></script>
</body>
</html>
