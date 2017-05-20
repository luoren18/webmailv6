/**
 * 加载所有收到的邮件
 */
function loadData() {
	$.get(
		"getMailBox?stateid=1",
		function(data) {
			var $tbody = $("#content_emaildata");
			var index=0;
			$.each(data, function(i, mail) {
					var $tr = $("<tr></tr>");
					$tr.append("<td class='text-center'>" + (++index) + "</td>")
						.append("<td>" + mail.subject + "</td>")
						.append("<td>" + mail.senddate + "</td>")
						.append("<td>" + mail.addresser + "</td>");
					if(mail.isnew == true) {
						$tr.append("<td>" + '未读' + "</td>");
					} else {
						$tr.append("<td>" + '已读' + "</td>");
					}
					var $td = $("<td></td>");
					$td.append('<a href="getmailById?id=' + mail.id + '" class="btn btn-primary active" role="button">查看</a>');
					$td.append('<a href="delmail?id=' + mail.id + '" class="btn btn-danger active" role="button">删除</a>');
					$tr.append($td);
					$tbody.append($tr);
			});
		}, "json"
	);
}

/**
 * 加载未读邮件
 */
function unread_loadData() {
	$.get(
		"getUnreadMail",
		function(data) {
			var $tbody = $("#content_emaildata");
			$.each(data, function(i, mail) {
					var $tr = $("<tr></tr>");
					$tr.append("<td class='text-center col-sm-1'>" + (++i) + "</td>")
						.append("<td class='col-sm-2'>" + mail.subject + "</td>")
						.append("<td class='col-sm-2'>" + mail.senddate + "</td>")
						.append("<td class='col-sm-2'>" + mail.addresser + "</td>");
					var $td = $("<td></td>");
					$td.append('<a href="getmailById?id=' + mail.id + '" class="btn btn-primary active" role="button">查看</a>');
					$td.append('<a href="toread?id=' + mail.id + '" class="btn btn-primary active" role="button">查收</a>');
					$td.append('<a href="delmail?id=' + mail.id + '" class="btn btn-danger active" role="button">删除</a>');
					$tr.append($td);
					$tbody.append($tr);
			});
		}, "json"
	);
}

/**
 * 
 * 加载发信箱邮件
 * 
 */
function sendmailbox_loadData(){
	$.get(
			"getMailBox?stateid=2",
			function(data) {
				var $tbody = $("#content_emaildata");
				$.each(data, function(i, mail) {
						var $tr = $("<tr></tr>");
						$tr.append("<td class='col-sm-1 text-center'>" + (++i) + "</td>")
							.append("<td class='col-sm-2'>" + mail.subject + "</td>")
							.append("<td class='col-sm-1'>" + mail.senddate + "</td>")
							.append("<td>" + mail.receiver + "</td>");
						var $td = $("<td></td>");
						$td.append('<a href="getmailById?id=' + mail.id + '" class="btn btn-primary active" role="button">查看</a>');
						$tr.append($td);
						$tbody.append($tr);
				});
			}, "json"
		);
	}


/**
 * 加载删除箱的邮件
 * @returns
 */
function delmailbox_loadData(){
	$.get(
			"getMailBox?stateid=4",
			function(data) {
				var $tbody = $("#content_emaildata");
				$.each(data, function(i, mail) {
						var $tr = $("<tr></tr>");
						$tr.append("<td class='text-center'>" + (++i) + "</td>")
							.append("<td class='col-sm-2'>" + mail.subject + "</td>")
							.append("<td class='col-sm-1'>" + mail.senddate + "</td>")
							.append("<td>" + mail.receiver + "</td>");
						var $td = $("<td></td>");
						$td.append('<a href="getmailById?id=' + mail.id + '" class="btn btn-success active" role="button">看一眼</a>');
						$td.append('<a href="renewmailById?id=' + mail.id + '" class="btn btn-info active" role="button">恢复</a>');
						$tr.append($td);
						$tbody.append($tr);
				});
			}, "json"
		);
}


/**
 * 加载草稿箱的邮件
 * @returns
 */
function draftMail_loadData(){
	$.get(
			"getMailBox?stateid=3",
			function(data) {
				var $tbody = $("#content_emaildata");
				$.each(data, function(i, mail) {
						var $tr = $("<tr></tr>");
						$tr.append("<td class='text-center'>" + (++i) + "</td>");
							if(mail.subject==null||mail.subject==""){
								$tr.append("<td class='col-sm-2'>" + '<span style="color:red;">没有填写内容</span>' + "</td>");
							}else{
								$tr.append("<td class='col-sm-2'>" + mail.subject + "</td>");
							}
							$tr.append("<td class='col-sm-1'>" + mail.senddate + "</td>")
								.append("<td>" + mail.receiver + "</td>");
						var $td = $("<td></td>");
						$td.append('<a href="getmailById?id=' + mail.id + '" class="btn btn-primary active" role="button">重新处理</a>');
						$td.append('<a href="delmail?id=' + mail.id + '" class="btn btn-danger active" role="button">删除</a>');
						$tr.append($td);
						$tbody.append($tr);
				});
			}, "json"
		);
}
