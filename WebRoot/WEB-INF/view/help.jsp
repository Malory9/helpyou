<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-COMPATIBLE" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1 ,minimum-scale=1,user-scalable=0">
<meta name="renderer" content="webkit">
<meta name="keywords" content="互帮互助,学校,互助平台">
<meta name="author" content="张世凯，方浩">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>帮助中心——校帮</title>
<link rel="shortcut icon" href="${BASE_PATH}/images/favicon.ico">
<link href="${BASE_PATH}/css/commons.css" rel="stylesheet" type="text/css">
<link href="${BASE_PATH}/css/help.css" rel="stylesheet" type="text/css">
<!--<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>-->
<script src="${BASE_PATH}/js/jquery.min.js"></script>
</head>
<body>
	<%@include file="header.jsp"%>

	<div class="xb-help">
		<h1 class="xb-help-title">校帮FAQ</h1>
		<img class="xb-help-banner" src="${BASE_PATH}/images/help.jpg" >
		<div class="faq">
			<h2 class="xb-help-question">Q1.使用校帮之前应该了解？</h2>
			<p>A1:</p>
			<ol class="xb-help-answer-list">
				<li>你不可能永远让别人为你服务，想要获得更多的帮助，要么付出劳动，要么付出金钱。</li>
				<li>发布任务时请不要忘了这一点：你的任务可能不会被完成（即使已经有人接受了任务），所以发布重要的任务时记得作最坏的打算。</li>
				<li>对于没有把握的任务不要轻易尝试接受，否则你将为自己的失信付出代价。</li>
			</ol>
			<h2 class="xb-help-question">Q2.如何玩转校园互助平台？</h2>
			<p>A2:</p>
			<ol class="xb-help-answer-list">
				<li>注册与登录。按照流程进行注册登录后才能进行发布任务、进入个人主页等操作，否则只能以游客的身份浏览任务。</li>
				<li>个人中心。点击页面上方导航栏最右侧用户名按钮进入个人主页，在个人主页中可以修改个人资料和查看与自己相关的任务的状态。</li>
				<li>发布任务。点击页面上发导航栏中的发布任务按钮可快速发布任务，发布任务时需要设定任务的类型，同时记得写清楚详细要求和悬赏的金额哦。</li>
				<li>接受任务。任务广场中会随时发布最新的任务，点击感兴趣的任务进入详细内容，确认自己可以完成之后即可接受任务，当然也可以在导航栏中的搜索框输入关键字搜索感兴趣的任务。</li>
				<li>消息与留言。点击导航条中的消息按钮可查看系统消息或者别人的对你的留言，也可以输入昵称选择回复消息。</li>
			</ol>
			<h2 class="xb-help-question">Q3.对于哪些用户或者任务我应该毫不留情地举报？</h2>
			<p>A3:</p>
			<ol class="xb-help-answer-list">
				<li>公然发表明确或暗示性的包含暴力、色情等违规内容的言论的；</li>
				<li>公然对他人进行人身攻击的；</li>
				<li>在任务或言论中掺杂广告、严重影响到其它用户体验的。</li>
			</ol>
		</div>
	</div>

	<%@include file="footer.jsp"%>
	
	<script src="${BASE_PATH}/js/commons.js"></script>
</body>
</html>