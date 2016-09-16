<%@page import="com.jfinal.kit.StrKit"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-COMPATIBLE" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1 ,minimum-scale=1,user-scalable=0">
	<meta name="renderer" content="webkit">
	<meta name="keywords" content="互帮互助,学校,互助平台">
	<meta name="author" content="张世凯，方浩">
	<title>登陆——校帮</title>
	<link rel="shortcut icon" href="${BASE_PATH}/images/favicon.ico">
	<link href="${BASE_PATH}/css/login.css" rel="stylesheet" type="text/css">
	<!--<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>-->
	<script src="${BASE_PATH}/js/jquery.min.js"></script>
</head>
<body>
    <div class="xb-index-main">
        <div class="index-header">
            <a href="${BASE_PATH}/"><h1 class="logo">校帮</h1></a>
            <h2 class="subtitle">你身边的互助平台</h2>
        </div>
        <div class="sign-view">
            <div class="index-tab-navs">
                <a class="" href="#signup">注册</a>
                <a class="active" href="#login">登录</a>
            </div>
            <div class="view view-login selected">
                <form action="${BASE_PATH}/doLogin" method="post">
                    <div class="group-input">
                        <input type="text" placeholder="用户名" name="username" maxlength="12" 
                        	<%
                        		if(StrKit.notBlank(request.getParameter("username"))){
                        			out.print("value='"+request.getParameter("username")+"'");
                        		}
                        	%>
                         required>
                        <input type="password" placeholder="密码" name="password" maxlength="12" required>
                    </div>
                    <div class="loginErrorMsg errorMsg"><%=request.getAttribute("loginErrorMsg")!=null?request.getAttribute("loginErrorMsg"):"" %></div>
                    <button type="submit" class="sign-button" id="login-button">登陆</button>
                </form>
            </div>
            <div class="view view-signup">
                <form action="${BASE_PATH}/doSignUp" method="post">
                    <div class="group-input">
                        <input type="text" placeholder="用户名(字母开头,由字母和数字组成,3-12位)" name="username" maxlength="12" required>
                        <input type="password" placeholder="密码(6-12位)" name="password" maxlength="12" required>
                    </div>
                    <div class="signUpErrorMsg errorMsg"><%=request.getAttribute("signUpErrorMsg")!=null?request.getAttribute("signUpErrorMsg"):"" %></div>
                    <button type="submit" class="sign-button" id="signup-button">注册</button>
                </form>
            </div>
        </div>
    </div>
    
    <div class="xb-footer">
        <span class="copyright">版权信息</span>
        <div class="xb-footer-links">
            <a href="/aboutus">关于我们</a>
            <a href="/help">帮助中心</a>
            <a href="mailto:emailAddress@email.com">联系我们</a>
        </div>
    </div>
    
    <script src="${BASE_PATH}/js/login.js"></script>
</body>
</html>