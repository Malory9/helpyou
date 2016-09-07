function switchSignInAndSignUp() {
	$('.index-tab-navs > a').click(function () {
		$('.index-tab-navs > a').attr('class','');
		$(this).addClass('active');
		var state = $(this).attr('href').substring(1);
		$('.view').removeClass('selected').filter('.view-'+state).addClass('selected');
	})
}

function hideErrorMsg(){
    $('.view-signin input').focus(function(){
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
})
