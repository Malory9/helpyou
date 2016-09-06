function showTask(tasks) {
    $('.task').remove();

    var $taskList = $('.xb-task-list');
    for (let i = 0; i < tasks.length; i++) {
        let taskObject = tasks[i];
        console.log(taskObject);
        let task = $(`<li class="task">
                        <a href="/helpyouJFinal/task/${tasks[i].taskId}">
                            <h3 class="task-title">${tasks[i].title.toString()}</h3>
                            <p class="task-content">${tasks[i].content.toString()}</p>
                        </a>
                    </li>`);
        $taskList.append(task).css("opacity", 0).animate({
            opacity: 1
        }, 500);
    }
}

function selectTaskType() {
    $('.xb-task-type').click(function () {
        //获得任务类型
        var taskType = parseInt($(this).attr('class').split(' ')[1].substring(5));
        var url = $('#ajaxURL').data('url');
        $.ajax({
            type: 'post',
            url: url,
            data: {taskType:taskType},
            dataType: 'json',
            success: function (result) {
                $('.xb-task-type').removeClass('current');
                $('.type-'+taskType).addClass('current');
                showTask(result);
            }
        });
    });
}

$(document).ready(function () {
    selectTaskType();
});