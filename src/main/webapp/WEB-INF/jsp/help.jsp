<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
	type="text/javascript" charset="utf-8"></script>
<title>开开邮件</title>
</head>
<body>
电子邮件协议 编辑
常用的电子邮件协议有SMTP、POP3、IMAP4，它们都隶属于TCP/IP协议簇，默认状态下，分别通过TCP端口25、110和143建立连接。下面分别对其进行简单介绍。[1] 
中文名 电子邮件协议 外文名 Simple Mail Transfer Protocol 常    用 SMTP、POP3 建立连接 TCP端口25、110和143
目录
1 电子邮件协议
▪ SMTP协议
▪ POP协议
▪ IMAP协议
2 电子邮件传输协议的运用
电子邮件协议编辑
SMTP协议
SMTP的全称是“Simple Mail Transfer Protocol”，即简单邮件传输协议。它是一组用于从源地址到目的地址传输邮件的规范，通过它来控制邮件的中转方式。SMTP 协议属于TCP/IP协议簇，它帮助每台计算机在发送或中转信件时找到下一个目的地。SMTP 服务器就是遵循SMTP协议的发送邮件服务器。SMTP认证，简单地说就是要求必须在提供了账户名和密码之后才可以登录 SMTP 服务器，这就使得那些垃圾邮件的散播者无可乘之机。增加 SMTP 认证的目的是为了使用户避免受到垃圾邮件的侵扰。
SMTP[2]  已是事实上的E-Mail传输的标准。
POP协议
POP邮局协议负责从邮件服务器中检索电子邮件。它要求邮件服务器完成下面几种任务之一：从邮件服务器中检索邮件并从服务器中删除这个邮件；从邮件服务器中检索邮件但不删除它；不检索邮件，只是询问是否有新邮件到达。POP协议支持多用户互联网邮件扩展，后者允许用户在电子邮件上附带二进制文件，如文字处理文件和电子表格文件等，实际上这样就可以传输任何格式的文件了，包括图片和声音文件等。在用户阅读邮件时，POP命令所有的邮件信息立即下载到用户的计算机上，不在服务器上保留。
POP3(Post Office Protocol 3)即邮局协议的第3个版本,是因特网电子邮件的第一个离线协议标准。
IMAP协议
互联网信息访问协议（IMAP）是一种优于POP的新协议。和POP一样，IMAP也能下载邮件、从服务器中删除邮件或询问是否有新邮件，但IMAP克服了POP的一些缺点。例如，它可以决定客户机请求邮件服务器提交所收到邮件的方式，请求邮件服务器只下载所选中的邮件而不是全部邮件。客户机可先阅读邮件信息的标题和发送者的名字再决定是否下载这个邮件。通过用户的客户机电子邮件程序，IMAP可让用户在服务器上创建并管理邮件文件夹或邮箱、删除邮件、查询某封信的一部分或全部内容，完成所有这些工作时都不需要把邮件从服务器下载到用户的个人计算机上。
支持种IMAP的常用邮件客户端有：ThunderMail,Foxmail,Microsoft Outlook等。
电子邮件传输协议的运用编辑
Internet上传送电子邮件是通过一套称为邮件服务器的程序进行硬件管理并储存的。与个人计算机不同，这些邮件服务器及其程序必须每天24小时不停地运行，否则就不能收发邮件了，简单邮件传输协议SMTP（Simple Mail Transfer Protocol）和邮局协议POP（Post Office Protocol）是负责用客户机/服务器模式发送和检索电子邮件的协议。
用户计算机上运行的电子邮件客户机程序请求邮件服务器进行邮件传输，邮件服务器采用简单邮件传输协议标准。很多邮件传输工具，如outlook express、fox mail等，都遵守SMTP标准并用这个协议向邮件服务器发送邮件。SMTP协议规定了邮件信息的具体格式和邮件的管理方式。
</body>
</html>