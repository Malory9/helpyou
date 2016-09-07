function showMessageModal() {
	$('#xb-send-message').click(function () {
		$('.modal-bg').css('display','block').animate({opacity:0.5},300);
		$('.send-message-modal').css('display','block').animate({opacity:1,top:90},300);
	})
	$('#send-message-modal-close').click(function () {
    $('#receiver').val("");
    $('#messageContent').val("");
		$('.modal-bg').animate({opacity:0},300,function () {
			$(this).css('display','none');
		});
		$('.send-message-modal').animate({opacity:0,top:-350},300,function () {
			$(this).css('display','none');
		});
	})
  $('.answer').click(function(e){
      var nickname = $(this).prevAll('.xb-message-sender-nickname').text();
      $('#receiver').val(nickname);
      $('#xb-send-message').trigger("click");
  })
}

$(document).ready(function () {
	showMessageModal();
})
