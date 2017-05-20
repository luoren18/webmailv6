function form_check(){
	var from=$("#inputFrom").attr("placeholder");
	var to=$("#inputTo").val();
	var subject=$("#inputSubject").val();
	var content=$("#inputContent").val();
	var attapath=$("#attapath").val();
	/**********************收件人的验证*****************/
	to = to.replace(/\s+/g,"");     
	if(to.length==0){
		alert("收件人的不能为空!");
		$("#inputTo").focus();
		return false;
	}
	else{
		var tos=to.split(",");
		var reg=/^([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+.[a-zA-Z]{2,4}$/;
		$.each(tos, function(i,value) {
			if(!reg.test(value)){
				alert("第"+(i+1)+"个邮箱地址错误");
				$("#inputTo").focus();
				return false;
			}
		});
	}
	/*******************主题验证*********************/
	if(subject.trim().length==0){
		alert("邮件主题为空!");
		$("#inputSubject").focus();
		return false;
	}
	/***************邮件内容验证*********************/
	if(content.replace(/\s+/g,"").length==0){
		var flag=confirm("你确认要向对方发送一封空白邮件？");
		if(flag){
			content="<p>这是一封没有内容一封空白邮件</p>"
		}else{
			$("#inputContent").focus();
			return false;
		}
	}
	
	/*******************将邮件内容提交到后台***********************/
	$('.spinner').show();
	$('.spinner').css('z-index', 1000);
	$.post("sendMail",{
		"addresser":from,
		"receiver":to,
		"subject":subject,
		"bodytext":content,
		"attapath":attapath
	},
	function(data){
		if(data.flag==true){
			window.location.href="success";
		}else{
			window.location.href="unsuccessful";
		} 
	});
}
