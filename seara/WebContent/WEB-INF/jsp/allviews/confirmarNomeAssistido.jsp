<%@include file="../header.jspf"%>
<div id="containerPequeno">
	<div id="content">
		<dl class="curved">
			<dt>
				<big>Importante</big> <br>
			</dt>
			<dd style="text-align: center;">
				<br />

				<c:if test="${assistidoSession.assistido == null}">
					<div class="gambi">
						<p class="fonteTelaMensagem">Favor digitar primeiro os dados do assistido.</p>
					</div>
					<form action="loginAssistido1">
						<input type="hidden" name="origemRequest" value="${origemRequest}">
						<input class="btn" type="submit" value="OK" />
					</form>
				</c:if>

				<c:if test="${assistidoSession.assistido != null}">
					<div class="gambi">
						<p class="fonteTelaMensagem">
							Favor confirmar o nome do assistido:<br> <b>${assistidoSession.assistido.nomeCompleto}</b>
						</p>
					</div>
					<center>
						<table>
							<tr>
								<td>
									<form action="loginAssistido1">
										<input type="hidden" name="origemRequest"
											value="${origemRequest}"> <input class="btn"
											type="submit" value="Incorreto" />
									</form>
								</td>
								<td>
									<form action="${urlCorreto}">
										<input type="hidden" name="origemRequest"
											value="${origemRequest}"> <input class="btn"
											type="submit" value="Correto" />
									</form>
								</td>
							</tr>
						</table>
					</center>
				</c:if>
				<p class="last">&nbsp;</p>
			</dd>
		</dl>
	</div>
</div>
<br />
<br />
</body>
</html>
