<%@include file="../header.jspf"%>
<div id="containerMedio">
	<div id="content">

		<dl class="curved">
			<dt>
				<big>Marcar Data Para Tratamento</big>
			</dt>
			<dd style="text-align: center;">
				<form action="marcarDataAgendamento2">
					<input type="hidden" name="idAgendamento" value="${agendamento.id}" />

					<div class="gambi">
						<table>
							<tr>
								<td>Tratamento:&nbsp;</td>
								<td><b>${tratamento.nome}</b></td>
							</tr>
							<tr>
								<td>Assistido:&nbsp;</td>
								<td><b>${assistidoSession.assistido.nomeCompleto}</b></td>
							</tr>
							<tr>
								<td>Data:</td>
								<td><select name="dataAtendimento">
										<c:forEach items="${datasDisponiveis}" var="i">
											<option value="${i.data}">${i}</option>
										</c:forEach>
								</select></td>
							</tr>
						</table>
					</div>
					<input class="btn" style="width: 85px;" type="submit" name="action"
						value="Voltar" /><span
						style="display: inline-block; width: 20px;"></span><input
						class="btn" type="submit" name="action" value="Agendar" />
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
