function messageBinding() {
	//打开留言框
	$('#xb-send-message').click(function () {
		$('.modal-bg').css('display','block').animate({opacity:0.5},300);
		$('.send-message-modal').css('display','block').animate({opacity:1,top:90},300);
	});
	//关闭留言框
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
	//回复用户留言
	$('.answer').click(function(e){
        var nickname = $(this).prevAll('.xb-message-sender-nickname').text();
        $('#receiver').val(nickname);
        $('#xb-send-message').trigger("click");
	});
	//发送留言验证
	$('#xb-message-send-btn').click(function (e) {
	    if ($('#receiver').val().trim() != '' && $('#messageContent').val().trim() != '') {
	    	sendMessage();
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


function switchMessageAndNotice() {
    var $message = $('.xb-message');
    var $notice = $('.xb-notice');
	$('.xb-message-notice-btn').click(function (e) {
		$('.xb-message-notice-btn').removeClass('active');
		$(this).addClass('active');
		var which = $(this).attr('href').substring(1);
		$message.removeClass('selected');
        $notice.removeClass('selected');
        $('.'+which).addClass('selected');
        e.preventDefault();
        e.stopPropagation();
	})
}

$(document).ready(function () {
	messageBinding();
    switchMessageAndNotice();
})
