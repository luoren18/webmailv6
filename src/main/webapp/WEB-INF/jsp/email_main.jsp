<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="overflow: hidden;">

	<head>
		<meta charset="utf-8" />
		<link rel="icon" href="images/email_icon.ico" type="image/x-icon">
		<link rel="shortcut icon" href="images/email_icon.ico" type="image/x-icon">
		<!--页面-->
		<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="http://malsup.github.com/jquery.form.js"></script>
		<script src="js/email_weather_init.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			loadWeatherInfo();
		</script>
		<title>开开邮件</title>
	</head>

	<body>
		<nav class="collapse navbar-collapse" style="background-color:darkgray;">
			 <a class="navbar-brand"><strong>开开邮件</strong></a>
			 <!--天气信息-->
			 <div class="nav navbar-nav weather">
			 		<p class="navbar-text" id="currentCity">城市</p>
			 		<p class="navbar-text" ><img alt="" src="" id="pictureUrl"></p>
			 		<p class="navbar-text" id="weather">天气文字</p>
			 		<p class="navbar-text" id="temperature">温度</p>
			 		<p class="navbar-text" id="wind">风速强度</p>
			 		
			 </div>
			 <!--表单查询-->
			 <form class="navbar-form navbar-right" id="findweather" method="get" action="getweather">
				<div class="form-group">
					<input type="text" name="city" class="form-control" placeholder="输入城市查询天气">
				</div>
				<button type="submit" class="btn btn-default">查询</button>
			</form>
			<!-- 天气查询 -->
			<script type="text/javascript">
				$(function(){
					$("#findweather").ajaxForm(function(data){
						writeWeatherInfo(data);
					});
				});
				
			</script>
			<!-- 下拉列表 -->
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${sessionScope.user.email}<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li>
							<a href="userinfo" target="content">个人信息</a>
						</li>
						<li>
							<a href="#">设置</a>
						</li>
						<li>
							<a href="https://zh.wikipedia.org/zh-cn/%E4%BF%A1%E7%AE%B1" target="content">关于</a>
						</li>
						<li role="separator" class="divider"></li>
						<li>
							<a href="logout">退出登录</a>
						</li>
					</ul>
				</li>
			</ul>

		</nav>

		<div id="" class="" style="height:600px;">
			<div class="toolbar col-sm-2 " style="height: 100%;background-color: #E4E4E4;">
				<ul class="nav nav-pills nav-stacked container-fluid">
					<li role="">
						<p></p>
					</li>
					<a class="btn btn-default" href="write" target="content" role="button">发邮件</a>
					<a class="btn btn-default " href="content" target="content" role="button">收邮件</a>
					<li role="presentation">
						<a href="content" target="content"><img src="img/MailBox.ico">收信箱(${sessionScope.sidebar.inbox})</a>
					</li>
					<li role="presentation">
						<a href="unread_content" target="content"><img src="img/unread.ico" />未读邮件(${sessionScope.sidebar.unreadMail})</a>
					</li>
					<%-- <li role="presentation">
						<a href="draftmailbox" target="content"><img src="img/draft.ico" />草稿箱(${sessionScope.sidebar.drafts})</a>
					</li> --%>
					<li role="presentation">
						<a href="sendmailbox" target="content"><img src="img/red_mail_sent.ico" />已发送(${sessionScope.sidebar.sendmail})</a>
					</li>
					<li role="presentation">
						<a href="delmailbox" target="content"><img src="img/mail_deleted.ico"/>已删除邮件(${sessionScope.sidebar.delmail})</a>
					</li>
					<li role="presentation">
						<a href="emailview" target="content"><img src="img/statistics.ico"/>邮箱统计</a>
					</li>
					<li role="presentation">
						<hr color="#000000" />
					</li>
					<li class="presentation">
						<a href="email_music" target="_blank"><img src="img/music.ico" />邮箱音乐</a>
					</li>
					<li class="presentation">
						<a href="translate" target="_blank"><img src="img/fanyi.ico"/>邮箱翻译</a>
					</li>
					<li class="presentation">
						<a href="http://www.kuwo5.com/" target="content"><img src="img/calculator.net.ico"/>邮箱计算器</a>
					</li>
					<li class="presentation">
						<a href="contacts" target="content"><img src="img/contacts.ico"/>联系人</a>
					</li>
					<!-- <li class="presentation">
						<a href="#"><img src="img/chazhao.ico" />查找</a>
					</li> -->
					<li class="presentation">
						<a href="https://www.google.com.hk" target="_blank"><img src="img/Help.ico"/>帮助</a>
					</li>
					<li class="presentation">
						<a href="logout"><img src="img/logout.ico"/>退出</a>
					</li>
				</ul>
			</div>
			<div class="col-sm-10" style="height:100%; background-color: #F8F8F8;">
				<iframe id="content" name="content" src="content" frameborder="0" width="100%" scrolling="auto" height="600px" style="z-index: 99;">
					
				</iframe>
			</div>
		</div>
		
	</body>

</html>