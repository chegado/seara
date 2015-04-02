<%@include file="../header.jspf"%>
<div id="containerGrande">
	<div id="content">

		<dl class="curved">
			<dt>
				<big>Cadastro de Assistidos e Trabalhadores</big> </br>
			</dt>
			<dd style="text-align: center;">
				<form action="novoAssistido2">
					<input type="hidden" name="assistido.id" value="${assistido.id}" />
					<div class="gambi">

						<center>
							<c:forEach items="${errors}" var="error">
								<b><font color="red">${error.message }</font></b>
							</c:forEach>
						</center>

						<table>
							<tr>
								<td>RG:</td>
								<td><input name="assistido.rg" value="${assistido.rg}"
									size="11" type="text" /> <input class="btn2" type="submit"
									name="action" value="Pesquisar RG" /></td>
								<td>Nome Completo:</td>
								<td><input name="assistido.nomeCompleto"
									value="${assistido.nomeCompleto}" size="40" type="text" /></td>
							</tr>

							<tr>
								<td>Telefone:</td>
								<td><input name="assistido.telefone"
									value="${assistido.telefone}" size="11" type="text" /></td>
								<td>Email:</td>
								<td><input name="assistido.email"
									value="${assistido.email}" size="40" type="text" /></td>
							</tr>

							<tr>
								<td colspan="4" style="text-align: center;">É trabalhador
									da casa? <input type="radio" name="assistido.ehTrabalhador"
									value="true"
									<c:if test="${assistido.ehTrabalhador}">checked</c:if>>Sim
									<input type="radio" name="assistido.ehTrabalhador"
									value="false"
									<c:if test="${!assistido.ehTrabalhador}">checked</c:if>>Não
									&nbsp;&nbsp;&nbsp; Data de Nascimento: <select name="diaNiver">
										<c:forEach var="i" begin="1" step="1" end="31">
											<c:if test="${i < 10}">
												<option <c:if test="${i == diaNiver}">selected</c:if>>0${i}</option>
											</c:if>
											<c:if test="${i >= 10}">
												<option <c:if test="${i == diaNiver}">selected</c:if>>${i}</option>
											</c:if>
										</c:forEach>
								</select> <select name="mesNiver">
										<option value="01"
											<c:if test="${\"01\".equals(mesNiver)}">selected</c:if>>Janeiro</option>
										<option value="02"
											<c:if test="${\"02\".equals(mesNiver)}">selected</c:if>>Fevereiro</option>
										<option value="03"
											<c:if test="${\"03\".equals(mesNiver)}">selected</c:if>>Março</option>
										<option value="04"
											<c:if test="${\"04\".equals(mesNiver)}">selected</c:if>>Abril</option>
										<option value="05"
											<c:if test="${\"05\".equals(mesNiver)}">selected</c:if>>Maio</option>
										<option value="06"
											<c:if test="${\"06\".equals(mesNiver)}">selected</c:if>>Junho</option>
										<option value="07"
											<c:if test="${\"07\".equals(mesNiver)}">selected</c:if>>Julho</option>
										<option value="08"
											<c:if test="${\"08\".equals(mesNiver)}">selected</c:if>>Agosto</option>
										<option value="09"
											<c:if test="${\"09\".equals(mesNiver)}">selected</c:if>>Setembro</option>
										<option value="10"
											<c:if test="${\"10\".equals(mesNiver)}">selected</c:if>>Outubro</option>
										<option value="11"
											<c:if test="${\"11\".equals(mesNiver)}">selected</c:if>>Novembro</option>
										<option value="12"
											<c:if test="${\"12\".equals(mesNiver)}">selected</c:if>>Dezembro</option>
								</select> <select name="anoNiver">
										<c:forEach var="i" begin="1915" step="1" end="2020">
											<option <c:if test="${i == anoNiver}">selected</c:if>>${i}</option>
										</c:forEach>
								</select>
								</td>

							</tr>
							<tr>
								<td>CEP:</td>
								<td colspan="2"><input size="11" name="assistido.cep"
									value="${assistido.cep}" type="text" /><span
									style="display: inline-block; width: 20px;"></span><input
									class="btn2" type="submit" name="action" value="Pesquisar CEP" /></td>
							</tr>

							<tr>
								<td>Logradouro:</td>
								<td colspan="3"><input size="40" type="text"
									name="assistido.logradouro" value="${assistido.logradouro}"></td>
							</tr>
							<tr>
								<td>Bairro:</td>
								<td><input type="text" name="assistido.bairro"
									value="${assistido.bairro}" /></td>
								<td>Cidade:</td>
								<td><input type="text" name="assistido.cidade"
									value="${assistido.cidade}" /> UF: <input size="2" type="text"
									name="assistido.estado" value="${assistido.estado}" /></td>
							</tr>

							<tr>
								<td>Número:</td>
								<td><input name="assistido.numero"
									value="${assistido.numero}" size="11" type="text" /></td>
								<td><span style="display: inline-block; width: 20px;"></span>Complemento:</td>
								<td><input name="assistido.complemento"
									value="${assistido.complemento}" size="20" type="text" /></td>
							</tr>
						</table>
					</div>

					<c:choose>
						<c:when test="${assistido.id eq null}">
							<input class="btn" type="submit" name="action"
								value="Cadastrar" />
						</c:when>
						<c:otherwise>
							<input class="btn" type="submit" name="action"
								value="Atualizar Cadastro Existente" />
						</c:otherwise>
					</c:choose>
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
