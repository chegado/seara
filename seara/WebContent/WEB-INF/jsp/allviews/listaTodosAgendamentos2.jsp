<%@include file="../header.jspf"%>
<div id="containerMedio">
	<div id="content">

		<dl class="curved">
			<dt>
				<big>Relatório de Agendamentos</big>
			</dt>
			<dd style="text-align: center;">
				<form action="listaTodosAgendamentos1">
					<div class="gambicenter">
						<table>
							<tr>
								<td colspan=4>Tratamentos agendados para o dia ${data}:</td>
							</tr>
						</table>
					</div>

					<div id="tabs-1" class="CSSTableGenerator">
						<table>
							<tr>
								<td>Tratamento</td>
								<td width="1px">Vagas</td>
								<td width="1px">Agendamentos</td>
							</tr>

							<c:forEach items="${itens}" var="i">
								<tr>
									<td>${i.nomeTratamento}</td>
									<td>${i.vagas}</td>
									<td>${i.qtdAgendamentos}</td>
								</tr>
							</c:forEach>
						</table>
					</div>


					<input class="btn" type="submit" value="Voltar" />
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
