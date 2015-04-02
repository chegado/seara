<%@include file="../header.jspf"%>
<div id="containerMedio">
	<div id="content">

		<dl class="curved">
			<dt>
				<big>Cadastrar Novo Tratamento</big> </br>
			</dt>
			<dd style="text-align: center;">

				<center>
					<c:forEach items="${errors}" var="error">
						<b><font color="red">${error.message }</font></b>
					</c:forEach>
				</center>

				<form action="novoTratamento2">
					<div class="gambi">
						<table>
							<tr>
								<td>Nome:</td>
								<td><input name="tratamento.nome" size="30" type="text" />
									&nbsp;&nbsp;&nbsp;Abreviação: <input
									name="tratamento.abreviacao" size="8" type="text" /></td>
							</tr>
							<tr>
								<td>Dias de Atendimento:</td>
								<td><input type="checkbox" name="diasDaSemana" value="1">Dom<input
									type="checkbox" name="diasDaSemana" value="2">Seg <input
									type="checkbox" name="diasDaSemana" value="3">Ter <input
									type="checkbox" name="diasDaSemana" value="4">Qua <input
									type="checkbox" name="diasDaSemana" value="5">Qui <input
									type="checkbox" name="diasDaSemana" value="6">Sex <input
									type="checkbox" name="diasDaSemana" value="7">Sáb</td>
							</tr>

							<tr>
								<td>Vagas:</td>
								<td><select name="tratamento.vagas">
										<option value="-1">Ilimitado</option>
										<c:forEach var="i" begin="1" end="100" step="1">
											<option>${i}</option>
										</c:forEach>
								</select>&nbsp;&nbsp;&nbsp;Permitir <i>Overbooking</i>: <select
									name="tratamento.permitirOverbooking">
										<option value="false">Não</option>
										<option value="true">Sim</option>
								</select></td>
							</tr>
							<tr>
								<td>Preparação:</td>
								<td><textarea name="tratamento.msgComprovante"
										style="resize: none;" rows="4" cols="20"></textarea></td>
							</tr>
							<tr>
								<td>Pós-tratamento:</td>
								<td><textarea name="tratamento.msgSenha"
										style="resize: none;" rows="4" cols="20"></textarea></td>
							</tr>

						</table>
					</div>

					<input class="btn" style="width: 95px;" type="submit" name="action"
						value="Voltar" /><span
						style="display: inline-block; width: 20px;"></span><input
						class="btn" type="submit" name="action" value="Cadastrar" />

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
