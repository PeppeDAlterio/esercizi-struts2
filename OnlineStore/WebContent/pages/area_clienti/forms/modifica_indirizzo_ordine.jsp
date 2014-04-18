<%@ taglib prefix="s" uri="/struts-tags" %>

<s:push value="#request.ordine.indirizzo">

<table class="commonTable" style="width: 90%;">
		
	<tr class="commonTr">
		<th class="commonTh">
			<s:text name="cassaForm.indirizzo" />
		</th>
	</tr>
	
	<tr>
	
	<tr class="commonTr">
		<td class="commonTd">
			<s:form action="modificaIndirizzoOrdine" namespace="/areacliente" cssStyle="width: 90%;">
				<s:push value="indirizzo">
						<s:textfield label="%{getText('rubricaForm.nome_cognome')} *" key="nome_cognome" maxlength="46" required="true" cssStyle="width: 70%;" />
						<s:textfield label="%{getText('rubricaForm.indirizzo_1')} *" key="indirizzo_1" maxlength="50" required="true" cssStyle="width: 70%;" />
						<s:textfield label="%{getText('rubricaForm.indirizzo_2')}" key="indirizzo_2" maxlength="50" cssStyle="width: 70%;" />
						<s:textfield label="%{getText('rubricaForm.citta')} *" key="citta" maxlength="40" required="true" cssStyle="width: 70%;" />
						<s:textfield label="%{getText('rubricaForm.provincia')} *" key="provincia" maxlength="40" required="true" cssStyle="width: 70%;" />
						<s:textfield label="%{getText('rubricaForm.cap')} *" key="cap" maxlength="5" required="true" cssStyle="width: 70%;" />
						<s:textfield label="%{getText('rubricaForm.paese')} *" key="paese" maxlength="40" required="true" cssStyle="width: 70%;" />
						<s:textfield label="%{getText('rubricaForm.telefono')}" key="telefono" maxlength="11" cssStyle="width: 70%;" />
						<s:submit value="%{getText('cassaForm.indirizzo_modifica')}" />
				</s:push>
			</s:form>
		</td>
	</tr>
	
	
</table>

</s:push>