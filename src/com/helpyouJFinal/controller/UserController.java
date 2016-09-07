package com.helpyouJFinal.controller;

import java.util.List;

import com.helpyouJFinal.interceptor.AuthInterceptor;
import com.helpyouJFinal.model.Task;
import com.helpyouJFinal.model.TaskAccept;
import com.helpyouJFinal.model.User;
import com.helpyouJFinal.service.TaskService;
import com.helpyouJFinal.service.UserService;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;


public class UserController extends Controller {
	
	UserService userService = enhance(UserService.class);
	TaskService taskService = enhance(TaskService.class);
	
	/**
	 * 用户主界面，若登录和查看的为同一用户，则设置修改权限为true
	 */
	public void index() {
		Integer readUserId = getParaToInt(0);
		
		//获得查看的用户的实例
		User readUser = userService.getUserSpecific(readUserId);
		setAttr("readingUser", readUser);
		
		//获得查看用户发布的任务列表
		List<Task> publishTasks = taskService.getTaskListUserPublish(readUserId);
		setAttr("publishTasks", publishTasks);
		//获得查看用户完成的任务列表
		List<TaskAccept> acceptTasks = taskService.getTaskListUserAccept(readUserId);
		setAttr("acceptTasks", acceptTasks);
		
		User sessionUser = getSessionAttr("user");
		setAttr("editPower", false);
		if (sessionUser != null) {
			if (sessionUser.getInt("userId") == readUserId) {
				setAttr("editPower", true);
			}			
		}
		render("user.jsp");
	}
	
	/**
	 * 更新用户信息
	 */
	@Before(AuthInterceptor.class)
	public void updateInfo() {
		User user = getSessionAttr("user");
		Integer userId = user.getInt("userId");
		String nickname = getPara("nickname");
		String sex = getPara("sex");
		Integer age = getParaToInt("age");
		System.out.println(userId);
		System.out.println(nickname);
		System.out.println(sex);
		System.out.println(age);
		User newUser = userService.updateUserInfo(userId, nickname, sex, age);
		setSessionAttr("user", newUser);
		redirect("/user/"+userId);
	}
}
