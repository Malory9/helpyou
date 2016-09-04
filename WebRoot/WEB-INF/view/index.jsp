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
	<script src="${BASE_PATH}/js/jquery.js"></script>
</head>
<body>
	<%@ include file="header.jsp" %>
	
	<div class="xb-main">
        <div class="xb-task-type-box">
            <h3>任务类别</h3>
            <div class="xb-task-type type-new current">最新任务</div>
            <div class="xb-task-type type-online">线上任务</div>
            <div class="xb-task-type type-offline">线下任务</div>
            <div class="xb-task-type type-other">其他任务</div>
        </div>
		<ul class="xb-task-list">
            <li class="task">
                <a href="/task/taskID">
                    <h3 class="task-title">任务标题</h3>
                    <p class="task-content">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Alias deleniti explicabo molestiae odit perspiciatis placeat unde! Aut culpa deleniti dolore esse, expedita facilis fuga obcaecati porro quasi sequi ullam voluptates?</p>
                </a>
            </li>
        </ul>
    </div>
    
	<%@ include file="footer.jsp" %>
	
    <script src="${BASE_PATH}/js/commons.js"></script>
    <script src="${BASE_PATH}/js/square.js"></script>
</body>
</html>