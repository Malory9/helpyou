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