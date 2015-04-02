<%@include file="../header.jspf"%>
<div id="containerPequeno">
	<div id="content">
		<dl class="curved">
			<dt>
				<big>Iniciar Nova Sessão</big> </br>
			</dt>
			<dd style="text-align: center;">
				<center>
					<c:forEach items="${errors}" var="error">
						<b><font color="red">${error.message }</font></b>
					</c:forEach>
				</center>
				<form action="entrar" method="POST">
					<br />
					<div class="gambi">
						<table>
							<tr>
								<td><span style="display: inline-block; width: 30px;"></span>
									Usuário:</td>
								<td><input name="usuario.login" size="17" type="text" /></td>
							</tr>
							<tr>
								<td>Senha:</td>
								<td><input name="usuario.senha" size="17" type="password" /></td>
							</tr>
						</table>
					</div>
					<input class="btn" type="submit" value="Entrar" />
				</form>
				<p class="last">&nbsp;</p>
			</dd>
		</dl>
	</div>
</div>
</body>
</html>
