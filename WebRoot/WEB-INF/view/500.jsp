<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<title>500——出错了o(︶︿︶)o</title>
	<link rel="shortcut icon" href="${BASE_PATH}/images/favicon.ico">
	<link href="${BASE_PATH}/css/commons.css" rel="stylesheet" type="text/css">
	<link href="${BASE_PATH}/css/error.css" rel="stylesheet" type="text/css">
	<!--<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>-->
	<script src="${BASE_PATH}/js/jquery.min.js"></script>
</head>
<body>
	<%@ include file="header.jsp"%>
	
	<div class="xb-error">
	    <div class="xb-error-box">
	                服务器好像出现了一些问题o(︶︿︶)o
	        <a href="${BASE_PATH}/">__返回首页__</a>
	    </div>
	</div>
	
	<%@ include file="footer.jsp"%>
	<script src="${BASE_PATH}/js/commons.js"></script>
</body>
</html>