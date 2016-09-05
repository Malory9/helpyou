package com.helpyouJFinal.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.helpyouJFinal.model.Task;
import com.helpyouJFinal.model.TaskAccept;
import com.helpyouJFinal.model.TaskPublish;
import com.jfinal.aop.Before;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.tx.Tx;

@Before(Tx.class)
public class TaskService {

	/**
	 * 搜索最新任务
	 * @return 最新任务列表
	 */
	public List<Task> searchLatestTasks() {
		String sql = "select taskId,title,content from task where state <= 2 order by startTime DESC";
		return Task.dao.find(sql);
	}

	/**
	 * 搜索线上任务
	 * @return 线上任务列表
	 */
	public List<Task> searchOnlineTasks() {
		String sql = "select taskId,title,content from task where state <= 2 and type = 1 order by startTime DESC";
		return Task.dao.find(sql);
	}

	/**
	 * 搜索线下任务
	 * @return 线下任务列表
	 */
	public List<Task> searchOfflineTasks() {
		String sql = "select taskId,title,content from task where state <= 2 and type = 2 order by startTime DESC";
		return Task.dao.find(sql);
	}

	/**
	 * 搜索其他任务
	 * @return 其他任务列表
	 */
	public List<Task> searchOtherTasks() {
		String sql = "select taskId,title,content from task where state <= 2 and type = 3 order by startTime DESC";
		return Task.dao.find(sql);
	}

	/**
	 * 通过关键字查找任务
	 * @param keyword 关键字
	 * @return 查找到的任务列表
	 */
	public List<Task> searchTasksByKeyword(String keyword) {
		String sql = "select taskId,title,content from task where state <= 2 and (title like %?% or content like %?% ) order by startTime DESC";
		return Task.dao.find(sql, keyword,keyword);
	}
	
	/**
	 * 查找某用户发布的所有任务
	 * @param userId 用户id
	 * @return 任务类的一个列表
	 */
	public List<Task> searchTaskUserPublish(Integer userId) {
		String getTaskIdSQL = "select taskId from taskPublish where userId = ?";
		List<Integer> taskIds = Db.query(getTaskIdSQL,userId);
		List<Task> tasks = new ArrayList<Task>();
		for(int i = 0,len = taskIds.size();i < len;i++){
			Task task = new Task().findById(taskIds.get(i));
			tasks.add(task);
		}
		return tasks;
	}
	
	/**
	 * 查找某用户接受的所有任务
	 * @param userId 用户id
	 * @return 任务类的一个列表
	 */
	public List<TaskAccept> searchTaskUserAccept(Integer userId) {
		String getTaskSQL = "select taskId from taskAccept where userId = ?";
		return TaskAccept.dao.find(getTaskSQL,userId);
	}

	/**
	 * 添加一个新的任务
	 * @param userId 发布者id
	 * @param title 任务标题
	 * @param type 任务类型
	 * @param peopleNum 任务所需人数
	 * @param reward 任务报酬
	 * @param content 任务内容
	 */
	public void addNewTask(Integer userId, String title, Integer type, Integer peopleNum,
					Integer reward, String content) {
		//保存任务
		new Task().set("title", title).set("type", type).set("startTime", new Date())
						.set("peopleNum", peopleNum).set("reward", reward).set("content", content).save();
		//获取任务Id
		String taskIdSQL = "select taskId from task where title = ? and content = ?";
		Integer taskId = Db.queryFirst(taskIdSQL,title,content);
		//保存发布信息
		new TaskPublish().set("userId", userId).set("taskId", taskId).save();
	}
	
	/**
	 * 更新任务信息
	 * @param taskId 对应的任务的id
	 * @param title 任务标题
	 * @param type 任务类型
	 * @param peopleNum 任务所需人数
	 * @param reward 任务报酬
	 * @param content 任务内容
	 * @return 更新后的任务model实例
	 */
	public Task updateTaskInfo(Integer taskId,String title,Integer type,Integer peopleNum,Integer reward,String content) {
		Task task = new Task().findById(taskId);
		if (!StrKit.isBlank(title)) {
			task.set("title", title);
		}
		if (type != null) {
			task.set("type", type);
		}
		if (peopleNum != null) {
			task.set("peopleNum", peopleNum);
		}
		if (reward != null) {
			task.set("reward", reward);
		}
		if (!StrKit.isBlank(content)) {
			task.set("content", content);
		}
		task.update();
		return task;
	}
	
	/**
	 * 获得一个任务的具体model
	 * @param taskId 任务id
	 * @return 通过id找到的任务类Task的一个具体事例
	 */
	public Task getTaskSpecific(Integer taskId) {
		return Task.dao.findById(taskId);
	}
}
