package com.helpyouJFinal.controller;

import com.helpyouJFinal.interceptor.AuthInterceptor;
import com.helpyouJFinal.service.TaskService;
import com.helpyouJFinal.service.UserService;
import com.helpyouJFinal.validator.LoginValidator;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

public class MainController extends Controller {
	private UserService userService = new UserService();
	private TaskService taskService = new TaskService();

	// Controller默认调用方法
	public void index() {
		// 渲染视图并返回给浏览器
		this.render("index.jsp");
	}

	public void login() {
		this.render("login.jsp");
	}

	/**
	 * 用户登录
	 */
	@Before(LoginValidator.class)
	public void doLogin() {
		// 使用enhance对业务层进行增强，使其具有AOP能力
		UserService userService = enhance(UserService.class);
		String username = getPara("username");
		String password = getPara("password");
		Integer userId = userService.login(username, password);
		if (userId > 0) {
			setSessionAttr("username", username);
			setSessionAttr("userId", userId);
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
		boolean result = userService.add(username, password);
		if (result) {
			getSession().setAttribute("username", username);
			renderJsp("index.jsp");
		} else {
			renderJsp("login.jsp");
		}
	}

	@Before(AuthInterceptor.class)
	public void edit() {
		renderJson("user.jsp");
	}
}
