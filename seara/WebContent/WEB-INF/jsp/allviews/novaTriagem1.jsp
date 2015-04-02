<%@include file="../header.jspf"%>
<div id="container">


	<div id="content">
		<dl class="curved">
			<dt>
				<center>
					<table>
						<tr>
							<td>
								<form action="novaTriagem1">
									<input type="hidden" name="indiceTriagem"
										value="${indiceTriagem-1}"> <input type="image"
										src="../images/seta_esquerda.png"
										style="width: 20px; height: 20px; padding: 0px 0px 0px 0px;">
								</form>
							</td>
							<td><big>&nbsp;Ficha de Triagem (${indiceTriagem+1} de
									${totalTriagens})&nbsp;</big></td>
							<td>
								<form action="novaTriagem1">
									<input type="hidden" name="indiceTriagem"
										value="${indiceTriagem+1}"> <input type="image"
										src="../images/seta_direita.png"
										style="width: 20px; height: 20px; padding: 0px 0px 0px 0px;">
								</form>
							</td>
						</tr>
					</table>
				</center>
			</dt>
			<dd style="text-align: center;">
				<form action="novaTriagem2">
					<input type="hidden" name="triagem.id" value="${triagem.id}" />
					<div class="gambi">
						<table>
							<tr>
								<td style="text-align: center;" colspan=2><b>Assistido:</b>&nbsp;
									${assistidoSession.assistido.nomeCompleto}
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Atendente:</b>&nbsp;
									${triagem.nomeAtendente}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Data:&nbsp;
								</b> <fmt:formatDate value="${triagem.data.time}" type="date"
										pattern="dd/MM/yyyy" /></td>
							</tr>

							<c:if test="${indiceTriagem + 1 == totalTriagens}">
								<tr>
									<td><b>Tipo de problema:</b>&nbsp;</td>
									<td><input type="checkbox" name="triagem.ehEspiritual"
										<c:if test="${triagem.ehEspiritual}">checked</c:if>>Espiritual

										<input type="checkbox" name="triagem.ehMaterial"
										<c:if test="${triagem.ehMaterial}">checked</c:if>>Material

										<input type="checkbox" name="triagem.ehFisico"
										<c:if test="${triagem.ehFisico}">checked</c:if>>Físico

										<input type="checkbox" name="triagem.ehFamiliar"
										<c:if test="${triagem.ehFamiliar}">checked</c:if>>Familiar

										<input type="checkbox" name="triagem.ehConjugal"
										<c:if test="${triagem.ehConjugal}">checked</c:if>>Conjugal

										<input type="checkbox" name="triagem.ehPsicologico"
										<c:if test="${triagem.ehPsicologico}">checked</c:if>>Psicológico

										<input type="checkbox" name="triagem.ehMediunidade"
										<c:if test="${triagem.ehMediunidade}">checked</c:if>>Mediunidade

									</td>
								</tr>

								<tr>
									<td><b>Gravidade:</b>&nbsp;</td>
									<td>${triagem.showGravidade}
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <b>Feed back:</b>&nbsp;
										${triagem.showFeedBack}
									</td>
								</tr>
								<tr>
									<td><b>Descrição:</b>&nbsp;</td>
									<td><textarea name="triagem.descricao"
											style="resize: none;" rows="8" cols="64">${triagem.descricao}</textarea></td>
								</tr>

								<tr>
									<td><b>Tratamentos:</b>&nbsp;</td>
									<td><c:forEach items="${tratamentos}" var="i">
											<input title="${i.nome}" type="checkbox"
												name="tratamentosAgendados" value="${i.id}"
												<c:if test="${triagem.isTratamentoAgendado(i.id)}">checked</c:if>>${i.abreviacao}
								</c:forEach></td>
								</tr>
							</c:if>


							<c:if test="${indiceTriagem + 1 != totalTriagens}">
								<tr>
									<td><b>Tipo de problema:</b>&nbsp;</td>
									<td><c:if test="${triagem.ehEspiritual}">
											Espiritual;&nbsp;
									</c:if> <c:if test="${triagem.ehMaterial}">Material;&nbsp;</c:if> <c:if
											test="${triagem.ehFisico}">Físico;&nbsp;</c:if> <c:if
											test="${triagem.ehFamiliar}">Familiar;&nbsp;</c:if> <c:if
											test="${triagem.ehConjugal}">Conjugal;&nbsp;</c:if> <c:if
											test="${triagem.ehPsicologico}">Psicológico;&nbsp;</c:if> <c:if
											test="${triagem.ehMediunidade}">Mediunidade</c:if></td>
								</tr>

								<tr>
									<td><b>Gravidade:</b>&nbsp;</td>
									<td>${triagem.gravidade}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Feed
											back:</b>&nbsp; ${triagem.status.formatado}
									</td>
								</tr>
								<tr>
									<td><b>Descrição:</b>&nbsp;</td>
									<td><textarea readonly name="triagem.descricao"
											style="resize: none;" rows="8" cols="64">${triagem.descricao}</textarea></td>
								</tr>


								<tr>
									<td><b>Tratamentos:</b>&nbsp;</td>
									<td><c:set var="flag" value="true" /> <c:forEach
											items="${tratamentos}" var="i">
											<c:if test="${triagem.isTratamentoAgendado(i.id)}">${i.nome};&nbsp; <c:set
													var="flag" value="false" />
											</c:if>
										</c:forEach> <c:if test="${flag}">Nenhum tratamento agendado nesta triagem</c:if>
									</td>
								</tr>
							</c:if>

						</table>
					</div>

					<c:if test="${indiceTriagem + 1 == totalTriagens}">
						<input class="btn" type="submit" name="action" value="Salvar" />
					</c:if>
					<p class="last">&nbsp;</p>
				</form>
			</dd>


		</dl>
	</div>

</div>
<br />
<br />
</body>
</html>
