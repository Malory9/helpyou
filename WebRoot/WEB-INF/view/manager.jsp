<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.helpyouJFinal.model.Task"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%! SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日  hh时mm分"); %>
<%! 
	public String dateFormat(Date date){
		return simpleDateFormat.format(date);	
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-COMPATIBLE" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1 ,minimum-scale=1,user-scalable=0">
<meta name="renderer" content="webkit">
<meta name="keywords" content="校园互助平台后台">
<meta name="description" content="校园互助平台后台，对用户和任务进行管理">
<meta name="author" content="张世凯，方浩">
<title>校帮——后台管理</title>
<link rel="shortcut icon" href="${BASE_PATH}/images/favicon.ico">
<link href="${BASE_PATH}/css/bootstrap.css" rel="stylesheet"
	type="text/css">
<link href="${BASE_PATH}/css/manager.css" rel="stylesheet"
	type="text/css">
<!--<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>-->
<script src="${BASE_PATH}/js/jquery.min.js"></script>
<script src="${BASE_PATH}/js/bootstrap.min.js"></script>
</head>
<body>
	<% List<Task> reportedTasks = (List<Task>)request.getAttribute("reportedTasks"); %>
	<div class="navbar navbar-default navbar-fixed-top xb-manager-nav">
		<div class="navbar-header">
			<a href="/maneger" class="navbar-brand xb-manager-nav-title">校帮后台管理系统</a>
		</div>
		<button type="button"
			class="btn btn-info navbar-btn navbar-right send-notice-btn"
			data-toggle="modal" data-target="#send-notice-modal">发布公告</button>
	</div>

	<div class="main container">
		<div class="modal fade" id="send-notice-modal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header xb-notice-model-header">
						<button type="button" class="close" data-dismiss="modal" id="send-notice-modal-close">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">发送公告</h4>
					</div>
					<form action="${BASE_PATH}/manager/sendNotice" method="post" id="send-notice-form">
						<div class="modal-body">
							<div class="form-group">
								<label for="notice-title">公告标题</label> <input type="text"
									name="title" id="notice-title" class="form-control"
									placeholder="标题不能超过20字">
							</div>
							<div class="form-group">
								<label for="notice-content">公告内容</label>
								<textarea type="text" name="title" id="notice-content"
									class="form-control" placeholder="请输入公告的内容"></textarea>
							</div>
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary" id="send-notice-btn">确认发布</button>
						</div>
					</form>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->

		<div class="table-responsive container">
			<h4 class="h4">违规任务处理</h4>
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>任务编号</th>
						<th>任务标题</th>
						<th>任务时间</th>
						<th>处理措施</th>
					</tr>
				</thead>
				<tbody>
					<% 
						for(int i=0,len=reportedTasks.size();i < len;i++) {
							Task reportedTask = reportedTasks.get(i);
					%>
					<tr>
						<td class="taskId"><%=reportedTask.getInt("taskId") %></td>
						<td><a href="${BASE_PATH}/task/<%=reportedTask.getInt("taskId") %>"><%=reportedTask.getStr("title") %></a></td>
						<td><%=dateFormat(reportedTask.getDate("startTime")) %> 到 <%=dateFormat(reportedTask.getDate("endTime")) %></td>
						<td>
							<button type="button" class="btn btn-success btn-sm recover-task-btn" data-url="${BASE_PATH}/manager/recovery">恢复任务</button>
							<button type="button" class="btn btn-danger btn-sm delete-task-btn" data-url="${BASE_PATH}/manager/delete">删除任务</button>
						</td>
					</tr>
					<% } %>
				</tbody>
			</table>
		</div>
	</div>
	<script src="${BASE_PATH}/js/manager.js"></script>
</body>
</html>