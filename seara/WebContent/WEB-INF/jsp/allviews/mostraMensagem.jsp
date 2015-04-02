<%@include file="../header.jspf"%>
<div id="containerPequeno">
	<div id="content">
		<dl class="curved">
			<dt>
				<big>Mensagem</big> <br>
			</dt>
			<dd style="text-align: center;">
				<br />
				<div class="gambi">
					<p class="fonteTelaMensagem">${mensagem}</p>
				</div>
				<form action="${urlRetorno}">
					<input class="btn" type="submit" value="OK" />
				</form>

				<p class="last">&nbsp;</p>
			</dd>
		</dl>
	</div>
</div>
<br />
<br />
</body>
</html>
