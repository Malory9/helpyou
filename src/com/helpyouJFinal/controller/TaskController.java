package com.helpyouJFinal.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.helpyouJFinal.interceptor.AJAXAuthInterceptor;
import com.helpyouJFinal.interceptor.AuthInterceptor;
import com.helpyouJFinal.interceptor.SetOriginUrlInterceptor;
import com.helpyouJFinal.model.Task;
import com.helpyouJFinal.model.TaskAccept;
import com.helpyouJFinal.model.User;
import com.helpyouJFinal.service.TaskService;
import com.helpyouJFinal.service.UserService;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

public class TaskController extends Controller {
	TaskService taskService = enhance(TaskService.class);
	UserService userService = enhance(UserService.class);

	/**
	 * 任务主页(任务详细页面)
	 */
	@Before(SetOriginUrlInterceptor.class)
	public void index() {
		Integer taskId = getParaToInt(0);
		// 添加任务model到request中
		Task task = taskService.getTaskSpecific(taskId);
		// 找不到对应任务
		if (task == null || task.getInt("state") == 3) {
			renderError(404);
		}
		
		// 添加发布者model到request中
		User publisher = taskService.getTaskPublisher(task.getInt("taskId"));
		this.setAttr("publisher", publisher);
		// 添加接受状态到request中
		List<TaskAccept> taskAccepts = taskService.getTaskAcceptList(taskId);
		this.setAttr("taskAccepts", taskAccepts);
		// 添加接受人列表到request中
		List<User> accepters = new ArrayList<User>();
		for (int i = 0, len = taskAccepts.size(); i < len; i++) {
			int userId = taskAccepts.get(i).getInt("userId");
			accepters.add(userService.getUserSpecific(userId));
		}
		setAttr("accepters", accepters);
		//非被举报任务
		if (task.getInt("state") != 3) {
			// 到达人数上限，设置为不可接任务
			if (task.getInt("peopleNum") == accepters.size()) {
				task.set("state", 2).update();
				// 重新获取任务
				task = taskService.getTaskSpecific(taskId);
			}
			// 任务超时，强制结束
			if (task.getDate("endTime").before(new Date())) {
				taskService.forceEndTask(taskId);
				// 重新获取任务
				task = taskService.getTaskSpecific(taskId);
			}
		}
		this.setAttr("task", task);
		this.renderJsp("task.jsp");
	}

	/**
	 * 发布任务(AJAX)
	 */
	@Before(AJAXAuthInterceptor.class)
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
		Integer allReward = peopleNum*reward;
		boolean result = userService.canPublish(userId,allReward);
		if (!result) {
			renderText("failed");
		}
		result = taskService.addNewTask(userId, title, type, peopleNum, reward, content,
						days, hours, minutes);
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

	/**
	 * 通过类型搜索任务(AJAX) 返回：List<Task>的json
	 */
	public void searchByType() {
		Integer type = getParaToInt("taskType");
		System.out.println(type);
		List<Task> tasks = null;
		if (type == 0) {
			tasks = taskService.searchLatestTasks();
		} else if (type == 1) {
			tasks = taskService.searchOnlineTasks();
		} else if (type == 2) {
			tasks = taskService.searchOfflineTasks();
		} else if (type == 3) {
			tasks = taskService.searchOtherTasks();
		}
		renderJson(tasks);
	}

	/**
	 * 接受任务
	 */
	@Before(AuthInterceptor.class)
	public void accept() {
		Integer accepterId = getParaToInt("acceptId");
		Integer taskId = getParaToInt("taskId");
		boolean result = taskService.isUserAccept(accepterId, taskId);
		if (!result) {
			taskService.acceptTask(accepterId, taskId);
		}
		redirect("/task/" + taskId);
	}

	/**
	 * 提交完成任务
	 */
	@Before(AuthInterceptor.class)
	public void submitFinish() {
		Integer accepterId = getParaToInt("acceptId");
		Integer taskId = getParaToInt("taskId");
		taskService.submitFinishTask(taskId, accepterId);
		redirect("/task/" + taskId);
	}

	/**
	 * 确认完成任务
	 */
	public void confirmFinish() {
		Integer acceptId = getParaToInt("acceptId");
		Integer publishId = getParaToInt("publishId");
		Integer taskId = getParaToInt("taskId");
		boolean result = taskService.confirmFinishTask(taskId, publishId, acceptId);
		System.out.println(result);
		if (result) {
			renderText("success");
		} else {
			renderText("failed");
		}
	}

	/**
	 * 举报任务
	 */
	@Before(AuthInterceptor.class)
	public void report() {
		Integer taskId = getParaToInt("taskId");
		if (taskService.isTaskExist(taskId)) {
			taskService.reportTask(taskId);
		}
		redirect("/task/" + taskId);
	}

	/**
	 * 修改任务内容
	 */
	@Before(AuthInterceptor.class)
	public void updateContent() {
		Integer taskId = getParaToInt("taskId");
		String content = getPara("content");
		taskService.updateTaskInfo(taskId, content);
		redirect("/task/" + taskId);
	}

	/**
	 * 结束任务
	 */
	@Before(AuthInterceptor.class)
	public void end() {
		Integer taskId = getParaToInt("taskId");
		if (taskService.canEndTask(taskId)) {
			taskService.endTask(taskId);
			redirect("/task/" + taskId);
		} else {
			setAttr("endErrorMsg", "无法结束任务，还有未确认的提交");
			forwardAction("/task/" + taskId);
		}
	}

}
