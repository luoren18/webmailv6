//加载收到的邮件
function load_inbox() {
	$('#table')
		.bootstrapTable({
			url: 'getMailBox?stateid=1',
			method: 'get',
			pagination: true, // 设置为 true 会在表格底部显示分页条
			pageNumber: 1, // 初始化加载第一页，默认第一页
			pageSize: 10, // 每页的记录行数（*）
			pageList: [5,10, 25, 50, 100],
			search: true, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			striped: true, // 隔行变色
			sortName: 'senddate', // 默认排序行
			sortOrder: 'desc', // 排序方式
			showHeader: true,
			showColumns: true, // 是否显示所有的列
			showRefresh: true,
			minimumCountColumns: 2, // 最少允许的列数
			clickToSelect: true,
			uniqueId: "id", // 每一行的唯一标识，一般为主键列
			showToggle: true, // 是否显示详细视图和列表视图的切换按钮
			columns: [{
					field: '#',
					title: '编号',
					formatter: function(value, row, index) {
						return index + 1
					}
				},
				{
					field: 'subject',
					title: '主题',
					sortable: true
				},
				{
					field: 'senddate',
					title: '发送日期',
					sortable: true
				},
				{
					field: 'addresser',
					title: '发件人',
					sortable: true
				},
				{
					field: '#',
					title: '是否已读',
					formatter: function(value, row, index) {
						if(row.isnew == true) {
							return '未读'
						} else {
							return '已读'
						}
					}
				},
				{
					field: '#',
					title: '操作',
					sortable: true,
					formatter: function(value, row, index) {
						return '<a href="getmailById?id=' +
							row.id +
							'" class="btn btn-primary active" role="button">查看</a>' +
							'<a href="delmail?id=' +
							row.id +
							'" class="btn btn-danger active" role="button">删除</a>'
					}
				},
			]
		});
}

// 加载未读邮件

function unread_inbox_load() {
	$('#table')
		.bootstrapTable({
			url: 'getUnreadMail',
			method: 'get',
			pagination: true, // 设置为 true 会在表格底部显示分页条
			pageNumber: 1, // 初始化加载第一页，默认第一页
			pageSize: 5, // 每页的记录行数（*）
			pageList: [5,10, 25, 50, 100],
			search: true, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			striped: true, // 隔行变色
			sortName: 'senddate', // 默认排序行
			sortOrder: 'desc', // 排序方式
			showHeader: true,
			showColumns: true, // 是否显示所有的列
			showRefresh: true,
			minimumCountColumns: 2, // 最少允许的列数
			clickToSelect: true,
			uniqueId: "id", // 每一行的唯一标识，一般为主键列
			showToggle: true, // 是否显示详细视图和列表视图的切换按钮
			columns: [{
					field: '#',
					title: '编号',
					formatter: function(value, row, index) {
						return index + 1
					}
				},
				{
					field: 'subject',
					title: '主题',
					sortable: true
				},
				{
					field: 'senddate',
					title: '发送日期',
					sortable: true
				},
				{
					field: 'addresser',
					title: '发件人',
					sortable: true
				},
				{
					field: '#',
					title: '操作',
					formatter: function(value, row, index) {
						return '<a href="getmailById?id=' +
							row.id +
							'" class="btn btn-primary active" role="button">查看</a>' +
							'<a href="toread?id=' +
							row.id +
							'" class="btn btn-primary active" role="button">查收</a>' +
							'<a href="delmail?id=' +
							row.id +
							'" class="btn btn-danger active" role="button">删除</a>'
					}
				},
			]
		});
}

/**
 * 
 * 加载发信箱邮件
 * 
 */
function sendmailbox_load() {
	$('#table')
		.bootstrapTable({
			url: 'getMailBox?stateid=2',
			method: 'get',
			pagination: true, // 设置为 true 会在表格底部显示分页条
			pageNumber: 1, // 初始化加载第一页，默认第一页
			pageSize: 5, // 每页的记录行数（*）
			pageList: [5,10, 25, 50, 100],
			search: true, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			striped: true, // 隔行变色
			sortName: 'senddate', // 默认排序行
			sortOrder: 'desc', // 排序方式
			showHeader: true,
			showColumns: true, // 是否显示所有的列
			showRefresh: true,
			minimumCountColumns: 2, // 最少允许的列数
			clickToSelect: true,
			uniqueId: "id", // 每一行的唯一标识，一般为主键列
			showToggle: true, // 是否显示详细视图和列表视图的切换按钮
			columns: [{
					field: '#',
					title: '编号',
					formatter: function(value, row, index) {
						return index + 1
					}
				},
				{
					field: 'subject',
					title: '主题',
					sortable: true
				},
				{
					field: 'senddate',
					title: '发送日期',
					sortable: true
				},
				{
					field: 'receiver',
					title: '收件人',
					sortable: true
				},
				{
					field: '#',
					title: '操作',
					formatter: function(value, row, index) {
						return '<a href="getmailById?id=' +
							row.id +
							'" class="btn btn-primary active" role="button">查看</a>'
					}
				},
			]
		});
}

/**
 * 加载删除箱的邮件
 * 
 * @returns
 */
function delmailbox_loadData() {
	$('#table')
		.bootstrapTable({
			url: 'getMailBox?stateid=4',
			method: 'get',
			pagination: true, // 设置为 true 会在表格底部显示分页条
			pageNumber: 1, // 初始化加载第一页，默认第一页
			pageSize: 5, // 每页的记录行数（*）
			pageList: [5,10, 25, 50, 100],
			search: true, // 是否显示表格搜索，
			striped: true, // 隔行变色
			sortName: 'senddate', // 默认排序行
			sortOrder: 'desc', // 排序方式
			showHeader: true,
			showColumns: true, // 是否显示所有的列
			showRefresh: true,
			minimumCountColumns: 2, // 最少允许的列数
			clickToSelect: true,
			uniqueId: "id", // 每一行的唯一标识，一般为主键列
			showToggle: true, // 是否显示详细视图和列表视图的切换按钮
			columns: [{
					field: '#',
					title: '编号',
					formatter: function(value, row, index) {
						return index + 1
					}
				},
				{
					field: 'subject',
					title: '主题',
					sortable: true
				},
				{
					field: 'senddate',
					title: '发送日期',
					sortable: true
				},
				{
					field: 'addresser',
					title: '发件人',
					sortable: true
				},
				{
					field: '#',
					title: '操作',
					formatter: function(value, row, index) {
						return '<a href="getmailById?id=' +
							row.id +
							'" class="btn btn-success active" role="button">看一眼</a>' +
							'<a href="renewmailById?id=' +
							row.id +
							'" class="btn btn-info active" role="button">恢复</a>'
					}
				},
			]
		});
}

/**
 * 加载草稿箱的邮件
 * @returns
 */
function draftMail_loadData() {
	$('#table')
		.bootstrapTable({
			url: 'getMailBox?stateid=3',
			method: 'get',
			pagination: true, //设置为 true 会在表格底部显示分页条
			pageNumber: 1, //初始化加载第一页，默认第一页
			pageSize: 5, //每页的记录行数（*）
			pageList: [5,10, 25, 50, 100],
			search: true, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			striped: true, //隔行变色
			sortName: 'senddate', //默认排序行
			sortOrder: 'desc', //排序方式
			showHeader: true,
			showColumns: true, //是否显示所有的列
			showRefresh: true,
			minimumCountColumns: 2, //最少允许的列数  
			clickToSelect: true,
			uniqueId: "id", //每一行的唯一标识，一般为主键列  
			showToggle: true, //是否显示详细视图和列表视图的切换按钮  
			columns: [{
					field: '#',
					title: '编号',
					formatter: function(value, row, index) {
						return index + 1
					}
				},
				{
					field: 'subject',
					title: '主题',
					formatter: function(value, row, index) {
						if(row.subject == null ||
							row.subject == "") {
							return '<span style="color:red;">无主题邮件</span>'
						} else {
							return row.subject
						}
					}
				},
				{
					field: 'senddate',
					title: '存稿时间',
					sortable: true
				},
				{
					field: 'receiver',
					title: '收件人',
				},
				{
					field: '#',
					title: '操作',
					formatter: function(value, row, index) {
						return '<a href="getmailById?id=' +
							row.id +
							'" class="btn btn-primary active" role="button">重新处理</a>' +
							'<a href="delmail?id=' +
							row.id +
							'" class="btn btn-danger active" role="button">删除</a>'
					}
				},
			]
		});
}


/**
 * 联系人页面
 * @returns
 */
function contacts_load() {
	$('#table')
		.bootstrapTable({
			url: 'getContacts',
			method: 'get',
			pagination: true, //设置为 true 会在表格底部显示分页条
			pageNumber: 1, //初始化加载第一页，默认第一页
			pageSize: 5, //每页的记录行数（*）
			pageList: [5,10, 25, 50, 100],
			search: true, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			striped: true, //隔行变色
			showHeader: true,
			showColumns: true, //是否显示所有的列
			showRefresh: true,
			minimumCountColumns: 2, //最少允许的列数  
			clickToSelect: true,
			uniqueId: "id", //每一行的唯一标识，一般为主键列  
			showToggle: true, //是否显示详细视图和列表视图的切换按钮  
			columns: [{
					field: '#',
					title: '编号',
					formatter: function(value, row, index) {
						return index + 1
					}
				},

				{
					field: '#',
					title: '联系人',
					sortable: true,
					formatter: function(value, row, index) {
						return row
					}
				},
				{
					field: '#',
					title: '操作',
					formatter: function(value, row, index) {
						return '<a href="sendmailByEmail?email=' +
							row +
							'" class="btn btn-primary active" role="button">向他发送邮件</a>'

					}
				},
			]
		});
}