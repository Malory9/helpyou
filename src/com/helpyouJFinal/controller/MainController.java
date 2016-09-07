package com.helpyouJFinal.controller;

import java.util.Date;
import java.util.List;

import com.helpyouJFinal.model.Task;
import com.helpyouJFinal.model.User;
import com.helpyouJFinal.service.MessageService;
import com.helpyouJFinal.service.TaskService;
import com.helpyouJFinal.service.UserService;
import com.helpyouJFinal.validator.LoginValidator;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

public class MainController extends Controller {

	// 使用enhance对业务层进行增强，使其具有AOP能力
	private UserService userService = enhance(UserService.class);
	private TaskService taskService = enhance(TaskService.class);
	private MessageService messageService = enhance(MessageService.class);

	/**
	 * 默认主页，任务广场页面
	 */
	public void index() {
		//获得默认最新任务列表
		List<Task> tasks = taskService.searchLatestTasks();
		setAttr("tasks", tasks);
		// 渲染视图并返回给浏览器
		this.render("index.jsp");
	}

	/**
	 * 转向登陆界面
	 */
	public void login() {
		this.render("login.jsp");
	}

	/**
	 * 用户登录
	 */
	@Before(LoginValidator.class)
	public void doLogin() {
		String username = getPara("username");
		String password = getPara("password");
		User user = userService.login(username, password);
		
		//用户登录时，存储用户model到session，显示用户未读信息数量
		Long unreadMessageNum = 0L;
		if (user != null) {
			Date lastLoginTime = user.getDate("lastLoginTime");
			unreadMessageNum = messageService.getUnreadMessageNum(lastLoginTime);
			setSessionAttr("user", user);
			setSessionAttr("unreadMessageNum", unreadMessageNum);
			user.set("lastLoginTime", new Date()).update();
			redirect("/");
		} else {
			renderJsp("login.jsp");
		}
	}

	/*
	 * 用户注册
	 */
	public void doSignUp() {
		String username = getPara("username");
		String password = getPara("password");
		User user = userService.add(username, password);
		if (user != null) {
			// 存储新用户信息到session中
			getSession().setAttribute("user", user);
			redirect("/");
		} else {
			renderJsp("login.jsp");
		}
	}

	/**
	 * 用户注销
	 */
	public void logOut() {
		// 删除session中的user属性
		removeSessionAttr("user");
		// 重定向到首页
		redirect("/");
	}

	/**
	 * 帮助页面
	 */
	public void help() {
		this.render("help.jsp");
	}

	/**
	 * 关于我们页面
	 */
	public void about() {
		this.render("about.jsp");
	}

}
