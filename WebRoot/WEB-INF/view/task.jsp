<%@page import="com.jfinal.kit.StrKit"%>
<%@page import="com.helpyouJFinal.model.TaskAccept"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.helpyouJFinal.model.Task"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
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
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-COMPATIBLE" content="IE=edge,chrome=1">
	<meta name="viewport"
		content="width=device-width, initial-scale=1, maximum-scale=1 ,minimum-scale=1,user-scalable=0">
	<meta name="renderer" content="webkit">
	<meta name="keywords" content="互帮互助,学校,互助平台">
	<meta name="author" content="张世凯，方浩">
	<title>任务详细——校帮</title>
	<link rel="shortcut icon" href="${BASE_PATH}/images/favicon.ico">
	<link href="${BASE_PATH}/css/commons.css" rel="stylesheet" type="text/css">
	<link href="${BASE_PATH}/css/task.css" rel="stylesheet" type="text/css">
	<!--<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>-->
	<script src="${BASE_PATH}/js/jquery.min.js"></script>
</head>
<body>
	<%@ include file="header.jsp"%>
	
	<% 
		Task task = (Task)request.getAttribute("task");
		User publisher = (User)request.getAttribute("publisher");
		List<TaskAccept> taskAccepts = (List<TaskAccept>)request.getAttribute("taskAccepts");
		List<User> accepters = (List<User>)request.getAttribute("accepters");
    	boolean canFinish = false;
    	boolean hasTake = false;
	%>
	
	<div class="xb-task">
        <div class="task-simple-info">
            <div class="task-title"><%=task.getStr("title") %></div>
            <div class="task-type">任务类型：
            	<span>
            		<%  Integer type = task.getInt("type");
            			if(type == 1){ 
            				out.print("线上任务");
            			} else if(type == 2){
            				out.print("线下任务");
            			} else if(type == 3){
            				out.print("其他任务");
            			} %>
            	</span>
            </div>
            <div class="task-time">任务时间：
            	<span class="start-time"><%=dateFormat(task.getDate("startTime")) %></span>
            	到
            	<span class="end-time"><%=dateFormat(task.getDate("endTime")) %></span>
            </div>
            <div class="task-people-num">
            	任务人数：<span><%=task.getInt("peopleNum") %></span>人&nbsp;&nbsp;
            	已接人数：<span><%=accepters.size() %></span>人
            </div>
            <div class="task-reward">任务报酬：<span><%=task.getInt("reward") %></span>PY币</div>
            <div class="task-publisher">发布者：<a href="${BASE_PATH}/user/<%=publisher.getInt("userId") %>"><%=publisher.getStr("nickname") %></a></div>
            <div class="task-state">任务状态：<span>
            		<%  Integer state = task.getInt("state");
            			if(state == 1){ 
            				out.print("可接受");
            			} else if(state == 2){ 
            				out.print("不可接受");
            			} else if(state == 3){
            				out.print("被举报");
            			} else if(state == 4){
            				out.print("已结束");
            			}%>
            </span></div>
        </div>
       	<% if(state == 1 || state == 2) { %>
        	<% if(user == null) {%>
        		<% if(state == 1) { %>
      					<button type="submit" class="xb-task-btn xb-task-take-btn">立即接取</button>
      				<% } %>
	            <button type="submit" class="xb-task-btn xb-task-report-btn">举报任务</button>
	    	<%} else { %>
		        <% if(publisher.getInt("userId") != user.getInt("userId")){
					Integer userId = user.getInt("userId");
					for(int i=0,len=taskAccepts.size();i < len;i++){
						if(userId == taskAccepts.get(i).getInt("userId")){
							hasTake = true;
							if(taskAccepts.get(i).getInt("state")==1){
								canFinish = true;								
							}
						}
					}
					if(hasTake && canFinish) {%>
						<form action="${BASE_PATH}/task/submitFinish" method="post" class="xb-task-form">
				        	<input type="hidden" name="publishId" value="<%=publisher.getInt("userId") %>">
				       		<input type="hidden" name="acceptId" value="<%=user.getInt("userId") %>">
				       		<input type="hidden" name="taskId" value="<%=task.getInt("taskId") %>">
		            		<button type="submit" class="xb-task-btn xb-task-finish-btn" id="xb-task-finish-btn">完成任务</button>
		            	</form>
		            	<form action="${BASE_PATH}/task/giveUp" method="post" class="xb-task-form">
				        	<input type="hidden" name="publishId" value="<%=publisher.getInt("userId") %>">
				       		<input type="hidden" name="acceptId" value="<%=user.getInt("userId") %>">
				       		<input type="hidden" name="taskId" value="<%=task.getInt("taskId") %>">
		            		<button type="submit" class="xb-task-btn xb-task-giveup-btn" id="xb-task-giveup-btn">放弃任务</button>
		            	</form>
		            <%} else if(!hasTake && state == 1) { %>
		            	<form action="${BASE_PATH}/task/accept" method="post" class="xb-task-form">
				        	<input type="hidden" name="publishId" value="<%=publisher.getInt("userId") %>">
				       		<input type="hidden" name="acceptId" value="<%=user.getInt("userId") %>">
				       		<input type="hidden" name="taskId" value="<%=task.getInt("taskId") %>">
		            		<button type="submit" class="xb-task-btn xb-task-take-btn" id="xb-task-take-btn">立即接取</button>
		            	</form>
		            <% } %>
		            	<form action="${BASE_PATH}/task/report" method="post" class="xb-task-form">
				       		<input type="hidden" name="taskId" value="<%=task.getInt("taskId") %>">
				            <button type="submit" class="xb-task-btn xb-task-report-btn" id="xb-task-report-btn">举报任务</button>
		            	</form>
	        	<% } else { %>
		        		<form action="${BASE_PATH}/task/end" method="post" class="xb-task-form">
				        	<input type="hidden" name="publishId" value="<%=publisher.getInt("userId") %>">
				       		<input type="hidden" name="acceptId" value="<%=user.getInt("userId") %>">
				       		<input type="hidden" name="taskId" value="<%=task.getInt("taskId") %>">
				            <button type="submit" class="xb-task-btn xb-task-end-btn" id="xb-task-end-btn">结束任务</button>
		            	</form>
				        <button type="button" data-url="${BASE_PATH}/task/updateContent" class="xb-task-btn xb-task-edit-btn" id="xb-task-edit-btn">修改任务内容</button>
		        <% }
				}
			}%>
	        <div class="task-content">
	            <span class="xb-task-separator">任务介绍</span>
				<p><%=task.getStr("content") %></p>
				<% 
					if( state != 3 && state != 4 && user != null){
						if(publisher.getInt("userId") == user.getInt("userId")) { %>
							<form action="${BASE_PATH}/task/updateContent" method="post" class="xb-task-form" id="xb-task-editInfo-form">
								<input type="hidden" name="taskId" value="<%=task.getInt("taskId") %>">
								<textarea name="content"><%=task.getStr("content") %></textarea>
								<button type="submit" class="xb-task-btn xb-task-edit-upload-btn" id="xb-task-edit-upload-btn">提交修改</button>
							</form>
					<%  }
					}%>
	        </div>
        
        
        <h2 class="xb-task-separator">任务接受列表</h2>
        <div class="errorMsg"><%=StrKit.notBlank((String)request.getAttribute("endErrorMsg"))?request.getAttribute("endErrorMsg"):"" %></div>
        <div class="xb-task-accepter-list">
            <div class="xb-task-accepter-list-header">
                <div class="xb-task-accepter-nickname">用户昵称</div>
                <div class="xb-task-accepter-acceptTime">接受时间</div>
                <div class="xb-task-accepter-state">状态</div>
            </div>
            <div class="xb-task-accepter-list-body">
            	<% 
            		for(int i=0,len=accepters.size();i < len;i++) { 
            			TaskAccept taskAccept = taskAccepts.get(i);
            			User accepter = accepters.get(i);
            	%>
	                <div class="xb-task-accepter">
	                    <div class="xb-task-accepter-nickname">
	                    	<a href="${BASE_PATH}/user/<%=accepter.getInt("userId") %>">
	                    		<%=accepter.getStr("nickname") %>
	                    	</a>
	                    </div>
	                    <div class="xb-task-accepter-acceptTime"><%=dateFormat(taskAccept.getDate("acceptTime")) %></div>
	                    <div class="xb-task-accepter-state">
			                <%  Integer acceptState = taskAccept.getInt("state");
		            			if(acceptState == 1){
		            				out.print("已接受");
		            			} else if(acceptState == 2){
		            				out.print("已完成，待确认");
		            				if(user != null) {
			            		        if(publisher.getInt("userId") == user.getInt("userId") && state != 3 && state != 4){ %>
			            		        	<a href="${BASE_PATH}/task/confirmFinish" class="xb-task-confirmFinish">确认完成</a>
			            			<%	}
		            				}
		            			} else if(acceptState == 3){
		            				out.print("已完成");
		            			}
		            		%>
	                    </div>
	                </div>
                <% } %>
            </div>
        </div>
    </div>
	
	<%@ include file="footer.jsp" %>
	
	<script src="${BASE_PATH}/js/commons.js"></script>
    <script src="${BASE_PATH}/js/task.js"></script>
</body>
</html>