<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>kaikaimail</title>
<link rel="stylesheet" type="text/css"
	href="simditor/styles/simditor.css" />
<link rel="stylesheet" type="text/css"
	href="simditor/styles/simditor-emoji.css" />
<link rel="stylesheet" type="text/css"
	href="simditor/styles/simditor-checklist.css" />
<link rel="stylesheet" type="text/css"
	href="css/write_texiao.css"/>
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="simditor/scripts/module.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="simditor/scripts/hotkeys.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="simditor/scripts/uploader.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="simditor/scripts/simditor.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="simditor/scripts/simditor-emoji.js"></script>
<script src="simditor/scripts/simditor-checklist.js"></script>
<script src="js/write_page_from_check.js" type="text/javascript"
	charset="utf-8"></script>

</head>
<body>
	<div class="content" id="content">
		<div style="width: 100%;padding: 0;margin: 0">
				<h4 align="center">发邮件</h4>	
		</div>
		<div>
			<br />
		</div>
		<div class="form" id="form">
			<form class="form-horizontal">
			<input type="hidden" name="attapath" id="attapath">
				<div class="form-group">
					<label for="inputFrom" class="col-sm-1 control-label">发件人:</label>
					<div class="col-sm-9">
						<input type="email" class="form-control" id="inputFrom"
							placeholder="${sessionScope.user.email}" disabled>
					</div>
				</div>
				<div class="form-group">
					<label for="inputTo" class="col-sm-1 control-label">收件人:</label>
					<div class="col-sm-9">
						<input type="email" class="form-control" id="inputTo" list="contacts" value="${requestScope.email.receiver}"
							placeholder="收件人   多个收件人用   ,   隔开">
							<datalist id="contacts">
				
							</datalist>
					</div>
				</div>
				<div class="form-group">
					<label for="inputSubject" class="col-sm-1 control-label">主题:</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="inputSubject"
							placeholder="邮件主题">
					</div>
				</div>
				<div class="form-group">
					<label for="inputContent" class="col-sm-1 control-label">邮件内容:</label>
					<div class="col-sm-9">
						<textarea class="form-control" id="inputContent"
							placeholder="邮件内容"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label for="exampleInputFile" class="col-sm-1 control-label">发送：</label>
					
					<div class="col-sm-8">
						 <input type="button" class="btn btn-primary  btn-lg" id="send" value="发送">
					</div>
				</div>
			</form>
			<form class="form" action="fileupload" enctype="multipart/form-data" method="post">
				<div class="form-group">
					<label for="exampleInputFile" class="col-sm-1 control-label">附件：</label>
					<div class="col-sm-8">
						 <input type="file" id="exampleInputFile" name="attachment">
						<p class="help-block">上传的附件请保持在10M以下</p> 
						<p id="msg" class="help-block"></p>
					</div>
					 <input type="button" class="btn btn-primary btn-lg" id="fileuploadbutt" value="上传" onclick="javascript:fileupload()">
				</div>
			</form> 
		</div>
	</div>
	<div class="spinner">
		<div class="bounce1"></div>
		<div class="bounce2"></div>
		<div class="bounce3"></div>
	</div>
</body>
<script type="text/javascript">
	var editor = new Simditor({
		textarea : $('#inputContent'),
		placeholder : '请输入内容。。。',
		toolbar : [ 'title', 'bold', 'italic', 'underline', 'strikethrough',
				'fontScale', 'color', '|', 'ol', 'ul', 'blockquote', 'code',
				'table', '|', 'link', 'image', 'hr', 'checklist', '|',
				'indent', 'outdent', 'alignment', 'emoji' ],
		emoji : {
			imagePath : 'simditor/images/emoji/'
		},
		defaultImage : 'simditor/images/image.png',
	});
	$('#send').click(function() {
		form_check();
	});
	
	
	function fileupload(){
		var file = $("#exampleInputFile").prop("files")[0];
		var datas = new FormData();
		datas.append("attachment",file);
		$.ajax({
		    type: 'POST',
		    data: datas,
		    url: 'fileupload',
		    cache: false,
		    contentType: false,
		    processData: false,
		    success: function(data){
		        if(data.msgcode==true){
		        	//表示上传成功
		        	//对上传按钮和上传框禁用
		        	$("#exampleInputFile").attr("disabled","disabled");
		        	$("#fileuploadbutt").attr('disabled','disabled');
		        	
		        	$("#msg").css("color","blue").text(data.msg);
		        	//将文件路径加到隐藏框
		        	$("#attapath").val(data.filepath);
		        	alert(data.filepath)
		        }else{
		        	//表示上传失败
		        	$("#msg").css("color","blue").text(data.msg);
		        	//装载错误消息
		        	alert(data.msg);
		        	//清空上传框
		        	$("#exampleInputFile").val("");
		        }
		    }
		});
	}
	
	$(function(){
		$.getJSON("getContacts",
		function(data){
			var $contacts=$("#contacts");
			$.each(data, function(i,value) {
				$contacts.append("<option value='"+value+"'/>");
			});
		});
	});
</script>
</html>
