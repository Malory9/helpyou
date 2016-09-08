<%@page import="com.helpyouJFinal.model.Task"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.helpyouJFinal.model.TaskAccept"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%! SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  hh:mm"); %>
<%! 
	public String dateFormat(Date date){
		return simpleDateFormat.format(date);	
	}
%>
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
<link href="${BASE_PATH}/css/user.css" rel="stylesheet" type="text/css">
<!--<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>-->
<script src="${BASE_PATH}/js/jquery.min.js"></script>
</head>
<body>
	<%@include file="header.jsp"%>
	<% 
		User readUser = (User)request.getAttribute("readingUser"); 
		Boolean editPower = (Boolean)request.getAttribute("editPower");
	%>
	
	<% if(!editPower) { %>
        <button class="xb-send-message" id="xb-send-message">给他写留言</button>
		<div class="send-message-modal">
			<div class="modal-title">
				<span class="modal-title-text">发送留言</span> <span
					id="send-message-modal-close" class="modal-close iconfont">&#xe602;</span>
			</div>
			<div class="modal-content">
				<form action="${BASE_PATH}/message/send" method="post" class="send-message-form">
					<label for="receiver">发送对象 <small>对方的昵称</small></label> <input
						type="text" id="receiver" name="receiver"> <label
						for="messageContent">留言内容</label>
					<textarea name="messageContent" id="messageContent"></textarea>
					<button type="submit">发送</button>
				</form>
			</div>
		</div>
	<% } %>
	<div class="xb-user">
		<form action="${BASE_PATH}/user/updateInfo" method="post" id="xb-user-form">
			<div class="xb-user-info">
				<div class="xb-user-info-item">
					<label for="xb-user-nickname" class="xb-user-info-label"><i class="iconfont icon-nicheng"></i>昵称</label>
					<span class="xb-user-info-content xb-user-info-nickname"><%=readUser.getStr("nickname") %></span>
					<% if(editPower){ %>
						<span class="change-info"><i class="iconfont icon-xiugai"></i>修改</span>
						<input type="text" name="nickname" id="xb-user-edit-nickname">
					<% } %>
				</div>
				<div class="xb-user-info-item">
					<label for="xb-user-sex" class="xb-user-info-label"><i class="iconfont icon-gender"></i>性别</label>
					<span class="xb-user-info-content xb-user-info-sex"><%=readUser.getStr("sex") %></span>
					<% if(editPower){ %>
						<span class="change-info"><i class="iconfont icon-xiugai"></i>修改</span>
						<select name="sex" id="xb-user-edit-sex">
							<option value="未选择">未选择</option>
	                    	<option value="男">男</option>
	                    	<option value="女">女</option>
                    	</select>
					<% } %>
				</div>
				<div class="xb-user-info-item">
					<label for="xb-user-age" class="xb-user-info-label"><i class="iconfont icon-nianling"></i>年龄</label>
					<span class="xb-user-info-content xb-user-info-age"><span class="xb-user-age"><%=readUser.getInt("age")!=null?readUser.getInt("age"):0 %></span>岁</span>
					<% if(editPower){ %>
						<span class="change-info"><i class="iconfont icon-xiugai"></i>修改</span> 
						<input type="number" min="1" max="120" name="age" id="xb-user-edit-age" placeholder="1-120">
					<% } %>
				</div>
				<div class="xb-user-info-item">
					<label class="xb-user-info-label"><i class="iconfont icon-jifen"></i>用户积分</label> <span
						class="xb-user-info-content"><span class="xb-user-point"><%=readUser.getInt("point") %></span>PY币</span>
				</div>
			</div>
			<% if(editPower){ %>
				<button type="submit" class="xb-user-info-save" id="xb-user-info-save">保存修改</button>
			<% } %>
		</form>
		
		
		<% 
			List<Task> taskPublishs = (List<Task>)request.getAttribute("publishTasks");
			List<TaskAccept> taskAccepts = (List<TaskAccept>)request.getAttribute("acceptTasks");
		%>
		<div class="xb-user-task-watch">
			<div class="xb-user-task-nav">
				<a href="#publish" class="xb-user-task-item active">已发布的任务</a> <a
					href="#token" class="xb-user-task-item">已接受的任务</a>
			</div>
			<ul class="xb-user-task-list task-publish selected">
			<% 
				for(int i=0,len=taskPublishs.size();i < len;i++){
					Task taskPublish = taskPublishs.get(i);
			%>
					<li>
						<span class="task-time"><%=dateFormat(taskPublish.getDate("startTime")) %></span> 
						<a href="${BASE_PATH}/task/<%=taskPublish.getInt("taskId") %>">
							<span class="task-name"><%=taskPublish.getStr("title") %></span>
						</a> 
						<span class="task-state">
							<% 
								Integer state = taskPublish.getInt("state");
								if(state == 1 || state == 2){
									out.print("进行中");
								} else if(state == 3){
									out.print("被举报");
								} else if(state == 4){
									out.print("已结束");
								}
							%>
						</span>
					</li>
			<%} %>
			</ul>
			
			<ul class="xb-user-task-list task-token">
				<% 
					for(int i=0,len=taskAccepts.size();i < len;i++){
						TaskAccept taskAccept = taskAccepts.get(i);
				%>
					<li>
						<span class="task-time"><%=dateFormat(taskAccept.getDate("acceptTime")) %></span> 
						<a href="${BASE_PATH}/task/<%=taskAccept.getInt("taskId") %>">
							<span class="task-name"><%=taskAccept.getStr("taskTitle") %></span>
						</a> 
						<span class="task-state">
							<% 
								Integer state = taskAccept.getInt("state");
								if(state == 1){
									out.print("已接受");
								} else if(state == 2){
									out.print("已完成，等待确认");
								} else if(state == 3){
									out.print("已完成");
								} else if(state == 4){
									out.print("已放弃");
								}
							%>
						</span>
					</li>
				<% } %>
			</ul>
		</div>
	</div>

	<%@include file="footer.jsp"%>
	
	<script src="${BASE_PATH}/js/commons.js"></script>
    <script src="${BASE_PATH}/js/user.js"></script>
</body>
</html>