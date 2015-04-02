<%@include file="../header.jspf"%>
<div id="containerMedio">
	<div id="content">

		<dl class="curved">
			<dt>
				<big>Operadores Cadastrados no Sistema</big>
			</dt>
			<dd style="text-align: center;">
				<form action="novoUsuario1">
					<br>
					<div id="tabs-1" class="CSSTableGenerator">
						<table>
							<tr>
								<td>Nome</td>
								<td>Usuário</td>
								<td>Tipo de Usuário</td>
								<td style="width: 70px;"></td>
							</tr>

							<c:forEach items="${usuarios}" var="i">
								<tr>
									<td>${i.nome}</td>
									<td>${i.login}</td>
									<td>${i.tipoUsuario.formatado}</td>
									<td><a
										href="/seara/allviews/novoUsuario1?action=remover&login=${i.login}">REMOVER</a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<input class="btn" type="submit" value="Cadastrar Novo Usuário" />
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
