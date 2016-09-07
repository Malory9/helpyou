//切换显示用户已接受和已发布任务列表
function switchTaskPublishAndToken() {
    $('.xb-user-task-item').click(function (e) {
        $('.xb-user-task-item').removeClass('active');
        $(this).addClass('active');
        var state = $(this).attr('href').substring(1);
        $('.xb-user-task-list').removeClass('selected').filter('.task-' + state).addClass('selected');
        e.preventDefault();
        e.stopPropagation();
    });
}

/*
//通过AJAX修改用户信息
function editUserInfo() {
        var userId = $('.xb-top-userinfo').attr('href').toString().replace('/helpyouJFinal/', '');
        if (userId.startWith('user')) {
            userId = parseInt(userId.replace('user/', ''));
        } else {
            userId = 0;
        }
        
        var userInfo = {
            userId: parseInt(userId),
            nickname: $('#xb-user-edit-nickname').val(),
            sex: $('#xb-user-edit-sex option:select').val(),
            age: $('#xb-user-edit-age').val()
        };
        
        $.ajax({
            url: $('#xb-user-form').attr('action'),
            type: 'post',
            data: userInfo,
            dataType: 'json',
            success: function(user){
                alert('修改成功');
                $('#xb-user-info-nickname').text(user.nickname);
                $('#xb-user-info-sex').text(user.sex);
                $('#xb-user-info-age').text(user.age);
                //清空表单文本框内数据并使其不显示
                $('#xb-user-form').find('input').val('').hide();
                $('#xb-user-info-save').hide();
            }
        });
}
*/

//显示修改信息框
function showUserInfoEditInput() {
    $('.change-info').click(function () {
        $(this).hide().next().prop('required', 'true').show(500);
        $('#xb-user-info-save').show(500);
    });
//    $('#xb-user-info-save').click(function(e){
//    	editUserInfo();
//    	e.preventDefault();
//        e.stopPropagation();
//    })
}



$(document).ready(function () {
    switchTaskPublishAndToken();
    showUserInfoEditInput();
})