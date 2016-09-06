<%@page import="com.helpyouJFinal.model.Task"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-COMPATIBLE" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1 ,minimum-scale=1,user-scalable=0">
	<meta name="renderer" content="webkit">
	<meta name="keywords" content="互帮互助,学校,互助平台">
	<meta name="author" content="张世凯，方浩">
	<title>校帮——你身边的互助平台</title>
	<link rel="shortcut icon" href="${BASE_PATH}/images/favicon.ico">
	<link href="${BASE_PATH}/css/commons.css" rel="stylesheet" type="text/css">
	<link href="${BASE_PATH}/css/square.css" rel="stylesheet" type="text/css">
	<!--<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>-->
	<script src="${BASE_PATH}/js/jquery.min.js"></script>
</head>
<body>
	<%@ include file="header.jsp" %>
	
	<div class="xb-main">
        <div class="xb-task-type-box">
            <h3>任务类别</h3>
            <span id="ajaxURL" hidden="hidden" data-url="${BASE_PATH}/task/searchByType"></span>
            <div class="xb-task-type type-0 current">最新任务</div>
            <div class="xb-task-type type-1">线上任务</div>
            <div class="xb-task-type type-2">线下任务</div>
            <div class="xb-task-type type-3">其他任务</div>
        </div>
		<ul class="xb-task-list">
		<%	List<Task> tasks = (List<Task>)request.getAttribute("tasks"); %>
		<%	for(int i=0,len=tasks.size(); i < len ; i++) {
				Task task = tasks.get(i);
		%>
            <li class="task">
                <a href="${BASE_PATH}/task/<%=task.getInt("taskId") %>">
                    <h3 class="task-title"><%=task.getStr("title") %></h3>
                    <p class="task-content"><%=task.getStr("content") %></p>
                </a>
            </li>
        <%} %>
        </ul>
    </div>
    
	<%@ include file="footer.jsp" %>
	
    <script src="${BASE_PATH}/js/commons.js"></script>
    <script src="${BASE_PATH}/js/square.js"></script>
</body>
</html>