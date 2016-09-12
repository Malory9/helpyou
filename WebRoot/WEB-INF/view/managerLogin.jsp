<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

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
<link href="${BASE_PATH}/css/managerLogin.css" rel="stylesheet" type="text/css">
</head>

<body>
	<h2 class="manager-login-title">校帮后台管理系统</h2>
	<div class="box">
		<form action="${BASE_PATH}/manager/doLogin" class="boxBody">
			<span class="errorMsg" id="errorMsg"><%=request.getAttribute("errorMsg")==null?"":request.getAttribute("errorMsg") %></span>
			<div class="comboBox">
				<label>管理员用户名:</label> <input type="text" name="adminname">
			</div>
			<div class="comboBox">
				<label>登陆密码:</label> <input type="password" name="password">
			</div>

			<button type="submit" class="btnLogin">登陆</button>
		</form>
	</div>
</body>

</html>