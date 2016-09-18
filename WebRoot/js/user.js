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


//显示修改信息框
function showUserInfoEditInput() {
    $('.change-info').click(function () {
    	$('.nicknameErrorMsg').hide();
        $(this).hide().next().prop('required', 'true').show(500);
        $('#xb-user-info-save').show(500);
    });
}

//显示发送留言窗口
function showMessageModal() {
	$('#xb-send-message').click(function () {
        var nickname = $('.xb-top-userinfo').text().trim();
        //未登录时跳转到登录页面
        if(nickname == "登陆"){
            window.location.href = "http://localhost:8080/helpyouJFinal/login";
            return;
        }
        var receiverNickname = $('.xb-user-info-nickname').text();
        $('#receiver').val(receiverNickname);
		$('.modal-bg').css('display','block').animate({opacity:0.5},300);
		$('.send-message-modal').css('display','block').animate({opacity:1,top:90},300);
	});
	$('#send-message-modal-close').click(function () {
        $('#receiver').val("");
        $('#messageContent').val("");
		$('.modal-bg').animate({opacity:0},300,function () {
			$(this).css('display','none');
		});
		$('.send-message-modal').animate({opacity:0,top:-350},300,function () {
			$(this).css('display','none');
		});
	});
	//发送留言验证
	$('#xb-message-send-btn').click(function (e) {
	    if ($('#receiver').val().trim() != '' && $('#messageContent').val().trim() != '') {
	    	sendMessage();
	    }else {
	    	$('.send-message-errorMsg').text("请正确填写留言内容!");
	    }
	    e.preventDefault();
	    e.stopPropagation();
	});
}

function sendMessage(){
  var data = {
	receiver: $('#receiver').val().trim(),
    messageContent: $('#messageContent').val().trim(),
    userId: $('.xb-top-userinfo').attr('href').replace('/helpyouJFinal/user/', '')
  };
  $.ajax({
    url: $('#send-message-form').attr('action'),
    type: 'post',
    data: data,
    success: function (result) {
      if (result == "success") {
        alert("消息发布成功!");
        //清除发布框内的内容
        $('.send-message-errorMsg').text('');
        $('#send-message-modal-close').trigger('click');
      } else if (result == "noTargetUser") {
        $('.send-message-errorMsg').text("没有对应的用户");
      } else if (result == "failed") {
        $('.send-message-errorMsg').text("发送失败，请重试");
      }
    }
  })
}


$(document).ready(function () {
    switchTaskPublishAndToken();
    showUserInfoEditInput();
    showMessageModal();
})