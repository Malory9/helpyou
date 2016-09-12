package com.helpyouJFinal.test;

import org.junit.Test;

import com.helpyouJFinal.service.TaskService;

import junit.framework.Assert;

public class TaskServiceTest extends JFinalTestBase {

	@Test
	public void confirmFinishTaskTest(){
		TaskService taskService = new TaskService();
		Assert.assertEquals(true, taskService.confirmFinishTask(5, 1, 2));
	}
	
	@Test
	public void deleteTaskTest(){
		TaskService taskService = new TaskService();
		Assert.assertEquals(true, taskService.deleteTask(6));
	}
}
