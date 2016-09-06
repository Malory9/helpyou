//添加字符串对象的startWith方法
String.prototype.startWith = function (str) {
    if (str == null || str == "" || this.length == 0 || str.length > this.length)
        return false;
    if (this.substr(0, str.length) == str)
        return true;
    else
        return false;
    return true;
}
String.prototype.endWith = function (str) {
    if (str == null || str == "" || this.length == 0 || str.length > this.length)
        return false;
    if (this.substring(this.length - str.length) == str)
        return true;
    else
        return false;
    return true;
}


//对返回顶部按钮进行重新布局
function backiconReput() {
    var winWidth = 1280;
    //获得浏览器窗口的宽度大小
    if (window.innerWidth) {
        winWidth = window.innerWidth;
    } else if ((document.body) && (document.body.clientWidth)) {
        winWidth = document.body.clientWidth;
    }
    let leftSpace = 10;
    let backleft = winWidth / 2 + 480 + leftSpace;
    $('.xb-backtotop').css('left', backleft);
}

function allBinding() {

    //当页面大小改变时，重新定位顶部按钮
    $(window).resize(function () {
        backiconReput();
    });

    //返回顶部按钮的出现和消失
    $(window).scroll(function () {
        if ($(window).scrollTop() > 300) {
            $("#xb-backtotop").fadeIn(500);
        } else {
            $("#xb-backtotop").fadeOut(500);
        }
    });

    //返回顶部按钮的功能实现
    $('#xb-backtotop').click(function () {
        $('body,html').animate({
            scrollTop: 0
        }, 500);
    });

    //发布任务框的出现
    $('#xb-top-add-task').click(function () {
        $('.modal-bg').css('display', 'block').animate({
            opacity: 0.5
        }, 300);
        $('.add-task-modal').css('display', 'block').animate({
            opacity: 1,
            top: 90
        }, 300);
    });

    //发布任务框关闭
    $('#task-publish-modal-close').click(function () {
        $('.modal-bg').animate({
            opacity: 0
        }, 300, function () {
            $(this).css('display', 'none');
        });
        $('.add-task-modal').animate({
            opacity: 0,
            top: -550
        }, 300, function () {
            $(this).css('display', 'none');
        });
    });

    //消息框显示与否
    if ($('.notice-number').text() == 0) {
        $('.notice-number').css('display', 'none');
    }
    $('.notice-number').click(function () {
        $(this).css('display', 'none');
    })

    $('#xb-add-task').click(function (e) {
        addTask();
        e.preventDefault();
    })
}

//使对输入框的输入内容进行限制
function inputLimit() {
    //数字框无法输入除数字以外字符
    $('input[type=number]').keydown(function () {
        $(this).val().replace(/\D/, '');
    });
    //文本框两端不能有空格
    $('input').keydown(function () {
        var inputContent = $(this).val().trim();
        $(this).val(inputContent);
    })
}

//添加(发布)任务
function addTask() {
    var userId = $('.xb-top-userinfo').attr('href').replace('/helpyouJFinal/', '');
    if (userId.startWith('user')) {
        userId = parseInt(userId.replace('user/', ''));
    } else {
        userId = 0;
    }
    var data = {
        userId: userId,
        taskTitle: $('#taskTitle').val(),
        taskType: $('#taskType option:selected').val(),
        peopleNum: $('#peopleNum').val(),
        days: $('#taskTime-day').val(),
        hours: $('#taskTime-hour').val(),
        minutes: $('#taskTime-minute').val(),
        reward: $('#taskReward').val(),
        content: $('#taskContent').val()
    };
    $.ajax({
        url: $('.add-task-form').attr('action'),
        type: 'post',
        data: data,
        success: function (result) {
            if (result == "success") {
                alert("任务发布成功!");
                //清除发布框内的内容
                $('.add-task-form').children()
                    .not(':button, :submit, :reset, :hidden')
                    .val('')
                    .removeAttr('selected');
                $('#task-publish-modal-close').trigger('click');
                //若位于主页，则刷新以显示新任务
                if(window.location.href.endWith('helpyouJFinal/')){
                    location.reload();
                }
            } else if (result == "failed") {
                alert("发布失败，请重新发布任务");
            } else if (result == "noUser") {
                window.location.href = "http://localhost:8080/helpyouJFinal/login";
            }
        }
    })
}

$(document).ready(function () {
    backiconReput();
    allBinding();
    inputLimit();
})