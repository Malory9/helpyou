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
	$('.xb-backtotop').css('left',backleft);
}

function allBinding() {

	//当页面大小改变时，重新定位顶部按钮
	$(window).resize(function () {
		backiconReput();
	});

	//返回顶部按钮的出现和消失
	$(window).scroll(function(){
		if ($(window).scrollTop()>300){
			$("#xb-backtotop").fadeIn(500);
		} else {
			$("#xb-backtotop").fadeOut(500);
		}
	});

	//返回顶部按钮的功能实现
	$('#xb-backtotop').click(function () {
		$('body,html').animate({scrollTop:0},500);
	});

	//发布任务框的出现
	$('#xb-top-add-task').click(function () {
		$('.modal-bg').css('display','block').animate({opacity:0.5},300);
		$('.add-task-modal').css('display','block').animate({opacity:1,top:90},300);
	});

	//发布任务框关闭
	$('#task-publish-modal-close').click(function () {
		$('.modal-bg').animate({opacity:0},300,function () {
			$(this).css('display','none');
		});
		$('.add-task-modal').animate({opacity:0,top:-550},300,function () {
			$(this).css('display','none');
		});
	});

	//消息框显示与否
	if ($('.notice-number').text() == 0) {
		$('.notice-number').css('display','none');
	}
	$('.notice-number').click(function () {
		$(this).css('display','none');
	})
	
	$('#xb-add-task').click(function(e) {
	 	addTask();
	 	e.preventDefault();
	})
}

//使数字框只能输入数字
function numberOnly(){
    $('input[type=number]').keydown(function(){
        $(this).val().replace(/\D/,'');
    })
}

//添加任务
function addTask() {
    var data = {
            taskTitle:$('#taskTitle').val(),
            taskType:$('#taskType option:selected').val(),
            peopleNum:$('#peopleNum').val(),
            days:$('#taskTime-day').val(),
            hours:$('#taskTime-hour').val(),
            minutes:$('#taskTime-minute').val(),
            reward:$('#taskReward').val(),
            content:$('#taskContent').val()
        };
	$.ajax({
 		url: $('.add-task-form').attr('action'),
        type: 'post',
        data: data,
        success:function(result){
            
        }
 	})
}

$(document).ready(function () {
	backiconReput();
	allBinding();
    numberOnly();
})