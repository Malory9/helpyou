function takeTask(){
    $('.xb-task-take-btn').click(function(){
        if(confirm("是否确认接收该任务?")) {
            var json = {
                userId:$('.xb-top-userinfo').attr('href').substring(8),
                taskId:$('.task-id').text(),
                acceptTime:new Date()
            }
            $.ajax({
                url:'/takeTask',
                data:JSON.stringify(json),
                success:function(data){
                    if(data == 1) {
                        alert("接受成功!");
                        $('.xb-task-take-btn').hide();
                    }
                }
            })
        }
    })
}

$(document).ready(function(){
    takeTask();
})