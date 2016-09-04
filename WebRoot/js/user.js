function switchTaskPublishAndToken() {
	$('.xb-user-task-item').click(function () {
		$('.xb-user-task-item').removeClass('active');
		$(this).addClass('active');
		var state = $(this).attr('href').substring(1);
		$('.xb-user-task-list').removeClass('selected').filter('.task-'+state).addClass('selected');
	})
}

function showUserInfoEditInput() {
	$('.change-info').click(function () {
		$(this).hide().next('input').prop('required','true').show();
		$('#xb-user-info-save').show();
	})
}

$(document).ready(function () {
	switchTaskPublishAndToken();
	showUserInfoEditInput();
})

