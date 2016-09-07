function noLoginRedirect() {
    $('.xb-task-btn').click(function () {
        var userId = $('.xb-top-userinfo').attr('href').replace('/helpyouJFinal/', '');
        //非登录情况下跳转到登录界面
        if (!userId.startWith('user')) {
            window.location.href = "http://localhost:8080/helpyouJFinal/login";
            return;
        }
    });
}

function btnConfirm() {
    $('#xb-task-take-btn').click(function (e) {
        if (!confirm('是否接受这个任务?')) {
            e.preventDefault();
            e.stopPropagation();
        }
    });
    $('#xb-task-finish-btn').click(function (e) {
        if (!confirm('是否已经完成这个任务?')) {
            e.preventDefault();
            e.stopPropagation();
        }
    });
    $('#xb-task-report-btn').click(function (e) {
        if (!confirm('是否举报这个任务?')) {
            e.preventDefault();
            e.stopPropagation();
        }
    });
    $('#xb-task-end-btn').click(function (e) {
        if (!confirm('是否结束这个任务?')) {
            e.preventDefault();
            e.stopPropagation();
        }
    });
    $('#xb-task-edit-upload-btn').click(function (e) {
        if (!confirm('是否修改这个任务?')) {
            e.preventDefault();
            e.stopPropagation();
        }
    });
}

function editContent() {
    $('#xb-task-edit-btn').click(function () {
        $('.task-content').find('p').hide();
        $('#xb-task-editInfo-form').show();
    })
}

function confirmFinish() {
    $('.xb-task-accepter-state a').click(function (e) {
        var url = $(this).attr('href');
        var taskId = $('input[name=taskId]').val();
        var publishId = $('input[name=publishId]').val();
        var acceptId = $(this).parent().prevAll('.xb-task-accepter-nickname').children('a').attr('href').replace('/helpyouJFinal/user/', '');

        var $that = $(this);
        
        $.ajax({
            url: url,
            type: 'post',
            data: {
                taskId: taskId,
                publishId: publishId,
                acceptId: acceptId
            },
            success: function (result) {
                if (result == "success") {
                    $that.parent().html("已完成");
                } else if(result == "failed"){
                    alert('确认失败!');
                }
            }
        });
    });
}

$(document).ready(function () {
    var userId = $('.xb-top-userinfo').attr('href').replace('/helpyouJFinal/', '');
    if (!userId.startWith('user')) {
        noLoginRedirect();
    } else {
        btnConfirm();
        confirmFinish();
        editContent();
    }
})