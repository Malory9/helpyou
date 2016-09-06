package com.helpyouJFinal.controller;

<<<<<<< HEAD
import java.util.Date;
import java.util.List;

import com.helpyouJFinal.interceptor.SetOriginUrlInterceptor;
import com.helpyouJFinal.model.Task;
import com.helpyouJFinal.model.User;
import com.helpyouJFinal.service.MessageService;
import com.helpyouJFinal.service.TaskService;
=======
import org.apache.log4j.Logger;

import com.helpyouJFinal.interceptor.AuthInterceptor;
>>>>>>> dd79e600cc3c71135998e44a198fe852675ddefd
import com.helpyouJFinal.service.UserService;
import com.helpyouJFinal.validator.LoginValidator;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;

<<<<<<< HEAD
=======
public class MainController extends Controller {
	private UserService userService = new UserService();
	private static final Logger logger = Logger.getLogger(MainController.class);
>>>>>>> dd79e600cc3c71135998e44a198fe852675ddefd

public class MainController extends Controller {
	
	// 使用enhance对业务层进行增强，使其具有AOP能力
	private UserService userService = enhance(UserService.class);
	private TaskService taskService = enhance(TaskService.class);
	private MessageService messageService = enhance(MessageService.class);
	
	/**
	 * 默认主页，任务广场页面
	 */
	@Before(SetOriginUrlInterceptor.class)
	public void index() {
<<<<<<< HEAD
		List<Task> tasks = taskService.searchLatestTasks();
		setAttr("tasks", tasks);
=======
		String username = getSessionAttr("username");
		if (StrKit.isBlank(username)) {
			username = "游客";
		}
		logger.info(username+"进入到主页");
>>>>>>> dd79e600cc3c71135998e44a198fe852675ddefd
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
		String username = getPara("username");
		String password = getPara("password");
		User user = userService.login(username, password);
		if (user != null) {
			setSessionAttr("userLastLoginTime", user.getDate("lastLoginTime"));
			setSessionAttr("user", user);
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
			//存储新用户信息到session中
			getSession().setAttribute("user",user);
			redirect("/");
		} else {
			renderJsp("login.jsp");
		}
	}
	
	/**
	 * 用户注销
	 */
	public void logOut() {
		//获取session中的原先地址
		String redirectURL = getSessionAttr("originURL");
		//删除session中的user属性
		removeSessionAttr("user");
		//重定向到注销前的网址
		redirect(redirectURL);
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
