<%@include file="../header.jspf"%>
<div id="containerMedio">
	<div id="content">

		<dl class="curved">
			<dt>
				<big>Agendar Tratamento</big>
			</dt>
			<dd style="text-align: center;">
				<form action="novoAgendamento2">
					<input type="hidden" name="agendamento.idTratamento"
						value="${tratamento.id}" /> <input type="hidden"
						name="agendamento.nomeTratamento" value="${tratamento.nome}" />

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
								<td>Número de Sessões:</td>
								<td><select name="nSessoes">
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
										<option value="13">13</option>
										<option value="14">14</option>
										<option value="15">15</option>
										<option value="16">16</option>
										<option value="17">17</option>
										<option value="18">18</option>
										<option value="19">19</option>
										<option value="20">20</option>
								</select></td>
							</tr>
							<tr>
								<td>Data da 1ª Sessão:</td>
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
