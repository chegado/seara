<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1></h1>
<c:if test="${ehUmNumeroSoh}">
	<center>
		<table>
			<tr>
				<td style="text-align: center;"><nomeTratamento>
					<b>${nomeTratamento}</b> </nomeTratamento></td>
			</tr>
			<tr>
				<td><numeroGrande><b>${senha}</b></numeroGrande></td>
			</tr>
		</table>
	</center>
</c:if>
<c:if test="${!ehUmNumeroSoh}">
	<center>
		<table>
			<tr>
				<td style="text-align: center;" colspan=3><nomeTratamento>
					<b>${nomeTratamento}</b> </nomeTratamento></td>
			</tr>
			<tr>
				<td><numeroPequeno> <b>${senhaDe}</b></numeroPequeno></td>
				<td><ao>&nbsp;<b>AO</b>&nbsp;</ao></td>
				<td><numeroPequeno> <b>${senhaAo}</b></numeroPequeno></td>
			</tr>
		</table>
	</center>
</c:if>

