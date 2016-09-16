package com.helpyouJFinal.controller;

import java.util.List;

import com.helpyouJFinal.model.Admin;
import com.helpyouJFinal.model.Task;
import com.helpyouJFinal.service.AdminService;
import com.helpyouJFinal.service.NoticeService;
import com.helpyouJFinal.service.TaskService;
import com.jfinal.core.Controller;

public class ManagerController extends Controller {
	AdminService adminService = enhance(AdminService.class);
	TaskService taskService = enhance(TaskService.class);
	NoticeService noticeService = enhance(NoticeService.class);
	
	/**
	 * 跳转到管理员登录界面
	 */
	public void index() {
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
			redirect("/manager/main");
		} else {
			keepPara("adminname");
			setAttr("errorMsg", "账号或密码错误");
			render("managerLogin.jsp");
		}
	}
	
	/**
	 * 进入管理界面
	 */
	public void main() {
		if ((Admin)getSessionAttr("admin") == null) {
			redirect("/manager");
		}
		//获得被举报任务
		List<Task> reportedTasks = taskService.getTasksReport();
		setAttr("reportedTasks", reportedTasks);
		render("manager.jsp");
	}
	
	/**
	 * 恢复任务
	 */
	public void recovery() {
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
	public void delete() {
		Integer taskId = getParaToInt("taskId");
		boolean success = taskService.deleteTask(taskId);
		if (success) {
			renderText("success");
		} else {
			renderText("failed");
		}
	}
	
	/**
	 * 发布公告
	 */
	public void sendNotice() {
		String title = getPara("title");
		String content = getPara("content");
		boolean result = noticeService.addNewNotice(title, content);
		if (result) {
			renderText("success");
		} else {
			renderText("failed");
		}
	}

}
