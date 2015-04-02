<%@include file="../header.jspf"%>
<div id="containerMedio">
	<div id="content">

		<dl class="curved">
			<dt>
				<big>Alterar Tratamento</big> </br>
			</dt>
			<dd style="text-align: center;">

				<center>
					<c:forEach items="${errors}" var="error">
						<b><font color="red">${error.message }</font></b>
					</c:forEach>
				</center>

				<form action="editaTratamento2">
					<input type="hidden" name="tratamento.id" value="${tratamento.id}" />
					<div class="gambi">
						<table>
							<tr>
								<td>Nome:</td>
								<td><input name="tratamento.nome" size="30" type="text"
									value="${tratamento.nome}" />&nbsp;&nbsp;&nbsp;Abreviação: <input
									name="tratamento.abreviacao" value="${tratamento.abreviacao}"
									size="8" type="text" /></td>
							</tr>

							<tr>
								<td>Dias de Atendimento:</td>
								<td><input type="checkbox" name="diasDaSemana" value="1"
									<c:if test="${tratamento.realizadoDom}">checked</c:if>>Dom
									<input type="checkbox" name="diasDaSemana" value="2"
									<c:if test="${tratamento.realizadoSeg}">checked</c:if>>Seg
									<input type="checkbox" name="diasDaSemana" value="3"
									<c:if test="${tratamento.realizadoTer}">checked</c:if>>Ter
									<input type="checkbox" name="diasDaSemana" value="4"
									<c:if test="${tratamento.realizadoQua}">checked</c:if>>Qua
									<input type="checkbox" name="diasDaSemana" value="5"
									<c:if test="${tratamento.realizadoQui}">checked</c:if>>Qui
									<input type="checkbox" name="diasDaSemana" value="6"
									<c:if test="${tratamento.realizadoSex}">checked</c:if>>Sex
									<input type="checkbox" name="diasDaSemana" value="7"
									<c:if test="${tratamento.realizadoSab}">checked</c:if>>Sáb</td>
							</tr>

							<tr>
								<td>Vagas:</td>
								<td><select name="tratamento.vagas">
										<option value="-1"
											<c:if test="${tratamento.vagas == -1}">selected</c:if>>Ilimitado</option>
										<c:forEach var="i" begin="1" end="100" step="1">
											<option <c:if test="${tratamento.vagas == i}">selected</c:if>>${i}</option>
										</c:forEach>
								</select>&nbsp;&nbsp;&nbsp;Permitir <i>Overbooking</i>: <select
									name="tratamento.permitirOverbooking">
										<option value="false"
											<c:if test="${!tratamento.permitirOverbooking}">selected</c:if>>Não</option>
										<option value="true"
											<c:if test="${tratamento.permitirOverbooking}">selected</c:if>>Sim</option>
								</select></td>
							</tr>
							<tr>
								<td>Preparação:</td>
								<td><textarea name="tratamento.msgComprovante"
										style="resize: none;" rows="4" cols="20">${tratamento.msgComprovante}</textarea></td>
							</tr>
							<tr>
								<td>Pós-tratamento:</td>
								<td><textarea name="tratamento.msgSenha"
										style="resize: none;" rows="4" cols="20">${tratamento.msgSenha}</textarea></td>
							</tr>

						</table>
					</div>

					<input class="btn" style="width: 95px;" type="submit" name="action"
						value="Voltar" /><span
						style="display: inline-block; width: 20px;"></span><input
						class="btn" type="submit" name="action" value="Alterar" />

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
