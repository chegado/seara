<%@include file="../header.jspf"%>
<div id="containerMedio">
	<div id="content">
		<dl class="curved">
			<dt>
				<big>Digite os dados do assistido</big>
			</dt>
			<dd style="text-align: center; margin-top: 20px;">
				<center>
					<c:forEach items="${errors}" var="error">
						<b><font color="red">${error.message }</font></b>
					</c:forEach>
				</center>

				<form action="loginAssistido2">
					<input type="hidden" name="origemRequest" value="${origemRequest}">
					<div class="gambi">
						<table>
							<tr>
								<td>RG:</td>
								<td><input name="rg" size="17" type="text" value="${rg}" /></td>
							</tr>

							<tr>
								<td>Nome Completo:</td>
								<td><input name="nomeCompleto" size="40" type="text"
									value="${nomeCompleto}" /></td>
							</tr>
						</table>
					</div>
					<input class="btn" type="submit" name="action"
						value="Cadastrar Novo Assistido" /> <input class="btn"
						type="submit" name="action" value="Iniciar Atendimento" />
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
