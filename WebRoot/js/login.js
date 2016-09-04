function switchSignInAndSignUp() {
	$('.index-tab-navs > a').click(function () {
		$('.index-tab-navs > a').attr('class','');
		$(this).addClass('active');
		var state = $(this).attr('href').substring(1);
		$('.view').removeClass('selected').filter('.view-'+state).addClass('selected');
	})
}


$(document).ready(function () {
	switchSignInAndSignUp();
})
