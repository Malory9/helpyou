var tasks = [{
	taskId:1,
	taskTitle:"任务标题1",
	taskContent:"任务内容1"
},{
	taskId:2,
	taskTitle:"任务标题2",
	taskContent:"任务内容2"
},{
	taskId:3,
	taskTitle:"任务标题3",
	taskContent:"任务内容3"
},{
	taskId:4,
	taskTitle:"任务标题4",
	taskContent:"任务内容4"
},{
	taskId:1,
	taskTitle:"任务标题1",
	taskContent:"任务内容1"
},{
	taskId:2,
	taskTitle:"任务标题2",
	taskContent:"任务内容2"
},{
	taskId:3,
	taskTitle:"任务标题3",
	taskContent:"任务内容3"
},{
	taskId:4,
	taskTitle:"任务标题4",
	taskContent:"任务内容4"
}];

function showTask(tasks){
	$('.task').remove();

	var $taskList = $('.xb-task-list');
	for(let i = 0;i < tasks.length;i++){
		var task = $(`<li class="task">
                        <a href="/task/${tasks[i].taskId}">
                        <h3 class="task-title">${tasks[i].taskTitle}</h3>
                        <p class="task-content">${tasks[i].taskContent}</p>
                        </a>
                    </li>`);

		$taskList.append(task);
	}
}

$(document).ready(function(){
	showTask(tasks);
})