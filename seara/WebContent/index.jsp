<html>
<head>
<title>AJAX in java web application using jQuery</title>
<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script>
	jQuery(document).ready(setAutoRefreshInSeconds(3));

	function readResponse(responseText) {
		jQuery('#ajaxResponse').html(responseText);
	}

	function sendRequest() {
		var name = jQuery('#userName').val();
		jQuery.get('allviews/atualizaPainel', {
			userName : name
		}, readResponse);
	}

	function setAutoRefreshInSeconds(seconds) {
		setInterval(sendRequest, seconds * 1000);
	}
</script>


</head>
<body>


	<form>
		<input type="hidden" id="userName" />
	</form>
	<div
		style="display: table; height: 100%; width: 100%; overflow: hidden;">
		<div id="ajaxResponse"
			style="display: table-cell; vertical-align: middle; text-align: center; padding: 0 0 0 0; height: 100%; width: 100%; background: #000; color: #fff; font: 180px arial, sans-serif;">
		</div>
	</div>

</body>
</html>