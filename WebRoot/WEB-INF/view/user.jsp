<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-COMPATIBLE" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1 ,minimum-scale=1,user-scalable=0">
<meta name="renderer" content="webkit">
<meta name="keywords" content="互帮互助,学校,互助平台">
<meta name="author" content="张世凯，方浩">
<title>用户界面——校帮</title>
<link rel="shortcut icon" href="${BASE_PATH}/images/favicon.ico">
<link href="${BASE_PATH}/css/commons.css" rel="stylesheet"
	type="text/css">
<link href="${BASE_PATH}/css/task.css" rel="stylesheet" type="text/css">
<!--<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>-->
<script src="${BASE_PATH}/js/jquery.min.js"></script>
</head>
<body>
	<%@include file="header.jsp"%>

	<div class="xb-user">
		<form action="/editUserInfo" method="post">
			<div class="xb-user-info">
				<div class="xb-user-info-item">
					<label for="xb-user-nickname" class="xb-user-info-label">昵称</label>
					<span class="xb-user-info-content">用户昵称</span> <span
						class="change-info">修改</span> <input type="text"
						class="xb-user-nickname" name="nickname" id="xb-user-nickname">
				</div>
				<div class="xb-user-info-item">
					<label for="xb-user-sex" class="xb-user-info-label">性别</label> <span
						class="xb-user-info-content">男</span> <span class="change-info">修改</span>
					<input type="text" class="xb-user-sex" name="sex" id="xb-user-sex">
				</div>
				<div class="xb-user-info-item">
					<label for="xb-user-age" class="xb-user-info-label">年龄</label> <span
						class="xb-user-info-content"><span class="xb-user-age">20</span>岁</span>
					<span class="change-info">修改</span> <input type="text"
						class="xb-user-age" name="age" id="xb-user-age">
				</div>
				<div class="xb-user-info-item">
					<label class="xb-user-info-label">用户积分</label> <span
						class="xb-user-info-content"><span class="xb-user-point">100</span>PY币</span>
				</div>
			</div>
			<button type="submit" class="xb-user-info-save"
				id="xb-user-info-save">保存修改</button>
		</form>

		<div class="xb-user-task-watch">
			<div class="xb-user-task-nav">
				<a href="#publish" class="xb-user-task-item active">已发布的任务</a> <a
					href="#token" class="xb-user-task-item">已接受的任务</a>
			</div>
			<ul class="xb-user-task-list task-publish selected">
				<li><span class="task-time">2016-05-04 09:12:00</span> <a
					href="/task/taskId"><span class="task-name">任务4</span></a> <span
					class="task-state">正在进行</span></li>
				<li><span class="task-time">2015-06-12 12:56:00</span> <a
					href="/task/taskId"><span class="task-name">任务5</span></a> <span
					class="task-state">已结束</span></li>
				<li><span class="task-time">2015-10-25 16:37:00</span> <a
					href="/task/taskId"><span class="task-name">任务6</span></a> <span
					class="task-state">已结束</span></li>
			</ul>
			<ul class="xb-user-task-list task-token">
				<li><span class="task-time">2016-05-04 09:12:00</span> <a
					href="/task/taskId"><span class="task-name">任务1</span></a> <span
					class="task-state">正在进行</span></li>
				<li><span class="task-time">2015-06-12 12:56:00</span> <a
					href="/task/taskId"><span class="task-name">任务2</span></a> <span
					class="task-state">已结束</span></li>
				<li><span class="task-time">2015-10-25 16:37:00</span> <a
					href="/task/taskId"><span class="task-name">任务3</span></a> <span
					class="task-state">已结束</span></li>
			</ul>
		</div>
	</div>

	<%@include file="footer.jsp"%>
	
	<script src="${BASE_PATH}/js/commons.js"></script>
    <script src="${BASE_PATH}/js/user.js"></script>
</body>
</html>