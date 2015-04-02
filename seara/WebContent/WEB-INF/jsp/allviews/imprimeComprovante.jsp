<%@include file="../header.jspf"%>
<div id="containerPequeno">
	<div id="content">
		<dl class="curved">
			<dt>
				<big>Emiss�o de Comprovante</big> </br>
			</dt>
			<dd style="text-align: center;">
				<br />
				<form action="imprimir">
					<div
						style="margin-left: auto; margin-right: auto; width: 355px; background-color: white">
						<p style="text-align: left; font-family: Courier New", Courier, monospace;">
							<b>LAR SEARA ESPERAN�A IRM� MATILDE</b> <br /> <br />
							Tratamento: ${agendamento.nomeTratamento}<br /> Data:
							<fmt:formatDate value="${agendamento.dataAtendimento.time}"
								type="date" pattern="dd/MM/yyyy" />
							(${agendamento.diaDaSemana})<br /> Assistido:
							${assistidoSession.assistido.nomeCompleto}<br />
							<br /> --- INSTRU��ES --- <br />${tratamento.msgComprovante}
						</p>
					</div>
					<input class="btn" type="submit" value="Imprimir" />
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
