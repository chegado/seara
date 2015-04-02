function teste() {
	$(document).ready(function() {
		$('#userName').blur(function(event) {
			var name = $('#userName').val();
			$.get('allviews/jquery', {
				userName : name
			}, function(responseText) {
				$('#ajaxResponse').text(responseText);
			});
		});
	});
}
