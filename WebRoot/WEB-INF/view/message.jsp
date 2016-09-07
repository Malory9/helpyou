<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.helpyouJFinal.model.Message"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%! SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日  hh:mm:ss"); %>
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
<title>留言——校帮</title>
<link rel="shortcut icon" href="${BASE_PATH}/images/favicon.ico">
<link href="${BASE_PATH}/css/commons.css" rel="stylesheet"
	type="text/css">
<link href="${BASE_PATH}/css/message.css" rel="stylesheet"
	type="text/css">
<!--<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>-->
<script src="${BASE_PATH}/js/jquery.min.js"></script>
</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="xb-message">
		<button class="xb-send-message" id="xb-send-message">写留言</button>
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

		<% List<Message> messages = (List<Message>)request.getAttribute("messages"); %>
		<% List<String> nicknames = (List<String>)request.getAttribute("nicknames"); %>
		<%	for(int i=0,len = messages.size(); i < len ; i++) {
				Message message = messages.get(i);
				String nickname = nicknames.get(i);
		%>
		<div class="xb-message-item">
			<a href="${BASE_PATH}/user/<%=message.getInt("senderId")%>" class="xb-message-sender-nickname"><%=nickname %></a>:&nbsp;
			<span class="xb-message-receiver-nickname"><%=user.getStr("nickname") %></span>,你好
			<p class="xb-message-content"><%=message.getStr("content")%></p>
			<span class="xb-message-time"><%=dateFormat(message.getDate("time")) %></span> <a
				href="javascript:void(0)" class="answer">回复</a>
		</div>
		<%} %>

	</div>
	
	<%@ include file="footer.jsp"%>

	<script src="${BASE_PATH}/js/commons.js"></script>
	<script src="${BASE_PATH}/js/message.js"></script>
</body>
</html>