<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.helpyouJFinal.model.User"%>
<%!
	public boolean isBlank(String str) {
		return str == null || "".equals(str.trim());
	} 
%>

<div class="xb-top">
    <div class="xb-top-inner">
        <a href="/" class="xb-top-logo">校帮</a>
        <div class="xb-top-search">
            <form action="/search" method="get">
                <input type="search" class="xb-top-search-input" placeholder="搜索你感兴趣的任务" autocomplete="off" name="taskName">
                <button type="submit" class="xb-top-search-button">
                    <i class="iconfont xb-top-search-button-icon">&#xe600;</i>
                </button>
            </form>
        </div>
        <div class="xb-top-nav">
            <ul class="xb-top-nav-ul">
                <li class="xb-top-nav-ul-li"><a class="xb-top-nav-link" href="/">任务广场</a></li>
                <li class="xb-top-nav-ul-li"><a class="xb-top-nav-link" href="/user/message">
                    消息<span class="notice-number">0</span>
                </a></li>
            </ul>
        </div>
        <div class="xb-top-profile">
        <% User user = (User)session.getAttribute("user"); %>
            <% if(user == null){ %>
				<a class="xb-top-userinfo" href="${BASE_PATH}/login">
					<span class="username">登陆</span>
				</a>
			<%} else {%>
				<a class="xb-top-userinfo" href="${BASE_PATH}/user/<%=user.getInt("userId") %>">
					<span class="username"><%=user.getStr("nickname") %></span>
	                <!--<img src="images/head.png" class="avatar" alt="用户名">-->
				</a>
				<a class="xb-top-logOut" href="${BASE_PATH}/logOut">注销</a>
			<%} %>
        </div>
        <button id="xb-top-add-task" class="xb-top-add-task">发布任务</button>
    </div>
</div>

<div class="modal-bg"></div>
<div id="add-task-modal" class="add-task-modal">
    <div class="modal-title">
        <span class="modal-title-text">发布任务</span>
        <span id="task-publish-modal-close" class="modal-close iconfont">&#xe602;</span>
    </div>
    <div class="modal-content">
        <form action="/addTask" method="post" class="add-task-form">
            <label for="taskTitle">任务标题</label>
            <input id="taskTitle" name="taskName" type="text">
            <label for="taskType">任务类型</label>
            <select id="taskType" name="taskType">
                <option value="online">线上任务</option>
                <option value="offline">线下任务</option>
                <option value="other">其他任务</option>
            </select>
            <label for="taskPeopleNum">任务人数</label>
            <input type="number" id="taskPeopleNum" name="taskPeopleNum">人
            <label for="taskTime">任务时间</label>
            <div id="taskTime">
                <input type="number" id="taskTime-day" name="taskTime">天
                <input type="number" id="taskTime-hour" name="taskTime">小时
                <input type="number" id="taskTime-minute" name="taskTime">分钟
            </div>
            <label for="taskReward">任务报酬&nbsp;&nbsp;&nbsp;<small>(每人每次任务报酬不能超过10PY币)</small></label>
            <input type="number" id="taskReward" name="taskReward" min="1" max="10">PY币
            <label for="taskContent">任务详情</label>
            <textarea name="taskContent" id="taskContent"></textarea>
            <button type="submit">发布</button>
        </form>
    </div>
</div>