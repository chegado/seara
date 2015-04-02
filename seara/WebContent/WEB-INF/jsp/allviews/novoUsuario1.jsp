<%@include file="../header.jspf"%>
<div id="containerPequeno">
	<div id="content">

		<dl class="curved">
			<dt>
				<big>Cadastrar Novo Usuário</big> </br>
			</dt>
			<dd style="text-align: center;">

				<center>
					<c:forEach items="${errors}" var="error">
						<b><font color="red">${error.message }</font></b>
					</c:forEach>
				</center>

				<form action="novoUsuario2">
					<div class="gambi">
						<table>
							<tr>
								<td>Nome:</td>
								<td><input name="usuario.nome" size="20" type="text" /></td>
							</tr>
							<tr>
								<td>Tipo Usuário:</td>
								<td><select name="usuario.tipoUsuario">
										<option value="<%=TipoDoUsuario.Recepção%>"><%=TipoDoUsuario.Recepção%></option>
										<option value="<%=TipoDoUsuario.Triagem%>"><%=TipoDoUsuario.Triagem%></option>
										<option value="<%=TipoDoUsuario.Administrador%>"><%=TipoDoUsuario.Administrador%></option>
										<option value="<%=TipoDoUsuario.Auxiliar_Externo%>"><%=TipoDoUsuario.Auxiliar_Externo.getFormatado()%></option>
								</select></td>
							</tr>

							<tr>
								<td>Usuário:</td>
								<td><input name="usuario.login" size="20" type="text" /></td>
							</tr>
							<tr>
								<td>Senha:</td>
								<td><input name="usuario.senha" size="20" type="password" /></td>
							</tr>
							<tr>
								<td>Confirmar Senha:</td>
								<td><input name="senhaAgain" size="20" type="password" /></td>
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
