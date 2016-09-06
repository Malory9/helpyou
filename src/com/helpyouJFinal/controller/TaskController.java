package com.helpyouJFinal.controller;

import java.util.List;

import com.helpyouJFinal.interceptor.SetOriginUrlInterceptor;
import com.helpyouJFinal.model.Task;
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
	
	public void add() {
		
	}
	
	/**
	 * 搜索任务
	 */
	public void search() {
		String keyword = getPara("keyword");
		System.out.println(keyword);
		List<Task> tasks = taskService.searchTasksByKeyword(keyword);
		setAttr("tasks", tasks);
		this.render("searchResult.jsp");
	}
}
