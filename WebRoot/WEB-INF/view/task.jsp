<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.helpyouJFinal.model.Task"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%! SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss"); %>
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
	<script src="${BASE_PATH}/js/jquery.js"></script>
</head>
<body>
	<%@ include file="header.jsp"%>
	
	<% Task task = (Task)request.getAttribute("task"); %>
	<% if(task != null){ %>
	<div class="xb-task">
        <div class="task-simple-info">
            <div class="task-id"><%=task.getInt("taskId") %></div>
            <div class="task-title"><%=task.getStr("title") %></div>
            <div class="task-type">任务类型：
            	<span>
            		<%  Integer type = task.getInt("type");
            			if(type == 1){ %>
            				<%="线上任务" %>
            		<%  } else if(type == 2){ %>
            				<%="线下任务" %>
            		<%	} else if(type == 3){ %>
            				<%="其他任务" %>
            		<%	} %>
            	</span>
            </div>
            <div class="task-time">任务时间：
            	<span class="start-time"><%=dateFormat(task.getDate("startTime")) %></span>
            	到
            	<span class="end-time"><%=dateFormat(task.getDate("endTime")) %></span>
            </div>
            <div class="task-people-num">任务人数：<span><%=task.getInt("peopleNum") %></span>人</div>
            <div class="task-reward">任务报酬：<span><%=task.getInt("reward") %></span>PY币</div>
            <% if(user != null) {%>
            <% if(request.getAttribute("publishId") != user.getInt("userId")){ %>
            	<button type="button" class="xb-task-take-btn">立即接取</button>
            <% } else { %>
            	<button type="button" class="xb-task-take-btn">修改信息</button>
            <% } 
            } %>
        </div>
        <div class="task-content">
            <span>任务介绍</span>
			<p><%=task.getStr("content") %></p>
        </div>
        <%
        	} else {
        		out.println("没有对应的任务");
          	}
        %>
        
    </div>
	
	<%@ include file="footer.jsp" %>
	
	<script src="${BASE_PATH}/js/commons.js"></script>
    <script src="${BASE_PATH}/js/task.js"></script>
</body>
</html>