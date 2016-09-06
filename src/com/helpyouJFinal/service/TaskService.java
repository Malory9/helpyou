package com.helpyouJFinal.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.helpyouJFinal.model.Task;
import com.helpyouJFinal.model.TaskAccept;
import com.helpyouJFinal.model.TaskPublish;
import com.helpyouJFinal.model.User;
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
		String sql = "select taskId,title,content from task where state <= 2 and (title like '%"+keyword+"%' or content like '%"+keyword+"%') order by startTime DESC";
		return Task.dao.find(sql);
	}
	
	/**
	 * 查找某用户发布的所有任务
	 * @param userId 用户id
	 * @return 任务类的一个列表
	 */
	public List<Task> getTaskListUserPublish(Integer userId) {
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
	 * 获得任务发布者id
	 * @param taskId 任务id
	 * @return 任务发布者id
	 */
	public Integer getTaskPublishId(Integer taskId) {
		String sql = "select userId from taskPublish where taskId = ?";
		return Db.queryInt(sql,taskId);
	}
	
	/**
	 * 查找某用户接受的所有任务
	 * @param userId 用户id
	 * @return 任务类的一个列表
	 */
	public List<TaskAccept> getTaskListUserAccept(Integer userId) {
		String getTaskSQL = "select taskId from taskAccept where userId = ?";
		return TaskAccept.dao.find(getTaskSQL,userId);
	}

	/**

	 */
	/**
	 * 发布一个新的任务
	 * @param userId 发布者id
	 * @param title 任务标题
	 * @param type 任务类型
	 * @param peopleNum 任务所需人数
	 * @param reward 任务报酬
	 * @param content 任务内容
	 * @param day 任务天数
	 * @param hour 任务小时数
	 * @param minute 任务分钟数
	 */
	public void addNewTask(Integer userId, String title, Integer type, Integer peopleNum,
					Integer reward, String content,Integer days,Integer hours,Integer minutes) {
		Date startDate = new Date();
		//保存任务
		Task task = new Task().set("title", title).set("type", type).set("startTime", startDate)
						.set("peopleNum", peopleNum).set("reward", reward).set("content", content);
		//添加结束日期
		Date endDate = (Date) startDate.clone();
		endDate.setDate(startDate.getDate()+days);
		endDate.setHours(startDate.getHours()+hours);
		endDate.setMinutes(startDate.getMinutes()+minutes);
		task.set("endTime", endDate);
		//获取任务Id
		String taskIdSQL = "select taskId from task where title = ? and content = ?";
		Integer taskId = Db.queryFirst(taskIdSQL,title,content);
		//保存发布信息
		new TaskPublish().set("userId", userId).set("taskId", taskId).save();
	}

	/**
	 * 接受一个新的任务
	 * @param userId 接受者id
	 * @param taskId 任务id
	 */
	public void takeTask(Integer userId,Integer taskId){
		new TaskAccept().set("userId",userId).set("taskId",taskId).set("acceptTime",new Date()).save();
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
	
	/**
	 * 举报任务
	 * @param taskId 任务ID
	 */
	public void reportTask(Integer taskId) {
		Task.dao.findById(taskId).set("state", 3).update();
	}
	
	/**
	 * 提交完成任务
	 * @param taskId 任务id
	 * @param acceptId 接受者id
	 */
	public void submitFinishTask(Integer taskId,Integer acceptId) {
		String findAcceptSQL = "select * from taskAccept where taskId = ? and userId = ?";
		TaskAccept.dao.findFirst(findAcceptSQL, taskId, acceptId).set("state", 2).update();
	}
	
	/**
	 * 放弃任务
	 * @param taskId 任务id
	 * @param acceptId 接受者id
	 * @param publishId 发布者id
	 */
	public void giveUpTask(Integer taskId,Integer acceptId,Integer publishId) {
		String findAcceptSQL = "select * from taskAccept where taskId = ? and userId = ?";
		TaskAccept.dao.findFirst(findAcceptSQL, taskId,acceptId).set("state", 3).update();
		String taskRewardSQL = "select reward from task where taskId = ?";
		Integer reward = Db.queryInt(taskRewardSQL, taskId);
		User publisher = new User().findById(publishId);
		User accepter = new User().findById(acceptId);
		publisher.set("point", publisher.getInt("point")+reward);
		accepter.set("point", accepter.getInt("point")-reward);
	}
	
	/**
	 * 确认完成任务
	 * @param taskId 任务id
	 * @param publishId 发布者id
	 * @param acceptId 接受者id
	 */
	public void confirmFinishTask(Integer taskId,Integer publishId,Integer acceptId) {
		String taskRewardSQL = "select reward from task where taskId = ?";
		Integer reward = Db.queryInt(taskRewardSQL, taskId);
		User publisher = new User().findById(publishId);
		User accepter = new User().findById(acceptId);
		publisher.set("point", publisher.getInt("point")-reward);
		accepter.set("point", accepter.getInt("point")+reward);
	}
	
	/**
	 * 结束任务
	 * @param taskId 任务id
	 */
	public void endTask(Integer taskId) {
		Task.dao.findById(taskId).set("state", 4).set("endTime", new Date()).update();
	}
}
