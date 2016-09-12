package com.helpyouJFinal.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
		String getTaskIdSQL = "select taskId from taskPublish where userId = ? order by publishId DESC";
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
	 * 获得任务发布者信息
	 * @param taskId 任务id
	 * @return 任务发布者model实例
	 */
	public User getTaskPublisher(Integer taskId) {
		String sql = "select userId from taskPublish where taskId = ?";
		Integer publisherId = Db.queryInt(sql,taskId);
		return User.dao.findById(publisherId);
	}
	
	/**
	 * 查找某用户接受的所有任务
	 * @param userId 用户id
	 * @return 任务类的一个列表
	 */
	public List<TaskAccept> getTaskListUserAccept(Integer userId) {
		String sql = "select * from taskAccept where userId = ? order by acceptId DESC";
		return TaskAccept.dao.find(sql,userId);
	}
	
	/**
	 * 查找接取某任务的记录
	 * @param taskId 任务id
	 * @return 接取该任务的记录的列表
	 */
	public List<TaskAccept> getTaskAcceptList(Integer taskId) {
		String sql = "select * from taskAccept where taskId = ? and state != 4 order by acceptId DESC";
		return TaskAccept.dao.find(sql, taskId);
	}

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
	public boolean addNewTask(Integer userId, String title, Integer type, Integer peopleNum,
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
		task.save();
		//获取任务Id
		String taskIdSQL = "select taskId from task where title = ? and content = ?";
		Integer taskId = Db.queryFirst(taskIdSQL,title,content);
		//保存发布信息
		return new TaskPublish().set("userId", userId).set("taskId", taskId).save();
	}

	/**
	 * 接受一个新的任务
	 * @param userId 接受者id
	 * @param taskId 任务id
	 */
	public boolean acceptTask(Integer userId,Integer taskId){
		String taskTitle = this.getTaskSpecific(taskId).getStr("title");
		return new TaskAccept().set("userId",userId).set("taskId",taskId).set("taskTitle", taskTitle).set("acceptTime",new Date()).set("state", 1).save();			
	}
	
	/**
	 * 检查某个用户是否接受了某个任务
	 * @param userId 用户id
	 * @param taskId 任务id
	 * @return true为接受了任务，false为未接受任务
	 */
	public boolean isUserAccept(Integer userId,Integer taskId) {
		String sql = "select acceptId from taskAccept where userId = ? and taskId = ?";
		Integer acceptId = Db.queryFirst(sql,userId,taskId);
		if (acceptId != null && acceptId > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 更新任务内容
	 * @param taskId 对应的任务的id
	 * @param content 任务内容
	 * @return 更新后的任务model实例
	 */
	public Task updateTaskInfo(Integer taskId,String content) {
		Task task = new Task().findById(taskId);
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
	 * 查询某任务是否存在
	 * @param taskId 任务id
	 * @return 任务存在返回true，不存在返回false
	 */
	public boolean isTaskExist(Integer taskId) {
		String sql = "select title from task where taskId = ?";
		String title = Db.queryFirst(sql,taskId);
		return StrKit.notBlank(title);
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
		publisher.set("point", publisher.getInt("point")+reward).update();
		accepter.set("point", accepter.getInt("point")-reward).update();
	}
	
	/**
	 * 确认完成任务
	 * @param taskId 任务id
	 * @param publishId 发布者id
	 * @param acceptId 接受者id
	 * @return 返回数据库是否修改成功
	 */
	public boolean confirmFinishTask(Integer taskId,Integer publishId,Integer acceptId) {
		String taskRewardSQL = "select reward from task where taskId = ?";
		Integer reward = Db.queryFirst(taskRewardSQL, taskId);
		System.out.println(reward);
		User publisher = User.dao.findById(publishId);
		User accepter = User.dao.findById(acceptId);
		publisher.set("point", publisher.getInt("point")-reward).update();
		accepter.set("point", accepter.getInt("point")+reward).update();
		String findAcceptSQL = "select * from taskAccept where taskId = ? and userId = ?";
		return TaskAccept.dao.findFirst(findAcceptSQL, taskId, acceptId).set("state", 3).update();
	}
	
	/**
	 * 查询任务能否结束
	 * @param taskId 任务id
	 * @return 能结束返回true，不能结束返回false
	 */
	public boolean canEndTask(Integer taskId) {
		String sql = "select * from taskAccept where taskId = ?";
		List<TaskAccept> taskAccepts = TaskAccept.dao.find(sql,taskId);
		for(int i=0,len=taskAccepts.size();i<len;i++){
			Integer state = taskAccepts.get(i).getInt("state");
			if (state == 1|| state == 2) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 结束任务
	 * @param taskId 任务id
	 */
	public void endTask(Integer taskId) {
		Task.dao.findById(taskId).set("state", 4).set("endTime", new Date()).update();
	}
	
	/**
	 * 强制结束任务
	 * @param taskId 任务id
	 */
	public void forceEndTask(Integer taskId) {
		//获得发布者id
		String publishIdSQL = "select userId from taskPublish where taskId = ?";
		Integer publishId = TaskPublish.dao.findFirst(publishIdSQL,taskId).getInt("userId");
		
		//给完成任务的人强制发送报酬
		String sql = "select * from taskAccept where taskId = ? and state = 2";
		List<TaskAccept> taskAccepts = TaskAccept.dao.find(sql, taskId);
		for (int i = 0; i < taskAccepts.size(); i++) {
			TaskAccept taskAccept = taskAccepts.get(i);
			Integer acceptId = taskAccept.getInt("userId");
			this.confirmFinishTask(taskId, publishId, acceptId);
		}
		//结束任务
		Task.dao.findById(taskId).set("state", 4).update();
	}
	
	
	/**
	 * 寻找所有被举报的任务
	 * @return 被举报的任务的列表
	 */
	public List<Task> getTasksReport() {
		String sql = "select * from task where state = 3";
		return Task.dao.find(sql);
	}
	
	/**
	 * 恢复任务
	 * @param taskId 任务Id
	 * @return 是否恢复成功
	 */
	public boolean recoveryTask(Integer taskId) {
		return Task.dao.findById(taskId).set("state", 1).update();
	}
	
	/**
	 * 删除某个任务相关的所有信息
	 * @param taskId 任务id
	 * @return 是否删除成功
	 */
	public boolean deleteTask(Integer taskId) {
		String taskAcceptSQL = "select * from taskAccept where taskId = ?";
		List<TaskAccept> taskAccepts = TaskAccept.dao.find(taskAcceptSQL,taskId);
		for (int i = 0; i < taskAccepts.size(); i++) {
			taskAccepts.get(i).delete();
		}
		String taskPublishSQL = "select * from taskPublish where taskId = ?";
		TaskPublish.dao.findFirst(taskPublishSQL,taskId).delete();
		return Task.dao.deleteById(taskId);
	}
}
