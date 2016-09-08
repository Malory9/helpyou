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
        var inputContent = $(this).val().trim().replaceAll(/\s/,'');
        $(this).val(inputContent);
    })
}

function signUpConfirm(){
    $('#sign-button').click(function(e){
        var $signUp = $('.view-signup');
        if(!$signUp.find('input[name=username]').val().match(/^[a-zA-z][a-zA-Z0-9]{2,11}$/)){
            e.preventDefault();
            e.stopPropagation();
        }
        if(!$signUp.find('input[name=password]').val().match(/^[a-zA-Z0-9]{6,12}$/)){
            e.preventDefault();
            e.stopPropagation();
        }
    })
}

function hideErrorMsg(){
    $('.view input').focus(function(){
        $('.errorMsg').animate({
            opacity:0
        },500,function(){
            $('.errorMsg').css('display','none');
        });
    });
}

$(document).ready(function () {
	switchSignInAndSignUp();
	hideErrorMsg();
    signUpConfirm();
    inputLimit();
})
