<%@include file="../header.jspf"%>
<div id="containerGrande">
	<div id="content">
		<dl class="curved">
			<dt>
				<big>Fila de Espera</big> <br>
			</dt>
			<dd style="text-align: center;">
				<br />
				<div class="CSSTableGenerator">
					<table>
						<tr>
							<td style="font-size: 130%;">Tratamento</td>
							<td style="font-size: 130%;">Última da Fila</td>
							<td style="font-size: 130%;">Última Senha Chamada</td>
							<td></td>
						</tr>
						<c:forEach var="i" items="${estatisticasSenhas}">
							<tr>
								<td style="font-size: 130%;">${i.nomeTratamento}</td>
								<td style="font-size: 130%; text-align: center;">${i.ultimaSenhaEmitida}</td>
								<td style="font-size: 130%; text-align: center;">${i.ultimaSenhaChamada}</td>
								<td>
									<form action="chamarProximo">
										<input type="hidden" name="idTratamento"
											value="${i.idTratamento}"> <select name="qtdSenhas">
											<c:forEach var="j" begin="0" end="19" step="1">
												<c:set var="gambi" value="${20-j}" />
												<option value="${-gambi}">${-gambi}</option>
											</c:forEach>

											<option value="0" selected>0</option>
											<c:forEach var="j" begin="1" end="20" step="1">
												<option value="${j}">${j}</option>
											</c:forEach>
										</select> <input class="btn2" type="submit" name="action"
											value="Chamar" />
									</form>
								</td>
							</tr>

						</c:forEach>

					</table>
				</div>
				<p class="last">&nbsp;</p>
			</dd>
		</dl>
	</div>
</div>
<br />
<br />
</body>
</html>
