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
	<title>任务详细——校帮</title>
	<link rel="shortcut icon" href="${BASE_PATH}/images/favicon.ico">
	<link href="${BASE_PATH}/css/commons.css" rel="stylesheet" type="text/css">
	<link href="${BASE_PATH}/css/task.css" rel="stylesheet" type="text/css">
	<!--<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>-->
	<script src="${BASE_PATH}/js/jquery.min.js"></script>
</head>
<body>
	<%@ include file="header.jsp" %>
	
	<div class="xb-task">
        <div class="task-simple-info">
            <div class="task-id" hidden>任务ID</div>
            <div class="task-title">任务标题</div>
            <div class="task-type">任务类型：<span>线上任务</span></div>
            <div class="task-time">任务时间：<span class="start-time">2016年9月1日22:12:09</span> 到 <span class="end-time">2016年9月1日23:12:09</span></div>
            <div class="task-people-num">任务人数：<span>3</span>人</div>
            <div class="task-reward">任务报酬：<span>4</span>PY币</div>
            <button type="button" class="xb-task-take-btn">立即接取</button>
        </div>
        <div class="task-content">
            <span>任务介绍</span>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Blanditiis, praesentium, dolor reprehenderit molestiae eos sapiente nostrum repellat molestias atque ducimus culpa placeat autem, soluta nihil. Delectus nostrum iste at, repellat?</p>
        </div>
    </div>
	
	
	<%@ include file="footer.jsp" %>
	
	<script src="${BASE_PATH}/js/commons.js"></script>
    <script src="${BASE_PATH}/js/task.js"></script>
</body>
</html>