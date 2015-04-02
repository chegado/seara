<%@include file="../header.jspf"%>
<div id="containerPequeno">
	<div id="content">
		<dl class="curved">
			<dt>
				<big>Gerara��o de Relat�rio de Agendamentos</big>
			</dt>
			<dd style="text-align: center;">
				<center>
					<c:forEach items="${errors}" var="error">
						<b><font color="red">${error.message }</font></b>
					</c:forEach>
				</center>

				<form action="listaTodosAgendamentos2">
					<br />
					<div class="gambi">
						<table>
							<tr>
								<td>Escolha Uma Data:</td>
								<td><input type="text" id="data" size=8 name="data"></td>
							</tr>

						</table>
					</div>
					<script>
						configDatePick("#data");
					</script>

					<script>
						$(function() {
							$("#data").datepicker();
						});
					</script>

					<input class="btn" type="submit"
						value="Ver Relat�rio de Agendamentos" />
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
