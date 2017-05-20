<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8">
<title>用户信息</title>
<meta name="description" content="">
<meta http-equiv="cleartype" content="on">
<link rel="stylesheet" href="css/platform-1.css">
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>
</head>
<body>
	<script type="text/javascript">
		var WIDTH = $(window).innerWidth();
		var HEIGHT = $(window).innerHeight();
		$(document).ready(function() {
			$(".page").css("height", HEIGHT + "px");
			$("#id").val('${sessionScope.user.id}');
			$("#password").val('${sessionScope.user.password}');
			$("#psw2").val('${sessionScope.user.password}');
			$("#username").val('${sessionScope.user.username}');
			$("#email").val('${sessionScope.user.email}');
			$("#phone").val('${sessionScope.user.phone}');
		});
		
	</script>
	<div class="page">
		<div class="form-div" id="form-div">
			<form id="reg-form" action="updateUserInfo" method="post">
				<input name="id" type="hidden" value="" id="id">
				<table>
					<tr>
						<td>用户名</td>
						<td><input name="username" type="text" id="username">
						</td>
					</tr>
					<tr>
						<td>密码</td>
						<td><input name="password" type="password" id="password" autocomplete="new-password" onfocus="this.blur()"></td>
					</tr>
					<tr>
						<td>确认密码</td>
						<td><input name="psw" type="password" id="psw2" onfocus="this.blur()"></td>
					</tr>
					<tr>
						<td>email</td>
						<td><input name="email" type="text" id="email" onfocus="this.blur()"></td>
					</tr>
					<tr>
						<td>手机号码</td>
						<td><input name="phone" type="text" id="phone"></td>
					</tr>
				</table>

				<div class="buttons">
					<input value="提交并修改" type="submit" 
						style="margin-right: 20px; margin-top: 20px;"> 
				</div>

				<br class="clear">
			</form>
		</div>



	</div>

	<script>
		$(document).ready(function() {
			var options={
					 url:"updateUserInfo", //form提交数据的地址
					 type:"post", //form提交的方式(method:post/get)
					 target:"#form-div", //服务器返回的响应数据显示在元素(Id)号确定
					 beforeSubmit:function(){
						 var regu=/^[a-zA-Z0-9_]{6,16}$/;
						 var username=$("#username").val();
						 var regh=/^1(3|4|5|7|8)[0-9]\d{8}$/;
						 var phone=$("#phone").val();
						 if(!regu.test(username)){
							 alert("用户名只能含有数字字母下划线，长度保持在6-16");
							 $("#username").focus();
							 return false;
						 }
						 if(!regh.test(phone)){
							 alert("请输入正确的手机号码");
							 $("#phone").focus();
							 return false;
						 }
						 return true;
					 }, //提交前执行的回调函数
					 success:function(data){
						 if(data.flag==true){
								window.location.href="success";
							}else{
								window.location.href="unsuccessful";
							} 
					 }, //提交成功后执行的回调函数
					 dataType:"json", //服务器返回数据类型
					 clearForm:true, //提交成功后是否清空表单中的字段值
					 restForm:true, //提交成功后是否重置表单中的字段值，即恢复到页面加载时的状态
					 timeout:6000 //设置请求时间，超过该时间后，自动退出请求，单位(毫秒)。
					}
			$('#reg-form').ajaxForm(options);
		});
	</script>
</body>
</html>
