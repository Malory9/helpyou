<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.helpyouJFinal.model.User"%>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
>>>>>>> dd79e600cc3c71135998e44a198fe852675ddefd
<%!
	public boolean isBlank(String str) {
		return str == null || "".equals(str.trim());
	} 
%>

<div class="xb-top">
    <div class="xb-top-inner">
        <a href="/" class="xb-top-logo">校帮</a>
        <div class="xb-top-search">
            <form action="${BASE_PATH}/task/search" method="post">
                <input type="search" class="xb-top-search-input" placeholder="搜索你感兴趣的任务" autocomplete="off" name="keyword">
                <button type="submit" class="xb-top-search-button" id="xb-top-search-button">
                    <i class="iconfont xb-top-search-button-icon">&#xe600;</i>
                </button>
            </form>
        </div>
        <div class="xb-top-nav">
            <ul class="xb-top-nav-ul">
                <li class="xb-top-nav-ul-li"><a class="xb-top-nav-link" href="${BASE_PATH}/">任务广场</a></li>
                <li class="xb-top-nav-ul-li"><a class="xb-top-nav-link" href="${BASE_PATH}/message">
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
        <form action="${BASE_PATH}/task/add" method="post" class="add-task-form">
            <label for="taskTitle">任务标题</label>
            <input id="taskTitle" name="taskName" type="text" required>
            <label for="taskType">任务类型</label>
            <select id="taskType" name="taskType" required>
                <option value="1">线上任务</option>
                <option value="2">线下任务</option>
                <option value="3">其他任务</option>
            </select>
            <label for="taskPeopleNum">任务人数</label>
            <input type="number" id="peopleNum" name="peopleNum">人
            <label for="taskTime">任务时间</label>
            <div id="taskTime">
                <input type="number" id="taskTime-day" name="taskTime" required>天
                <input type="number" id="taskTime-hour" name="taskTime" required>小时
                <input type="number" id="taskTime-minute" name="taskTime" required>分钟
            </div>
            <label for="taskReward">任务报酬&nbsp;&nbsp;&nbsp;<small>(每人每次任务报酬不能超过10PY币)</small></label>
            <input type="number" id="taskReward" name="taskReward" min="1" max="10" required>PY币
            <label for="taskContent">任务详情</label>
            <textarea name="taskContent" id="taskContent" required></textarea>
            <button type="submit" id="xb-add-task">发布</button>
        </form>
    </div>
</div>