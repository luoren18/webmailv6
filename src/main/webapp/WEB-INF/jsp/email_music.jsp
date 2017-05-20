<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="referrer" content="never">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="images/email_icon.ico" type="image/x-icon">
<link rel="shortcut icon" href="images/email_icon.ico"
	type="image/x-icon">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="css/iconfont/iconfont.css">
<link rel="stylesheet" href="css/getmusic.css">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/progress-volume.css">
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<title>开开邮箱音乐</title>


</head>

<body>
	<h2 style="color:#eed;" class="cue" onclick="javascript:hide(this);">1.看到那个黑色的坨坨没，你难道不点一下吗!!!</h3>
	<h3 style="color:#ded;" class="cue" onclick="javascript:hide(this);">2.点完后,点那个白色的箭头又可以变回原样哦!!!</h4>
	<h4 style="color:#ced;" class="cue" onclick="javascript:hide(this);">3.看到三道杠了吗，可以选择你喜欢的音乐类型哦!!!</h5>
	<h5 style="color:#bed;" class="cue" onclick="javascript:hide(this);">4.你肯定看到了那个缩小版的三道杠，那是歌词,去点一下啊!!!</h6>
	<h6 style="color:#aed;" class="cue" onclick="javascript:hide(this);">5.觉着我们烦，那没得法，点我们好了!!!</h6>
	<div id="email_music" style="z-index: 999;">
		<div id="fm">
			<audio id="music"></audio>
			<div class="panel-min panel-handle switchover icon-music-b"></div>
			<div class="panel">
				<!-- 返回键 -->
				<div class="back close icon-back" title="小窗口"></div>
				<!-- 标题栏 -->
				<div class="title panel-handle">
					<p class="song-name"></p>
					<p class="singer"></p>
				</div>
				<!-- 频道栏 -->
				<div class="channels">
					<div class="channel-btn icon-menu" title="频道"></div>
					<ul class="channels-list">
					</ul>
				</div>
				<!-- 黑胶旋转 -->
				<div class="rotate">
					<div class="needle needle-pause"></div>
					<div class="disco">
						<div class="cover"></div>
						<div class="current-cover"></div>
					</div>
				</div>
				<!-- 歌词栏 -->
				<div class="lyric">
					<div class="lyric-ct">
						<div class="lyric-box"></div>
					</div>
				</div>
				<!-- 歌词显示按钮 -->
				<div class="lyric-btn icon-lyric" title="歌词显示"></div>
				<!-- 音量控制 -->
				<div class="volume clearfix" title="音量">
					<div class="volume-button icon-volume-on"></div>
					<div class="volume-bar">
						<div class="volume-pathway">
							<div class="volume-line"></div>

							<div class="volume-handle"></div>
						</div>
					</div>
				</div>
				<!-- 进度条 -->
				<div class="progress clearfix">
					<div class="time current-time">0:00</div>
					<div class="time full-time">0:00</div>
					<div class="progress-bar">
						<div class="progress-pathway">
							<div class="progress-line"></div>

							<div class="progress-handle"></div>
						</div>
					</div>
				</div>
				<!--  播放控制  -->
				<div class="control">
					<div class="prev icon-prev"></div>
					<div class="next icon-next"></div>
					<div class="on-off play icon-start1"></div>
				</div>
			</div>
		</div>

		<script src="js/jquery.min.js"></script>
		<script src="js/draggabilly.js"></script>
		<script src="js/progress.js "></script>
		<script src="js/volume-ctrl.js "></script>
		<script src="js/panelctrl.js "></script>
		<script src="js/getmusic.js"></script>

		<script>
			//    getmusic
			new GetMusic($('.panel'));
			//    音量控制
			new Volume($('.volume'));

			//    进度控制
			new Progress($('.progress'));
			//    小窗口
			new PanelCtrl($('#fm'));
			
			function hide(o){
				$(o).css("display","none");
			}
		</script>

	</div>
</body>

</html>
