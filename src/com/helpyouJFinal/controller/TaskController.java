package com.helpyouJFinal.controller;

import java.util.List;

import com.helpyouJFinal.interceptor.AuthInterceptor;
import com.helpyouJFinal.interceptor.SetOriginUrlInterceptor;
import com.helpyouJFinal.model.Task;
import com.helpyouJFinal.model.User;
import com.helpyouJFinal.service.TaskService;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

public class TaskController extends Controller {
	TaskService taskService = enhance(TaskService.class);
	
	/**
	 * 任务主页(任务详细页面)
	 */
	@Before(SetOriginUrlInterceptor.class)
	public void index() {
		Integer taskId = getParaToInt(0);
		Task task = taskService.getTaskSpecific(taskId);
		this.setAttr("task", task);
		this.renderJsp("task.jsp");
	}
	
	/**
	 * 发布任务
	 */
	@Before(AuthInterceptor.class)
	public void add() {
		Integer userId = getParaToInt("userId");
		String title = getPara("taskTitle");
		Integer type = getParaToInt("taskType");
		Integer peopleNum = getParaToInt("peopleNum");
		Integer days = getParaToInt("days");
		Integer hours = getParaToInt("hours");
		Integer minutes = getParaToInt("minutes");
		Integer reward = getParaToInt("reward");
		String content = getPara("content");
		boolean result = taskService.addNewTask(userId, title, type, peopleNum, reward, content, days, hours, minutes);
		if (result) {
			renderText("success");
		} else {
			renderText("failed");
		}
	}
	
	/**
	 * 搜索任务
	 */
	public void search() {
		String keyword = getPara("keyword");
		System.out.println(keyword);
		List<Task> tasks = taskService.searchTasksByKeyword(keyword);
		System.out.println(tasks.size());
		setAttr("tasks", tasks);
		this.render("searchResult.jsp");
	}
	
	public void searchByType() {
		Integer type = getParaToInt("taskType");
		System.out.println(type);
		List<Task> tasks = null;
		if (type == 0) {
			tasks = taskService.searchLatestTasks();
		} else if(type == 1) {
			tasks = taskService.searchOnlineTasks();
		} else if (type == 2) {
			tasks = taskService.searchOfflineTasks();
		} else if (type == 3) {
			tasks = taskService.searchOtherTasks();
		}
		renderJson(tasks);
	}
}
