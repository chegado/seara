<%@include file="../header.jspf"%>
<div id="container">
	<div id="content">

		<dl class="curved">
			<dt>
				<big>Tratamentos Agendados Para <b>${assistidoSession.assistido.nomeCompleto}</b></big>
			</dt>
			<dd style="text-align: center;">
				<br>
				<script type="text/javascript">
					$(function() {
						$("#tabs").tabs();
					});
				</script>

				<div id="tabs" class="tabs">
					<ul>
						<li><a href="#tabs-1">Agendadas (${agendadas.size()})</a></li>
						<li><a href="#tabs-2">Pendentes (${pendentes.size()})</a></li>
						<li><a href="#tabs-3">Finalizadas (${finalizadas.size()})</a></li>
					</ul>

					<div id="tabs-1" class="CSSTableGenerator">
						<table>
							<tr>
								<td>Tratamento</td>
								<td>Data Atendimento</td>
								<td>Data Agendamento</td>
								<td>Senha de Espera</td>
								<td style="width: 180px;">Ações</td>
							</tr>

							<c:forEach items="${agendadas}" var="i">
								<tr>
									<td>${i.nomeTratamento}</td>
									<td style="text-align: center;">${i.dataAtendimentoFormatada}</td>
									<td style="text-align: center;"><fmt:formatDate
											value="${i.dataAgendamento.time}" type="date"
											pattern="dd/MM/yyyy" /></td>
									<td style="text-align: center;">${i.senha}</td>
									<td><c:forEach items="${i.listaAcoes}" var="j">
												${j}
												<br />
										</c:forEach></td>

								</tr>
							</c:forEach>
						</table>
					</div>

					<div id="tabs-2" class="CSSTableGenerator">
						<table>
							<tr>
								<td>Tratamento</td>
								<td>Data Agendamento</td>
								<td>Pendência</td>
								<td style="width: 180px;">Ações</td>
							</tr>

							<c:forEach items="${pendentes}" var="i">
								<tr>
									<td>${i.nomeTratamento}</td>
									<td style="text-align: center;"><fmt:formatDate
											value="${i.dataAgendamento.time}" type="date"
											pattern="dd/MM/yyyy" /></td>
									<td style="text-align: center;">${i.pendencia}</td>
									<td><c:forEach items="${i.listaAcoes}" var="j">
												${j}
												<br />
										</c:forEach></td>

								</tr>
							</c:forEach>

						</table>
					</div>

					<div id="tabs-3" class="CSSTableGenerator">
						<table>
							<tr>
								<td>Tratamento</td>
								<td>Data Atendimento</td>
								<td>Data Agendamento</td>
							</tr>

							<c:forEach items="${finalizadas}" var="i">
								<tr>
									<td>${i.nomeTratamento}</td>
									<td><fmt:formatDate value="${i.dataAtendimento.time}"
											type="date" pattern="dd/MM/yyyy" /> (${i.diaDaSemana})</td>
									<td><fmt:formatDate value="${i.dataAgendamento.time}"
											type="date" pattern="dd/MM/yyyy" /></td>

								</tr>
							</c:forEach>

						</table>
					</div>

				</div>
				<br /> <br />

				<form action="novoAgendamento1">
					<div class="gambi">
						<table>
							<tr>
								<td>Novo Tratamento:</td>
								<td><select name="idTratamento">
										<c:forEach var="t" items="${tratamentos}">
											<option value="${t.id}">${t.nome}</option>
										</c:forEach>
								</select></td>
							</tr>
						</table>
					</div>

					<input class="btn" type="submit" name="action"
						value="Agendar Novo Tratamento" />
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
