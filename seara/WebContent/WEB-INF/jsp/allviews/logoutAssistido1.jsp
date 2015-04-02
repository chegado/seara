<%@include file="../header.jspf"%>
<div id="containerPequeno">
	<div id="content">
		<dl class="curved">
			<dt>
				<big>Importante</big> <br>
			</dt>
			<dd style="text-align: center;">
				<br />

					<div class="gambi">
						<p class="fonteTelaMensagem">
							Deseja finalizar o atendimento?
						</p>
					</div>
					<center>
						<table>
							<tr>
								<td>
									<form action="${urlNao}">
										<input type="hidden" name="origemRequest"
											value="${origemRequest}"> <input class="btn"
											type="submit" value="Não" />
									</form>
								</td>
								<td>
									<form action="logoutAssistido2">
										<input type="hidden" name="origemRequest"
											value="${origemRequest}"> <input class="btn"
											type="submit" value="Sim" />
									</form>
								</td>
							</tr>
						</table>
					</center>
				<p class="last">&nbsp;</p>
			</dd>
		</dl>
	</div>
</div>
<br />
<br />
</body>
</html>
