<%@include file="../header.jspf"%>
<div id="container">
	<div id="content">

		<dl class="curved">
			<dt>
				<big>Tratamentos Cadastrados no Sistema</big>
			</dt>
			<dd style="text-align: center;">
				<form action="novoTratamento1">
					<br>
					<div id="tabs-1" class="CSSTableGenerator">
						<table>
							<tr>
								<td>Tratamento</td>
								<td>Dias de <br />Atendimento
								</td>
								<td>Vagas</td>
								<td>Permite<br /> Overbooking
								</td>
								<td>Preparação</td>
								<td>Pós-tratamento</td>
								<td style="width: 70px; padding: 0px; margin: 0px;">Ações</td>
							</tr>

							<c:forEach items="${tratamentos}" var="i">
								<tr>
									<td>${i.nome} (${i.abreviacao})</td>
									<td><c:forEach items="${i.listaDias}" var="j">
										${j}<br />
										</c:forEach></td>

									<c:choose>
										<c:when test="${i.vagasIlimitadas}">
											<td>Ilimidado</td>
										</c:when>
										<c:otherwise>
											<td>${i.vagas}</td>
										</c:otherwise>
									</c:choose>


									<c:choose>
										<c:when test="${i.permitirOverbooking}">
											<td>Sim</td>
										</c:when>
										<c:otherwise>
											<td>Não</td>
										</c:otherwise>
									</c:choose>

									<td>${i.msgComprovante}</td>
									<td>${i.msgSenha}</td>
									<td><a
										href="/seara/allviews/removeTratamento?idTratamento=${i.id}">REMOVER</a><br />
										<a
										href="/seara/allviews/editaTratamento1?idTratamento=${i.id}">ALTERAR</a></td>

								</tr>
							</c:forEach>
						</table>
					</div>


					<input class="btn" type="submit" value="Cadastrar Novo Tratamento" />
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
