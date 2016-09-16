function noticeValidate() {
  //发布公告验证
  $('#send-notice-btn').click(function (e) {
    if ($('#notice-title').val().trim() != '' && $('#notice-content').val().trim() != '') {
    	sendNotice();
    }
    e.preventDefault();
    e.stopPropagation();
  });
}

//发布公告
function sendNotice() {
  var data = {
    title: $('#notice-title').val().trim(),
    content: $('#notice-content').val().trim()
  };
  $.ajax({
    url: $('#send-notice-form').attr('action'),
    type: 'post',
    data: data,
    success: function (result) {
      if (result == "success") {
        alert("公告发布成功!");
        //清除发布框内的内容
        $('#send-notice-form').children('input,textarea').val('');
        $('#send-notice-modal-close').trigger('click');
      } else if (result == "failed") {
        alert("公告发布失败");
      }
    }
  })
}

//处理任务
function dealTask() {
  $('.recover-task-btn').click(function () {
    var url = $(this).data('url');
    var taskId = $(this).parent().prevAll('.taskId').text();
    var $tr = $(this).parent().parent();
    $.ajax({
      url: url,
      type: 'post',
      data: {
        taskId: taskId
      },
      success: function (result) {
        if (result == 'success') {
          $tr.remove();
        }
      }
    })
  });

  $('.delete-task-btn').click(function () {
    var url = $(this).data('url');
    var taskId = $(this).parent().prevAll('.taskId').text();
    var $tr = $(this).parent().parent();
    $.ajax({
      url: url,
      type: 'post',
      data: {
        taskId: taskId
      },
      success: function (result) {
        if (result == 'success') {
          $tr.remove();
        }
      }
    })
  });

}


$(document).ready(function () {
  noticeValidate();
  dealTask();
})