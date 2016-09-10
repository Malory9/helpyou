package com.helpyouJFinal.controller;

import java.util.List;

import com.helpyouJFinal.interceptor.AJAXManagerAuthInterceptor;
import com.helpyouJFinal.interceptor.ManagerAuthInterceptor;
import com.helpyouJFinal.model.Admin;
import com.helpyouJFinal.model.Task;
import com.helpyouJFinal.model.User;
import com.helpyouJFinal.service.AdminService;
import com.helpyouJFinal.service.TaskService;
import com.helpyouJFinal.service.UserService;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

public class ManagerController extends Controller {
	AdminService adminService = enhance(AdminService.class);
	TaskService taskService = enhance(TaskService.class);
	UserService userService = enhance(UserService.class);
	
	/**
	 * 跳转到管理员登录界面
	 */
	public void login() {
		render("managerLogin.jsp");
	}
	
	/**
	 * 管理员登录
	 */
	public void doLogin() {
		String adminname = getPara("adminname");
		String password = getPara("password");
		Admin admin = adminService.login(adminname, password);
		if (admin != null) {
			setSessionAttr("admin", admin);
		} else {
			keepPara("adminname");
			setAttr("errorMsg", "账号或密码错误");
			render("managerLogin.jsp");
		}
	}
	
	/**
	 * 进入管理界面
	 */
	@Before(ManagerAuthInterceptor.class)
	public void index() {
		//获得被举报任务
		List<Task> reportedTasks = taskService.getTasksReport();
		setAttr("reportedTasks", reportedTasks);
		//获得所有用户
		List<User> users = userService.getAllUsers();
		setAttr("users", users);
		render("manager.jsp");
	}
	
	/**
	 * 冻结用户
	 */
	@Before(AJAXManagerAuthInterceptor.class)
	public void freezeUser() {
		Integer userId = getParaToInt("userId");
		boolean success = userService.freezeUser(userId);
		if (success) {
			renderText("success");
		} else {
			renderText("failed");
		}
	}
	
	/**
	 * 冻结用户
	 */
	@Before(AJAXManagerAuthInterceptor.class)
	public void unfreezeUser() {
		Integer userId = getParaToInt("userId");
		boolean success = userService.unfreezeUser(userId);
		if (success) {
			renderText("success");
		} else {
			renderText("failed");
		}
	}
	
	/**
	 * 恢复任务
	 */
	@Before(AJAXManagerAuthInterceptor.class)
	public void recoveryTask() {
		Integer taskId = getParaToInt("taskId");
		boolean success = taskService.recoveryTask(taskId);
		if (success) {
			renderText("success");
		} else {
			renderText("failed");
		}
	}
	
	/**
	 * 删除任务
	 */
	@Before(AJAXManagerAuthInterceptor.class)
	public void deleteTask() {
		Integer taskId = getParaToInt("taskId");
		boolean success = taskService.deleteTask(taskId);
		if (success) {
			renderText("success");
		} else {
			renderText("failed");
		}
	}

}
