<%@ taglib prefix="s" uri="/struts-tags" %>

<s:if test="%{#request.indirizzo==null}">
	<s:set var="indirizzo" value="#request.ordine.indirizzo" scope="request" />
</s:if>

<s:if test="%{#parameters.id_ordine!=null && #request.id_ordine==null}">
	<s:set var="id_ordine" value="#parameters.id_ordine" scope="request" />
</s:if>

<s:push value="#request.indirizzo" >

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
					<s:textfield label="%{getText('rubricaForm.nome_cognome')} *" key="nome_cognome" maxlength="46" required="true" cssStyle="width: 70%;" />
					<s:textfield label="%{getText('rubricaForm.indirizzo_1')} *" key="indirizzo_1" maxlength="50" required="true" cssStyle="width: 70%;" />
					<s:textfield label="%{getText('rubricaForm.indirizzo_2')}" key="indirizzo_2" maxlength="50" cssStyle="width: 70%;" />
					<s:textfield label="%{getText('rubricaForm.citta')} *" key="citta" maxlength="40" required="true" cssStyle="width: 70%;" />
					<s:textfield label="%{getText('rubricaForm.provincia')} *" key="provincia" maxlength="40" required="true" cssStyle="width: 70%;" />
					<s:textfield label="%{getText('rubricaForm.cap')} *" key="cap" maxlength="5" required="true" cssStyle="width: 70%;" />
					<s:textfield label="%{getText('rubricaForm.paese')} *" key="paese" maxlength="40" required="true" cssStyle="width: 70%;" />
					<s:textfield label="%{getText('rubricaForm.telefono')}" key="telefono" maxlength="11" cssStyle="width: 70%;" />
					<s:hidden key="id_ordine" value="%{#request.id_ordine}" />
					<s:submit value="%{getText('cassaForm.indirizzo_modifica')}" />
				</s:form>
			</td>
		</tr>
		
		
	</table>

</s:push>