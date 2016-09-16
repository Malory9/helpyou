function switchSignInAndSignUp() {
	$('.index-tab-navs > a').click(function () {
		$('.index-tab-navs > a').attr('class','');
		$(this).addClass('active');
		var state = $(this).attr('href').substring(1);
		$('.view').removeClass('selected').filter('.view-'+state).addClass('selected');
	})
}

function inputLimit(){
    //文本框中不能有空格
    $('input').keydown(function () {
        var inputContent = $(this).val().replace(/\s/g,'');
        $(this).val(inputContent);
    });
    $('input').change(function () {
        var inputContent = $(this).val().replace(/\s/g,'');
        $(this).val(inputContent);
    });
}

function signUpConfirm(){
    $('#signup-button').click(function(e){
        var $signUp = $('.view-signup');
        if(!$signUp.find('input[name=username]').val().match(/^[a-zA-z][a-zA-Z0-9]{2,11}$/g)){
            e.preventDefault();
            e.stopPropagation();
            $('.signUpErrorMsg').text('请按规定方式输入注册信息').animate({
                opacity:1
            },100);
        }
        if(!$signUp.find('input[name=password]').val().match(/^[a-zA-Z0-9]{6,12}$/g)){
            e.preventDefault();
            e.stopPropagation();
            $('.signUpErrorMsg').text('请按规定方式输入注册信息').animate({
                opacity:1
            },100);
        }
    })
    $('#login-button').click(function(e){
        var $signUp = $('.view-login');
        if(!$signUp.find('input[name=username]').val().match(/^[a-zA-z][a-zA-Z0-9]{2,11}$/g)){
            e.preventDefault();
            e.stopPropagation();
            $('.loginErrorMsg').text('登录格式不正确').animate({
                opacity:1
            },100);
        }
        if(!$signUp.find('input[name=password]').val().match(/^[a-zA-Z0-9]{6,12}$/g)){
            e.preventDefault();
            e.stopPropagation();
            $('.loginErrorMsg').text('登录格式不正确').animate({
                opacity:1
            },100);
        }
    })
}

function hideErrorMsg(){
    $('.view input').focus(function(){
        $('.errorMsg').animate({
            opacity:0
        },1000);
    });
}

$(document).ready(function () {
	switchSignInAndSignUp();
	hideErrorMsg();
    signUpConfirm();
})
