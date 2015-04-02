
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1">
<style>
body {
	font: 80% "Lucida Sans Unicode", "Lucida Grande", sans-serif;
	color: #ff8800;
	background: #059a68;
}

nomeTratamento {
	margin: 0px 0px 0px 0px;
	text-align: center;
	font-size: 700%;
	line-height: 100%;
}

numeroGrande {
	margin: 0px 0px 0px 0px;
	text-align: center;
	font-size: 3700%;
	line-height: 80%;
}

numeroPequeno {
	margin: 0px 0px 0px 0px;
	text-align: center;
	font-size: 2700%;
	line-height: 80%;
	letter-spacing: -20px;
}

ao {
	margin: 0px 0px 0px 0px;
	text-align: center;
	font-size: 600%;
}

</style>

<link rel="shortcut icon" href="images/pomba_da_paz.png">

<!-- SCRIPT PARA ATUALIZAR O PAINEL VIA AJAX -->
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
<!--  -->

<!-- SCRIPT PARA PISCAR -->
<script type="text/javascript">
	function blink() {
		var blinks = document.getElementsByTagName('blink');
		for (var i = blinks.length - 1; i >= 0; i--) {
			var s = blinks[i];
			s.style.visibility = (s.style.visibility === 'visible') ? 'hidden'
					: 'visible';
		}

		if (s.style.visibility == 'visible') {
			window.setTimeout(blink, 1000);
		} else {
			window.setTimeout(blink, 300);
		}
	}

	if (document.addEventListener)
		document.addEventListener("DOMContentLoaded", blink, false);
	else if (window.addEventListener)
		window.addEventListener("load", blink, false);
	else if (window.attachEvent)
		window.attachEvent("onload", blink);
	else
		window.onload = blink;
</script>

</head>
<body>
	<blink id="ajaxResponse">
	</blink>
</body>
</html>