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
        $taskList.append(task).css("opacity",0).animate({opacity:1},500);
    }
}

function selectTaskType(){
    $('.xb-task-type').click(function(){
        $('.xb-task-type').removeClass('current');
        $(this).addClass('current');
        //获得任务类型
        var taskType = $(this).attr('class').split(' ')[1].substring(5);
        $.get('/taskType',taskType,function(result){
            result = JSON.parse(result);
            showTask(result);
        });
    })
}
$(document).ready(function(){
    selectTaskType();
});