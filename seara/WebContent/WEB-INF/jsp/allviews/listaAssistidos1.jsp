<%@include file="../header.jspf"%>
<div id="containerGrande">
	<div id="content">

		<dl class="curved">
			<dt>
				<big>Assistidos Cadastrados no Sistema</big>
			</dt>
			<dd style="text-align: center;">
				<form action="listaAssistidos1">
					<br>

					<div class="gambi">
						<table>
							<tr>
								<td>Parte do nome:</td>
								<td><input name="parteDoNome" size=30
									value="${parteDoNome}"></td>
								<td>É trabalhador da casa?</td>
								<td><input type="radio" name="ehTrabalhador" value="sim"
									<c:if test="${ehTrabalhador eq 'sim'}">checked</c:if>>Sim
									<input type="radio" name="ehTrabalhador" value="nao"
									<c:if test="${ehTrabalhador eq 'nao'}">checked</c:if>>Não
									<input type="radio" name="ehTrabalhador" value="tanto faz"
									<c:if test="${ehTrabalhador eq 'tanto faz'}">checked</c:if>>Tanto
									faz</td>
							</tr>
							<tr>
								<td colspan=4 style="text-align: center;">Tem agendamento
									de: <select name="idTratamento">
										<option value="-1"></option>
										<c:forEach var="t" items="${tratamentos}">
											<option value="${t.id}"
												<c:if test="${idTratamento eq t.id}">selected</c:if>>${t.nome}</option>
										</c:forEach>
								</select>&nbsp;&nbsp; na data: <input type="text" size=8 id="data"
									name="data" value="${data}"></input>
								</td>
							</tr>
							<tr>
								<td colspan=4 style="text-align: center;"><input
									class="btn" type="submit" name="action" value="Limpar" /> <input
									class="btn" type="submit" name="action" value="Buscar" />
							</tr>
						</table>
					</div>
					<br>
					<script>
						configDatePick("#data");
					</script>

					<script>
						$(function() {
							$("#data").datepicker();
						});
					</script>


					<c:choose>
						<c:when test="${empty assistidos}">
							<p class="fonteTelaMensagem">Nenhum assistido ou trabalhador
								encontrado.</p>
						</c:when>
						<c:otherwise>

							<div class="CSSTableGenerator">
								<table id="tb1">
									<tr>
										<td>Nome Completo</td>
										<td>RG</td>
										<td>Data de Nascimento</td>
										<td>Email</td>
										<td>Telefone</td>
										<td>É Trabalhador?</td>

									</tr>

									<c:forEach items="${assistidos}" var="i">
										<tr>
											<td
												title="Endereço: ${i.logradouro}, ${i.numero} (${i.complemento}), ${i.bairro}, CEP: ${i.cep}">${i.nomeCompleto}</td>
											<td>${i.rg}</td>
											<td><fmt:formatDate value="${i.dataNascimento.time}"
													type="date" pattern="dd/MM/yyyy" /></td>
											<td>${i.email}</td>
											<td>${i.telefone}</td>
											<td><c:if test="${i.ehTrabalhador}">Sim</c:if> <c:if
													test="${!i.ehTrabalhador}">Não</c:if></td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</c:otherwise>
					</c:choose>


				</form>

				<br>
				<!-- div onde será criados os links da paginação -->
				<div id="pageNav"></div>
				<script>
					var numElemPerPage = 8;
					var pager = new Pager('tb1', numElemPerPage);
					pager.init();
					pager.showPageNav('pager', 'pageNav');
					pager.showPage(1);
				</script>

				<p class="last">&nbsp;</p>


			</dd>
		</dl>
	</div>
</div>
<br />
<br />
</body>
</html>
